package com.itwillbs.domain;

import java.util.List;

import lombok.Data;

@Data
public class MarkVO {
	private int mark_code;
	private int store_code;
	private String user_id;
	
	private List<StoreVO> storeVO;
}
