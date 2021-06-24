package com.patzzzcode.DanubeProject.restcontrollers;

import java.util.List;

import com.patzzzcode.DanubeProject.bo.Category;
import com.patzzzcode.DanubeProject.repositories.CategoryRepository;
import com.patzzzcode.DanubeProject.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/api/categories/createCategory", method = RequestMethod.POST)
    public ResponseEntity<Object> createCategory(@RequestBody Category category) {
        try {
            if (category != null) {
                Category existingCategory = categoryRepository.findByName(category.getName()).orElse(null);
                if (existingCategory != null) {
                    return new ResponseEntity<Object>(HttpStatus.ALREADY_REPORTED);
                } else {
                    Category c = categoryService.createCategory(category);
                    return new ResponseEntity<Object>(c, HttpStatus.CREATED);
                }
            } else {
                return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/categories/getCategory", method = RequestMethod.GET)
    public ResponseEntity<Object> getCategory(@RequestParam Long categoryId) {
        try {
            if (categoryId != null) {
                Category existingCategory = categoryRepository.findById(categoryId).orElse(null);
                if (existingCategory != null) {
                    return new ResponseEntity<Object>(existingCategory, HttpStatus.OK);
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

    @RequestMapping(value = "/api/categories/getCategories", method = RequestMethod.GET)
    public ResponseEntity<Object> getCategories() {
        try {
            List<Category> categories = categoryRepository.findAll();
            return new ResponseEntity<Object>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
