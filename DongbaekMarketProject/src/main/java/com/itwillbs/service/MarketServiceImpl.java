package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.MarketVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.StoreVO;
import com.itwillbs.persistence.MarketDAO;

@Service
public class MarketServiceImpl implements MarketService{

	private static final Logger logger = LoggerFactory.getLogger(MarketServiceImpl.class);
	
	@Inject
	private MarketDAO mdao;

	@Override
	public MarketVO getMarketList() throws Exception {
		return mdao.getMarketList();
	}

	@Override
	public List<StoreVO> getStoreList() throws Exception {
		return mdao.getStoreList();
	}

	@Override
	public List<MarketVO> getMarketListCode(int market_code) throws Exception {
		return mdao.getMarketListCode(market_code);
	}

	@Override
	public List<ProductVO> getProductList() throws Exception {
		return mdao.getProductList();
	}
	
}
