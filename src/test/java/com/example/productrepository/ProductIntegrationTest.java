package com.example.productrepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;   //Dependency Injection des Repositories; cleaner ohne Injection und Zugriff direkt auf den ProductService

    @Test
    @DirtiesContext
    void getAllProductsTest() throws Exception {
        productRepository.save(new Product("1", "ProductTitle", 100));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                            {
                                "id": "1",
                                "title": "ProductTitle",
                                "price": 100
                            }
                        ]
                        """));
    }

    @Test
    @DirtiesContext
    void addProductTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                            {
                                "title": "test-title",
                                "price": 100
                            }
                            """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                            {
                                                "title": "test-title",
                                                "price": 100
                                            }
                                            """))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    @DirtiesContext
    void findProductByIdTest() throws Exception {
        productRepository.save(new Product("1", "ProductTitle", 100));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            {
                                "id": "1",
                                "title": "ProductTitle",
                                "price": 100
                            }
                        """));
    }

    @Test
    @DirtiesContext
    void removeProductTest() throws Exception {
        productRepository.save(new Product("1", "ProductTitle", 100));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                            {
                                "id": "1",
                                "title": "ProductTitle",
                                "price": 100
                            }
                            """))
                .andExpect(status().isOk())                                 //reicht aus für Testing
                .andExpect(jsonPath("$.id").doesNotExist());

    }

    //neuer findProductByIdTest() würde removeProductTest() bei Funktionalität bestätigen

}
