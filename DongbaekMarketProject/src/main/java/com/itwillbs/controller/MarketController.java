package com.itwillbs.controller;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	  // 시장 메인 페이지 
//	  @RequestMapping(value = "/marketMain", method = RequestMethod.GET) 
//	  public void marketMain(Model model) throws Exception{
//		  logger.debug(" marketMain 호출 "); 
//		  List<MarketVO> marketList = mService.getMarketList(); 
//		  List<StoreVO> storeList = mService.getStoreList();
//		  logger.info(" marketList : " + marketList.size());
//		  model.addAttribute("marketList", marketList); 
//		  model.addAttribute("storeList", storeList); 
//	  }
	
	// http://localhost:8088/market/marketMain
	@RequestMapping(value = "/marketMain", method = RequestMethod.GET) 
	public void marketMain(Model model) throws Exception {
	    logger.debug(" marketMain 호출 ");
	        List<MarketVO> marketList = mService.getMarketList();
	        List<StoreVO> storeList = mService.getStoreList();
	        List<ProductVO> productList = mService.getProductList();
	        logger.info(" marketList : " + marketList.size());
	        model.addAttribute("marketList", marketList);
	        model.addAttribute("storeList", storeList);
	        model.addAttribute("productList", productList);
	       logger.debug(" productList : " + productList.size());
	    }
	

//	@RequestMapping(value = "/marketMain", method = RequestMethod.POST)
//	public void marketMainPost(Model model, @RequestParam(value = "market_code") int market_code) throws Exception{
//		logger.debug(" marketMain POST 호출 ");
//		
//        List<MarketVO> marketList = mService.getMarketListCode(market_code);
//        model.addAttribute("marketList", marketList);
//	}
	 	
	@RequestMapping(value = "/marketMain", method = RequestMethod.POST )
	@ResponseBody
	public ResponseEntity<Map<String, Object>> marketMain(@RequestParam(value = "market_code", required = false) Integer market_code) throws Exception {
		logger.info("marketMain 호출");

	    if (market_code != null) {
	        // market_code에 해당하는 시장 정보를 가져와서 JSON 형태로 반환
	        MarketVO marketInfo = (MarketVO) mService.getMarketList();
	        if (marketInfo != null) {
	            return ResponseEntity.ok(Collections.singletonMap("marketInfo", marketInfo));
	        } else {
	            return ResponseEntity.notFound().build();
	        }
		} else {
			// 전체 시장 및 가게 목록을 가져와서 페이지에 렌더링
			List<MarketVO> marketList = mService.getMarketList();
			List<StoreVO> storeList = mService.getStoreList();
			logger.info("marketList: " + marketList.size());
			Map<String, Object> responseData = new HashMap<>();
			responseData.put("marketList", marketList);
			responseData.put("storeList", storeList);
			return ResponseEntity.ok(responseData);
		}
	}

}
