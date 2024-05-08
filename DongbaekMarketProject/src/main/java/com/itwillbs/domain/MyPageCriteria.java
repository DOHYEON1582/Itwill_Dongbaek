package com.itwillbs.domain;

import lombok.Data;

@Data
public class MyPageCriteria {
	
	private int page; //현재페이지
	private int pageStart;	//limit 첫 번째 변수 역할
	private int perPageNum; //한 페이지당 출력 게시물 수 
	
	public MyPageCriteria() { //첫페이지 실행시 적용될 값들 (기본 생성자)
		this.page = 1; //첫 페이지 초기화
		this.perPageNum = 30; //한 페이지당 출력 할 게시물 수
	}
	
	//sql 구문에서 limit 시작 값,? ( 추가메서드 )
	public int getPageStart() {
		return (this.page - 1) * this.perPageNum;
	}
}
