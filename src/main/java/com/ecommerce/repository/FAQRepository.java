package com.ecommerce.repository;

import com.ecommerce.model.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FAQRepository extends JpaRepository<FAQ, Integer> {
    // Puedes agregar métodos personalizados si es necesario
}
