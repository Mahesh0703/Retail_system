package com.retail_syst.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.retail_syst.service.RetailDetailsService;
import com.retail_syst.vo.RetailItems;

@Repository
public interface ItemDetailsDao extends CrudRepository<RetailItems, Integer> {

	@Query(value="select * from Doc_Retail_Item order by Item_Name Desc",nativeQuery = true)
	List<RetailItems> getAllItems();

	RetailItems getItemByName(String name);

	@Query(value="select count(*) from Doc_Retail_Item where Item_Category = :category" , nativeQuery = true)
	Long countByCategory(@Param ("category") String category);

	@Query(value="select Item_Category , sum(Item_Total_Price) from Doc_Retail_Item where Item_Category= :category" , nativeQuery = true)
	String sumOfPricesByCategory(@Param ("category") String category);
	
	@Query(value="select Item_Category, max(Item_Unit_Rate) from Doc_Retail_Item where Item_Category= :category", nativeQuery = true)
	String costlyItemByCategory(@Param ("category") String category);

	@Query(value="select * from Doc_Retail_Item where Item_Category= :category", nativeQuery = true)
	List<RetailItems> getItemByCategory(@Param("category") String category);
	
}
