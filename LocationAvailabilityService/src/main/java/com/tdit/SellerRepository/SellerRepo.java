package com.tdit.SellerRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tdit.model.Seller;

@Repository
public interface SellerRepo extends JpaRepository<Seller, Integer> {

}
