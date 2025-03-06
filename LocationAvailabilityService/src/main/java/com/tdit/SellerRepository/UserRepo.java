package com.tdit.SellerRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tdit.model.Userdata;

@Repository
public interface UserRepo extends JpaRepository<Userdata, Integer> {

}
