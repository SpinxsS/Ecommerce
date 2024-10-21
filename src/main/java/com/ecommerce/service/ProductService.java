package com.ecommerce.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.exception.ResourceNotFoundException;
//import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.util.Utilidades;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ObjectMapper objectMapper;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product createProduct(Product product) {
		// Manejo de fechas
		if (product.getCreated_at() == null) {
			product.setCreated_at(LocalDateTime.now());
		}
		product.setUpdated_at(LocalDateTime.now());

		// Leer el JSON de especificaciones
		String specificationsJson = product.getSpecifications(); // Suponiendo que este campo ahora es un String JSON
		Map<String, String> specs = new HashMap<>();

		// Convertir el JSON a un Map
		try {
			specs = objectMapper.readValue(specificationsJson, new TypeReference<Map<String, String>>() {
			});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			// Manejo de error
		}

		// Imprimir el mapa antes de convertir a JSON
		System.out.println("Especificaciones como mapa: " + specs);

		// Convertir el mapa a JSON
		String specificationsJsonFinal = "";
		try {
			specificationsJsonFinal = objectMapper.writeValueAsString(specs);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			// Manejo de error
		}

		// Establecer el JSON en el campo de especificaciones
		product.setSpecifications(specificationsJsonFinal);

		// Imprimir el JSON antes de guardar
		System.out.println("Especificaciones en JSON: " + specificationsJsonFinal);

		// Guardar el producto
		return productRepository.save(product);
	}

	public Product getProductById(Integer id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
	}

	public Product updateProduct(Integer id, Product productDetails) {
		Product product = getProductById(id);
		product.setName(productDetails.getName());
		product.setDescription(productDetails.getDescription());
		product.setPrice(productDetails.getPrice());
		product.setStock_quantity(productDetails.getStock_quantity());
		product.setImage_url(productDetails.getImage_url());
		product.setVideo_url(productDetails.getVideo_url());
		product.setSummary(productDetails.getSummary());
		product.setSpecifications(productDetails.getSpecifications()); // Asegúrate de que esto sea un String en formato
																		// JSON
		product.setUpdated_at(LocalDateTime.now()); // Actualiza la fecha

		return productRepository.save(product);
	}

	public void deleteProduct(Integer id) {
		productRepository.deleteById(id);
	}
}