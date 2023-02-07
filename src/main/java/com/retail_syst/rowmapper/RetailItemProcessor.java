package com.retail_syst.rowmapper;

import org.springframework.batch.item.ItemProcessor;

import com.retail_syst.model.RetailItemCSV;
import com.retail_syst.vo.RetailItems;

public class RetailItemProcessor implements ItemProcessor<RetailItems, RetailItems> {

	@Override
	public RetailItems process(RetailItems item) throws Exception {
		
		return item;
	}


}
