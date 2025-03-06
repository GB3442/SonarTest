package com.tdit.controller;

import com.tdit.model.Location;
import com.tdit.model.Seller;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@WebMvcTest(SellerController.class)
@ExtendWith(MockitoExtension.class)
class SellerControllerTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testAddSellerWithProductsAndLocations() throws Exception {
        Seller seller = new Seller();
        seller.setSellerId(1);
        seller.setName("John Doe");
        seller.setDescription("Electronics Seller");
        seller.setActive(true);
        seller.setVerified(true);

        Location loc1 = new Location(1, "New York", seller);
        Location loc2 = new Location(2, "Boston", seller);
        seller.setAvailableLocations(Set.of(loc1, loc2));

        Product prod1 = new Product(1, "Laptop", seller);
        Product prod2 = new Product(2, "Smartphone", seller);
        seller.setProductList(Set.of(prod1, prod2));

        Mockito.when(sellerRepo.save(any(Seller.class))).thenReturn(seller);

        mockMvc.perform(post("/seller/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(seller)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.availableLocations.size()").value(2))
                .andExpect(jsonPath("$.productList.size()").value(2));
    }

    // ✅ Test: Get Seller by ID (With Locations & Products)
    @Test
    void testGetSellerByIdWithRelations() throws Exception {
        Seller seller = new Seller(1, "John Doe", "Electronics Seller", true, true, Set.of(), Set.of());

        Mockito.when(sellerRepo.findById(1)).thenReturn(java.util.Optional.of(seller));

        mockMvc.perform(get("/seller/1"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.availableLocations").isEmpty())
                .andExpect(jsonPath("$.productList").isEmpty());
    }

}