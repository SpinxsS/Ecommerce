package com.ecommerce.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.exception.UserNotFoundException;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User createUser(@Valid User user) {
    	// Obtener la fecha y hora actual y restar 5 horas
        LocalDateTime now = LocalDateTime.now().minusHours(5);
        
        // Establecer las fechas de creación y actualización
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User updateUser(Integer id, @Valid User userDetails) {
        User user = getUserById(id);
        user.setGoogleId(userDetails.getGoogleId());
        user.setFacebookId(userDetails.getFacebookId());
        user.setPasswordHash(userDetails.getPasswordHash());
        user.setEmail(userDetails.getEmail());
        user.setUsername(userDetails.getUsername());
        user.setUpdatedAt(LocalDateTime.now()); // Asegúrate de actualizar el timestamp
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }
}
