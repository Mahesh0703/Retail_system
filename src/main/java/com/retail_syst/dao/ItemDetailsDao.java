package com.retail_syst.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail_syst.service.RetailDetailsService;
import com.retail_syst.vo.RetailItems;

@Repository
public interface ItemDetailsDao extends CrudRepository<RetailItems, Integer> {

	@Query(value="select * from Doc_Retail_Item order by Item_Name Desc",nativeQuery = true)
	List<RetailItems> getAllUser();

	RetailItems getItemByName(String name);

	List<RetailItems> getAllItemByCategory(String category);

//	@Query(value="update Doc_Retail_Item set item_category=item ")
//	RetailItems reviseItem(RetailItems item);

	
}
