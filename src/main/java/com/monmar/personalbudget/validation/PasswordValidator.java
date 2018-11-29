package com.monmar.personalbudget.validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.monmar.personalbudget.entity.User;

@Component
public class PasswordValidator implements ConstraintValidator<CheckPassword, String> {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private HttpServletRequest request;

	@Override
	public boolean isValid(final String password, final ConstraintValidatorContext context) {
		
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		return passwordEncoder.matches(password, user.getPassword());
	}

}