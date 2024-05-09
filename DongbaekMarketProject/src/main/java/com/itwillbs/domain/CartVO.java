package com.itwillbs.domain;

import lombok.Data;

@Data
public class CartVO {
	private int cart_code;
	private String user_id;
	private int product_code;
	private String bundle_code;
	private int count;
	private int price;
	private String delivery_fee;
	private String refund_reason;
}
