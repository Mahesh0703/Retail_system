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
		
		items.setUnitTotalPrice(items.getUnitRate() * items.getQty());

		System.out.println("new Item after total value set ::" + items);
		return dao.save(items);
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
	public List<RetailItems> getAllItems() {
		
		return dao.getAllItems();
	}

	@Override
	public RetailItems reviseItemById(RetailItems item , int id) {
		
		 Optional<RetailItems> oldItem = dao.findById(id);
		if(oldItem.get() == null && oldItem.isEmpty()) {
			return null;
		} else {
			RetailItems newItem = oldItem.get();

			//Setting updated name
			if (item.getName() != null && !item.getName().isEmpty())
				newItem.setName(item.getName());
			else
				newItem.setName(oldItem.get().getName());

			//Setting updated category
			if (item.getCategory() != null && !item.getCategory().isEmpty())
				newItem.setCategory(item.getCategory());
			else
				newItem.setCategory(oldItem.get().getCategory());

			//Setting updated Qty
			if (item.getQty() != 0)
				newItem.setQty(item.getQty());
			else
				newItem.setQty(oldItem.get().getQty());

			//Setting unit Rate
			if (item.getUnitRate() != 0)
				newItem.setUnitRate(item.getUnitRate());
			else
				newItem.setUnitRate(oldItem.get().getUnitRate());

			//Setting updated total price
			if (item.getUnitRate() != 0 && item.getQty() != 0)
				newItem.setUnitTotalPrice(item.getUnitRate() * item.getQty());
			else if (item.getQty() != 0 && item.getUnitRate() == 0)
				newItem.setUnitTotalPrice(item.getQty() * oldItem.get().getUnitRate());
			else if (item.getUnitRate() != 0 && item.getQty() == 0)
				newItem.setUnitTotalPrice(item.getUnitRate() * oldItem.get().getQty());
			else
				newItem.setUnitTotalPrice(oldItem.get().getUnitTotalPrice());

			return dao.save(newItem);
		}
		
	}

	public RetailItems getItemDetailsByName(String name) {

		RetailItems itemByName = dao.getItemByName(name);
		System.out.println(itemByName);
		return itemByName;

	}

	@Override
	public Long getItemCountByCategory(String category) {
		Long itemCount=dao.countByCategory(category);
		System.out.println(itemCount);
		return itemCount;
	}

	@Override
	public String getSumOfPricesByCategory(String category) {
		String sumOfItem=dao.sumOfPricesByCategory(category);
		return sumOfItem;
	}
	
	public String costliestItemByCategory(String category) {
		String costlyItem=dao.costlyItemByCategory(category);
		System.out.println("Costly Item in :: "+category +" is "+costlyItem);
		return costlyItem;
	}

	@Override
	public List<RetailItems> getAllItemsByCategory(String category) {
		List<RetailItems> allItems=dao.getItemByCategory(category);
		return allItems;
	}


}
