package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.MarketVO;
import com.itwillbs.domain.StoreVO;

public interface MarketDAO {

	public List<MarketVO> getMarketList() throws Exception;
	
	public List<StoreVO> getStoreList() throws Exception;
	
	
}
