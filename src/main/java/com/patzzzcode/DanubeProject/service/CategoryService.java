package com.patzzzcode.DanubeProject.service;

import com.patzzzcode.DanubeProject.bo.Category;
import com.patzzzcode.DanubeProject.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        Category c = new Category(null, category.getName());
        c = categoryRepository.save(c);
        return c;
    }
}
