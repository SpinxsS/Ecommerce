package com.ecommerce.repository;

import com.ecommerce.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {
    List<Discount> findByCategoryId(Integer categoryId);
    Discount findByCode(String code);
}
