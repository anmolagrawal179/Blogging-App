package com.blog.service.impl;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.exception.ResourceNotFoundException;
import com.blog.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<com.blog.entity.User> user = userRepository.findByEmail(username);
		if (user.isPresent()) {
			return User.builder().username(username).password(user.get().getPassword()).roles()
					.build();
		}

		else {
			throw new ResourceNotFoundException("User not found with username: " + username);
		}
	}

	public CustomUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
}
