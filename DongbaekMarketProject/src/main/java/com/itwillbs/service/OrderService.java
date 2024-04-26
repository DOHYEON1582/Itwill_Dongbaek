package com.itwillbs.service;

import com.itwillbs.domain.CartVO;

public interface OrderService {

	// 주문 할 상품 정보 가져오기
	public CartVO selectProductInfo(int cart_code) throws Exception;
	
	// 적립금 가져오기
	public String selectUserPoint(String user_id) throws Exception;

}
