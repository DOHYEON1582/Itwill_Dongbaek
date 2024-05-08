package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.MarketVO;

public interface MainService {
	
	// 메인페이지 시장정보 가져오기
	public List<MarketVO> getMarket(MarketVO mvo) throws Exception;
	
	public MarketVO getMarketCode(int market_code) throws Exception;
}
