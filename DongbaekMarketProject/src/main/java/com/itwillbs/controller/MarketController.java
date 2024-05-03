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

import com.itwillbs.domain.Criteria;
import com.itwillbs.domain.MarketVO;
import com.itwillbs.domain.PageVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.QuestionVO;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.domain.StoreVO;
import com.itwillbs.domain.UserVO;
import com.itwillbs.service.MarketService;

@Controller
@RequestMapping(value = "/market/*")
public class MarketController {

	@Inject
	private MarketService mService;

	private static final Logger logger = LoggerFactory.getLogger(MarketController.class);

	
	// http://localhost:8088/market/marketMain
	@RequestMapping(value = "/marketMain", method = {RequestMethod.GET, RequestMethod.POST}) 
	public void marketMain(@RequestParam(name = "orderBy", required = false, defaultValue = "popularity") String orderBy,
			Model model,@RequestParam(defaultValue = "1") int market_code, HttpSession session, ProductVO pvo) throws Exception {
	    logger.debug(" marketMain 호출 ");
	    List<StoreVO> storeList = mService.getStoreList();
	    List<ProductVO> productList = mService.getProductAll(pvo);
	    model.addAttribute("storeList", storeList);
	    model.addAttribute("productList", productList);
    	if(market_code == 1) {
    		MarketVO marketList = mService.getMarketList();
 	        model.addAttribute("marketList", marketList);
    	} else if(market_code == 2) {
    		MarketVO marketList = mService.getMarketListCode();
	        model.addAttribute("marketList", marketList);
    	}
		session.setAttribute("viewUpdateStatus", 1);
    }
	
	// http://localhost:8088/market/storeMain
	// 가게 메인페이지
	@RequestMapping(value = "/storeMain", method = RequestMethod.GET)
	public void storeMain(@RequestParam(name = "orderBy", required = false, defaultValue = "popularity") String orderBy, 
			@RequestParam("store_code") int store_code, Model model, HttpSession session) throws Exception{
		logger.debug(" storeMain() 호출 ");
		StoreVO store = mService.selectStore(store_code);
		List<ProductVO> product = mService.productOnStore(orderBy, store_code);
		model.addAttribute("store", store);
		model.addAttribute("product", product);
		int status = (Integer)session.getAttribute("viewUpdateStatus");
		if(status == 1) {
			// 글 조회수 1 증가
			mService.updateViewcnt(store_code);
			// 조회수 상태 0으로 만들기
			session.setAttribute("viewUpdateStatus", 0);
		}
	}



	// 상품 메인페이지
	@RequestMapping(value = "/productMain", method = RequestMethod.GET)
	public void productMain(@RequestParam("product_code") int product_code, Model model, HttpSession session, QuestionVO qvo, Criteria cri) throws Exception{
		logger.debug(" productMain() 호출 ");

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("product_code", product_code);
		ProductVO product = mService.eachProduct(product_code);
		model.addAttribute("product", product);
		List<QuestionVO> question = mService.newQuestion(product_code);
		List<ReviewVO> review = mService.productReview(product_code);
		model.addAttribute("question", question);
		model.addAttribute("review", review);

	}
	
	@RequestMapping(value = "/questionMain", method = RequestMethod.GET)
	public void questionMain(@RequestParam("product_code") int product_code, Criteria cri, Model model) throws Exception {
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(mService.questionCount());
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("startPage", pageVO.getStartPage());
		paramMap.put("pageSize", cri.getPageSize());
	
		List<QuestionVO> question = mService.getQuestion(paramMap);
		
		model.addAttribute("cri", cri);
		model.addAttribute("pageVO", pageVO);
	}
	
	
	
	
	
	
//	// 상품 POST 페이지
//	@RequestMapping(value = "/productMain", method = RequestMethod.POST)
//	public void productMainPOST(@RequestParam("product_code") int product_code, Model model, HttpSession session, QuestionVO qvo) throws Exception{
//		logger.debug(" productMainPOST() 호출 ");
//		//mService.writeQuestion(qvo);
//
//	}
	
}
