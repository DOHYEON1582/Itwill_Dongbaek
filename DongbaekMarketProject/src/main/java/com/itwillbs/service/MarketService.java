package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.MarketVO;
import com.itwillbs.domain.StoreVO;

public interface MarketService {
	
	// 시장 정보 가져오기
	public List<MarketVO> getMarketList() throws Exception;
	
	// 가게 정보 가져오기
	public List<StoreVO> getStoreList() throws Exception;
	
	
}
