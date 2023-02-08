package com.retail_syst;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.retail_syst.dao.ItemDetailsDao;
import com.retail_syst.service.ItemDetailsService;
import com.retail_syst.vo.RetailItems;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RetailSystemTestCases {

	@MockBean
	private ItemDetailsDao dao;
	
	@Autowired
	private ItemDetailsService service;
	
	@Test
	public void getItemDetailByName() {
		
		String name="cake";
		
		RetailItems item=new RetailItems(10,"cake","food",150,10,(150*10));
		Mockito.when(dao.getItemByName(name)).thenReturn(item);
		
		assertEquals(item, service.getItemDetailsByName(name));
	}
	
	@Test
	public void getCostlyItemByCategory() {
		
		String category="toy";
		ArrayList<RetailItems> itemList=new ArrayList<>();
		itemList.add(new RetailItems(10,"milk","beverage",20,15,(20*25)));
		itemList.add(new RetailItems(20,"pizza","food",10,20,(10*20)));
		itemList.add(new RetailItems(30,"bat","sport",200,5,(200*5)));
		itemList.add(new RetailItems(40,"doll","toy",250,15,(250*15)));
		itemList.add(new RetailItems(50,"ballon","toy",300,10,(300*10)));
		itemList.add(new RetailItems(60,"car","toy",500,5,(500*5)));
		
		Optional<RetailItems> max = itemList.stream().filter(s -> s.getCategory().equalsIgnoreCase(category))
				.max(Comparator.comparingLong(RetailItems::getUnitRate));
		
		String newItem="Id :-"+max.get().getId()+", Name:- "+max.get().getName()+", Category:- "+max.get().getCategory()+", QTy:- "+max.get().getQty()+", Unit Rate:- "+max.get().getUnitRate();
		
		Mockito.when(dao.costlyItemByCategory(category)).thenReturn(newItem);
		
		assertEquals(newItem, service.costliestItemByCategory(category));
		
	}
}
