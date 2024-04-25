package com.itwillbs.domain;

import lombok.Data;

@Data
public class AdminStoreVO {
	
	private int store_code;
	private int market_code;
	private String seller_id;
	private String store_name;
	private String store_value;
	private String store_explain;
	private String img1;
	private String img2;
	private String img3;
	private String phone;
	private String store_addr1;
	private String store_addr2;
	private String min_price;
	private String time;
	private String status;
	private String seller_name;
	private String store_number;
	
	private AdminSellerVO AdminSellerVO;
}
