package com.tdit.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdit.SellerRepository.SellerRepo;
import com.tdit.model.Location;
import com.tdit.model.Product;
import com.tdit.model.Seller;
import com.tdit.model.Userdata;

@Service
public class LocationAvailabilityService {
	private boolean status;
	@Autowired
	SellerRepo sellerRepo;

	// check availability based on user and product data
	public boolean checkAvailabilty(Userdata user, Product product) throws Exception {
		List<Seller> sellerList = sellerRepo.findAll();
		return sellerList.stream().filter(seller -> seller.getProductList().contains(product)) 
				.flatMap(seller -> seller.getAvailableLocations().stream()) 
				.map(Location::getPincode).anyMatch(pin -> pin.equalsIgnoreCase(user.getPincode()));
	}

	// check availabiltiy based on pincode
	public boolean checkAvailabilty(String pincode) {

		List<Set<Location>> locList = sellerRepo.findAll().stream().map(i -> i.getAvailableLocations()).toList();

		return sellerRepo.findAll().stream().flatMap(i -> i.getAvailableLocations().stream())
				.anyMatch(loc -> loc.getPincode().equalsIgnoreCase(pincode));

	}

}
