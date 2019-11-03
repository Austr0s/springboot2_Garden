package io.garden.project.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.garden.project.model.entity.CustomUserDetails;
import io.garden.project.model.entity.User;
import io.garden.project.repository.UserRepository;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = repository.findByUserName(username);

		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

		return user.map(CustomUserDetails::new).get();
	}

}
