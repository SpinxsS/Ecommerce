package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
