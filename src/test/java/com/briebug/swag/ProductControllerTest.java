package com.briebug.swag;

import com.briebug.swag.controllers.ProductController;
import com.briebug.swag.models.Product;
import com.briebug.swag.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import com.briebug.swag.exception.ResourceNotFoundException;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.times;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest(controllers = {ProductController.class})
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper object_mapper;

    @MockBean
    ProductService productService;

    @Test
    void listAllProductsTest() throws Exception {
        List<Product> catalog = new ArrayList<>();
        catalog.add(new Product("Hats", 3.99f, 2));
        catalog.add(new Product("Shirts", 20.00f, 5));
        Mockito.doReturn(catalog).when(productService).list();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)));

        Mockito.verify(productService, times(1)).list();
    }

    @Test
    void getProductByIdTest() throws Exception {
        Product hats = new Product(1L, "Hats", 3.99f, 2);
        Mockito.doReturn(Optional.of(hats)).when(productService).get(1L);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/{id}",1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.name", is(hats.getName())))
                .andExpect(jsonPath("$.cost", is(3.99)))
                .andExpect(jsonPath("$.stock", is(hats.getStock())));
    }

    @Test
    void getProductBadIdTest() throws Exception {
        Mockito.doReturn(Optional.empty()).when(productService).get(99L);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/{id}",99))
                .andExpect(status().isNotFound());
    }

    @Test
    void createProductTest() throws Exception {
        Product hats = new Product(1L, "Hats", 3.99f, 2);
        Mockito.doNothing().when(productService).save(hats);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(object_mapper.writeValueAsString(hats)))
                .andExpect(status().isCreated());
    }

    @Test
    void createBadProductTest() throws Exception {
        Product bad_hats = new Product();
        Mockito.doNothing().when(productService).create(bad_hats);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(object_mapper.writeValueAsString(bad_hats)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateProductTest() throws Exception {
        Product hats = new Product(1L, "Hats", 3.99f, 2);
        Mockito.doNothing().when(productService).update(hats, hats.getId());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/products/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(object_mapper.writeValueAsString(hats)))
                .andExpect(status().isNoContent());
    }

    @Test
    void updateInvalidProductTest() throws Exception {
        Product bad_hats = new Product();
        Mockito.doNothing().when(productService).save(bad_hats);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/products/{id}", 99)
                .contentType(MediaType.APPLICATION_JSON)
                .content(object_mapper.writeValueAsString(bad_hats)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteProductTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteInvalidProductTest() throws Exception {
        Mockito.doThrow(new ResourceNotFoundException()).when(productService).delete(99L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/{id}", 99))
                .andExpect(status().isNotFound());
    }
}
