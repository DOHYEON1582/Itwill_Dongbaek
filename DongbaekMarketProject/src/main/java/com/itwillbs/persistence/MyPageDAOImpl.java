package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.CartVO;

@Repository
public class MyPageDAOImpl implements MyPageDAO {

	private static final Logger logger = LoggerFactory.getLogger(MyPageDAOImpl.class);

	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.itwillbs.mapper.MyPageMapper";

	// 장바구니에 담긴 상품 수 세어오기
	@Override
	public int selectCountCart(CartVO vo) throws Exception {
		logger.debug(" === D : selectCountCart(CartVO vo) === ");
		int result = sqlSession.selectOne(NAMESPACE+".selectCountCart",vo);
		return result;
	}

	// 장바구니 목록 가져오기
	@Override
	public List<CartVO> selectCartList(CartVO vo) throws Exception {
		logger.debug(" === D : selectCartList(CartVO vo) ===");
		List<CartVO> list = sqlSession.selectList(NAMESPACE+".selectCartList",vo);
		return list;
	}
	
	// 장바구니 상품 수량 변경
	@Override
	public void updateProductCount(int cart_code) throws Exception {
		logger.debug(" === D : updateProductCount(int cart_code) ===");
		sqlSession.update(NAMESPACE+".deleteCartProduct",cart_code);
	}

	// 장바구니 상품 선택 삭제
	@Override
	public void deleteCartProduct(int cart_code) throws Exception {
		logger.debug(" === D : deleteCartProduct(int cart_code) ===");
		sqlSession.delete(NAMESPACE+".deleteCartProduct", cart_code);
		
	}

	

	
}
