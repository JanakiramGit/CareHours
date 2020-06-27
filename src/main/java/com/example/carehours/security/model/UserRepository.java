 package com.example.carehours.security.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	
	//Define a method to retreive user object from database using methon naming convention. 
	User findByEmail(String email);

}
