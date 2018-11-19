package com.monmar.personalbudget.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {

		return "login-page";
	}
	
	
	@GetMapping("/showAccessDenied")
	public String showAccessDenied() {
		
		return "show-access-denied-page";	
	}
	
	@GetMapping(value="/logout")  
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){      
           new SecurityContextLogoutHandler().logout(request, response, auth);
        }  
        return "login-page";
     }  
	
	@GetMapping("/username")
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }
	

}
