package com.itwillbs.domain;

public class Criteria {
	private int page; // 페이지 번호
	private int pageSize; // 페이지 크기
	
	// -----------------검색------------------ 	
	private String keyword; // 검색어 키워드
	private String type; // 검색어 카테고리
	
	private String[] typeArr;	
	
	public String[] getTypeArr() {
		return typeArr;
	}

	public void setTypeArr(String[] typeArr) {
		this.typeArr = typeArr;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		this.typeArr = type.split("");
	}
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	// -----------------검색------------------ 	
	
	
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
