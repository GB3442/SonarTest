package com.tdit;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LocationAvailabilityServiceApplication {
   	public static void main(String[] args) {
		SpringApplication.run(LocationAvailabilityServiceApplication.class, args);
	}

}
