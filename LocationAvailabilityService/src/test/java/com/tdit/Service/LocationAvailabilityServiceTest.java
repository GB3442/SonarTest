package com.tdit.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.when;

import com.tdit.model.Location;
import com.tdit.model.Product;
import com.tdit.model.Seller;
import com.tdit.model.Userdata;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tdit.SellerRepository.SellerRepo;
import com.tdit.service.LocationAvailabilityService;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class LocationAvailabilityServiceTest {

	@InjectMocks
	private LocationAvailabilityService locationAvailabilityService;

	@Mock
	private SellerRepo sellerRepo;

	private Seller seller;
	private Product product;
	private Location location;
	private Userdata user;


	@BeforeEach
	void setUp() {
		// Mock objects setup
		location = new Location();
		location.setPincode("123456");

		product = new Product();

		seller = new Seller();
		seller.setAvailableLocations(Set.of(location));
		seller.setProductList(Set.of(product));

		user = new Userdata();
		user.setPincode("123456");
	}

	@Test
	void testCheckAvailability_WithUserAndProduct_Available() throws Exception {
		when(sellerRepo.findAll()).thenReturn(List.of(seller));
		boolean result = locationAvailabilityService.checkAvailabilty(user, product);
		assertTrue(result);
	}

	@Test
	void testCheckAvailability_WithUserAndProduct_NotAvailable() throws Exception {
		when(sellerRepo.findAll()).thenReturn(List.of());
		boolean result = locationAvailabilityService.checkAvailabilty(user, product);
		assertFalse(result);
	}

	@Test
	void testCheckAvailability_WithPincode_Available() {
		when(sellerRepo.findAll()).thenReturn(List.of(seller));
		boolean result = locationAvailabilityService.checkAvailabilty("123456");
		assertTrue(result);
	}

	@Test
	void testCheckAvailability_WithPincode_NotAvailable() {
		when(sellerRepo.findAll()).thenReturn(List.of());
		boolean result = locationAvailabilityService.checkAvailabilty("654321");
		assertFalse(result);
	}

	@Test
	void testCheckAvailability_WithNullProductList() throws Exception {
		seller.setProductList(null);
		when(sellerRepo.findAll()).thenReturn(List.of(seller));
		boolean result = locationAvailabilityService.checkAvailabilty(user, product);
		assertFalse(result);
	}

	@Test
	void testCheckAvailability_WithEmptyProductList() throws Exception {
		seller.setProductList(Collections.emptySet());
		when(sellerRepo.findAll()).thenReturn(List.of(seller));
		boolean result = locationAvailabilityService.checkAvailabilty(user, product);
		assertFalse(result);
	}

	@Test
	void testCheckAvailability_WithNullLocationList() throws Exception {
		seller.setAvailableLocations(null);
		when(sellerRepo.findAll()).thenReturn(List.of(seller));
		boolean result = locationAvailabilityService.checkAvailabilty(user, product);
		assertFalse(result);
	}

	@Test
	void testCheckAvailability_WithEmptyLocationList() throws Exception {
		seller.setAvailableLocations(Collections.emptySet());
		when(sellerRepo.findAll()).thenReturn(List.of(seller));
		boolean result = locationAvailabilityService.checkAvailabilty(user, product);
		assertFalse(result);
	}

	@Test
	void testCheckAvailability_ExceptionHandling() {
		when(sellerRepo.findAll()).thenThrow(new RuntimeException("Database error"));
		assertThrows(Exception.class, () -> locationAvailabilityService.checkAvailabilty(user, product));
	}
	
}
