package com.patzzzcode.DanubeProject.service;

import com.patzzzcode.DanubeProject.bo.Category;
import com.patzzzcode.DanubeProject.bo.Subcategory;
import com.patzzzcode.DanubeProject.repositories.SubcategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    public Subcategory createSubcategory(Subcategory subcategory, Category category) {
        Subcategory s = new Subcategory(null, subcategory.getName(), category);
        s = subcategoryRepository.save(s);
        return s;
    }
}
