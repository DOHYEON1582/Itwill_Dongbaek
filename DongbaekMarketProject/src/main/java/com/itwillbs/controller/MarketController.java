package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MarketVO;
import com.itwillbs.domain.StoreVO;
import com.itwillbs.service.MarketService;

@Controller
@RequestMapping(value = "/market/*")
public class MarketController {

	@Inject 
	private MarketService mService;
	
	private static final Logger logger = LoggerFactory.getLogger(MarketController.class);
	
	// 시장 메인 페이지 
	// http://localhost:8088/market/marketMain
	@RequestMapping(value = "/marketMain", method = RequestMethod.GET)
	public void marketMain(Model model) throws Exception{
		logger.info(" marketMain 호출 ");
		List<MarketVO> marketList = mService.getMarketList();
		List<StoreVO> storeList = mService.getStoreList();
		logger.info(" marketList : " + marketList.size());
		model.addAttribute("marketList", marketList);
		model.addAttribute("storeList", storeList);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
