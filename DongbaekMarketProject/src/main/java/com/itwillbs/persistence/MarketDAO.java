package com.itwillbs.persistence;

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

import com.itwillbs.domain.SubscribeProductVO;

import com.itwillbs.domain.WishVO;

public interface MarketDAO {
	
	// 마켓 전체 조회
	public List<MarketVO> getMarketAll(MarketVO mvo) throws Exception;

	public MarketVO getMarketList() throws Exception;
	
	public List<StoreVO> getStoreList() throws Exception;
	
	public StoreVO selectStore(int store_code) throws Exception;
	
	public MarketVO getMarketListCode() throws Exception;
	
	// 제품 조회
	public List<ProductVO> selectProductAll(ProductVO pvo) throws Exception;
	
	public List<ProductVO> productOnStore(Map<String, Object> map) throws Exception;
	
	// 가게 조회수 1증가
	public void storeViewcntUpdate(int store_code) throws Exception;
	
	// 각 상품 조회
	public ProductVO eachProduct(int product_code) throws Exception;
	
	// 각 상품 리뷰 불러오기
	public List<ReviewVO> reviewList(int product_code) throws Exception;
	
	// 문의 작성하기
	public void writeQuestion(QuestionVO qvo) throws Exception;
	
	// 상품별 문의 글 불러오기
	public List<QuestionVO> getQuestion(Map<String, Object> paramMap) throws Exception;
	
	// 문의 글 개수 계산
	public int questionCount() throws Exception;

	// 페이징 처리
	public List<QuestionVO> questionListPage(int page) throws Exception;
	
	// 최근 문의글 10개 불러오기
	public List<QuestionVO> newQuestion(int product_code) throws Exception;
	
	// 문의 상세정보 
	public List<QuestionVO> questionDetail(int q_code) throws Exception;
	// 문의 답글 달기
	public void qAnswer(AnswerVO avo) throws Exception;
	// 문의 답글 조회
	public List<AnswerVO> selectAnswer(int q_code) throws Exception;
	// 문의 답글 중복체크
	public int checkDuplicateAnswer(int q_code) throws Exception;
	
	// 즐겨찾기에 넣기
	public void markStore(MarkVO mvo) throws Exception;
	
	// 찜 넣기
	public void wishProduct(WishVO wish) throws Exception;
	// 찜 중복 체크
	public int checkDuplicateWish(Map<String, Object> map) throws Exception;
	// 찜 상품 표시
	public List<WishVO> selectWish(String user_id) throws Exception;
	
	// 카트에 넣기
	public void insertCart(CartVO cart) throws Exception;
	

	// 구독 제품 리스트 가져오기
	public List<ProductVO> getSubProductList()throws Exception;
	
	// 찜 리스트 가져오기
	public List<WishVO> getUserWish(String user_id)throws Exception;
	
	// 찜 유무 확인
	public int userProductWish(int product_code, String user_id)throws Exception;
	
	// 찜 등록하기
	public int insertProductWish(int product_code, String user_id)throws Exception;
	
	// 찜 삭제하기
	public int deleteProductWish(int product_code, String user_id)throws Exception;
	
	// 회원 구독 상품 추가하기
	public int insertSubProduct(SubscribeProductVO vo)throws Exception;
	
	
	

}
