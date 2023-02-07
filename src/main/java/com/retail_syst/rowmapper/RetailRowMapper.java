package com.retail_syst.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.retail_syst.model.RetailItemCSV;
import com.retail_syst.vo.RetailItems;

public class RetailRowMapper implements RowMapper<RetailItems> {

	@Override
	public RetailItems mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		RetailItems retailItem =new RetailItems();
		retailItem.setId(rs.getInt("item_id"));
		retailItem.setName(rs.getString("item_name"));
		retailItem.setCategory(rs.getString("item_category"));
		retailItem.setQty(rs.getInt("item_unit_qty"));
		retailItem.setUnitRate(rs.getLong("item_unit_rate"));
		retailItem.setUnitTotalPrice(rs.getLong("item_total_price"));
		return retailItem;
	}

}
