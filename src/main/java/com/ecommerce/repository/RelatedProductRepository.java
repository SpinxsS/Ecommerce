package com.ecommerce.repository;

import com.ecommerce.model.RelatedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelatedProductRepository extends JpaRepository<RelatedProduct, Integer> {
    // Puedes agregar métodos personalizados si es necesario
}
