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
import com.ecommerce.model.RelatedProduct;
import com.ecommerce.service.RelatedProductService;

@RestController
@RequestMapping("/api/related-products")
@Validated
public class RelatedProductController {

    @Autowired
    private RelatedProductService relatedProductService;

    @PostMapping("/crearProductosRelacionados")
    public ResponseEntity<RelatedProduct> createRelatedProduct(@Valid @RequestBody RelatedProduct relatedProduct) {
        RelatedProduct createdRelatedProduct = relatedProductService.createRelatedProduct(relatedProduct);
        return new ResponseEntity<>(createdRelatedProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public List<RelatedProduct> getAllRelatedProducts() {
        return relatedProductService.getAllRelatedProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelatedProduct> getRelatedProductById(@PathVariable Integer id) {
        try {
            RelatedProduct relatedProduct = relatedProductService.getRelatedProductById(id);
            return ResponseEntity.ok(relatedProduct);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RelatedProduct> updateRelatedProduct(@PathVariable Integer id, @Valid @RequestBody RelatedProduct relatedProductDetails) {
        RelatedProduct updatedRelatedProduct = relatedProductService.updateRelatedProduct(id, relatedProductDetails);
        return ResponseEntity.ok(updatedRelatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRelatedProduct(@PathVariable Integer id) {
        relatedProductService.deleteRelatedProduct(id);
        return ResponseEntity.noContent().build();
    }
}
