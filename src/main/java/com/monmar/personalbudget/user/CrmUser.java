package com.monmar.personalbudget.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.monmar.personalbudget.validation.FieldMatch;
import com.monmar.personalbudget.validation.ValidEmail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CrmUser {

	@NotNull(message = "User name is required")
	@Size(min = 1, message = "User name is too short")
	private String userName;

	@NotNull(message = "Password is required")
	@Size(min = 1, message = "Password is too short")
	private String password;
	
	@NotNull(message = "Passwords is required")
	@Size(min = 1, message = "Password is too short")
	private String matchingPassword;

	@NotNull(message = "First name is requried")
	@Size(min = 1, message = "First name is too short")
	private String firstName;

	@NotNull(message = "Last name is required")
	@Size(min = 1, message = "Last name is too short")
	private String lastName;

	@ValidEmail
	@NotNull(message = "Email is required")
	private String email;

}
