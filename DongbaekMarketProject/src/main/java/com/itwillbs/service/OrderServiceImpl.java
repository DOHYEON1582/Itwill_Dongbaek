package com.itwillbs.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.CartVO;
import com.itwillbs.persistence.OrderDAO;

@Service
public class OrderServiceImpl implements OrderDAO {

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Inject
	private OrderDAO odao;

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
	
	
	
}
