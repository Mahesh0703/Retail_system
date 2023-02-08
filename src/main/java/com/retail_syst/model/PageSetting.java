package com.retail_syst.model;

import org.springframework.stereotype.Component;

@Component
public class PageSetting {

	private int page=0;
	
	private int pageSize=5;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
