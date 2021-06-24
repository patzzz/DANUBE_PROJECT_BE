package com.patzzzcode.DanubeProject.repositories;

import java.util.Optional;

import com.patzzzcode.DanubeProject.bo.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
