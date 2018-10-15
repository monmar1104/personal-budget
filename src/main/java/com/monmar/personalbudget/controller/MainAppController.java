package com.monmar.personalbudget.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainAppController {

    @GetMapping("/")
    public String showHomePage(){
       return "index";
    }
}
