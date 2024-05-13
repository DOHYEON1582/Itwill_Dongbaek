package com.itwillbs.domain;

import lombok.Data;

@Data
public class SearchCriteria extends MyPageCriteria{


	private String searchType;
	private String keyword;
}
