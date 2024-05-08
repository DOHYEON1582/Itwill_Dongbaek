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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;


import com.itwillbs.domain.CartVO;
import com.itwillbs.domain.AnswerVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.domain.MarkVO;
import com.itwillbs.domain.MarketVO;
import com.itwillbs.domain.PageVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.QuestionVO;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.domain.StoreVO;
import com.itwillbs.domain.UserVO;
import com.itwillbs.domain.WishVO;
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
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		// 사용자 찜상품 표시
		if(userVO != null) {
		String user_id = userVO.getUser_id();
		model.addAttribute("user_id", user_id);
		List<WishVO> userWishList = mService.selectWish(user_id);
		model.addAttribute("userWishList", userWishList);
		logger.debug("userWishList>>>>>>>>>" + userWishList);
		}
		session.setAttribute("viewUpdateStatus", 1);
    }
	
	// http://localhost:8088/market/storeMain
	// 가게 메인페이지
	@RequestMapping(value = "/storeMain", method = RequestMethod.GET)
	public void storeMain(@RequestParam(name = "orderBy", required = false, defaultValue = "popularity") String orderBy, 
			@RequestParam("store_code") int store_code, Model model, HttpSession session) throws Exception{
		logger.debug(" storeMain() 호출 ");
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String user_id = userVO.getUser_id();
		StoreVO store = mService.selectStore(store_code);
		List<ProductVO> product = mService.productOnStore(orderBy, store_code);
		model.addAttribute("store", store);
		model.addAttribute("product", product);
		model.addAttribute("user_id", user_id);
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
	public void productMain(@RequestParam("product_code") int product_code, Model model, HttpSession session) throws Exception{
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
	
	@RequestMapping(value = "/productMain", method = RequestMethod.POST, consumes = "application/json")
	public void questionAdd(@RequestBody QuestionVO question, HttpSession session) throws Exception{
		logger.debug(" questionAddPOST 실행 ");
		logger.debug(" qvo : " + question);
		mService.writeQuestion(question);
	}
	
	// 제품 상세보기
	@RequestMapping(value = "/productDetail", method = RequestMethod.GET)
	public void productDetailGET(@RequestParam("q_code") int q_code, Model model) throws Exception {
		logger.debug(" productDetailGET() 제품 상세보기 실행 ");
		logger.debug("q_code : " + q_code);
		
		List<QuestionVO> detailList = mService.questionDetail(q_code);
		List<AnswerVO> answerList = mService.selectAnswer(q_code);
		logger.debug("list.size : " + detailList.size());
		
		model.addAttribute("detailList", detailList);
		model.addAttribute("answerList", answerList);
	}
	@PostMapping(value = "/productDetail")
	public ResponseEntity<String> qAnswer(@RequestBody AnswerVO avo) throws Exception {
		logger.debug(" qAnswer() 실행 ");
        
		try {
			mService.qAnswer(avo);
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.contentType(MediaType.valueOf("text/plain; charset=UTF-8"))
					.body("실패");
		}
		return ResponseEntity.ok()
				.contentType(MediaType.valueOf("text/plain; charset=UTF-8"))
				.body("성공");
	}
	@PostMapping("/checkDuplicateAnswer")
	public ResponseEntity<String> checkDuplicateAnswer(@RequestParam("q_code") int q_code) throws Exception {
	    logger.debug(" checkDuplicateAnswer() 실행 ");
	    boolean isDuplicate = mService.isDuplicateAnswer(q_code);
	    if (isDuplicate) {
	        return ResponseEntity.ok("true");
	    } else {
	        return ResponseEntity.ok("false");
	    }
	}
	
	@RequestMapping(value = "/questionMain", method = RequestMethod.GET)
	public void questionMain(@RequestParam("product_code") int product_code, Criteria cri, Model model) throws Exception {
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(mService.questionCount());
		logger.debug("pagevo" + pageVO);
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("product_code", product_code);
		ProductVO product = mService.eachProduct(product_code);
		paramMap.put("cri", cri);
		
		logger.debug("paramMap" + paramMap);
	
		List<QuestionVO> question = mService.getQuestion(paramMap);
		model.addAttribute("question", question);
		model.addAttribute("product", product);
		model.addAttribute("cri", cri);
		model.addAttribute("pageVO", pageVO);
	}
	
	// 검색기능
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String search(@RequestParam("query") String query, @RequestParam("type") String type, Model model) throws Exception {
	    logger.debug("search() 호출");
	    
	    // type에 따라 다른 페이지로 리다이렉트
	    if (type.equals("market")) {
	        if (query.contains("자갈치")) {
	            return "redirect:/market/marketMain?market_code=2";
	        } else if (query.contains("구포")) {
	            return "redirect:/market/marketMain?market_code=1";
	        } else {
	            return "redirect:/";
	        }
	    } else if (type.equals("product")) {
	    	
	        return "redirect:/member/product"; // productMain.jsp 페이지로 리다이렉트
	    } 
	    return "redirect:/"; // 기본 페이지로 리다이렉트
	}


	@RequestMapping(value = "/storeMain", method = RequestMethod.POST, consumes = "application/json")
	public void storeMainPOST(HttpSession session, @RequestBody MarkVO mvo) throws Exception{
		logger.debug("storeMainPOST 호출 ");
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String user_id = userVO.getUser_id();
		mService.markStore(mvo);
		logger.debug("mvo " + mvo);
	}
	
	@RequestMapping(value = "/addWish", method = RequestMethod.POST, consumes = "application/json")
	public void addWish(HttpSession session, @RequestBody WishVO wish) throws Exception{
		logger.debug(" addWish 호출 ");
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String user_id = userVO.getUser_id();
		wish.setUser_id(user_id);
		mService.wishProduct(wish);
		logger.debug("wish >>>>>>>>>>>>>" + wish);
	}
	
	@RequestMapping(value = "/addCart", method = RequestMethod.POST, consumes = "application/json")
	public void addCart(@RequestBody CartVO cart, HttpSession session) throws Exception{
		logger.debug(" addCart 호출 ");
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String user_id = userVO.getUser_id();
		cart.setUser_id(user_id);
		mService.insertCart(cart);
		logger.debug(" cart >>>>>>>>>>>>> " + cart);
	}
}
