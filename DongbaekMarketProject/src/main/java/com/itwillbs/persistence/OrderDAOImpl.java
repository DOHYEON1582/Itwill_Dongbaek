package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.CartVO;

@Repository
public class OrderDAOImpl implements OrderDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(OrderDAOImpl.class);

	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.itwillbs.mapper.OrderMapper";
	
	// 장바구니 목록 가져오기
	@Override
	public List<CartVO> selectCartList(CartVO vo) throws Exception {
		logger.debug(" === D : selectCartList(CartVO vo) 실행 === ");
		return sqlSession.selectList(NAMESPACE+".selectProductInfo");
	}
	
	// 주문 할 상품 정보 가져오기
	@Override
	public CartVO selectProductInfo(int cart_code) throws Exception {
		logger.debug(" === D : selectProductInfo(int cart_code) 실행 === ");
		return sqlSession.selectOne(NAMESPACE+".selectProductInfo");
	}

	// 적립금 가져오기
	@Override
	public String selectUserPoint(String user_id) throws Exception {
		logger.debug(" === D : selectUserPoint(String user_id) 실행 === ");
		return sqlSession.selectOne(NAMESPACE+".selectUserPoint");
	}

	
	
}
