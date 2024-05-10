package com.itwillbs.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


import com.itwillbs.domain.CartVO;
import com.itwillbs.domain.AnswerVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.domain.MarkVO;
import com.itwillbs.domain.MarketVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.QuestionVO;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.domain.StoreVO;
import com.itwillbs.domain.WishVO;


@Repository
public class MarketDAOImpl implements MarketDAO{

	@Inject
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(MarketDAOImpl.class);
	private static final String NAMESPACE = "com.itwillbs.mapper.MarketMapper";
	
	@Override
	public List<MarketVO> getMarketAll(MarketVO mvo) throws Exception {
		logger.debug(" getMarketAll(MarketVO mvo) 호출 ");
		return sqlSession.selectList(NAMESPACE + ".selectMarketAll", mvo);
	}

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
	public List<ProductVO> selectProductAll(ProductVO pvo) throws Exception {
		logger.debug(" selectProductAll(ProductVO pvo) 호출 ");
		return sqlSession.selectList(NAMESPACE + ".getProductAll", pvo);
	}
	

	@Override
	public List<ProductVO> productOnStore(Map<String, Object> map) throws Exception {
		String orderBy = (String) map.get("orderBy");
		logger.debug("map1 : " + map);
	    map.put("orderBy", orderBy);
		return sqlSession.selectList(NAMESPACE + ".productOnStore", map);
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
	public List<QuestionVO> getQuestion(Map<String, Object> paramMap) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".selectQuestion", paramMap);
	}

	@Override
	public int questionCount() throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".totalCount");
	}

	@Override
	public List<QuestionVO> questionListPage(int page) throws Exception {
		page = (page - 1) * 5;
		return sqlSession.selectList(NAMESPACE + ".questionPage", page);
	}

	@Override
	public List<QuestionVO> newQuestion(int product_code) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".newQuestion", product_code);
	}

	@Override
	public List<QuestionVO> questionDetail(int q_code) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".questionDetail", q_code);
	}

	@Override
	public void markStore(MarkVO mvo) throws Exception {
		sqlSession.insert(NAMESPACE + ".markStore", mvo);
	}

	@Override
	public void wishProduct(WishVO wish) throws Exception {
		sqlSession.insert(NAMESPACE + ".wishProduct", wish);
	}

	@Override
	public List<WishVO> selectWish(String user_id) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".selectWish", user_id);
	}

	@Override
	public void insertCart(CartVO cart) throws Exception {
		sqlSession.insert(NAMESPACE + ".insertCart", cart);
	}

	public void qAnswer(AnswerVO avo) throws Exception {
		logger.debug(" qAnswer(AnswerVO avo) 호출 ");
		sqlSession.insert(NAMESPACE + ".qAnswer", avo);
	}

	@Override
	public List<AnswerVO> selectAnswer(int q_code) throws Exception {
		logger.debug(" selectAnswer(int q_code) 호출 ");
		return sqlSession.selectList(NAMESPACE + ".selectAnswer", q_code);
	}

	@Override
	public int checkDuplicateAnswer(int q_code) throws Exception {
		logger.debug(" checkDuplicateAnswer(int q_code) 호출");
		logger.debug(" q_code : " + q_code);
		return sqlSession.selectOne(NAMESPACE + ".checkDuplicateAnswer", q_code);
	}

	@Override
	public int checkDuplicateWish(Map<String, Object> paramMap) throws Exception {
		logger.debug(" checkDuplicateWish 호출 ");
		return sqlSession.selectOne(NAMESPACE + ".checkDuplicateWish", paramMap);
	}
	
}
