package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Puedes agregar métodos personalizados aquí
}