package com.example.carehours.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.carehours.security.model.Role;
import com.example.carehours.security.model.User;
import com.example.carehours.security.model.UserRepository;
import com.example.carehours.web.dto.UserRegistrationDTO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("Invalid User Name or Password:" + username);
		}
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
						
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
					
	}

	@Override
	public User save(UserRegistrationDTO registrationDTO) {
		
		User user = new User(registrationDTO.getEmail(),
				registrationDTO.getFirstName(),
				registrationDTO.getLastName(),
				passwordEncoder.encode(registrationDTO.getPassword()),
				Arrays.asList(new Role("ROLE_USER")));
		return userRepo.save(user);
	}

}
