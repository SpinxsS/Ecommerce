package com.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.ProductVideo;
import com.ecommerce.service.ProductVideoService;

@RestController
@RequestMapping("/api/product-videos")
@Validated
public class ProductVideoController {

    @Autowired
    private ProductVideoService productVideoService;

    @PostMapping("/crearVideosProductos")
    public ResponseEntity<ProductVideo> createProductVideo(@Valid @RequestBody ProductVideo productVideo) {
        ProductVideo createdProductVideo = productVideoService.createProductVideo(productVideo);
        return new ResponseEntity<>(createdProductVideo, HttpStatus.CREATED);
    }

    @GetMapping
    public List<ProductVideo> getAllProductVideos() {
        return productVideoService.getAllProductVideos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductVideo> getProductVideoById(@PathVariable Integer id) {
        try {
            ProductVideo productVideo = productVideoService.getProductVideoById(id);
            return ResponseEntity.ok(productVideo);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductVideo> updateProductVideo(@PathVariable Integer id, @Valid @RequestBody ProductVideo productVideoDetails) {
        ProductVideo updatedProductVideo = productVideoService.updateProductVideo(id, productVideoDetails);
        return ResponseEntity.ok(updatedProductVideo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductVideo(@PathVariable Integer id) {
        productVideoService.deleteProductVideo(id);
        return ResponseEntity.noContent().build();
    }
}
