package com.itwillbs.domain;

import java.util.List;

import lombok.Data;

@Data
public class SubscribeProductVO {
	
	private int product_code;
	private String user_id;
	private int count;
	
	private List<ProductVO> productVO;
}
