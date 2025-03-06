package com.tdit.SellerRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tdit.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
