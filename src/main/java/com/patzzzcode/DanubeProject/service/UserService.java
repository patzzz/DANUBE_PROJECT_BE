package com.patzzzcode.DanubeProject.service;

import com.patzzzcode.DanubeProject.bo.User;
import com.patzzzcode.DanubeProject.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    public User createUser(User user) {
        User u = new User(null, user.getUsername(), null, user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getPhoneNumber(), new Date());
        u.setPassword(bcryptEncoder.encode(user.getPassword()));
        u = userRepository.save(u);
        return u;
    }
}
