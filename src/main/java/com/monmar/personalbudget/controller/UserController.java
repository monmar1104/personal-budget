package com.monmar.personalbudget.controller;

import java.io.PushbackReader;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.monmar.personalbudget.entity.User;
import com.monmar.personalbudget.user.CrmPassword;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/showUserEditForm")
	public String showUserEditForm() {
		return "edit-user-form";
	}
	
	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute("user") User user, Model model) {
		
		return "edit-user-form";
	}
	
	@PostMapping("/changePassword")
	public String changePassword(@ModelAttribute("password") CrmPassword crmPassword) {
		return "edit-user-form";
	}

}
