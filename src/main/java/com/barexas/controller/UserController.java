package com.barexas.controller;

import com.barexas.entities.CustomUser;
import com.barexas.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashSet;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Valid @ModelAttribute("customuser")CustomUser user, BindingResult result){
        if (result.hasErrors()){
            System.out.println(result.toString());
            return "redirect:/signup?error";
        }
        user.setBook(new HashSet<>());
        userService.addUser(user);
        return "redirect:/";
    }
}
