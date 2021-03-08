package com.game.polechudes.controller;

import com.game.polechudes.service.history.GameHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
@RequestMapping("/history")
public class PlayerHistoryController {
    private final GameHistoryService gameHistoryService;
    @GetMapping
    public String getHistory(Model model, Principal principal){
        List<String> history = gameHistoryService.getAllByPlayerName(principal.getName());
        model.addAttribute("history",history);
        return "/users/history";
    }
}
