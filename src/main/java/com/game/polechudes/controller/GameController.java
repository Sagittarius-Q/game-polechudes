package com.game.polechudes.controller;

import com.game.polechudes.domain.model.view.QuestionViewModel;
import com.game.polechudes.domain.model.history.GameHistoryModel;
import com.game.polechudes.service.history.GameHistoryService;
import com.game.polechudes.service.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
@RequestMapping("/game")
public class GameController {

    private final ModelMapper modelMapper;
    private final GameHistoryService gameHistoryService;
    private final QuestionService questionService;

    @GetMapping
    public String getGamePage(Model model, Principal principal){
        QuestionViewModel questionViewModel = this.modelMapper
                .map(questionService.getRandomly(),QuestionViewModel.class);

        GameHistoryModel gameHistoryModel = new GameHistoryModel();
        gameHistoryModel.setPlayerName(principal.getName());
        gameHistoryModel.setQuestionName(questionViewModel.getName());
        gameHistoryService.save(gameHistoryModel);

        model.addAttribute("question", questionViewModel.getHint());
        model.addAttribute("answer" , questionViewModel.getAnswer());
        return "/game/frame";
    }
}
