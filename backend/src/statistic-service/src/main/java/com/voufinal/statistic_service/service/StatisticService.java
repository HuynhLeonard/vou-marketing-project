package com.voufinal.statistic_service.service;

import com.voufinal.statistic_service.model.*;
import com.voufinal.statistic_service.model.request.CreateQuizGameStatReq;
import com.voufinal.statistic_service.model.request.CreateQuizQuestionStatReq;
import com.voufinal.statistic_service.model.request.CreateQuizWinnerReq;
import com.voufinal.statistic_service.model.statistic.QuizGameStatistic;
import com.voufinal.statistic_service.model.statistic.QuizQuestionStatistic;
import com.voufinal.statistic_service.model.statistic.QuizStatistic;
import com.voufinal.statistic_service.model.statistic.ShakeStatistic;
import com.voufinal.statistic_service.repository.QuizGameStatisticRepo;
import com.voufinal.statistic_service.repository.QuizQuestionStatisticRepo;
import com.voufinal.statistic_service.repository.QuizWinnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatisticService {
    @Autowired
    private GameClient gameClient;
    @Autowired
    private EventClient eventClient;
    @Autowired
    private QuizWinnerRepo quizWinnerRepository;
    @Autowired
    private QuizGameStatisticRepo quizGameStatsRepository;
    @Autowired
    private QuizQuestionStatisticRepo quizQuestionStatsRepository;
    @Autowired
    private UserClient userClient;


    public StatisticResponse getStatistics(Long id_event) {
        Optional<Game> game = gameClient.getGameByIdEvent(id_event);

        if (game.isPresent() && Objects.equals(game.get().getType(), "shake-game")) {
            System.out.println("Shake Game debugging");
            // Call API count play session bên GameClient
            Optional<Integer> participants = gameClient.getParticipantsCount(id_event);
            Integer participantCount = null;
            if (participants.isPresent()) {
                participantCount = participants.get();
                System.out.println("Participant count: " + participantCount);
            }
            // Call API Lấy total voucher, remaining voucher, share count của event
            Optional<Event> event = eventClient.getEvent(id_event);
            Event getEvent = null;
            if (event.isPresent()) {
                getEvent = event.get();
                System.out.println("Event count: " + getEvent);
            }
            if (participantCount != null && getEvent != null) {
                ShakeStatistic statistics = new ShakeStatistic((long) participantCount);
                statistics.setGivenVouchers((long) (getEvent.getNumberOfVouchers() - getEvent.getRemainingVouchers()));
                statistics.setRemainingVouchers((long) getEvent.getRemainingVouchers());
                statistics.setShareCount(getEvent.getShareCount());
                return statistics;
            }
        } else {
            if (game.isPresent()) {
                // Lấy thông tin người chiến thắng từ DB
                List<QuizWinner> winners = quizWinnerRepository.findQuizWinnersByIdEventAndIdGame(id_event, game.get().getIdGame());
                List<Long> requestUserIds = winners.stream().map(QuizWinner::getWinnerId).collect(Collectors.toList());
                Optional<List<User>> users = userClient.getUsers(requestUserIds);
                List<User> userList = null;
                if (users.isPresent()) {
                    userList = users.get();
                }
                // Lấy danh sách thống kê câu hỏi
                List<QuizQuestionStatistic> quizQuestionStats = quizQuestionStatsRepository.findQuizQuestionStatsByIdEventAndIdGame(id_event, game.get().getIdGame());
                List<RatesQuestionCorrect> correctRates = quizQuestionStats.stream().map(quizQuestionStats1 -> new RatesQuestionCorrect(quizQuestionStats1.getIdQuizQuestion(),  ((double) quizQuestionStats1.getCorrectCount() / (double) (quizQuestionStats1.getCorrectCount() + quizQuestionStats1.getIncorrectCount())))).collect(Collectors.toList());
                // Lấy số người tham gia
                QuizGameStatistic gameStats =  quizGameStatsRepository.findByIdEventAndIdGame(id_event, game.get().getIdGame());
                Long participant = gameStats.getNumberOfParticipants();
                QuizStatistic statistics = new QuizStatistic(participant);
                statistics.setCorrectRates(correctRates);
                statistics.setWinners(userList);
                return statistics;
            }
        }

        return null;
    }

    public QuizWinner saveWinner(CreateQuizWinnerReq request) throws Exception {
        try {
            QuizWinner quizWinner = new QuizWinner(request.getIdEvent(), request.getIdGame(), request.getRank(), request.getUserId());
            return quizWinnerRepository.save(quizWinner);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public QuizGameStatistic updateQuizParticipants(CreateQuizGameStatReq request) throws Exception {
        try {
            QuizGameStatistic quizGameStats = quizGameStatsRepository.findByIdEventAndIdGame(request.getIdEvent(), request.getIdGame());
            if (quizGameStats != null) {
                quizGameStats.setNumberOfParticipants(quizGameStats.getNumberOfParticipants() + 1);
                return quizGameStatsRepository.save(quizGameStats);
            } else {
                QuizGameStatistic newQuizGameStats = new QuizGameStatistic();
                newQuizGameStats.setNumberOfParticipants(1L);
                newQuizGameStats.setIdEvent(request.getIdEvent());
                newQuizGameStats.setIdGame(request.getIdGame());
                return quizGameStatsRepository.save(newQuizGameStats);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public QuizQuestionStatistic updateQuestionResultCount(CreateQuizQuestionStatReq request) throws Exception {
        try {
            QuizQuestionStatistic stats = quizQuestionStatsRepository.findByIdQuizQuestion(request.getIdQuizQuestion());
            if (stats != null) {
                if (request.getResult()) {
                    stats.setCorrectCount(stats.getCorrectCount() + 1);
                } else {
                    stats.setIncorrectCount(stats.getIncorrectCount() + 1);
                }
                return quizQuestionStatsRepository.save(stats);
            } else {
                QuizQuestionStatistic newStats = new QuizQuestionStatistic();
                newStats.setIdQuizQuestion(request.getIdQuizQuestion());
                newStats.setCorrectCount(0L);
                newStats.setIncorrectCount(0L);
                newStats.setIdEvent(request.getIdEvent());
                newStats.setIdGame(request.getIdGame());
                if (request.getResult()) {
                    newStats.setCorrectCount(newStats.getCorrectCount() + 1);
                } else {
                    newStats.setIncorrectCount(newStats.getIncorrectCount() + 1);
                }
                return quizQuestionStatsRepository.save(newStats);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
