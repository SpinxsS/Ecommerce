package com.ecommerce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.ModelsOfBrands;
import com.ecommerce.repository.ModelsOfBrandsRepository;

@Service
public class ModelsOfBrandsService {

	@Autowired
	private ModelsOfBrandsRepository modelsOfBrandsRepository;

	public List<ModelsOfBrands> getAllModels() {
		return modelsOfBrandsRepository.findAll();
	}

	public ModelsOfBrands getModelById(Integer id) {
		return modelsOfBrandsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ModelsOfBrandsService not found with id " + id));
	}

	public ModelsOfBrands createModel(ModelsOfBrands model) {
		model.setCreatedAt(LocalDateTime.now());
		return modelsOfBrandsRepository.save(model);
	}

	public ModelsOfBrands updateModel(Integer id, ModelsOfBrands modelDetails) {
		ModelsOfBrands model = getModelById(id);
		model.setName(modelDetails.getName());
		model.setBrand(modelDetails.getBrand());
		model.setUpdatedAt(LocalDateTime.now());
		return modelsOfBrandsRepository.save(model);
	}

	public void deleteModel(Integer id) {
		ModelsOfBrands model = getModelById(id);
		modelsOfBrandsRepository.delete(model);
	}
}
