package com.itwillbs.domain;

import lombok.Data;

@Data
public class StoreVO {
	private String store_code;
	private String market_code;
	private String seller_id;
	private String store_name;
	private String store_value;
	private String store_explain;
	private String img;
	private String phone;
	private String store_addr1;
	private String store_addr2;
	private String min_price;
	private String time;
	private String status;

}
