package com.itwillbs.domain;

public class ProductCri {
	
	private int page;
	private int pageSize;
	private int store_code;
	
	// 페이지정보를 인덱스로 변경하는 메서드
	public int getStartPage() {
		return (this.page - 1) * pageSize;
	}
		
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		if(page <= 0) { //페이지번호가 음수일때
			this.page = 1;
			return;
		}
		this.page = page;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		if(pageSize <= 0 || pageSize > 100) { //페이지 크기가 이상한경우
			this.pageSize = 9;
			return;
		}
		this.pageSize = pageSize;
	}
	
	public ProductCri() {
		this.page=1;
		this.pageSize=9;
	}

	public int getStore_code() {
		return store_code;
	}

	public void setStore_code(int store_code) {
		this.store_code = store_code;
	}

	@Override
	public String toString() {
		return "ProductCri [page=" + page + ", pageSize=" + pageSize + ", store_code=" + store_code + "]";
	}

	
	
}
