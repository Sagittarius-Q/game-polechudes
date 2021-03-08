package com.game.polechudes.service.question;

import com.game.polechudes.domain.entity.Question;
import com.game.polechudes.domain.model.service.QuestionServiceModel;
import com.game.polechudes.exceptions.QuestionDuplicateException;
import com.game.polechudes.exceptions.QuestionNotFoundException;
import com.game.polechudes.repo.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    @Override
    public void existsByName(String name) {
        if(questionRepository.existsByName(name))
            throw new QuestionDuplicateException( name + " already exists, try different name");
    }

    @Override
    public void save(QuestionServiceModel serviceModel) {
        Question question = this.modelMapper.map(serviceModel, Question.class);
        questionRepository.save(question);
    }

    @Override
    public void removeQuestionById(Long id) {
        Question question = this.questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question is not found"));
        this.questionRepository.delete(question);
    }

    @Override
    public List<QuestionServiceModel> getAllQuestions() {
        return questionRepository
                .findAll().stream()
                .map(question -> this.modelMapper.map(question, QuestionServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public QuestionServiceModel getRandomly() {
        List<Long> ids = this.questionRepository.getAllIds();
        Random rand = new Random();
        return this.modelMapper
                .map(this.getQuestionById(ids.get(rand.nextInt(ids.size()))),QuestionServiceModel.class);
    }

    @Override
    public QuestionServiceModel getQuestionById(Long id) throws QuestionNotFoundException {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> {
                    throw new QuestionNotFoundException("Question is not found");
                });
        return this.modelMapper.map(question, QuestionServiceModel.class);
    }

    @Override
    public void updateQuestion(QuestionServiceModel model) throws QuestionNotFoundException {
        Question question = questionRepository.findById(model.getId())
                .orElseThrow(() -> new QuestionNotFoundException("question is not found"));
        question.setName(model.getName());
        question.setHint(model.getHint());
        question.setAnswer(model.getAnswer());
        questionRepository.save(question);
    }


}
