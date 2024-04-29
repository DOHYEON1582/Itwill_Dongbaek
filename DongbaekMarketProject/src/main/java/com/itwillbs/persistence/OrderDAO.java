package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.CartVO;

public interface OrderDAO {
	
	// 장바구니 목록 가져오기
	public List<CartVO> selectCartList(CartVO vo) throws Exception;
		
	// 주문 할 상품 정보 가져오기
	public CartVO selectProductInfo(int cart_code) throws Exception;
	
	// 적립금 가져오기
	public String selectUserPoint(String user_id) throws Exception;
	
}
