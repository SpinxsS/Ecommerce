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
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryTests {

    @Autowired
    private MockMvc mockMvc;
    
    private Category createdCategory;

    @Test
    public void testCreateCategory() throws Exception {
        String categoryJson = "{\"name\":\"Electrodomesticos\"}";

        String response = mockMvc.perform(post("/api/categories/crearCategoria")
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoryJson))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        // Deserializar la respuesta para obtener el ID de la categoría creada
        ObjectMapper objectMapper = new ObjectMapper();
        createdCategory = objectMapper.readValue(response, Category.class);
    }
    
    @Test
    public void testReadCategory() throws Exception {
        mockMvc.perform(get("/api/categories/" + createdCategory.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Electrodomesticos"));
    }
    
    @Test
    public void testUpdateCategory() throws Exception {
        String updatedCategoryJson = "{\"name\":\"Electrodomésticos Avanzados\"}";

        mockMvc.perform(put("/api/categories/actualizarCategoria/" + createdCategory.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedCategoryJson))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/categories/" + createdCategory.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Electrodomésticos Avanzados"));
    }
    
    @Test
    public void testDeleteCategory() throws Exception {
        mockMvc.perform(delete("/api/categories/eliminarCategoria/" + createdCategory.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/categories/" + createdCategory.getId()))
                .andExpect(status().isNotFound());
    }
    
}
