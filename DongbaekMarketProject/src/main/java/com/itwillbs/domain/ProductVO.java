package com.itwillbs.domain;

import lombok.Data;

@Data
public class ProductVO {
	private String product_code;
	private String store_code;
	private String price;
	private String product_name;
	private int count;
	private int viewcnt;
	private String img;
	private String product_explain;
	private String seller_id;
	private String country;
	private int max_account;
	private String category;
	private String sub_product;
	private String unit;
}
