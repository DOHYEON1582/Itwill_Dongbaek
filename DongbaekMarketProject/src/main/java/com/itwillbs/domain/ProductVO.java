package com.itwillbs.domain;

import java.util.List;
import lombok.Data;

@Data
public class ProductVO {

	private int product_code;
	private int store_code;
	private String price;
	private String product_name;
	private int viewcnt;
	private String img1;
	private String img2;
	private String img3;
	private String product_explain;
	private String seller_id;
	private String country;
	private int max_account;
	private String category;
	private String sub_product;
	private String unit;
	
	private List<StoreVO> storeVO;
}
