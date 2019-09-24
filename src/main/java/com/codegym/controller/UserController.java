package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/sign-up")
    public ModelAndView signUpForm(){
        ModelAndView modelAndView = new ModelAndView("/signUp");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/result")
    public String result(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model){
        new User().validate(user, bindingResult);
        if (bindingResult.hasFieldErrors()){
            return "/signUp";
        }
        else {
            userService.save(user);
            List<User> userList = userService.findAll();
            model.addAttribute("users", userList);
            return "/result";
        }
    }
}
