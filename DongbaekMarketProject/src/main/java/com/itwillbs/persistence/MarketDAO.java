package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.MarketVO;

public interface MarketDAO {

	public List<MarketVO> getMarketList() throws Exception;
	
	
	
}
