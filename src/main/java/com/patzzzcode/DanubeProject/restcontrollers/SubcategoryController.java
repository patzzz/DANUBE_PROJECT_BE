package com.patzzzcode.DanubeProject.restcontrollers;

import com.patzzzcode.DanubeProject.bo.Category;
import com.patzzzcode.DanubeProject.bo.Subcategory;
import com.patzzzcode.DanubeProject.repositories.CategoryRepository;
import com.patzzzcode.DanubeProject.repositories.SubcategoryRepository;
import com.patzzzcode.DanubeProject.service.SubcategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubcategoryController {

    @Autowired
    private SubcategoryRepository subcategoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubcategoryService subcategoryService;

    @RequestMapping(value = "/api/subcategories/createSubcategory", method = RequestMethod.POST)
    public ResponseEntity<Object> createSubcategory(@RequestBody Subcategory subcategory,
            @RequestParam Long categoryId) {
        try {
            if (subcategory != null) {
                Category category = categoryRepository.findById(categoryId).orElse(null);
                Subcategory existingSubcategory = subcategoryRepository.findByName(subcategory.getName()).orElse(null);
                if (existingSubcategory != null) {
                    return new ResponseEntity<Object>(HttpStatus.ALREADY_REPORTED);
                } else {
                    if (category != null) {
                        Subcategory s = subcategoryService.createSubcategory(subcategory, category);
                        return new ResponseEntity<Object>(s, HttpStatus.CREATED);
                    } else {
                        return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
                    }
                }
            } else {
                return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/subcategories/getSubcategory", method = RequestMethod.GET)
    public ResponseEntity<Object> getSubcategory(@RequestParam Long subcategoryId) {
        try {
            if (subcategoryId != null) {
                Subcategory existingSubcategory = subcategoryRepository.findById(subcategoryId).orElse(null);
                if (existingSubcategory != null) {
                    return new ResponseEntity<Object>(existingSubcategory, HttpStatus.OK);
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
