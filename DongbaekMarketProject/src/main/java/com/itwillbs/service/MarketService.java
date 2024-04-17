package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.MarketVO;

public interface MarketService {
	
	// 시장 정보 가져오기
	public List<MarketVO> getMarketList() throws Exception;
	
}
