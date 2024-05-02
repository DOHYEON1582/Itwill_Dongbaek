package com.itwillbs.domain;

import lombok.Data;

@Data
public class SearchCriteria extends Criteria{

	private String searchType;
	private String keyword;
	
	// 주문리스트
	private String states;
	private String startDate;
	private String endDate;
}
