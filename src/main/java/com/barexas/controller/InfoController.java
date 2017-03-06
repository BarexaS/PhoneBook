package com.barexas.controller;

import com.barexas.entities.CustomUser;
import com.barexas.entities.PhoneInfo;
import com.barexas.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class InfoController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addInfo", method = RequestMethod.POST)
    public String addInfo(@Valid @ModelAttribute("phoneinfo")PhoneInfo info, BindingResult result, Principal principal){
        if (result.hasErrors()){
            return "redirect:/?error";
        }
        CustomUser user = userService.getUserByLogin(principal.getName());
        PhoneInfo note = new PhoneInfo(info.getLastName(),info.getFirstName(),info.getMiddleName(),info.getMobileNumb(),info.getHomeNumb(),info.getAddress(),info.getEmail());
        user.addInfo(note);
        userService.updateUser(user);
        return "redirect:/";
    }
}
