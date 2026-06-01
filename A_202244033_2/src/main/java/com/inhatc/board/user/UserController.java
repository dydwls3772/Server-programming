package com.inhatc.board.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	
	@GetMapping("/signup")
	public String signup(UserForm userForm) {
		return "signup_form";
	}
	
	@PostMapping("/signup")
    public String signup(@Valid UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }	
        
        if (!userForm.getPassword1().equals(userForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordNotMatch", 
                    "Passwords do not match.");
            return "signup_form";
        }
        
        try {
        userService.create(userForm.getUsername(), 
        userForm.getEmail(), userForm.getPassword1());
        } catch (DataIntegrityViolationException e) {
        	e.printStackTrace();
        	bindingResult.reject("signupFailed", "Name or email is already registered.");
			return "signup_form";
		} catch (Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", e.getMessage());
			return "signup_form";
		}

        return "redirect:/";
	}
	
    @GetMapping("/signin")
    public String signinString() {
    return "signin_form";
    }
	
}
