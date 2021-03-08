package com.game.polechudes.controller;

import com.game.polechudes.domain.model.binding.QuestionBindingModel;
import com.game.polechudes.domain.model.service.QuestionServiceModel;
import com.game.polechudes.domain.model.view.QuestionViewModel;
import com.game.polechudes.service.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class QuestionController {
    private final ModelMapper modelMapper;
    private final QuestionService questionService;


    @GetMapping
    public String getAllQuestions(Model model){
        List<QuestionViewModel> questionList = this.questionService.getAllQuestions()
                .stream()
                .map(questionServiceModel -> this.modelMapper.map(questionServiceModel,QuestionViewModel.class))
                .collect(Collectors.toList());
        model.addAttribute("questionList", questionList);
        return "/question/questions";
    }

    @GetMapping("/add")
    public String getQuestionForm(Model model){
        model.addAttribute("question", new QuestionBindingModel());
        return "/question/add-question";
    }

    @PostMapping("/add")
    public String saveQuestion(@Valid @ModelAttribute("question") QuestionBindingModel questionBindingModel,
                               BindingResult result){
        this.questionService.existsByName(questionBindingModel.getName());

        if(result.hasErrors()){
            return "redirect:add";
        }
        QuestionServiceModel serviceModel = this.modelMapper
                .map(questionBindingModel, QuestionServiceModel.class);
        this.questionService.save(serviceModel);
        return "redirect:/question";
    }

    @GetMapping("/update/{id}")
    public String getQuestionEditForm(@PathVariable Long id, Model model){
        QuestionBindingModel questionBindingModel= this.modelMapper
                .map(questionService.getQuestionById(id), QuestionBindingModel.class);
        model.addAttribute("question", questionBindingModel);
        return "/question/edit-question";
    }

    @PostMapping("/update")
    public String updateQuestion(@Valid @ModelAttribute("question") QuestionBindingModel questionBindingModel,
                                 BindingResult result){
        if(result.hasErrors()){
            return "redirect:update/" + questionBindingModel.getId();
        }
        QuestionServiceModel model = this.modelMapper.map(questionBindingModel,QuestionServiceModel.class);
        this.questionService.updateQuestion(model);
        return "redirect:/question";
    }


    @GetMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable Long id){
        this.questionService.removeQuestionById(id);
        return "redirect:/question";
    }

}
