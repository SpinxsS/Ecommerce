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
import com.ecommerce.model.FAQ;
import com.ecommerce.service.FAQService;

@RestController
@RequestMapping("/api/faqs")
@Validated
public class FAQController {

    @Autowired
    private FAQService faqService;

    @PostMapping
    public ResponseEntity<FAQ> createFAQ(@Valid @RequestBody FAQ faq) {
        FAQ createdFAQ = faqService.createFAQ(faq);
        return new ResponseEntity<>(createdFAQ, HttpStatus.CREATED);
    }

    @GetMapping
    public List<FAQ> getAllFAQs() {
        return faqService.getAllFAQs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FAQ> getFAQById(@PathVariable Integer id) {
        try {
            FAQ faq = faqService.getFAQById(id);
            return ResponseEntity.ok(faq);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FAQ> updateFAQ(@PathVariable Integer id, @Valid @RequestBody FAQ faqDetails) {
        FAQ updatedFAQ = faqService.updateFAQ(id, faqDetails);
        return ResponseEntity.ok(updatedFAQ);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFAQ(@PathVariable Integer id) {
        faqService.deleteFAQ(id);
        return ResponseEntity.noContent().build();
    }
}
