package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.CartVO;
import com.itwillbs.persistence.MyPageDAO;

@Service
public class MyPageServiceImpl implements MyPageService {
	
	private static final Logger logger = LoggerFactory.getLogger(MyPageServiceImpl.class);
	
	@Inject
	private MyPageDAO mdao;
	
	// 장바구니에 담긴 상품 수 세어오기
	@Override
	public int getCartNum(CartVO vo) throws Exception {
		logger.debug(" === S : getCartNum(CartVO vo) 실행 === ");
		return mdao.selectCountCart(vo);
	}

	// 장바구니 목록 가져오기
	@Override
	public List<CartVO> getCartList(CartVO vo) throws Exception {
		logger.debug(" === S : getCartList(CartVO vo) 실행 === ");
		return mdao.selectCartList(vo);
	}

	// 장바구니 상품 수량 변경
	@Override
	public void modifyProductCount(int cart_code) throws Exception {
		logger.debug(" === S : modifyProductCount(int cart_code) 실행 === ");
		mdao.updateProductCount(cart_code);
	}
		
	// 장바구니 상품 삭제
	@Override
	public void removeCartProduct(int cart_code) throws Exception {
		logger.debug(" === S : removeCartProduct(int cart_code) 실행 === ");
		mdao.deleteCartProduct(cart_code);
	}
	
	

}
