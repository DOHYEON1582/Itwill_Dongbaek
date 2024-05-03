package com.itwillbs.domain;

public class Criteria {
	private int page; // 페이지 번호
	private int pageSize; // 페이지 크기
	
	public Criteria() {
		this.page = 1;
		this.pageSize = 10;
	}

	public void setPage(int page) {
		if(page <= 0) { // 페이지 번호가 음수일 경우
			this.page = 1;
			return;
		}	
		this.page = page;
	}
	public void setPageSize(int pageSize) {
		if(pageSize <= 0 || pageSize > 100) { // 페이지 크기가 이상한 경우 
			this.pageSize = 5;
			return;
		}
		this.pageSize = pageSize;
	}
	
	public int getStartPage() {
		return (this.page - 1)*pageSize;
	}
	
	
	public int getPage() {
		return page;
	}
	public int getPageSize() {
		return pageSize;
	}

	
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", pageSize=" + pageSize + "]";
	}


}
