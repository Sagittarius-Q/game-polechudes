package com.game.polechudes.service.question;

import com.game.polechudes.domain.model.service.QuestionServiceModel;
import com.game.polechudes.exceptions.QuestionNotFoundException;

import java.util.List;

public interface QuestionService {
    void existsByName(String name);
    void save(QuestionServiceModel serviceModel);
    void removeQuestionById(Long id);
    List<QuestionServiceModel> getAllQuestions();
    QuestionServiceModel getRandomly();
    QuestionServiceModel getQuestionById(Long id) throws QuestionNotFoundException;
    void updateQuestion(QuestionServiceModel questionServiceModel);
}
