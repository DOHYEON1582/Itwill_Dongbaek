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
import org.springframework.http.MediaType;
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
	public void marketMain(Model model,@RequestParam(defaultValue = "0") int market_code, HttpSession session) throws Exception {
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
	    	session.setAttribute("viewUpdateStatus", 1);
		    
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
	public void productMain(@RequestParam("product_code") int product_code, Model model, HttpSession session, QuestionVO qvo) throws Exception{
		logger.debug(" productMain() 호출 ");
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String user_id = userVO.getUser_id();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("product_code", product_code);
		ProductVO product = mService.eachProduct(product_code);
		model.addAttribute("product", product);
		List<QuestionVO> question = mService.newQuestion(product_code);
		List<ReviewVO> review = mService.productReview(product_code);
		model.addAttribute("question", question);
		model.addAttribute("review", review);
		//mService.writeQuestion(qvo);
		//model.addAttribute("user_id", user_id);

	}
	
	@RequestMapping(value = "/productMain", method = RequestMethod.POST, consumes = "application/json")
	public void questionAdd(@RequestBody QuestionVO question, HttpSession session) throws Exception{
		logger.debug(" questionAddPOST 실행 ");
		logger.debug(" qvo : " + question);
		mService.writeQuestion(question);
	}
	
	
	@RequestMapping(value = "/questionMain", method = RequestMethod.GET)
	public void questionMain(@RequestParam("product_code") int product_code, Criteria cri, Model model) throws Exception {
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(mService.questionCount());
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("product_code", product_code);
		ProductVO product = mService.eachProduct(product_code);
		paramMap.put("startPage", pageVO.getStartPage());
		paramMap.put("pageSize", cri.getPageSize());
	
		List<QuestionVO> question = mService.getQuestion(paramMap);
		model.addAttribute("question", question);
		model.addAttribute("product", product);
		model.addAttribute("cri", cri);
		model.addAttribute("pageVO", pageVO);
	}
	
	@RequestMapping(value = "/questionDetail", method = RequestMethod.GET)
	public void questionDetail(Model model, @RequestParam("q_code") int q_code) throws Exception{
		logger.debug("questionDetil 호출 ");
		QuestionVO detail = mService.questionDetail(q_code);
		model.addAttribute("detail", detail);
		logger.debug("detail >>>>>>>>>>>>" + detail);
	}
	
	
	
//	// 상품 POST 페이지
//	@RequestMapping(value = "/productMain", method = RequestMethod.POST)
//	public void productMainPOST(@RequestParam("product_code") int product_code, Model model, HttpSession session, QuestionVO qvo) throws Exception{
//		logger.debug(" productMainPOST() 호출 ");
//		//mService.writeQuestion(qvo);
//
//	}
	
}
