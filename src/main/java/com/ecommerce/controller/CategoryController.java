package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.service.CategoryService;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/crearCategoria")
	public ResponseEntity<Category> createCategory( @RequestBody Category category) {
		Category categoria = categoryService.createCategory(category);
		return new ResponseEntity<>(categoria, HttpStatus.CREATED);
	}

	@GetMapping
	public List<Category> getAllCategories() {
		return categoryService.getAllCategories();
	}
	
	@GetMapping("/exists/{name}")
	public Boolean existsByName(@PathVariable String name) {
 		 return categoryService.existsByName(name);
	}
	
	@GetMapping("/categoriaPorNombre/{name}")
	public ResponseEntity<Category> getCategoryByName(@PathVariable String name) {
		 Category category = categoryService.getCategoryByName(name);
 		 return ResponseEntity.ok(category);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
		 Category category = categoryService.getCategoryById(id);
 		 return ResponseEntity.ok(category);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @Valid @RequestBody Category categoryDetails) {
		Category updatedCategory = categoryService.updateCategory(id, categoryDetails);
		return  ResponseEntity.ok(updatedCategory);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
		categoryService.deleteCategory(id);
		return ResponseEntity.noContent().build();
	}

}
