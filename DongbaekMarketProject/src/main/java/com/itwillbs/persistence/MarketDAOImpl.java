package com.itwillbs.persistence;

import java.util.HashMap;
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


import com.itwillbs.domain.SubscribeProductVO;
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
	public int checkDuplicateWish(Map<String, Object> map) throws Exception {
		logger.debug(" checkDuplicateWish 호출 ");
		Integer product_code = (Integer) map.get("product_code");
		map.put("product_code", product_code);
		return sqlSession.selectOne(NAMESPACE + ".checkDuplicateWish", map);
	}

	public List<ProductVO> getSubProductList() throws Exception {
		logger.debug(" getSubProductList() 호출 ");
		
		return sqlSession.selectList(NAMESPACE+".getSubList");
	}

	@Override
	public List<WishVO> getUserWish(String user_id) throws Exception {
		logger.debug(" getUserWish(String user_id) 호출 ");
		
		return sqlSession.selectList(NAMESPACE+".getUserWish", user_id);
	}

	@Override
	public int userProductWish(int product_code, String user_id) throws Exception {
		logger.debug(" userProductWish(int product_code, String user_id) 호출 ");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("product_code", product_code);
		map.put("user_id", user_id);
		
		return sqlSession.selectOne(NAMESPACE+".userProductWish", map);
	}

	@Override
	public int insertProductWish(int product_code, String user_id) throws Exception {
		logger.debug(" insertProductWish(int product_code, String user_id) 호출 ");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("product_code", product_code);
		map.put("user_id", user_id);
		
		return sqlSession.insert(NAMESPACE+".insertWish", map);
	}

	@Override
	public int deleteProductWish(int product_code, String user_id) throws Exception {
		logger.debug(" deleteProductWish(int product_code, String user_id) 호출 ");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("product_code", product_code);
		map.put("user_id", user_id);
		
		return sqlSession.delete(NAMESPACE+".deleteWish", map);
	}

	@Override
	public int insertSubProduct(SubscribeProductVO vo) throws Exception {
		logger.debug(" insertSubProduct(int product_code, int count, String user_id) 호출 ");
		
		return sqlSession.insert(NAMESPACE+".insertSubPro", vo);
	}

	
	
	// 0509 카트 번호 생성
	// maxCartCode 가져오기
	@Override
	public int selectMaxCartCode() throws Exception {
		logger.debug(" selectMaxCartCode() 호출");
		return sqlSession.selectOne(NAMESPACE+".selectMaxCartCode");
	}

	
	// 즐겨찾기 중복 체크
	@Override
	public int checkDuplicateMark(int store_code, String user_id) throws Exception {
		logger.debug(" checkDuplicateMark() 호출 ");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("store_code", store_code);
		map.put("user_id", user_id);
		return sqlSession.selectOne(NAMESPACE + ".checkDuplicateMark", map);
	}
	
	
	
}