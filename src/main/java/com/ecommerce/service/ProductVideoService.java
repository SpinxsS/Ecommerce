package com.ecommerce.service;

import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.ProductVideo;
import com.ecommerce.repository.ProductVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductVideoService {

    @Autowired
    private ProductVideoRepository productVideoRepository;

    public ProductVideo createProductVideo(ProductVideo productVideo) {
    	// Obtener la fecha y hora actual y restar 5 horas
        LocalDateTime now = LocalDateTime.now();
        
        // Establecer las fechas de creación y actualización
        productVideo.setCreatedAt(now);
        return productVideoRepository.save(productVideo);
    }

    public List<ProductVideo> getAllProductVideos() {
        return productVideoRepository.findAll();
    }

    public ProductVideo getProductVideoById(Integer id) {
        return productVideoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product video not found with id " + id));
    }

    public ProductVideo updateProductVideo(Integer id, ProductVideo productVideoDetails) {
        ProductVideo productVideo = getProductVideoById(id);
        productVideo.setVideoUrl(productVideoDetails.getVideoUrl());
        productVideo.setProduct(productVideoDetails.getProduct());
        productVideo.setType(productVideoDetails.getType());
        return productVideoRepository.save(productVideo);
    }

    public void deleteProductVideo(Integer id) {
        ProductVideo productVideo = getProductVideoById(id);
        productVideoRepository.delete(productVideo);
    }
}
