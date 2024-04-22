package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.MarketVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.StoreVO;

public interface MarketDAO {

	public MarketVO getMarketList() throws Exception;
	
	public List<StoreVO> getStoreList() throws Exception;
	
	public StoreVO selectStore(int store_code) throws Exception;
	
	public MarketVO getMarketListCode() throws Exception;
	
	public List<ProductVO> getProductList() throws Exception;
	
	public List<ProductVO> getProductList1() throws Exception;
	
	public List<ProductVO> productOnStore(int store_code) throws Exception;
}
