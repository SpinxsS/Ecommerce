package com.ecommerce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.UserProductView;
import com.ecommerce.repository.UserProductViewRepository;

@Service
public class UserProductViewService {

    @Autowired
    private UserProductViewRepository userProductViewRepository;

    public UserProductView createUserProductView(UserProductView userProductView) {
        return userProductViewRepository.save(userProductView);
    }

    public List<UserProductView> getAllUserProductViews() {
        return userProductViewRepository.findAll();
    }

    public UserProductView getUserProductViewById(Integer id) {
        return userProductViewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User product view not found with id " + id));
    }

    public UserProductView updateUserProductView(Integer id, UserProductView userProductViewDetails) {
        UserProductView userProductView = getUserProductViewById(id);
        userProductView.setId(userProductViewDetails.getId());
        userProductView.setProduct(userProductViewDetails.getProduct());
        userProductView.setViewedAt(userProductViewDetails.getViewedAt());
        return userProductViewRepository.save(userProductView);
    }

    public void deleteUserProductView(Integer id) {
        UserProductView userProductView = getUserProductViewById(id);
        userProductViewRepository.delete(userProductView);
    }
}
