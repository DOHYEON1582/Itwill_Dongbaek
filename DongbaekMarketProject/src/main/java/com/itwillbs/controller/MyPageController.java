package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.domain.CartVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.domain.PageMaker;
import com.itwillbs.service.MyPageService;

@Controller
@RequestMapping(value = "/mypage/*")
public class MyPageController {

	private static final Logger logger = LoggerFactory.getLogger(MyPageController.class);

	@Inject
	private MyPageService cService;

	// 장바구니 상품 목록
	@GetMapping(value = "/cart")
	public String cartListGET(HttpSession session, 
							  HttpServletRequest request, 
							  HttpServletResponse response,
							  @ModelAttribute("cri") Criteria cri,
							  Model model)
			throws Exception {
		logger.debug(" === cartListGET() 실행 ===");

		CartVO cvo = new CartVO();

		// 세션 가져오기
		String userid = (String) session.getAttribute("user_id");
		String username = (String) session.getAttribute("user_name");
		String bundleCode = (String) session.getAttribute("cart");

		// cvo 담기...
		cvo.setUser_id(userid);
		cvo.setBundle_code(bundleCode);

		// 장바구니 리스트
		List<CartVO> cartList = cService.getCartList(cvo);
		
		PageMaker pageMaker = new PageMaker();
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(cService.getCartNum(cvo));

		logger.debug("pageMaker : " + pageMaker);
		logger.debug("cri : " + cri);
		model.addAttribute("cartList", cartList);
		model.addAttribute("pageMaker",pageMaker); 
		
		return "/mypage/cart";
	}
	
	// 장바구니 상품 수량 변경
	@PostMapping(value="/cart/modify")
	public String modifyProductCount() throws Exception {
		logger.debug(" === modifyProductCount() 실행 ===");
		
		
		return "redirect:/mypage/cart";
	}
	
	// 장바구니 상품 선택 삭제
	@PostMapping(value="/cart/remove")
	public String cartRemovePOST(@RequestParam("checkList") String[] strCheckList) throws Exception{
		logger.debug(" === cartRemovePOST() 실행 ===");
		
		int[] checkList = new int[strCheckList.length];
		for (int i = 0; i < strCheckList.length; i++) {
			checkList[i] = Integer.parseInt(strCheckList[i]);
			logger.debug(" checkList " + checkList[i]);
			cService.removeCartProduct(checkList[i]);
		}
		
		return "redirect:/mypage/cart";
	}
	

}
