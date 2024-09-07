package com.voufinal.gameservice.service;

import com.voufinal.gameservice.model.Quiz;
import com.voufinal.gameservice.repository.QuizRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Transactional
    public List<Quiz> saveQuizzes(List<Quiz> quizzes) {
        return quizRepository.saveAll(quizzes);
    }
}
