package com.itwillbs.domain;

import lombok.Data;

@Data
public class SearchCriteria extends MyPageCriteria{

	private String searchType;
	private String keyword;
	
	// 주문리스트
	private String user_id;
	
	private String states;
	private String startDate;
	private String endDate;
	
}
