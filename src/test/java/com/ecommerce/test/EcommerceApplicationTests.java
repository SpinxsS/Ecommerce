package com.ecommerce.test;

 import com.ecommerce.model.Category; // Asegúrate de importar la clase Category
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
public class EcommerceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    private static final String CATEGORY_NAME = "Clothing";

    @Test
    public void testCreateProductUnderClothingCategory() throws Exception {
        // Verificar si la categoría ya existe, y crearla si no existe
        if (!categoryService.existsByName(CATEGORY_NAME)) {
            mockMvc.perform(post("/api/categories/crearCategoria")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\":\"" + CATEGORY_NAME + "\"}"))
                    .andExpect(status().isCreated());
        }

        // Obtener la categoría
        Category category = categoryService.getCategoryByName(CATEGORY_NAME);

        // Crear un nuevo producto bajo la categoría de ropa
        mockMvc.perform(post("/api/products/crearProducto")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"T-Shirt\", \"category\":" + category.getId() + ", \"price\":19.99, \"stock_quantity\":100, " +
                         "\"description\":\"A comfortable t-shirt\", \"video_url\":\"http://example.com/video\", " +
                         "\"summary\":\"T-Shirt summary\", \"image_url\":\"http://example.com/image.jpg\", " +
                         "\"specifications\":\"{\\\"color\\\":\\\"blue\\\", \\\"size\\\":\\\"M\\\"}\"}"))
                .andExpect(status().isCreated());

        // Verificar que el producto fue creado
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.name == 'T-Shirt')]").exists());
    }

   /* @Test
    public void testUpdateProduct() throws Exception {
        // Verificar si la categoría ya existe, y crearla si no existe
        if (!categoryService.existsByName(CATEGORY_NAME)) {
            mockMvc.perform(post("/api/categories/crearCategoria")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\":\"" + CATEGORY_NAME + "\"}"))
                    .andExpect(status().isCreated());
        }

        // Crear un producto para actualizar
        Category category = categoryService.getCategoryByName(CATEGORY_NAME);
        Product product = new Product();
        product.setName("Original T-Shirt");
        product.setCategory(category); // Relación directa
        product.setPrice(BigDecimal.valueOf(19.99));
        product.setStock_quantity(100);
        product.setDescription("Original description");
        product.setSpecifications("{\"color\":\"blue\", \"size\":\"M\"}");
        product.setImage_url("http://example.com/image.jpg");
        product.setVideo_url("http://example.com/video");
        product.setSummary("Original T-Shirt summary");
        product = productService.createProduct(product);

        // Actualizar el producto creado
        mockMvc.perform(put("/api/products/{id}", product.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated T-Shirt\", \"category\":" + category.getId() + ", \"price\":24.99, \"stock_quantity\":80, " +
                         "\"description\":\"Updated description\", \"specifications\":\"{\\\"color\\\":\\\"red\\\", \\\"size\\\":\\\"L\\\"}\", " +
                         "\"image_url\":\"http://example.com/updated-image.jpg\", \"video_url\":\"http://example.com/updated-video\", " +
                         "\"summary\":\"Updated T-Shirt summary\"}"))
                .andExpect(status().isOk());

        // Verificar que el producto fue actualizado
        mockMvc.perform(get("/api/products/{id}", product.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated T-Shirt"));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        // Verificar si la categoría ya existe, y crearla si no existe
        if (!categoryService.existsByName(CATEGORY_NAME)) {
            mockMvc.perform(post("/api/categories/crearCategoria")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\":\"" + CATEGORY_NAME + "\"}"))
                    .andExpect(status().isCreated());
        }

        // Crear un producto para eliminar
        Category category = categoryService.getCategoryByName(CATEGORY_NAME);
        Product product = new Product();
        product.setName("Product to Delete");
        product.setCategory(category); // Relación directa
        product.setPrice(BigDecimal.valueOf(19.99));
        product.setStock_quantity(100);
        product.setDescription("Product description");
        product.setSpecifications("{\"color\":\"black\", \"size\":\"L\"}");
        product.setImage_url("http://example.com/delete-image.jpg");
        product.setVideo_url("http://example.com/delete-video");
        product.setSummary("Product summary");
        product = productService.createProduct(product);

        // Eliminar el producto creado
        mockMvc.perform(delete("/api/products/{id}", product.getId()))
                .andExpect(status().isNoContent());

        // Verificar que el producto fue eliminado
        mockMvc.perform(get("/api/products/{id}", product.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCRUDFlow() throws Exception {
        // Flujo completo de pruebas CRUD
        // 1. Crear categoría
        if (!categoryService.existsByName("Footwear")) {
            mockMvc.perform(post("/api/categories/crearCategoria")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\":\"Footwear\"}"))
                    .andExpect(status().isCreated());
        }

        // 2. Crear producto
        Category category = categoryService.getCategoryByName("Footwear");
        mockMvc.perform(post("/api/products/crearProducto")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Sneakers\", \"category\":" + category.getId() + ", \"price\":59.99, \"stock_quantity\":50, " +
                         "\"description\":\"Sneakers description\", \"specifications\":\"{\\\"color\\\":\\\"white\\\", \\\"size\\\":\\\"42\\\"}\", " +
                         "\"image_url\":\"http://example.com/sneakers.jpg\", \"video_url\":\"http://example.com/sneakers-video\", " +
                         "\"summary\":\"Sneakers summary\"}"))
                .andExpect(status().isCreated());

        // 3. Obtener todos los productos
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.name == 'Sneakers')]").exists());

        // 4. Actualizar producto
        Integer productId = productService.getAllProducts().get(0).getId(); // Obtener el ID del primer producto
        mockMvc.perform(put("/api/products/{id}", productId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated Sneakers\", \"category\":" + category.getId() + ", \"price\":69.99, \"stock_quantity\":30, " +
                         "\"description\":\"Updated Sneakers description\", \"specifications\":\"{\\\"color\\\":\\\"black\\\", \\\"size\\\":\\\"43\\\"}\", " +
                         "\"image_url\":\"http://example.com/updated-sneakers.jpg\", \"video_url\":\"http://example.com/updated-sneakers-video\", " +
                         "\"summary\":\"Updated Sneakers summary\"}"))
                .andExpect(status().isOk());

        // 5. Eliminar producto
        mockMvc.perform(delete("/api/products/{id}", productId))
                .andExpect(status().isNoContent());
    }*/
}
