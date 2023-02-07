package com.retail_syst.service;

import java.util.List;

import com.retail_syst.vo.RetailItems;

public interface ItemDetailsService {

	Object inserItemDetails(RetailItems items);

	boolean removeItemById(Integer id);

	List<RetailItems> getAllUser();

	RetailItems reviseItem(RetailItems item);

	RetailItems getItemDetailsByName(String name);

	List<RetailItems> getAllItemByCategory(String category);
	
}
