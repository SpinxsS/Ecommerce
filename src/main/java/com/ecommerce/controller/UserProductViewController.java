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
import com.ecommerce.model.UserProductView;
import com.ecommerce.service.UserProductViewService;

@RestController
@RequestMapping("/api/user-product-views")
@Validated
public class UserProductViewController {

    @Autowired
    private UserProductViewService userProductViewService;

    @PostMapping("/crearVistaProductoUsuario")
    public ResponseEntity<UserProductView> createUserProductView(@Valid @RequestBody UserProductView userProductView) {
        UserProductView createdUserProductView = userProductViewService.createUserProductView(userProductView);
        return new ResponseEntity<>(createdUserProductView, HttpStatus.CREATED);
    }

    @GetMapping
    public List<UserProductView> getAllUserProductViews() {
        return userProductViewService.getAllUserProductViews();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProductView> getUserProductViewById(@PathVariable Integer id) {
        try {
            UserProductView userProductView = userProductViewService.getUserProductViewById(id);
            return ResponseEntity.ok(userProductView);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProductView> updateUserProductView(@PathVariable Integer id, @Valid @RequestBody UserProductView userProductViewDetails) {
        UserProductView updatedUserProductView = userProductViewService.updateUserProductView(id, userProductViewDetails);
        return ResponseEntity.ok(updatedUserProductView);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserProductView(@PathVariable Integer id) {
        userProductViewService.deleteUserProductView(id);
        return ResponseEntity.noContent().build();
    }
}
