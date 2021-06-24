package com.patzzzcode.DanubeProject.jwt.controller;

import com.patzzzcode.DanubeProject.bo.User;
import com.patzzzcode.DanubeProject.jwt.config.JwtTokenUtil;
import com.patzzzcode.DanubeProject.jwt.model.JwtRequest;
import com.patzzzcode.DanubeProject.jwt.model.JwtResponse;
import com.patzzzcode.DanubeProject.jwt.service.JwtUserDetailsService;
import com.patzzzcode.DanubeProject.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/")
public class JwtAuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	private com.patzzzcode.DanubeProject.service.UserService userService;
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody User user) throws Exception {
		User existingUserName = userRepository.findByUsername(user.getUsername()).orElse(null);
		if (existingUserName != null) {
			return new ResponseEntity<>("User allready exist", HttpStatus.BAD_REQUEST);
		}
		User existingUserEmail = userRepository.findByEmail(user.getEmail()).orElse(null);
		if (existingUserEmail != null) {
			return new ResponseEntity<>("Email allready used", HttpStatus.BAD_REQUEST);
		}
		else {
			return ResponseEntity.ok(userService.createUser(user));
		}
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}