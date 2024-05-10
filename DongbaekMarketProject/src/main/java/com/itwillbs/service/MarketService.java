package com.itwillbs.service;

import java.util.List;
import java.util.Map;


import com.itwillbs.domain.CartVO;
import com.itwillbs.domain.AnswerVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.domain.MarkVO;
import com.itwillbs.domain.MarketVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.QuestionVO;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.domain.StoreVO;

import com.itwillbs.domain.SubscrbeProductVO;
import com.itwillbs.domain.WishVO;

public interface MarketService {
	
	// 시장 정보 전체 가져오기
	public List<MarketVO> getMarketAll(MarketVO mvo) throws Exception;
	
	// 시장 정보 가져오기
	public MarketVO getMarketList() throws Exception;
	
	// 가게 정보 가져오기
	public List<StoreVO> getStoreList() throws Exception;
	
	// 각 가게 정보
	public StoreVO selectStore(int store_code) throws Exception;
	
	// 시장 정보 코드 가져오기
	public MarketVO getMarketListCode() throws Exception;
	
	// 제품 조회
	public List<ProductVO> getProductAll(ProductVO pvo) throws Exception;
		
	// 가게별 상품 가져오기
	public List<ProductVO> productOnStore(String orderBy, int store_code) throws Exception;
	
	// 가게 조회수 1 증가
	public void updateViewcnt(int store_code) throws Exception;
	
	// 각 상품 정보 들고오기
	public ProductVO eachProduct(int product_code) throws Exception;
	
	// 각 상품 리뷰 정보 들고오기
	public List<ReviewVO> productReview(int product_code) throws Exception;
	
	// 문의 글 작성 
	public void writeQuestion(QuestionVO qvo) throws Exception;
	
	// 상품별 문의글 들고오기
	//public List<QuestionVO> selectQuestion(Criteria cri) throws Exception;
	public List<QuestionVO> getQuestion(Map<String, Object> paramMap) throws Exception;
	
	// 상품 글 개수 계산
	public int questionCount() throws Exception;
	
	// 최근 문의글 10개 들고오기
	public List<QuestionVO> newQuestion(int product_code) throws Exception;
		
	// 즐겨찾기에 넣기
	public void markStore(MarkVO mvo) throws Exception;
	

	// 찜에 넣기
	public void wishProduct(WishVO wish) throws Exception;

	public List<QuestionVO> questionDetail(int q_code) throws Exception;
	// 문의 답글 달기
	public void qAnswer(AnswerVO avo) throws Exception;
	// 문의 답글 조회
	public List<AnswerVO> selectAnswer(int q_code) throws Exception;
	// 문의 답글 중복체크
	public boolean isDuplicateAnswer(int q_code) throws Exception;

	// 찜 목록 들고오기
	public List<WishVO> selectWish(String user_id) throws Exception;
  
	// 문의 상세정보 들고오기
	public QuestionVO questionDetail(int q_code) throws Exception;
	
	// 구독 제품 리스트 가져오기
	public List<ProductVO> getSubProductList()throws Exception;
	
	// 찜 리스트 가져오기
	public List<WishVO> getUserWish(String user_id)throws Exception;
	
	// 찜 조회후 등록, 삭제 하기
	public int userProductWish(int product_code, String user_id)throws Exception;
	
	// 회원 구독 상품 추가하기
	public int insertSubProduct(SubscrbeProductVO vo)throws Exception;
	
	
	// 카트에 넣기
	public void insertCart(CartVO cart) throws Exception;
	
	// 찜 중복체크
	public boolean isDuplicateWish(Map<String, Object> paramMap) throws Exception;
}
