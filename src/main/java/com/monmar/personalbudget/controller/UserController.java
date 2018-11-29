package com.monmar.personalbudget.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.monmar.personalbudget.entity.User;
import com.monmar.personalbudget.service.UserService;
import com.monmar.personalbudget.user.CrmPassword;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	HttpSession session;

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	UserService userService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/showUserEditForm")
	public String showUserEditForm(Model model) {
		
		session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		model.addAttribute("user", user);
		model.addAttribute("password", new CrmPassword());
		
		return "edit-user-form";
	}
	
	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute("user") User user, Model model) {
		
		return "edit-user-form";
	}
	
	@PostMapping("/changePassword")
	public String changePassword(@Valid @ModelAttribute("password") CrmPassword crmPassword, BindingResult bindingResult, Model  model) {
		
		session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		model.addAttribute("user", user);
		model.addAttribute("password", new CrmPassword());
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("oldPasswordError", bindingResult.getAllErrors().get(0).getDefaultMessage());
			return "edit-user-form";
		}
		user.setPassword(passwordEncoder.encode(crmPassword.getNewPassword()));
		userService.updateUser(user);
		model.addAttribute("changePasswordSuccess", "Password changed successfully!");
		
		System.out.println(crmPassword.getNewPassword());
		
		
		return "edit-user-form";
	}

}
