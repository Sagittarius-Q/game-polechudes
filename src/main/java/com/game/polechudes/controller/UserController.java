package com.game.polechudes.controller;

import com.game.polechudes.domain.model.binding.UserRegisterBindingModel;
import com.game.polechudes.domain.model.service.UserServiceModel;
import com.game.polechudes.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @PreAuthorize("isAnonymous()")
    @GetMapping
    public String getRegisterForm(Model model){
        model.addAttribute("user", new UserRegisterBindingModel());
        return "/users/sign-up";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping
    public String saveUser(@Valid  @ModelAttribute UserRegisterBindingModel userModel, BindingResult result){
        this.userService.existsByUsername(userModel.getName());
        if(result.hasErrors()){
            return "redirect:/user";
        }
        UserServiceModel serviceModel = this.modelMapper.map(userModel,UserServiceModel.class);
        this.userService.save(serviceModel);
        return "home";
    }

}
