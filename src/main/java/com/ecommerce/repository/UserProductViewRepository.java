package com.ecommerce.repository;

import com.ecommerce.model.UserProductView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProductViewRepository extends JpaRepository<UserProductView, Integer> {
    // Puedes agregar métodos personalizados si es necesario
}
