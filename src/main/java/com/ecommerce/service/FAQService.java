package com.ecommerce.service;

import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.FAQ;
import com.ecommerce.repository.FAQRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FAQService {

    @Autowired
    private FAQRepository faqRepository;

    public FAQ createFAQ(FAQ faq) {
        faq.setCreatedAt(LocalDateTime.now());
        return faqRepository.save(faq);
    }

    public List<FAQ> getAllFAQs() {
        return faqRepository.findAll();
    }

    public FAQ getFAQById(Integer id) {
        return faqRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FAQ not found with id " + id));
    }

    public FAQ updateFAQ(Integer id, FAQ faqDetails) {
        FAQ faq = getFAQById(id);
        faq.setQuestion(faqDetails.getQuestion());
        faq.setAnswer(faqDetails.getAnswer());
        faq.setUpdatedAt(LocalDateTime.now());
        return faqRepository.save(faq);
    }

    public void deleteFAQ(Integer id) {
        FAQ faq = getFAQById(id);
        faqRepository.delete(faq);
    }
}
