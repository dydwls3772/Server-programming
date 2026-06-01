package com.inhatc.board.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {
	
	@Size(min = 2, max = 30, message = "Name must be between {min} and {max} characters.")
	@NotEmpty(message = "Name is required.")
	private String username;
	
	@NotEmpty(message = "Password is required.")
	private String password1;
	
	@NotEmpty(message = "Password confirmation is required.")
	private String password2;
	
	@NotEmpty(message = "Email is required.")
	@Email
	private String email;
}