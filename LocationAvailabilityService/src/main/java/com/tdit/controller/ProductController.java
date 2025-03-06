package com.tdit.controller;

import com.tdit.ExceptionHandeling.ProductNotFoundException;
import com.tdit.ExceptionHandeling.SellerNotFoundException;
import com.tdit.SellerRepository.SellerRepo;
import com.tdit.model.ProductRequestDTO;
import com.tdit.model.Seller;
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

import com.tdit.ExceptionHandeling.ProductNotSavedException;
import com.tdit.ExceptionHandeling.ResourceNotFoundException;
import com.tdit.SellerRepository.ProductRepo;
import com.tdit.model.Product;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductRepo repo;

	@Autowired
	private SellerRepo srepo;

	@PostMapping("/")
	public ResponseEntity<Product> addProduct(@RequestBody ProductRequestDTO product) {
		Seller seller = srepo.findById(product.getSellerId())
				.orElseThrow(() -> new SellerNotFoundException("Seller not found"));

		Product product1 = new Product();
		product1.setName(product.getName());
		product1.setDescription(product.getDescription());
		product1.setSeller(seller);

		return new ResponseEntity<>(repo.save(product1),HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(@PathVariable("id") Integer id) {
		Product product = repo.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product is not found of given id"));
		return ResponseEntity.ok(product);
	}
}
