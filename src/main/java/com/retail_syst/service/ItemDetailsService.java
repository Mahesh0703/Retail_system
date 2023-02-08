package com.retail_syst.service;

import java.util.List;

import com.retail_syst.model.PageSetting;
import com.retail_syst.model.PaginationResponse;
import com.retail_syst.vo.RetailItems;

public interface ItemDetailsService {

	Object inserItemDetails(RetailItems items);

	boolean removeItemById(Integer id);

	List<RetailItems> getAllItemsByDesc();

	RetailItems getItemDetailsByName(String name);

	Long getItemCountByCategory(String category);

	String getSumOfPricesByCategory(String category);
	
	String costliestItemByCategory(String category);

	List<RetailItems> getAllItemsByCategory(String category);

	RetailItems reviseItemById(RetailItems item, int id);

	RetailItems getItemDetailsById(int id);

	List<RetailItems> getAllItems();
	
	PaginationResponse getAllItemsPagination(PageSetting page);

	
}
