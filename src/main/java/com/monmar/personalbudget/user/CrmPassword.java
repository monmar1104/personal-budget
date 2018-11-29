package com.monmar.personalbudget.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.monmar.personalbudget.validation.CheckPassword;
import com.monmar.personalbudget.validation.FieldMatch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@FieldMatch.List({
    @FieldMatch(first = "newPassword", second = "matchingNewPassword", message = "The password fields must match")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CrmPassword {
	
	@CheckPassword(message="Invalid old password")
	private String oldPassword;
	
	@NotNull(message = "Password is required")
	@Size(min = 1, message = "Password is too short")
	private String newPassword;
	
	@NotNull(message = "Passwords is required")
	@Size(min = 1, message = "Password is too short")
	private String matchingNewPassword;

}
