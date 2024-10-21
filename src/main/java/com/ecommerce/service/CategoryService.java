package com.ecommerce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.exception.CategoryNotFoundException;
import com.ecommerce.model.Category;
import com.ecommerce.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	public Category createCategory(Category category) {
		if (categoryRepository.existsByName(category.getName())) {
			throw new IllegalArgumentException("Category with this name already exists.");
		}
		// Obtener la fecha y hora actual y restar 5 horas
	    LocalDateTime now = LocalDateTime.now();
	    
	    // Establecer las fechas de creación y actualización
	    category.setCreatedAt(now);
	    category.setUpdatedAt(now);
		return categoryRepository.save(category);
	}

	public boolean existsByName(String name) {
		return categoryRepository.existsByName(name);
	}

	public Category getCategoryByName(String name) {
		return categoryRepository.findByName(name);
	}

	public Category getCategoryById(Integer id) {
		return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
	}

	public Category updateCategory(Integer id, Category categoryDetails) {
		Category category = getCategoryById(id);
		category.setName(categoryDetails.getName());
		category.setUpdatedAt(categoryDetails.getUpdatedAt()); // Asegúrate de tener un setter para updatedAt
		return categoryRepository.save(category);
	}

	public void deleteCategory(Integer id) {
		if (!categoryRepository.existsById(id)) {
			throw new CategoryNotFoundException(id);
		}
		categoryRepository.deleteById(id);
	}
}
