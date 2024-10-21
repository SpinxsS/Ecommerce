package com.ecommerce.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private String imageUrl;
    private String videoUrl;
    private String summary;
    // Agrega otros campos según sea necesario
}