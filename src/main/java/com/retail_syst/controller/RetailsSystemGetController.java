package com.retail_syst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.retail_syst.service.ItemDetailsService;
import com.retail_syst.vo.RetailItems;


public class RetailsSystemGetController {

	@Autowired
	ItemDetailsService service;
	
//	@GetMapping("/item/{name}")
//	public ResponseEntity<?> getItemDetailsByName(@PathVariable ("name") String name){
//		RetailItems itemDetailsByName = service.getItemDetailsByName(name);
//
//		return ResponseEntity.ok(itemDetailsByName);
//	}
	
}
