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

import com.ecommerce.model.Brand;

@SpringBootTest
@AutoConfigureMockMvc
public class BrandTests {

    @Autowired
    private MockMvc mockMvc;
    
    private Brand createdBrand;

    @Test
    public void testCreateBrand() throws Exception {
        mockMvc.perform(post("/api/brands/crearMarca")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Samsung\"}"))
                .andExpect(status().isCreated());
    }
    
    @Test
    public void testReadBrand() throws Exception {
        mockMvc.perform(get("/api/brands/" + createdBrand.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Samsung"));
    }

    @Test
    public void testUpdateBrand() throws Exception {
        String updatedBrandJson = "{\"name\":\"Samsung Electronics\"}";

        mockMvc.perform(put("/api/brands/actualizarMarca/" + createdBrand.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedBrandJson))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/brands/" + createdBrand.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Samsung Electronics"));
    }

    @Test
    public void testDeleteBrand() throws Exception {
        mockMvc.perform(delete("/api/brands/eliminarMarca/" + createdBrand.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/brands/" + createdBrand.getId()))
                .andExpect(status().isNotFound());
    }
}
