package com.retail_syst.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.retail_syst.vo.RetailItems;

@Component
public class PaginationResponse {

	private List<RetailItems> item;
	
	private int pageNo;
	
	private int elementsPerPage;
	
	private long totalElements;
	
	private int totalPages;
	
	private boolean last;

	public PaginationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaginationResponse(List<RetailItems> item, int pageNo, int elementsPerPage, long totalElements,
			int totalPages, boolean last) {
		super();
		this.item = item;
		this.pageNo = pageNo;
		this.elementsPerPage = elementsPerPage;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.last = last;
	}

	public List<RetailItems> getItem() {
		return item;
	}

	public void setItem(List<RetailItems> item) {
		this.item = item;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getElementsPerPage() {
		return elementsPerPage;
	}

	public void setElementsPerPage(int elementsPerPage) {
		this.elementsPerPage = elementsPerPage;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}
	
	
}
