package com.retail_syst.model;

public class RetailItemCSV {

	
	private int Id;
	String name;
	String category;
	long unitRate;
	int qty;
	long unitTotalPrice;
	public RetailItemCSV() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RetailItemCSV(int id, String name, String category, long unitRate, int qty, long unitTotalPrice) {
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
		return "RetailItemCSV [Id=" + Id + ", name=" + name + ", category=" + category + ", unitRate=" + unitRate
				+ ", qty=" + qty + ", unitTotalPrice=" + unitTotalPrice + "]";
	}
	
	
	
}
