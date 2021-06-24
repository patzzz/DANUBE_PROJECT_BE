package com.patzzzcode.DanubeProject.restcontrollers;

import org.springframework.web.bind.annotation.RestController;

import com.patzzzcode.DanubeProject.bo.Announce;
import com.patzzzcode.DanubeProject.bo.Category;
import com.patzzzcode.DanubeProject.bo.Subcategory;
import com.patzzzcode.DanubeProject.bo.User;
import com.patzzzcode.DanubeProject.dto.AnnounceDto;
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
    private AnnounceService announceService;

    @RequestMapping(value = "/api/announces/createAnnounce", method = RequestMethod.POST)
    public ResponseEntity<Object> createAnnounce(UsernamePasswordAuthenticationToken principal,
            @RequestBody AnnounceDto announceDto, @RequestParam Long categoryId, @RequestParam Long subcategoryId) {
        try {
            announceService.addAnnounce(principal, announceDto, categoryId, subcategoryId);
            return new ResponseEntity<Object>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/announces/createAnnounceWithMapper", method = RequestMethod.POST)
    public ResponseEntity<Object> createAnnounceWithMapper(UsernamePasswordAuthenticationToken principal,
            @RequestBody AnnounceDto announceDto, @RequestParam Long categoryId, @RequestParam Long subcategoryId) {
        try {
            announceService.addAnnounceWithMapper(principal, announceDto, categoryId, subcategoryId);
            return new ResponseEntity<Object>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
