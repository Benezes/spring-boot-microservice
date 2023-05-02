package com.mnz.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mnz.product.dto.request.ProductRequest;
import com.mnz.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductControllerTest {

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:latest"));
    private static final String API_PATH = "/api/products";

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private ProductRepository productRepository;
    ProductRequest product = buildProductRequest();

    @Test
    void shouldCreateProductWithSuccess() throws Exception {

        String productRequestJson = mapper.writeValueAsString(product);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productRequestJson))
                .andExpect(status().isCreated());

        assertEquals(1, productRepository.findAll().size());
    }

    @Test
    void shouldGetAllProductsWithSuccess() throws Exception {

        String productRequestJson = mapper.writeValueAsString(product);
        mockMvc.perform(MockMvcRequestBuilders.post(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productRequestJson))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.get(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    private ProductRequest buildProductRequest() {
        return ProductRequest.builder()
                .name("IPHONE 14")
                .description("SMART PHONE")
                .price(BigDecimal.valueOf(4000.0))
                .build();
    }
}