package com.ecommerce.service;

import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Discount;
import com.ecommerce.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    public Discount createDiscount(Discount discount) {
        discount.setCreatedAt(LocalDateTime.now());
        discount.setUpdatedAt(LocalDateTime.now());
        return discountRepository.save(discount);
    }
    
    public Discount findByCode(String code) {
        return discountRepository.findByCode(code);
    }

    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    public Discount getDiscountById(Integer id) {
        return discountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discount not found with id " + id));
    }

    public Discount updateDiscount(Integer id, Discount discountDetails) {
        Discount discount = getDiscountById(id);
        discount.setCode(discountDetails.getCode());
        discount.setApplicableTo(discountDetails.getApplicableTo());
        discount.setPercentage(discountDetails.getPercentage());
        discount.setStartDate(discountDetails.getStartDate());
        discount.setEndDate(discountDetails.getEndDate());
        discount.setCategory(discountDetails.getCategory());
        discount.setUpdatedAt(LocalDateTime.now());
        return discountRepository.save(discount);
    }

    public void deleteDiscount(Integer id) {
        if (!discountRepository.existsById(id)) {
            throw new ResourceNotFoundException("Discount not found with id " + id);
        }
        discountRepository.deleteById(id);
    }

    public boolean isDiscountValid(Discount discount) {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(discount.getStartDate()) && now.isBefore(discount.getEndDate());
    }
}
