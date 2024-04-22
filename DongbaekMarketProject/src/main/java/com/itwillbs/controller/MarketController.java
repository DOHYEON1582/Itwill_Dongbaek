package com.itwillbs.controller;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.itwillbs.domain.MarketVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.StoreVO;
import com.itwillbs.service.MarketService;

@Controller
@RequestMapping(value = "/market/*")
public class MarketController {

	@Inject
	private MarketService mService;

	private static final Logger logger = LoggerFactory.getLogger(MarketController.class);

	
	// http://localhost:8088/market/marketMain
	@RequestMapping(value = "/marketMain", method = {RequestMethod.GET, RequestMethod.POST}) 
	public void marketMain(Model model,@RequestParam(defaultValue = "0") int market_code) throws Exception {
	    logger.debug(" marketMain 호출 ");
	    
	    	if(market_code == 0) {
	        MarketVO marketList = mService.getMarketList();
	        List<StoreVO> storeList = mService.getStoreList();
	        List<ProductVO> productList = mService.getProductList();    
	        model.addAttribute("marketList", marketList);
	        model.addAttribute("storeList", storeList);
	        model.addAttribute("productList", productList);
	        logger.debug(" marketList : " + marketList);
	        logger.debug(" storeList : " + storeList);
	    	} else if(market_code == 1) {
	    		MarketVO marketList = mService.getMarketListCode();
		        List<StoreVO> storeList = mService.getStoreList();
		        List<ProductVO> productList = mService.getProductList1();  
		        model.addAttribute("marketList", marketList);
		        model.addAttribute("storeList", storeList);
		        model.addAttribute("productList", productList);
	    		logger.debug(" marketList : " + marketList);
	    		logger.debug(" storeList : " + storeList);
	    	}
		    
	    }
	
	// http://localhost:8088/market/storeMain
	// 가게 메인페이지
	@RequestMapping(value = "/storeMain", method = RequestMethod.GET)
	public void storeMain(@RequestParam("store_code") int store_code, Model model, HttpSession session) throws Exception{
		logger.debug(" storeMain() 호출 ");
		StoreVO store = mService.selectStore(store_code);
		model.addAttribute("store", store);
		List<ProductVO> product = mService.productOnStore(store_code);
		model.addAttribute("product", product);
		
		session.setAttribute("viewUpdateStatus", 1);
	}


}
