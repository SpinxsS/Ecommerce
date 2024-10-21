package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecommerce.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Puedes añadir métodos personalizados aquí si es necesario
	
	boolean existsByName(String name);
    Category findByName(String name);
}
