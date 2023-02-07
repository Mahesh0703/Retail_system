package com.retail_syst.config;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import com.retail_syst.vo.RetailItems;

@Component
public class CsvFileGenerator {

	public void writeRetailDetailsToCsv(List<RetailItems> retails, Writer writer) {
		
		try {
			CSVPrinter printer= new CSVPrinter(writer, CSVFormat.DEFAULT);
			for(RetailItems retail: retails) {
				printer.printRecord(retail.getId(), retail.getName(), retail.getCategory(), retail.getQty(), retail.getUnitRate(),
						retail.getUnitTotalPrice());
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
