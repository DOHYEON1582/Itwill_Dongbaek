package com.itwillbs.domain;

import lombok.Data;

@Data
public class MarketVO {
	private int market_code;
	private String category_code;
	private String name;
	private String phone;
	private String build;
	private String traffic;
	private String explain;
	private String market_addr1;
	private String market_addr2;
	private String img1;
	private String img2;
	private String img3;
}
