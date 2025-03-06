package com.tdit.controller;

import java.util.List;
import java.util.Optional;

import com.tdit.ExceptionHandeling.SellerNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tdit.ExceptionHandeling.ResourceNotFoundException;
import com.tdit.SellerRepository.SellerRepo;
import com.tdit.model.Seller;

@RestController
@RequestMapping("/seller")
@Slf4j
public class SellerController {
	@Autowired
	private SellerRepo sellerrepo;

	@PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addSeller(@Valid @RequestBody Seller seller) {
		try {
			if(seller ==null){
				log.warn("Make sure seller can not be null");
				throw new RuntimeException("Selller must have values..");
			}
			Seller savedSeller = sellerrepo.save(seller);
			if(savedSeller!=null){
				log.info("Seller is saved successfully in DB");
			}
			return new ResponseEntity<>(savedSeller, HttpStatus.CREATED); // Return saved entity
		} catch (Exception e) {
			return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Seller> getSellerById(@PathVariable("id") Integer id) {
		Optional<Seller> opt = Optional.of(
				sellerrepo.findById(id).orElseThrow(() -> {
					log.error("Seller not found for ID: {}", id);
					return new SellerNotFoundException("Seller is not available for given ID");
				})
		);
		Seller seller = opt.get();
        return new ResponseEntity<Seller>(seller, HttpStatus.ACCEPTED);
	}

	@GetMapping("/")
	public ResponseEntity<List<Seller>> getAllSellers(){
		return new ResponseEntity<>(sellerrepo.findAll(),HttpStatus.OK);
	}
}
