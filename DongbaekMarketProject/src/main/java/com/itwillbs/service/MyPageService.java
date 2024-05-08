package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.Cart2VO;

public interface MyPageService {

	/* 장바구니 */
	// 장바구니에 담긴 상품 수 세어오기
	public int selectCountCart(Cart2VO vo) throws Exception;
	
	// 장바구니 목록 가져오기
	public List<Cart2VO> selectCartList(Cart2VO vo) throws Exception;
	
	// 장바구니 상품 수량 변경
	public void updateProductCount(Cart2VO vo) throws Exception;
		
	// 장바구니 상품 선택 삭제
	public void deleteCartProduct(int cart_code) throws Exception;
	
	// 장바구니 비우기
	public void deleteCartAllProduct(Cart2VO vo) throws Exception;
	
	
	
}
