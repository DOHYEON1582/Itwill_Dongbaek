package com.itwillbs.persistence;

import java.util.List;
import java.util.Map;

import com.itwillbs.domain.Criteria;
import com.itwillbs.domain.MarketVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.QuestionVO;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.domain.StoreVO;

public interface MarketDAO {

	public MarketVO getMarketList() throws Exception;
	
	public List<StoreVO> getStoreList() throws Exception;
	
	public StoreVO selectStore(int store_code) throws Exception;
	
	public MarketVO getMarketListCode() throws Exception;
	
	public List<ProductVO> getProductList() throws Exception;
	
	public List<ProductVO> getProductList1() throws Exception;
	
	public List<ProductVO> productOnStore(int store_code) throws Exception;
	
	// 가게 조회수 1증가
	public void storeViewcntUpdate(int store_code) throws Exception;
	
	// 각 상품 조회
	public ProductVO eachProduct(int product_code) throws Exception;
	
	// 각 상품 리뷰 불러오기
	public List<ReviewVO> reviewList(int product_code) throws Exception;
	
	// 문의 작성하기
	public void writeQuestion(QuestionVO qvo) throws Exception;
	
	// 상품별 문의 글 불러오기
	//public List<QuestionVO> getQuestion(Criteria cri, int product_code) throws Exception;
	public List<QuestionVO> getQuestion(Map<String, Object> paramMap) throws Exception;
	
	// 문의 글 개수 계산
	public int questionCount() throws Exception;

	// 페이징 처리
	public List<QuestionVO> questionListPage(int page) throws Exception;
	
	// 최근 문의글 10개 불러오기
	public List<QuestionVO> newQuestion(int product_code) throws Exception;
	
	// 문의 상세정보 
	public QuestionVO questionDetail(int q_code) throws Exception;
	
}
