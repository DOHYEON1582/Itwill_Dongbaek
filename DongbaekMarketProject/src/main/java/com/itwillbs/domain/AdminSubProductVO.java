package com.itwillbs.domain;

import java.util.List;

import lombok.Data;

@Data
public class AdminSubProductVO {
	
	private int product_code;
	private String user_id;
	private int count;
	
	private List<AdminProductVO> AdminProductVO;
}
