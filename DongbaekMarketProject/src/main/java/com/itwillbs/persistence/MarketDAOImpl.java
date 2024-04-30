package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MarketVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.QuestionVO;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.domain.StoreVO;


@Repository
public class MarketDAOImpl implements MarketDAO{

	@Inject
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(MarketDAOImpl.class);
	private static final String NAMESPACE = "com.itwillbs.mapper.MarketMapper";

	@Override
	public MarketVO getMarketList() throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".marketList");
	}

	// 전체 가게 리스트
	@Override
	public List<StoreVO> getStoreList() throws Exception {
		return sqlSession.selectList(NAMESPACE + ".storeList");
	}

	// 각 가게정보
	@Override
	public StoreVO selectStore(int store_code) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".selectStore", store_code);
	}
	
	@Override
	public MarketVO getMarketListCode() throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".marketListCode");
	}

	@Override
	public List<ProductVO> getProductList() throws Exception {
		return sqlSession.selectList(NAMESPACE + ".selectProduct");
	}

	@Override
	public List<ProductVO> getProductList1() throws Exception {
		return sqlSession.selectList(NAMESPACE + ".selectProduct1");
	}

	@Override
	public List<ProductVO> productOnStore(int store_code) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".productOnStore", store_code);
	}

	@Override
	public void storeViewcntUpdate(int store_code) throws Exception {
		sqlSession.update(NAMESPACE + ".updateViewcnt", store_code);
	}

	@Override
	public ProductVO eachProduct(int product_code) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".eachProduct", product_code);
	}

	@Override
	public List<ReviewVO> reviewList(int product_code) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".productReview", product_code);
	}

	@Override
	public void writeQuestion(QuestionVO qvo) throws Exception {
		sqlSession.insert(NAMESPACE + ".writeQuestion", qvo);
	}

	@Override
	public List<QuestionVO> getQuestion(int product_code) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".selectQuestion", product_code);
	}

	
	
}
