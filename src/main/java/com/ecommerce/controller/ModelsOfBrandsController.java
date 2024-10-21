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
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.model.ModelsOfBrands;
import com.ecommerce.service.ModelsOfBrandsService;

@RestController
@RequestMapping("/api/models")
@Validated
public class ModelsOfBrandsController {

    @Autowired
    private ModelsOfBrandsService modelsOfBrandsService;

    @GetMapping
    public ResponseEntity<List<ModelsOfBrands>> getAllModels() {
        return ResponseEntity.ok(modelsOfBrandsService.getAllModels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelsOfBrands> getModelById(@PathVariable Integer id) {
        try {
            ModelsOfBrands model = modelsOfBrandsService.getModelById(id);
            return ResponseEntity.ok(model);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/crearModelsOfBrands")
    public ResponseEntity<ModelsOfBrands> createModel(@Valid @RequestBody ModelsOfBrands model) {
        ModelsOfBrands createdModel = modelsOfBrandsService.createModel(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelsOfBrands> updateModel(@PathVariable Integer id, @Valid @RequestBody ModelsOfBrands modelDetails) {
        ModelsOfBrands updatedModel = modelsOfBrandsService.updateModel(id, modelDetails);
        return ResponseEntity.ok(updatedModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable Integer id) {
        modelsOfBrandsService.deleteModel(id);
        return ResponseEntity.noContent().build();
    }
}
