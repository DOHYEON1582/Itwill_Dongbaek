package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.CartVO;
import com.itwillbs.domain.OrderInfoVO;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.domain.SearchCriteria;
import com.itwillbs.persistence.MyPageDAO;

@Service
public class MyPageServiceImpl implements MyPageService {
	
	private static final Logger logger = LoggerFactory.getLogger(MyPageServiceImpl.class);
	
	@Inject
	private MyPageDAO mdao;
	
	// 장바구니에 담긴 상품 수 세어오기
	@Override
	public int selectCountCart(CartVO vo) throws Exception {
		logger.debug(" === S : selectCountCart(CartVO vo) 실행 === ");
		return mdao.selectCountCart(vo);
	}

	// 장바구니 목록 가져오기
	@Override
	public List<CartVO> selectCartList(CartVO vo) throws Exception {
		logger.debug(" === S : selectCartList(CartVO vo) 실행 === ");
		return mdao.selectCartList(vo);
	}

	// 장바구니 상품 수량 변경
	@Override
	public void updateProductCount(CartVO vo) throws Exception {
		logger.debug(" === S : updateProductCount(int cart_code) 실행 === ");
		mdao.updateProductCount(vo);
	}
		
	// 장바구니 상품 삭제
	@Override
	public void deleteCartProduct(int cart_code) throws Exception {
		logger.debug(" === S : deleteCartProduct(int cart_code) 실행 === ");
		mdao.deleteCartProduct(cart_code);
	}
	
	// 장바구니 비우기
	@Override
	public void deleteCartAllProduct(CartVO vo) throws Exception {
		logger.debug(" === S : deleteCartAllProduct(CartVO vo) 실행 === ");
		mdao.deleteCartAllProduct(vo);
	}

	/* 주문내역 */
	// 주문내역 리스트
	@Override
	public List<OrderInfoVO> selectUserOrderList(SearchCriteria searchCri) throws Exception {
		logger.debug(" === S : selectUserOrderList) 실행 === ");
		return mdao.selectUserOrderList(searchCri);
	}

	// 주문내역 갯수
	@Override
	public int selectCountOrder(String user_id) throws Exception {
		logger.debug(" === S : selectCountOrder(String user_id) 실행 === ");
		return mdao.selectCountOrder(user_id);
	}

	// 주문 상세 내역
	@Override
	public OrderInfoVO selectOrderInfo(int order_code) throws Exception {
		logger.debug(" === S : selectOrderInfo(int order_code) 실행 === ");
		return mdao.selectOrderInfo(order_code);
	}

	// 주문 상품 정보
	@Override
	public List<CartVO> selectOrderProduct(int order_code) throws Exception {
		logger.debug(" === S : selectOrderProduct(int order_code) 실행 === ");
		return mdao.selectOrderProduct(order_code);
	}

	/* 리뷰 */
	// 리뷰 작성 할 상품 정보 불러오기
	@Override
	public CartVO selectReviewProduct(String productCode) throws Exception {
		logger.debug(" === S : selectReviewProduct(String productCode) 실행 === ");
		return mdao.selectReviewProduct(productCode);
	}

	// 리뷰작성
	@Override
	public void insertReview(ReviewVO vo) throws Exception {
		logger.debug(" === S : insertReview(ReviewVO vo) 실행 === ");
		mdao.insertReview(vo);
	}

	// 내가 쓴 리뷰
	@Override
	public List<ReviewVO> selectReview(String user_id) throws Exception {
		logger.debug(" === S : selectReview(String user_id) 실행 === ");
		return selectReview(user_id);
	}

	

}
