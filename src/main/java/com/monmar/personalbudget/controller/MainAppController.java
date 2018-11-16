package com.monmar.personalbudget.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainAppController {

    @GetMapping("/whereismymoney")
    public String showHomePage(Model model){
       return "index";
    }
}
