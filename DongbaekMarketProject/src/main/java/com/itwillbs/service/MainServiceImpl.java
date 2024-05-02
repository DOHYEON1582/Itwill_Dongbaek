package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.MarketVO;
import com.itwillbs.persistence.MainDAO;

@Service
public class MainServiceImpl implements MainService {
	
	@Inject
	private MainDAO mdao;
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public List<MarketVO> getMarket(MarketVO mvo) throws Exception {
		logger.debug(" getMarket(MarketVO mvo) 호출 ");
		
		return mdao.getMarket(mvo);
	}

	@Override
	public MarketVO getMarketCode(int market_code) throws Exception {
		logger.debug(" getMarketCode(int market_code) 호출 ");
		return mdao.getMarketCode(market_code);
	}
	
	
	
}
