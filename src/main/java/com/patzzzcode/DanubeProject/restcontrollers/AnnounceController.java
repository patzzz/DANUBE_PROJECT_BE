package com.patzzzcode.DanubeProject.restcontrollers;

import org.springframework.web.bind.annotation.RestController;

import com.patzzzcode.DanubeProject.bo.Announce;
import com.patzzzcode.DanubeProject.bo.Category;
import com.patzzzcode.DanubeProject.bo.Subcategory;
import com.patzzzcode.DanubeProject.bo.User;
import com.patzzzcode.DanubeProject.jwt.service.DecodeJwtService;
import com.patzzzcode.DanubeProject.repositories.AnnounceRepository;
import com.patzzzcode.DanubeProject.repositories.CategoryRepository;
import com.patzzzcode.DanubeProject.repositories.SubcategoryRepository;
import com.patzzzcode.DanubeProject.repositories.UserRepository;
import com.patzzzcode.DanubeProject.service.AnnounceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class AnnounceController {

    @Autowired
    private AnnounceRepository announceRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubcategoryRepository subcategoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DecodeJwtService decodeJwtService;

    @Autowired
    private AnnounceService announceService;

    @RequestMapping(value = "/api/announces/createAnnounce", method = RequestMethod.POST)
    public ResponseEntity<Object> createAnnounce(UsernamePasswordAuthenticationToken principal,
            @RequestBody Announce announce, @RequestParam Long categoryId, @RequestParam Long subcategoryId) {
        try {
            Long userId = decodeJwtService.decodeJWT(principal).getUserId();
            User u = userRepository.findById(userId).orElse(null);
            Category c = categoryRepository.findById(categoryId).orElse(null);
            Subcategory s = subcategoryRepository.findById(subcategoryId).orElse(null);
            if (u != null && c != null && s != null) {
                Announce a = announceService.createAnnounce(announce, c, s, u);
                return new ResponseEntity<Object>(a, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
