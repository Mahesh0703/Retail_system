package com.retail_syst.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.retail_syst.config.CsvFileGenerator;
import com.retail_syst.dao.ItemDetailsDao;
import com.retail_syst.model.PageSetting;
import com.retail_syst.model.PaginationResponse;
import com.retail_syst.service.ItemDetailsService;
import com.retail_syst.vo.RetailItems;


@RestController
@RequestMapping("/retail-home")
public class RetailSystemApi {

	private ItemDetailsService service;	
	
	private ItemDetailsDao dao;
	
	private CsvFileGenerator csvFileGenerator; 
	
	private PageSetting pagesetting;
	
	@Autowired
	
	public RetailSystemApi(ItemDetailsService service, ItemDetailsDao dao, CsvFileGenerator csvFileGenerator,
			PageSetting pagesetting) {
		super();
		this.service = service;
		this.dao = dao;
		this.csvFileGenerator = csvFileGenerator;
		this.pagesetting = pagesetting;
	}

	@GetMapping("/welcome")
	public String welcome() {
		 String s="Its private page";
		 return s;
		
	}
	
	@PostMapping("/insertdetails")
	public ResponseEntity<?> insertItemDetails(@RequestBody RetailItems items){
		System.out.println("Items :: "+items);
		return ResponseEntity.ok(service.inserItemDetails(items));
	}
	
	@PutMapping("/revise-item/{Id}")
	public ResponseEntity<RetailItems> reviseItemById(@RequestBody RetailItems item , @PathVariable("Id") String Id){
		System.out.println("Enter into reviseItem() :: "+item+" And Id :: "+Id);
		int id=Integer.parseInt(Id);
		RetailItems updatedItem= service.reviseItemById(item , id);
		System.out.println("updated item details :: "+updatedItem);
		return ResponseEntity.ok(updatedItem);
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
		List<RetailItems> allUser = service.getAllItemsByDesc();
		System.out.println(allUser);
		return new ResponseEntity<List<RetailItems>>(allUser,HttpStatus.OK);
	}
		
	@GetMapping("/obtain/{name}")
	public ResponseEntity<?> getItemDetailsByName(@PathVariable ("name") String name){
		name.toLowerCase();
		RetailItems itemDetailsByName = service.getItemDetailsByName(name);
		System.out.println("Item details by name :: "+ itemDetailsByName);
		return ResponseEntity.ok(itemDetailsByName);
	}
	
	@GetMapping("/item-count")
	public ResponseEntity<?> getItemCountByCategory(@RequestParam String category){
		category.toLowerCase();
		Long itemCount = service.getItemCountByCategory(category);
		return ResponseEntity.ok(itemCount);
	}
	
	@GetMapping("/item-sums")
	public ResponseEntity<?> getSumOfPricesByCategory(@RequestParam String category){
		category.toLowerCase();
		String sumOfItem= service.getSumOfPricesByCategory(category);
		System.out.println("sum of category "+category +"is :: "+sumOfItem);
		return ResponseEntity.ok(sumOfItem);
	}
	
	@GetMapping("/item-costly")
	public ResponseEntity<?> getCostlyItemByCategory(@RequestParam String category){
		category.toLowerCase();
		String costlyItem=service.costliestItemByCategory(category);
		System.out.println("Costly Item :: "+costlyItem);
		return ResponseEntity.ok(costlyItem);
	}
	
	@GetMapping("/obtains-all/{category}")
	public ResponseEntity<List<RetailItems>> getAllItemDetailsByCategory(@PathVariable("category") String category){
		category.toLowerCase();
		List<RetailItems> allItems=service.getAllItemsByCategory(category);
		System.out.println("All item Details :: "+allItems);
		return ResponseEntity.ok(allItems);
	}
	
	@GetMapping("/export-to-csv")
	public void exportIntoCsv(HttpServletResponse response ) throws IOException{
		response.setContentType("text/csv");
	    response.addHeader("Content-Disposition", "attachment; filename=\"retail.csv\"");
	    csvFileGenerator.writeRetailDetailsToCsv(service.getAllItems(), response.getWriter());
	}
	
	@GetMapping("/item-hateoas/{Id}")
	public RetailItems getItemDetailsWithLink(@PathVariable int Id) {
		RetailItems item = service.getItemDetailsById(Id);
		item.add(linkTo(methodOn(RetailSystemApi.class).getItemDetailsWithLink(item.getId())).withSelfRel());
		item.add(linkTo(methodOn(RetailSystemApi.class).getItemDetailsByName(item.getName())).withRel("ItemDetailByName"));
		item.add(linkTo(methodOn(RetailSystemApi.class).getAllItemByDesc()).withRel("getAllItems"));
		
		return item;
	}
	
	@GetMapping("/item-pagi/{page}/{pagesize}")
	public PaginationResponse getItemPagination(@PathVariable int page,
										@PathVariable int pagesize ) {
		pagesetting.setPage(page);
		pagesetting.setPageSize(pagesize);
		
		return service.getAllItemsPagination(pagesetting);
	}
	
}
