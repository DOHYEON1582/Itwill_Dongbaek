package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.CartVO;
import com.itwillbs.domain.OrderInfoVO;
import com.itwillbs.persistence.OrderDAO;

@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Inject
	private OrderDAO odao;
	
	// 장바구니 목록 가져오기
	@Override
	public List<CartVO> selectCartList(CartVO vo) throws Exception {
		logger.debug(" === S : selectCartList(CartVO vo) 실행 === ");
		return odao.selectCartList(vo);
	}
	
	// 장바구니 상품 수 세어오기
	@Override
	public int selectCountCart(CartVO vo) throws Exception {
		logger.debug(" === S : selectCountCart(CartVO vo) 실행 === ");
		return odao.selectCountCart(vo);
	}
	
	// 주문 할 상품 정보 가져오기
	@Override
	public CartVO selectProductInfo(int cart_code) throws Exception {
		logger.debug(" === S : selectProductInfo(int cart_code) 실행 === ");
		return odao.selectProductInfo(cart_code);
	}

	// 적립금 가져오기
	@Override
	public String selectUserPoint(String user_id) throws Exception {
		logger.debug(" === S : selectUserPoint(String user_id) 실행 === ");
		return odao.selectUserPoint(user_id);
	}

	// 주문 번호 최대값 가져오기
	@Override
	public int selectMaxOrderCode() throws Exception {
		logger.debug(" === S : selectMaxOrderCode() 실행 === ");
		return odao.selectMaxOrderCode();
	}

	// 주문 정보 입력
	@Override
	public void insertOrderInfo(OrderInfoVO vo) throws Exception {
		logger.debug(" === S : insertOrderInfo() 실행 === ");
		odao.insertOrderInfo(vo);
	}

	// cart 주문현황 변경 
	@Override
	public void updateStates(String bundle_code) throws Exception {
		logger.debug(" === S : updateStates(String bundle_code) 실행 === ");
		odao.updateStates(bundle_code);
	}
	
}
