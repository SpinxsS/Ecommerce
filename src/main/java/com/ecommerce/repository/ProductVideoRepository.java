package com.ecommerce.repository;

import com.ecommerce.model.ProductVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVideoRepository extends JpaRepository<ProductVideo, Integer> {
    // Puedes agregar métodos personalizados si es necesario
}
