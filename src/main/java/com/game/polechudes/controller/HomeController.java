package com.game.polechudes.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @RequestMapping({"/", "", "/welcome"})
    public String getWelcomePage(){
        return "welcome";
    }

    @RequestMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public String getHome(Principal principal, Model model){
        model.addAttribute("user" , principal.getName());
        return "home";
    }

}
