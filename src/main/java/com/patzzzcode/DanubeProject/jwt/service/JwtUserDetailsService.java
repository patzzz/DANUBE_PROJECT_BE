package com.patzzzcode.DanubeProject.jwt.service;

import java.util.ArrayList;

import com.patzzzcode.DanubeProject.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.patzzzcode.DanubeProject.bo.User existingUser = userRepository.findByEmail(username).orElse(null);
		if (existingUser != null) {
			return new User(existingUser.getUsername(), existingUser.getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}