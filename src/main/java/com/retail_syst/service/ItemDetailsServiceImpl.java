package com.retail_syst.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail_syst.dao.ItemDetailsDao;
import com.retail_syst.vo.RetailItems;

@Service
public class ItemDetailsServiceImpl implements ItemDetailsService{

	@Autowired
	ItemDetailsDao dao;
	
	@Override
	public Object inserItemDetails(RetailItems items) {

		RetailItems newItem = new RetailItems();

		long unitPrice = items.getUnitRate() * items.getQty();

		newItem.setUnitTotalPrice(unitPrice);
		newItem.setName(items.getName().toLowerCase());
		newItem.setQty(items.getQty());
		newItem.setUnitRate(items.getUnitRate());
		newItem.setCategory(items.getCategory().toLowerCase());

		System.out.println("new Item after total value set ::" + newItem);
		return dao.save(newItem);
	}

	@Override
	public boolean removeItemById(Integer id) {
		try {
			dao.deleteById(id);
			return true;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<RetailItems> getAllUser() {
		
		return dao.getAllUser();
	}

	@Override
	public RetailItems reviseItem(RetailItems item) {
		RetailItems oldItems = dao.findById(item.getId()).get();
		
		System.out.println("Old Item :: "+ oldItems);
		
		return null;
	}

}
