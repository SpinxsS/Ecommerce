package com.ecommerce.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.service.CategoryService;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryService categoryService;
    
    private Product createdProduct;
    private Category createdCategory;

    @Test
    public void testCreateProduct() throws Exception {
        Category category = new Category();
        category.setName("Electrodomesticos");
        categoryService.createCategory(category); // Asegúrate de tener un método en el servicio para crear categorías

        mockMvc.perform(post("/api/products/crearProducto")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Refrigerador\", \"category\":" + category.getId() + ", \"price\":499.99, \"stock_quantity\":10, " +
                         "\"description\":\"Refrigerador de doble puerta\", \"video_url\":\"http://example.com/video\", " +
                         "\"summary\":\"Refrigerador de última generación\", \"image_url\":\"http://example.com/image.jpg\", " +
                         "\"specifications\":\"{\\\"color\\\":\\\"blanco\\\", \\\"tipo\\\":\\\"doble puerta\\\"}\"}"))
                .andExpect(status().isCreated());
    }
    
    @Test
    public void testReadProduct() throws Exception {
        mockMvc.perform(get("/api/products/" + createdProduct.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Refrigerador"));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        String updatedProductJson = "{\"name\":\"Refrigerador Premium\", \"category\":" + createdCategory.getId() + ", \"price\":599.99, \"stock_quantity\":5, " +
                "\"description\":\"Refrigerador de alta gama\", \"video_url\":\"http://example.com/video\", " +
                "\"summary\":\"Refrigerador de última tecnología\", \"image_url\":\"http://example.com/image.jpg\", " +
                "\"specifications\":\"{\\\"color\\\":\\\"negro\\\", \\\"tipo\\\":\\\"doble puerta\\\"}\"}";

        mockMvc.perform(put("/api/products/actualizarProducto/" + createdProduct.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedProductJson))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/products/" + createdProduct.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Refrigerador Premium"));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/api/products/eliminarProducto/" + createdProduct.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/products/" + createdProduct.getId()))
                .andExpect(status().isNotFound());
    }
}
