package com.barexas.controller;

import com.barexas.entities.PhoneInfo;
import com.barexas.service.notes.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Set;

@Controller
public class InfoController {
    @Autowired
    private InfoService infoService;

    @RequestMapping(value = "/addInfo", method = RequestMethod.POST)
    public String addInfo(@Valid @ModelAttribute("phoneinfo") PhoneInfo info, BindingResult result, Principal principal) {
        if (result.hasErrors() || (!info.getHomeNumb().isEmpty() && !info.getHomeNumb().matches("\\+380\\(\\d{2}\\)\\d{7}"))) {
            return "redirect:/?error";
        }
        infoService.addInfo(principal.getName(),info);
        return "redirect:/";
    }

    @RequestMapping(value = "/deleteInfo", method = RequestMethod.POST)
    public ResponseEntity<Void> delete(@RequestParam(value = "toDelete[]", required = false) long[] toDelete, Principal principal) {
        if (toDelete != null) {
            infoService.deleteInfo(principal.getName(),toDelete);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String pattern, Principal principal, Model model) {
        Set<PhoneInfo> resultSet = infoService.searchInfo(principal.getName(),pattern);

        model.addAttribute("notes",resultSet);
        model.addAttribute("phoneinfo", new PhoneInfo());
        return "home";
    }

    @RequestMapping("/edit_{id}")
    public ModelAndView editInfo(@PathVariable(value = "id") long Id, Principal principal) {
        PhoneInfo info = infoService.getInfo(principal.getName(), Id);
        if (info == null) {
            return new ModelAndView("edit","phoneinfo", new PhoneInfo());
        }
        return new ModelAndView("edit","phoneinfo", info);
    }

    @RequestMapping(value = "/editInfo", method = RequestMethod.POST)
    public String editInfo(@Valid @ModelAttribute("phoneinfo") PhoneInfo info, BindingResult result, Principal principal) {
        if (result.hasErrors() || (!info.getHomeNumb().isEmpty() && !info.getHomeNumb().matches("\\+380\\(\\d{2}\\)\\d{7}"))) {
            return "redirect:/edit_"+info.getId()+"?error";
        }
        infoService.editInfo(principal.getName(),info);
        return "redirect:/";
    }
}
