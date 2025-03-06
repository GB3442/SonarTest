package com.tdit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Userdata {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer uid;
	String name;
	String pincode;

}
