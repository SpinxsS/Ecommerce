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
import com.ecommerce.model.ModelsOfBrands;
import com.ecommerce.service.BrandService;

@SpringBootTest
@AutoConfigureMockMvc
public class ModelsOfBrandsTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BrandService brandService;
    
    private ModelsOfBrands createdModel;
    private Brand createdBrand;

    @Test
    public void testCreateModelOfBrand() throws Exception {
        Brand brand = new Brand();
        brand.setName("Samsung");
        brandService.createBrand(brand); // Asegúrate de tener un método en el servicio para crear marcas

        mockMvc.perform(post("/api/models/crearModelo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Galaxy S21\", \"brand_id\":" + brand.getId() + "}"))
                .andExpect(status().isCreated());
    }
    
    @Test
    public void testReadModel() throws Exception {
        mockMvc.perform(get("/api/models/" + createdModel.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Galaxy S21"));
    }

    @Test
    public void testUpdateModel() throws Exception {
        String updatedModelJson = "{\"name\":\"Galaxy S22\", \"brand_id\":" + createdBrand.getId() + "}";

        mockMvc.perform(put("/api/models/actualizarModelo/" + createdModel.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedModelJson))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/models/" + createdModel.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Galaxy S22"));
    }

    @Test
    public void testDeleteModel() throws Exception {
        mockMvc.perform(delete("/api/models/eliminarModelo/" + createdModel.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/models/" + createdModel.getId()))
                .andExpect(status().isNotFound());
    }
}
