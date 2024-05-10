package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.CartVO;
import com.itwillbs.domain.OrderInfoVO;

public interface OrderService {

	// 장바구니 목록 가져오기 (전체주문 할 때 사용)
	public List<CartVO> selectCartList(CartVO vo) throws Exception;
	
	// 장바구니 상품 수 세어오기
	public int selectCountCart(CartVO vo) throws Exception; 
		
	// 주문 할 상품 정보 가져오기 (선택 주문, 개별 주문 할 때 사용)
	public CartVO selectProductInfo(int cart_code) throws Exception;
	
	// 적립금 가져오기
	public String selectUserPoint(String user_id) throws Exception;
	
	// 주문 번호 최대값 가져오기
	public int selectMaxOrderCode() throws Exception;

	// 주문 정보 입력
	public void insertOrderInfo(OrderInfoVO vo) throws Exception;
}