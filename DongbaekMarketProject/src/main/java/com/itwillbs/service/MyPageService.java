package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.CartVO;

public interface MyPageService {

	// 장바구니에 담긴 상품 수 세어오기
	public int getCartNum(CartVO vo) throws Exception;
	
	// 장바구니 목록 가져오기
	public List<CartVO> getCartList(CartVO vo) throws Exception;
	
	// 장바구니 상품 수량 변경
	public void modifyProductCount(int cart_code) throws Exception;
		
	// 장바구니 상품 삭제
	public void removeCartProduct(int cart_code) throws Exception;
	
	
	
}
