package com.barexas.controller;

import com.barexas.entities.CustomUser;
import com.barexas.entities.PhoneInfo;
import com.barexas.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView home(Principal principal){
        ModelAndView model = new ModelAndView("home", "phoneinfo", new PhoneInfo());
        CustomUser user = userService.getUserByLogin(principal.getName());
        model.addObject("notes", user.getBook());
        return model;
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/signup")
    public ModelAndView signup(){
        return new ModelAndView("signup", "customuser", new CustomUser());
    }
}
