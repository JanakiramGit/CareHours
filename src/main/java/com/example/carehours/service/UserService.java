package com.example.carehours.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.carehours.security.model.User;
import com.example.carehours.web.dto.UserRegistrationDTO;


//This interface extends the UserDetailsService interface provided by the Spring Security
//This is required to integrate spring security with our application
public interface UserService extends UserDetailsService{
	
	User save(UserRegistrationDTO registrationDTO);
}
