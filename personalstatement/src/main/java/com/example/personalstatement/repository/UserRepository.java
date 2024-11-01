package com.example.personalstatement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.personalstatement.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

