package com.itwillbs.domain;

import java.util.List;

import lombok.Data;


@Data
public class AdminCartVO {
	
	private int cart_code;
	private String user_id;
	private int product_code;
	private String bundle_code;
	private int count;
	private int price;
	private int delivery_fee;
	private String states;
	private String refund_reason;
	
	private List<AdminProductVO> AdminProductVO;

}
