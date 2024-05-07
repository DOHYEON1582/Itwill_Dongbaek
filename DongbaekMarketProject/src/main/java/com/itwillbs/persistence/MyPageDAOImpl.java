package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.CartVO;
import com.itwillbs.domain.OrderInfoVO;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.domain.SearchCriteria;

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
		return sqlSession.selectOne(NAMESPACE+".selectCountCart",vo);
	}

	// 장바구니 목록 가져오기
	@Override
	public List<CartVO> selectCartList(CartVO vo) throws Exception {
		logger.debug(" === D : selectCartList(CartVO vo) ===");
		return sqlSession.selectList(NAMESPACE+".selectCartList",vo);
	}
	
	// 장바구니 상품 수량 변경
	@Override
	public void updateProductCount(CartVO vo) throws Exception {
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
	public void deleteCartAllProduct(CartVO vo) throws Exception {
		logger.debug(" === D : deleteCartAllProduct(CartVO vo) === ");
		sqlSession.delete(NAMESPACE+".deleteCart",vo);	
	}

	/* 주문내역 */
	// 주문내역 리스트
	@Override
	public List<OrderInfoVO> selectUserOrderList(SearchCriteria searchCri) throws Exception {
		logger.debug(" === D : selectUserOrderList(SearchCriteria searchCri) === ");
		return sqlSession.selectList(NAMESPACE+".selectUserOrderList",searchCri);
	}

	// 주문내역 갯수
	@Override
	public int selectCountOrder(String user_id) throws Exception {
		logger.debug(" === D : selectCountOrder(String user_id) === ");
		return sqlSession.selectOne(NAMESPACE+".selectCountOrder",user_id);
	}

	// 주문 상세 내역
	@Override
	public OrderInfoVO selectOrderInfo(int order_code) throws Exception {
		logger.debug(" === D : selectOrderInfo(int order_code) === ");
		return sqlSession.selectOne(NAMESPACE+".selectOrderInfo",order_code);
	}

	// 주문 상품 정보
	@Override
	public List<CartVO> selectOrderProduct(int order_code) throws Exception {
		logger.debug(" === D : selectOrderProduct(int order_code) === ");
		return sqlSession.selectList(NAMESPACE+".selectOrderProduct",order_code);
	}

	/* 리뷰 */
	// 리뷰 작성 할 상품 정보 불러오기
	@Override
	public CartVO selectReviewProduct(String productCode) throws Exception {
		logger.debug(" === D : selectReviewProduct(String productCode) === ");
		return sqlSession.selectOne(NAMESPACE+".selectReviewProduct",productCode);
	}

	// 리뷰작성
	@Override
	public void insertReview(ReviewVO vo) throws Exception {
		logger.debug(" === D : insertReview(ReviewVO vo) === ");
		sqlSession.insert(NAMESPACE+"insertReview",vo);
	}

	// 내가 쓴 리뷰
	@Override
	public List<ReviewVO> selectReview(String user_id) throws Exception {
		logger.debug(" === D : selectReview() === ");
		return sqlSession.selectList(NAMESPACE+".selectReview",user_id);
	}
	
}
