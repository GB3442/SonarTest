package com.tdit.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tdit.ClientApp.ClientApp;
import com.tdit.service.LocationAvailabilityService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/Serviceability")
@Slf4j
public class LocationController {
	@Autowired
	private LocationAvailabilityService locationAvailability;
	@Autowired
	private ClientApp clientapp;

	private ResponseEntity<String> messagEntity;

	@GetMapping("/isServiceAvailable/{uid}/{pid}")
	public ResponseEntity<String> checkProductAvailability(@PathVariable("uid") Integer userId,
			@PathVariable("pid") Integer productId) throws Exception {
		String response = clientapp.processRequest(userId, productId);
		log.info("incoming response is {}",response);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/isServiceAvailable")
	public ResponseEntity<String> checkProductAvailability(@RequestParam("pin") Integer pin) {
		log.debug("checkProductAvailability Method is called for checking location using pincode");
		try {
			String response = clientapp.processRequest(String.valueOf(pin));
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			log.error("Error message is {}",e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
		}
	}

}
