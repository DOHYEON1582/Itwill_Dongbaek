package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


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
import com.itwillbs.persistence.MarketDAO;

@Service
public class MarketServiceImpl implements MarketService{

	private static final Logger logger = LoggerFactory.getLogger(MarketServiceImpl.class);
	
	@Inject
	private MarketDAO mdao;

	
	@Override
	public List<MarketVO> getMarketAll(MarketVO mvo) throws Exception {
		logger.debug(" getMarketAll(MarketVO mvo) 실행 ");
		return mdao.getMarketAll(mvo);
	}

	@Override
	public MarketVO getMarketList() throws Exception {
		return mdao.getMarketList();
	}

	@Override
	public List<StoreVO> getStoreList() throws Exception {
		return mdao.getStoreList();
	}

	@Override
	public StoreVO selectStore(int store_code) throws Exception {
		return mdao.selectStore(store_code);
	}
	
	@Override
	public MarketVO getMarketListCode() throws Exception {
		return mdao.getMarketListCode();
	}

	@Override
	public List<ProductVO> getProductAll(ProductVO pvo) throws Exception {
		logger.debug(" getProductAll(ProductVO pvo) 호출 ");
		return mdao.selectProductAll(pvo);
	}
	
	@Override
	public List<ProductVO> productOnStore(String orderBy, int store_code) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("orderBy", orderBy);
		map.put("store_code", store_code);
		return mdao.productOnStore(map);
	}

	@Override
	public void updateViewcnt(int store_code) throws Exception {
		mdao.storeViewcntUpdate(store_code);
		
	}

	@Override
	public ProductVO eachProduct(int product_code) throws Exception {
		return mdao.eachProduct(product_code);
	
	}

	@Override
	public List<ReviewVO> productReview(int product_code) throws Exception {
		return mdao.reviewList(product_code);
	}

	@Override
	public void writeQuestion(QuestionVO qvo) throws Exception {
		mdao.writeQuestion(qvo);
	}

	@Override
	public int questionCount() throws Exception {
		return mdao.questionCount();
	}

	@Override
	public List<QuestionVO> getQuestion(Map<String, Object> paramMap) throws Exception {
		return mdao.getQuestion(paramMap);
	}

	@Override
	public List<QuestionVO> newQuestion(int product_code) throws Exception {
		return mdao.newQuestion(product_code);
	}

	@Override
	public List<QuestionVO> questionDetail(int q_code) throws Exception {
		return mdao.questionDetail(q_code);
	}

	@Override
	public void markStore(MarkVO mvo) throws Exception {
		mdao.markStore(mvo);
	}

	@Override
	public void wishProduct(WishVO wish) throws Exception {
		mdao.wishProduct(wish);
	}

	@Override
	public List<WishVO> selectWish(String user_id) throws Exception {
		return mdao.selectWish(user_id);
	}

	@Override
	public void insertCart(CartVO cart) throws Exception {
		mdao.insertCart(cart);
	}


	public void qAnswer(AnswerVO avo) throws Exception {
		logger.debug(" qAnswer(AnswerVO avo) 실행 ");
		mdao.qAnswer(avo);
	}

	@Override
	public List<AnswerVO> selectAnswer(int q_code) throws Exception {
		logger.debug(" selectAnswer(int q_code) 실행 ");
		return mdao.selectAnswer(q_code);
	}

	@Override
	public boolean isDuplicateAnswer(int q_code) throws Exception {
		logger.debug(" isDuplicateAnswer(int q_code) 실행 ");
		int count = mdao.checkDuplicateAnswer(q_code);
		logger.debug(" q_code : " + q_code);
		logger.debug(" count : " + count);
		return count > 0;
	}

	@Override
	public boolean isDuplicateWish(int product_code, String user_id) throws Exception {
	    logger.debug("isDuplicateWish() 실행");
	    Map<String, Object> map = new HashMap<>();
	    map.put("product_code", product_code);
	    map.put("user_id", user_id);
	    int count = mdao.checkDuplicateWish(map);
	    return count > 0;
	}


	public List<ProductVO> getSubProductList() throws Exception {
		logger.debug(" getSubProductList() 호출 ");
		
		return mdao.getSubProductList();
	}

	@Override
	public List<WishVO> getUserWish(String user_id) throws Exception {
		logger.debug(" getUserWish(String user_id) 호출 ");
		
		return mdao.getUserWish(user_id);
	}

	@Override
	public int userProductWish(int product_code, String user_id) throws Exception {
		logger.debug(" userProductWish(int product_code, String user_id) 호출 ");
		int result = mdao.userProductWish(product_code, user_id);
		if(result == 1) { // 찜 있음
			mdao.deleteProductWish(product_code, user_id);
			result = 0;
		}else {
			mdao.insertProductWish(product_code, user_id);
			result = 1;
		}
		return result;
	}

	@Override
	public int insertSubProduct(SubscribeProductVO vo) throws Exception {
		logger.debug(" insertSubProduct(SubscrbeProductVO vo) 호출 ");
		
		return mdao.insertSubProduct(vo);
	}
	
	
	// 0509 카트 번호 생성
	// maxCartCode 가져오기
	@Override
	public int selectMaxCartCode() throws Exception {
		logger.debug(" selectMaxCartCode() 실행 ");
		return mdao.selectMaxCartCode();
	}

	
	
	// 즐겨찾기 중복 체크
	@Override
	public boolean checkDuplicateMark(int store_code, String user_id) throws Exception {
		logger.debug(" checkDuplicateMark() 실행 ");
		Map<String, Object> map = new HashMap<>();
	    map.put("store_code", store_code);
	    map.put("user_id", user_id);
	    int count = mdao.checkDuplicateMark(store_code, user_id);
	    logger.debug("map : " + map);
	    logger.debug("count : " + count);
		return count == 0;
	}
	
	

}
