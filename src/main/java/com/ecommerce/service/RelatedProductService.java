package com.ecommerce.service;

import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.RelatedProduct;
import com.ecommerce.repository.RelatedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatedProductService {

    @Autowired
    private RelatedProductRepository relatedProductRepository;

    public RelatedProduct createRelatedProduct(RelatedProduct relatedProduct) {
        return relatedProductRepository.save(relatedProduct);
    }

    public List<RelatedProduct> getAllRelatedProducts() {
        return relatedProductRepository.findAll();
    }

    public RelatedProduct getRelatedProductById(Integer id) {
        return relatedProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Related product not found with id " + id));
    }

    public RelatedProduct updateRelatedProduct(Integer id, RelatedProduct relatedProductDetails) {
        RelatedProduct relatedProduct = getRelatedProductById(id);
        relatedProduct.setProduct(relatedProductDetails.getProduct());
        relatedProduct.setRelatedProduct(relatedProductDetails.getRelatedProduct());
        return relatedProductRepository.save(relatedProduct);
    }

    public void deleteRelatedProduct(Integer id) {
        RelatedProduct relatedProduct = getRelatedProductById(id);
        relatedProductRepository.delete(relatedProduct);
    }
}
