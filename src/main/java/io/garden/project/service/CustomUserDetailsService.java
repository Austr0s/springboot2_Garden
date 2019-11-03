package io.garden.project.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetailsService {
	
	/**
	 * Method to set User and load it from username.
	 * 
	 * @param name username
	 * @return user details
	 */
	UserDetails loadUserByUserName(String userName);

}
