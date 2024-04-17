package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.MarketVO;
import com.itwillbs.persistence.MarketDAO;

@Service
public class MarketServiceImpl implements MarketService{

	private static final Logger logger = LoggerFactory.getLogger(MarketServiceImpl.class);
	
	@Inject
	private MarketDAO mdao;

	@Override
	public List<MarketVO> getMarketList() throws Exception {
		return mdao.getMarketList();
	}
	
}
