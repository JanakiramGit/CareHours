package com.example.carehours.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.carehours.service.UserService;
import com.example.carehours.web.dto.UserRegistrationDTO;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	
	private UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
	public UserRegistrationDTO userRegistrationDTO() {
		return new UserRegistrationDTO();
	}
	
	@GetMapping
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new UserRegistrationDTO());
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDTO registrationDTO) {
		userService.save(registrationDTO);
		return "redirect:/registration?success";
	}
}
