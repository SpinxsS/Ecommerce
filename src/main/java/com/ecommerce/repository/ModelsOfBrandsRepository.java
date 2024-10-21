package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.ModelsOfBrands;

@Repository
public interface ModelsOfBrandsRepository extends JpaRepository<ModelsOfBrands, Integer> {
}
