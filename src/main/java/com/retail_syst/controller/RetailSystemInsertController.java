package com.retail_syst.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail_syst.dao.ItemDetailsDao;
import com.retail_syst.service.ItemDetailsService;
import com.retail_syst.vo.RetailItems;

@RestController
@RequestMapping("/retail-home")
public class RetailSystemInsertController {


	@Autowired
	ItemDetailsService service;

	@Autowired
	ItemDetailsDao dao;
	
	@GetMapping("/welcome")
	public String welcome() {
		 String s="Its private page";
		 return s;
		
	}
	
	@GetMapping("/users")
	public String getUser() {
		return "{\"name\":\"alex\"}";
	}
	
	@PostMapping("/insertdetails")
	public ResponseEntity<?> insertItemDetails(@RequestBody RetailItems items){
		System.out.println("Items :: "+items);
		return ResponseEntity.ok(service.inserItemDetails(items));
	}
	
	@DeleteMapping("/removeitem/{Id}")
	public ResponseEntity<?> removeItemById(@PathVariable("Id") String Id){
		System.out.println("Enter Into removeById method :: "+Id);
		int id = Integer.parseInt(Id);
		if(service.removeItemById(id)) {
			return ResponseEntity.ok("Item Deleted from DataBase :: ");
		}
		else {
			return ResponseEntity.ok("Item not deleted ::");
		}
	}
		
	@GetMapping("/all-items")
	public ResponseEntity<List<RetailItems>> getAllItemByDesc(){
		System.out.println("Enter into getAllItem() :: ");
		List<RetailItems> allUser = service.getAllUser();
		System.out.println(allUser);
		return new ResponseEntity<List<RetailItems>>(allUser,HttpStatus.OK);
	}
	
	@PatchMapping("/revise-item")
	public ResponseEntity<RetailItems> updateItem(@RequestBody RetailItems item){
		System.out.println("Enter Into updateItem() ::"+item);
		RetailItems reviseItem = service.reviseItem(item);
		System.out.println("Revise Item :: "+ reviseItem);
		return ResponseEntity.ok(reviseItem);
	}
	
	@GetMapping("/obtain/{name}")
	public ResponseEntity<?> getItemDetailsByName(@PathVariable ("name") String name){
		name.toLowerCase();
		RetailItems itemDetailsByName = service.getItemDetailsByName(name);
		System.out.println("Item details by name :: "+ itemDetailsByName);
		return ResponseEntity.ok(itemDetailsByName);
	}
	
	@GetMapping("/obtains/{category}")
	public ResponseEntity<List<RetailItems>> getAllItemByCategory(@PathVariable("category") String category){
	
		category.toLowerCase();
		List<RetailItems> allItemByCategory = service.getAllItemByCategory(category);
		System.out.println("All item by category :: "+ allItemByCategory);
		return ResponseEntity.ok(allItemByCategory);
		
	}
}
