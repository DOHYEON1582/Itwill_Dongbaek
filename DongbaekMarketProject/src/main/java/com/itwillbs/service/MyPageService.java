package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.CartVO;
import com.itwillbs.domain.OrderInfoVO;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.domain.SearchCriteria;

public interface MyPageService {

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
	public List<OrderInfoVO> selectUserOrderList(SearchCriteria searchCri) throws Exception;
	
	// 주문내역 갯수
	public int selectCountOrder(String user_id) throws Exception;
	
	// 주문 상세 내역
	public OrderInfoVO selectOrderInfo(int order_code) throws Exception;
	
	// 주문 상품 정보
	public List<CartVO> selectOrderProduct(int order_code) throws Exception;
	
	/* 리뷰 */
	// 리뷰 작성 할 상품 정보 불러오기
	public CartVO selectReviewProduct(String productCode) throws Exception;
	
	// 리뷰작성
	public void insertReview(ReviewVO vo) throws Exception;
	
	// 내가 쓴 리뷰
	public List<ReviewVO> selectReview(String user_id) throws Exception;
}
