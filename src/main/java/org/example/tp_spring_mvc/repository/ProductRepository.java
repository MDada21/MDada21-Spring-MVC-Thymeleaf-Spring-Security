package org.example.tp_spring_mvc.repository;


import org.example.tp_spring_mvc.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContains(String keyword);

    
}