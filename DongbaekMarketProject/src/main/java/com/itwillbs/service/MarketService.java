package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.MarketVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.QuestionVO;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.domain.StoreVO;

public interface MarketService {
	
	// 시장 정보 가져오기
	public MarketVO getMarketList() throws Exception;
	
	// 가게 정보 가져오기
	public List<StoreVO> getStoreList() throws Exception;
	
	// 각 가게 정보
	public StoreVO selectStore(int store_code) throws Exception;
	
	// 시장 정보 코드 가져오기
	public MarketVO getMarketListCode() throws Exception;
	
	// 인기상품 메인에 가져오기
	public List<ProductVO> getProductList() throws Exception;
	public List<ProductVO> getProductList1() throws Exception;
	
	// 가게별 상품 가져오기
	public List<ProductVO> productOnStore(int store_code) throws Exception;
	
	// 가게 조회수 1 증가
	public void updateViewcnt(int store_code) throws Exception;
	
	// 각 상품 정보 들고오기
	public ProductVO eachProduct(int product_code) throws Exception;
	
	// 각 상품 리뷰 정보 들고오기
	public List<ReviewVO> productReview(int product_code) throws Exception;
	
	// 문의 글 작성 
	public void writeQuestion(QuestionVO qvo) throws Exception;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
