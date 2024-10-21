package com.ecommerce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Brand;
import com.ecommerce.repository.BrandRepository;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Brand getBrandById(Integer id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id " + id));
    }

    public Brand createBrand(Brand brand) {
    	brand.setCreatedAt(LocalDateTime.now());
        return brandRepository.save(brand);
    }

    public Brand updateBrand(Integer id, Brand brandDetails) {
        Brand brand = getBrandById(id);
        brand.setName(brandDetails.getName());
        brand.setUpdatedAt(LocalDateTime.now());
        return brandRepository.save(brand);
    }

    public void deleteBrand(Integer id) {
        Brand brand = getBrandById(id);
        brandRepository.delete(brand);
    }
}

