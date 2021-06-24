package com.patzzzcode.DanubeProject.repositories;

import java.util.Optional;

import com.patzzzcode.DanubeProject.bo.Subcategory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    Optional<Subcategory> findByName(String name);
}
