package com.patzzzcode.DanubeProject.restcontrollers;

import com.patzzzcode.DanubeProject.bo.User;
import com.patzzzcode.DanubeProject.repositories.UserRepository;
import com.patzzzcode.DanubeProject.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/users/createUser", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        try {
            if (user != null) {
                User existingUser = userRepository.findByEmail(user.getEmail()).orElse(null);
                if (existingUser != null) {
                    return new ResponseEntity<Object>(HttpStatus.ALREADY_REPORTED);
                } else {
                    User u = userService.createUser(user);
                    return new ResponseEntity<Object>(u, HttpStatus.CREATED);
                }
            } else {
                return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/users/getUser", method = RequestMethod.GET)
    public ResponseEntity<Object> getUser(@RequestParam Long userId) {
        try {
            if (userId != null) {
                User existingUser = userRepository.findById(userId).orElse(null);
                if (existingUser != null) {
                    return new ResponseEntity<Object>(existingUser, HttpStatus.OK);
                } else {
                    return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
