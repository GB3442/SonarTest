package com.tdit.ClientApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tdit.ExceptionHandeling.ProductNotFoundException;
import com.tdit.ExceptionHandeling.UserNotFoundException;
import com.tdit.SellerRepository.ProductRepo;
import com.tdit.SellerRepository.SellerRepo;
import com.tdit.SellerRepository.UserRepo;
import com.tdit.model.Product;
import com.tdit.model.Seller;
import com.tdit.model.Userdata;
import com.tdit.service.LocationAvailabilityService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@EnableScheduling
public class ClientApp {
	@Autowired
	private LocationAvailabilityService service;

	@Autowired
	private UserRepo uRepo;
	@Autowired
	private SellerRepo sRepo;
	@Autowired
	private ProductRepo pRepo;

	public static final String SELLERURL = "";
	public static final String USERURL = "";
	public static final String PRODURL = "";
	String messageString;
	Seller seller = null;
	Userdata user = null;
	Product product = null;

	@Cacheable(value = "serviceAvailability",  key = "#uid + '-' + #pid")
	public String processRequest(Integer uid, Integer pid) throws Exception {
		log.debug("Process request is executed..");

		Userdata user=uRepo.findById(uid).orElseThrow(()->new UserNotFoundException("User not available for given id"));
		Product product=pRepo.findById(pid).orElseThrow(()->new ProductNotFoundException("Product is not available for given id"));
		
		boolean status = service.checkAvailabilty(user, product);
		log.info("incoming delivery status is {}",status);
		if (status) {
			messageString = "Location is Available for Delivery..";
			log.info(messageString);
		} else {
			messageString = "Product can't be delivered to this Location..";
			log.info(messageString);
		}
		return messageString;
	}

	@Cacheable(value = "serviceAvailability2", key = "#root.args[0]")
	public String processRequest(String pincode) throws Exception {
		boolean status = service.checkAvailabilty(pincode);
		log.info("incoming delivery status is {}",status);
		if (status) {
			messageString = "Location is Available for Delivery..";
			log.info(messageString);
		} else {
			messageString = "Product can't be delivered to this Location..";
			log.info(messageString);
		}
		return messageString;
	}

	@CacheEvict(value = "serviceAvailability", allEntries = true)
	@Scheduled(cron = " 0 30 * * * *")   // it will call this method every 30 min evey hour
	public void clearCache() {
		System.out.println("Clearing cache...");
		log.info("Cache is cleared..");
	}

}
