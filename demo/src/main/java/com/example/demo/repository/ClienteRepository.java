package com.example.demo.repository;

import com.example.demo.Entities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByEmail(String email);
}
