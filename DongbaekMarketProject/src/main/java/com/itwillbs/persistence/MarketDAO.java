package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.MarketVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.StoreVO;

public interface MarketDAO {

	public MarketVO getMarketList() throws Exception;
	
	public List<StoreVO> getStoreList() throws Exception;
	
	public List<MarketVO> getMarketListCode(int market_code) throws Exception;
	
	public List<ProductVO> getProductList() throws Exception;
	
}
