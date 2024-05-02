package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.CartVO;
import com.itwillbs.domain.OrderInfoVO;

public interface MyPageDAO {

	/* 장바구니 */
	// 장바구니에 담긴 상품 수 세어오기
	public int selectCountCart(CartVO vo) throws Exception;
	
	// 장바구니 목록 가져오기
	public List<CartVO> selectCartList(CartVO vo) throws Exception;
	
	// 장바구니 상품 수량 변경
	public void updateProductCount(CartVO vo) throws Exception;
		
	// 장바구니 상품 선택 삭제
	public void deleteCartProduct(int cart_code) throws Exception;
	
	// 장바구니 비우기
	public void deleteCartAllProduct(CartVO vo) throws Exception;
	
	/* 주문내역 */
	// 주문내역 리스트
	public List<OrderInfoVO> selectUserOrderList(String user_id) throws Exception;
	
	// 주문내역 갯수
	public int selectCountOrder(String user_id) throws Exception;
	
	
	
	
	 
	
}
