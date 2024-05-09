package com.itwillbs.domain;

import java.util.List;

import lombok.Data;

@Data
public class Subscrbe_productVO {
	private int product_code;
	private String user_id;
	private int count;
	
	private List<ProductVO> productVO;
}
