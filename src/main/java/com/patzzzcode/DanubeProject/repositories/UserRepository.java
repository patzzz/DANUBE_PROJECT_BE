package com.patzzzcode.DanubeProject.repositories;

import java.util.Optional;

import com.patzzzcode.DanubeProject.bo.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
