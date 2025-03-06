package com.tdit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdit.SellerRepository.UserRepo;
import com.tdit.model.Userdata;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepo repo;

	@PostMapping("/")
	public ResponseEntity<Userdata> registerUser(@RequestBody Userdata user) {
		return new ResponseEntity<Userdata>(repo.save(user), HttpStatus.CREATED);
	}
}
