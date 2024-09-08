package com.voufinal.mc_service.controller;

import com.voufinal.mc_service.client.EventClient;
import com.voufinal.mc_service.common.BadRequest;
import com.voufinal.mc_service.common.SuccessResponse;
import com.voufinal.mc_service.dto.*;
import com.voufinal.mc_service.exception.InternalServerError;
import com.voufinal.mc_service.library.RedisLibrary;
import com.voufinal.mc_service.model.*;
import com.voufinal.mc_service.repository.*;
import com.voufinal.mc_service.service.*;
import com.voufinal.mc_service.exception.*;
import com.voufinal.mc_service.common.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/game")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    private RedisLibrary redisCache;
    private EventSchedulerService eventSchedulerService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlaySessionRepository playSessionRepository;

    @Autowired
    private QuizGameRepository quizGameRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private ShakeGameRepository shakeGameRepository;

    @Autowired
    private RewardService rewardService;

    @Autowired
    private PlaySessionService playSessionService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    @Autowired
    private EventClient eventClient;

    @GetMapping("message/{room}")
    public ResponseEntity<List<String>> getMessages(@PathVariable String room) {
        return ResponseEntity.ok(messageService.getPlayers(room));
    }

//    @PostMapping("/create")
//    public ResponseEntity<String> createGame(){
//        List<QuizDTO> list = new ArrayList<>();
//        Timestamp time = Timestamp.valueOf("2024-09-04 01:20:30.123");
//        String date = messageService.startGame( list, 12312L, time);
//        return ResponseEntity.ok(date);
//    }

    @PostMapping("/quiz/create")
    public ResponseEntity<String> createQuiz(@RequestBody GameInfoDto gameInfoDTO){
        System.out.println("gameInfoDTO" +  gameInfoDTO);
        List<QuizDto> quizzdto = gameInfoDTO.getQuiz();

        Game game = new Game(gameInfoDTO.getName(),gameInfoDTO.getGameType(), gameInfoDTO.getEventId());
        System.out.println(game);
        gameRepository.save(game);
        if (gameInfoDTO.getGameType().equals("shake-game")){
            ShakeGame shakeGame = new ShakeGame();
            shakeGame.setGame(game);
            shakeGameRepository.save(shakeGame);
            return ResponseEntity.ok("Save successfully");
        }

        game.setStartedAt(gameInfoDTO.getStartedAt());
        QuizGame quizGame = new QuizGame(4);
        quizGame.setGame(game);
        quizGameRepository.save(quizGame);
        messageService.startGame(quizzdto, gameInfoDTO.getGameId(),gameInfoDTO.getEventId(), gameInfoDTO.getStartedAt());
        List<Quiz> quizzes = quizzdto.stream().map(quizz-> new Quiz(quizz, game.getIdGame())).collect(Collectors.toList());
        quizService.saveQuizzes(quizzes);
        return ResponseEntity.ok("Save successfully");
    }


    @PostMapping("/{id_game}/player/{id_player}")
    public ResponseEntity<?> playShakeGame(@PathVariable Long id_game, @PathVariable Long id_player) throws Exception {
        try {
            PlaySession playSession = playSessionService.findOrCreatePlaySession(id_game, id_player);
            if (playSession.getTurns() == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BadRequest("Not enough turns to play!"));
            }

            List<AwardDto> rewardsList = rewardService.getRewardListByIdPlayer(id_player).orElse(null);
            if (rewardsList == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError("Error while trying to load the user's inventory!"));
            }

            AwardDto[] rewards = rewardsList.toArray(new AwardDto[0]);

            double winProbability = 0.75;

            // Generate a random number between 0 and 1
            Random random = new Random();
            double randomValue = random.nextDouble();

            if (randomValue < winProbability) {
                int itemIndex = random.nextInt(rewards.length);
                AwardDto wonItem = rewards[itemIndex];
                AwardDto updatedReward;
                if (wonItem.getItemName().equals("Xu") || wonItem.getIdItem() == 5) {
                    Long coin = random.nextLong(200) + 1;
                    updatedReward = rewardService.incrementAmountByIdItemRepo(wonItem.getIdItemRepo(), coin);
                } else {
                    updatedReward = rewardService.incrementAmountByIdItemRepo(wonItem.getIdItemRepo(), null);
                }
                if (updatedReward == null) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError("Sorry. An error occurred while claiming the reward!"));
                }
                playSessionRepository.decrementTurns(id_player, id_game);
                AwardDetail rewardDetail;
                if  (playSession.getTurns() > 0) {
                    rewardDetail = new AwardDetail(updatedReward, playSession.getTurns() - 1);
                } else {
                    rewardDetail = new AwardDetail(updatedReward, playSession.getTurns());
                }
                return ResponseEntity.ok(new SuccessResponse("You won a " + updatedReward.getItemName(), HttpStatus.OK, rewardDetail));
            } else {
                return ResponseEntity.ok(new SuccessResponse("Missed it. Try again next time!", HttpStatus.OK, null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError("Changing password failed by server!"));
        }
    }

    @GetMapping("/game-info")
    public ResponseEntity<GameInfoDto>  getDetailGameInfo(@RequestParam Long eventId){
        Game game = gameRepository.findByIdEvent(eventId);

        List<Quiz> quizzes= quizRepository.findAllByIdGame(game.getIdGame());

        List<QuizDto> quizDto= quizzes.stream()
                .map(QuizDto::new)
                .collect(Collectors.toList());
        GameInfoDto gameInfoDTO =new GameInfoDto(
                game.getIdGame(),
                game.getName(),
                game.getType(),
                game.getIdEvent(),
                game.getStartedAt(),
                quizDto
        );
        return ResponseEntity.ok(gameInfoDTO);
    }

    @PutMapping("/game-info")
    public ResponseEntity<GameInfoDto>  updateGameInfo(@RequestBody GameInfoDto gameInfoDTO){
        Game game = gameRepository.findByIdGame(gameInfoDTO.getGameId());
        game.setName(gameInfoDTO.getName());
        gameRepository.save(game);
        if (gameInfoDTO.getGameType().equals("quiz-game")) {
            List<QuizDto> quizDTOS = gameInfoDTO.getQuiz();
            for (QuizDto quizDTO : quizDTOS) {
                Optional<Quiz> existingQuizOpt = quizRepository.findById(quizDTO.getQuizId());
                if (existingQuizOpt.isPresent()) {
                    Quiz existingQuiz = existingQuizOpt.get();
                    existingQuiz.setQuestion(quizDTO.getQuestion());
                    existingQuiz.setAns1(quizDTO.getAns1());
                    existingQuiz.setAns2(quizDTO.getAns2());
                    existingQuiz.setAns3(quizDTO.getAns3());
                    existingQuiz.setCorrectAnswerIndex(quizDTO.getCorrectAnswerIndex());
                    quizRepository.save(existingQuiz);
                } else {
                    Quiz newQuiz = new Quiz();
                    newQuiz.setQuestion(quizDTO.getQuestion());
                    newQuiz.setAns1(quizDTO.getAns1());
                    newQuiz.setAns2(quizDTO.getAns2());
                    newQuiz.setAns3(quizDTO.getAns3());
                    newQuiz.setCorrectAnswerIndex(quizDTO.getCorrectAnswerIndex());
                    newQuiz.setIdGame(gameInfoDTO.getGameId());
                    quizRepository.save(newQuiz);
                }
            }
        }
        return ResponseEntity.ok(gameInfoDTO);
    }

    @GetMapping("/{idGame}/players/{idPlayer}/turns")
    public ResponseEntity<?> getTurns(@PathVariable Long idGame, @PathVariable Long idPlayer) {
        try {
            PlaySession playSession = playSessionService.findPlaySessionByIdGameAndIdPlayer(idGame, idPlayer);
            if (playSession == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundResponse("Play session not found"));
            }
            return ResponseEntity.ok(new SuccessResponse("Successfully accessed the remaining turns of the player!", HttpStatus.OK, playSession));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new InternalServerError("Error while trying to access the user's remaining turns!"));
        }
    }

//    public boolean checkTurnRecords(Long idPlayer, Long idGame) {
//        return playSessionRepository.decrementTurns(idPlayer, idGame) == 1;
//    }

    @PostMapping("/turns")
    public ResponseEntity<ApiResponse> giftTurns(@RequestBody RequestGiftTurn requestGiftTurn) {
        try {
            PlayerDto playerDTO = null;
            if (requestGiftTurn.getEmail() != null) {
                playerDTO = playerService.findPlayerByIdentifier(requestGiftTurn.getEmail(), null, null).orElse(null);
            } else if (requestGiftTurn.getUsername() != null) {
                playerDTO = playerService.findPlayerByIdentifier(null, requestGiftTurn.getUsername(), null).orElse(null);
            } else if (requestGiftTurn.getReceiverId() != null) {
                playerDTO = playerService.findPlayerByIdentifier(null, null, requestGiftTurn.getReceiverId()).orElse(null);
            }
            if (playerDTO == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundResponse("User not found or error while searching for user"));
            }
            int turns = requestGiftTurn.getTurns();
            PlaySession playSession = playSessionService.findPlaySessionByIdGameAndIdPlayer(requestGiftTurn.getIdGame(), requestGiftTurn.getSenderId());
            if (playSession == null) {
                return ResponseEntity.internalServerError().body(new InternalServerError("System error while trying to access the giver's play session information!"));
            }
            if (playSession.getTurns() == 0 || playSession.getTurns() - turns < 0) {
                return ResponseEntity.ok(new SuccessResponse("Not enough turns to gift", HttpStatus.OK, null));
            }
            playSessionRepository.increaseTurns(playerDTO.getIdUser(), requestGiftTurn.getIdGame(), turns);
            playSessionRepository.decreaseTurns(requestGiftTurn.getSenderId(), requestGiftTurn.getIdGame(), turns);
//            PlaySession receiver = playSessionService.findPlaySessionByIdGameAndIdPlayer(giftTurnRequest.getIdGame(), giftTurnRequest.getReceiverId());
//            PlaySession sender = playSessionService.findPlaySessionByIdGameAndIdPlayer(giftTurnRequest.getIdGame(), giftTurnRequest.getSenderId());
            return ResponseEntity.ok(new SuccessResponse("Gifted successfully", HttpStatus.OK, null));
        } catch (Exception e ) {
            return ResponseEntity.internalServerError().body(new InternalServerError("System error while gifting turns!"));
        }
    }

    @GetMapping("/{idGame}")
    public ResponseEntity<?> findGameByIdGame(@PathVariable Long idGame) {
        try {
            Game game = gameService.findGameByIdGame(idGame);
            if (game == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(game);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/turns/{idUser}/{idGame}")
    public ResponseEntity<ApiResponse> shareToGetTurn(@PathVariable Long idUser, @PathVariable Long idGame) {
        PlaySession playSession = playSessionService.findPlaySessionByIdGameAndIdPlayer(idGame, idUser);
        if (playSession == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundResponse("User or game not found"));
        }
        try {
            playSessionService.shareToGetTurns(idGame, idUser);
            // Call api share count
            Game game = gameService.findGameByIdGame(idGame);
            eventClient.increaseShareCount(game.getIdEvent());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new InternalServerError("System error while sharing for turns: " + e.getMessage()));
        }
        playSession.setTurns(playSession.getTurns() + 1);
        return ResponseEntity.ok(new SuccessResponse("Congratulations, you have received 1 turn", HttpStatus.OK, playSession));
    }

    @GetMapping("/events/{id_event}")
    public ResponseEntity<?> findGameByIdEvent(@PathVariable Long id_event) {
        try {
            Game game = gameService.findGameByIdEvent(id_event);
            return ResponseEntity.ok(game);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id_event}/participants")
    public ResponseEntity<?> findParticipantsByEvent(@PathVariable Long id_event) {
        try {
            int numberParticipants = playSessionService.countParticipantsByIdEvent(id_event);
            return ResponseEntity.ok(numberParticipants);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
