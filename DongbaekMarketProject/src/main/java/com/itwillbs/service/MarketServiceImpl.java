package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.AnswerVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.domain.MarketVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.QuestionVO;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.domain.StoreVO;
import com.itwillbs.persistence.MarketDAO;

@Service
public class MarketServiceImpl implements MarketService{

	private static final Logger logger = LoggerFactory.getLogger(MarketServiceImpl.class);
	
	@Inject
	private MarketDAO mdao;

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

	
}
