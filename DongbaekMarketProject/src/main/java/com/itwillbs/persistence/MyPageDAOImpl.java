package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.Cart2VO;

@Repository
public class MyPageDAOImpl implements MyPageDAO {

	private static final Logger logger = LoggerFactory.getLogger(MyPageDAOImpl.class);

	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.itwillbs.mapper.MyPageMapper";

	// 장바구니에 담긴 상품 수 세어오기
	@Override
	public int selectCountCart(Cart2VO vo) throws Exception {
		logger.debug(" === D : selectCountCart(CartVO vo) === ");
		return sqlSession.selectOne(NAMESPACE+".selectCountCart",vo);
	}

	// 장바구니 목록 가져오기
	@Override
	public List<Cart2VO> selectCartList(Cart2VO vo) throws Exception {
		logger.debug(" === D : selectCartList(CartVO vo) ===");
		return sqlSession.selectList(NAMESPACE+".selectCartList",vo);
	}
	
	// 장바구니 상품 수량 변경
	@Override
	public void updateProductCount(Cart2VO vo) throws Exception {
		logger.debug(" === D : updateProductCount(int cart_code) ===");
		sqlSession.update(NAMESPACE+".deleteCartProduct",vo);
	}

	// 장바구니 상품 선택 삭제
	@Override
	public void deleteCartProduct(int cart_code) throws Exception {
		logger.debug(" === D : deleteCartProduct(int cart_code) ===");
		sqlSession.delete(NAMESPACE+".deleteCartProduct", cart_code);	
	}

	// 장바구니 비우기
	@Override
	public void deleteCartAllProduct(Cart2VO vo) throws Exception {
		logger.debug(" === D : deleteCartAllProduct(CartVO vo) === ");
		sqlSession.delete(NAMESPACE+".deleteCart",vo);	
	}
	
}
