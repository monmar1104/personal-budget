package com.monmar.personalbudget.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	HttpSession session;

	@Autowired
	HttpServletRequest request;
	
	@GetMapping("/showUserEditForm")
	public String showUserEditForm(Model model) {
		
		session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		model.addAttribute("user", user);
		model.addAttribute("crmPassword", new CrmPassword());
		
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
