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

import com.itwillbs.domain.Cart2VO;
import com.itwillbs.domain.MyPageCriteria;
import com.itwillbs.domain.PageMaker;
import com.itwillbs.service.MyPageService;

@Controller
@RequestMapping(value = "/mypage/*")
public class MyPageController {

	private static final Logger logger = LoggerFactory.getLogger(MyPageController.class);

	@Inject
	private MyPageService mService;

	/* 장바구니 */
	// 장바구니 상품 목록
	@GetMapping(value = "/cart")
	public String cartListGET(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("cri") MyPageCriteria cri, Model model) throws Exception {
		logger.debug(" === cartListGET() 실행 ===");

		Cart2VO cvo = new Cart2VO();

		// 세션 가져오기
		String userid = (String) session.getAttribute("user_id");
		String username = (String) session.getAttribute("user_name");
		String bundleCode = (String) session.getAttribute("cart");

		// cvo 담기...
		cvo.setUser_id(userid);
		cvo.setBundle_code(bundleCode);

		// 장바구니 리스트
		List<Cart2VO> cartList = mService.selectCartList(cvo);

		PageMaker pageMaker = new PageMaker();

		pageMaker.setCri(cri);
		pageMaker.setTotalCount(mService.selectCountCart(cvo));

		logger.debug("pageMaker : " + pageMaker);
		logger.debug("cri : " + cri);
		model.addAttribute("cartList", cartList);
		model.addAttribute("pageMaker", pageMaker);

		return "/mypage/cart";
	}

	// 장바구니 상품 선택 삭제
	@PostMapping(value = "/cart/deleteChecked")
	public String deleteCheckedCartProducts(@RequestParam("checkList") String[] strCheckList) throws Exception {
		logger.debug(" === deleteCheckedCartProducts() 실행 ===");

		int[] checkList = new int[strCheckList.length];
		for (int i = 0; i < strCheckList.length; i++) {
			checkList[i] = Integer.parseInt(strCheckList[i]);
			logger.debug(" checkList " + checkList[i]);
			mService.deleteCartProduct(checkList[i]);
		}
		return "redirect:/mypage/cart";
	}

	// 장바구니 상품 전체 삭제
	@PostMapping(value = "/cart/deleteAll")
	public String deleteAllCartProducts(HttpSession session, 
										HttpServletRequest request, 
										HttpServletResponse response) throws Exception {
		logger.debug(" === deleteAllCartProducts() 실행 ===");
		
		Cart2VO cvo = new Cart2VO();
		// 세션 가져오기
		String userid = (String) session.getAttribute("user_id");
		String bundleCode = (String) session.getAttribute("cart");

		cvo.setUser_id(userid);
		cvo.setBundle_code(bundleCode);
		
		mService.deleteCartAllProduct(cvo);
		
		return "redirect:/mypage/cart";
	}

	// 장바구니 상품 개별 삭제
	@PostMapping(value = "/cart/delete")
	public String deleteCartProducts(@RequestParam("ap_check") String cart_code ) throws Exception {
		logger.debug(" === deleteCartProducts() 실행 ===");
		
		mService.deleteCartProduct(Integer.parseInt(cart_code));
		
		return "redirect:/mypage/cart";
	}

	// 장바구니 상품 수량 변경
	@PostMapping(value = "/cart/updateCount")
	public String updateCartProductsCount(@RequestParam("cartCode") int cartCode, 
											@RequestParam("newCount") int newCount) throws Exception {
		logger.debug(" === updateCartProductsCount() 실행 ===");
		
		Cart2VO cvo = new Cart2VO();
		cvo.setCart_code(cartCode);
		cvo.setCount(newCount);
		
		mService.updateProductCount(cvo);
		
		return "redirect:/mypage/cart";
	}
}
