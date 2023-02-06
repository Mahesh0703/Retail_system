package com.retail_syst.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "Doc_Retail_Item")
public class RetailItems {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="Item_Id")
	int Id;
	
	@Column(name ="Item_Name")
	String name;
	
	@Column(name ="Item_Category")
	String category;
	
	@Column(name ="Item_UnitRate")
	long unitRate;
	
	@Column(name ="Item_UnitQty")
	int qty;
	
	@Column(name ="Item_TotalPrice")
	long unitTotalPrice;

	public RetailItems() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RetailItems(int id, String name, String category, long unitRate, int qty, long unitTotalPrice) {
		super();
		Id = id;
		this.name = name;
		this.category = category;
		this.unitRate = unitRate;
		this.qty = qty;
		this.unitTotalPrice = unitTotalPrice;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getUnitRate() {
		return unitRate;
	}

	public void setUnitRate(long unitRate) {
		this.unitRate = unitRate;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public long getUnitTotalPrice() {
		return unitTotalPrice;
	}

	public void setUnitTotalPrice(long unitTotalPrice) {
		this.unitTotalPrice = unitTotalPrice;
	}

	@Override
	public String toString() {
		return "RetailItems [Id=" + Id + ", name=" + name + ", category=" + category + ", unitRate=" + unitRate
				+ ", qty=" + qty + ", unitTotalPrice=" + unitTotalPrice + "]";
	}

	
	
	
}
