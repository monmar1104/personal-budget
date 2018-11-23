package com.monmar.personalbudget.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/service")
public class ServiceController {
	
	@GetMapping("break")
	public String showServiceWorkMessage() {
		return "service-break";
	}
	
	

}
