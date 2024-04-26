package com.itwillbs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.domain.CartVO;
import com.itwillbs.service.MyPageService;
import com.itwillbs.service.OrderService;

@Controller
@RequestMapping(value = "/order/*")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Inject
	private OrderService oService;

	// 240426 같은 가게 상품만 주문 가능하게 구현할건지...
	// order_info store_code 삭제 할 말... 
	
	// 주문페이지
	@GetMapping(value = "/orderform")
	public void orderFormGET(HttpSession session,
			@RequestParam(required = false, value = "checkList") String[] strCheckList,
			@RequestParam(required = false) String cartCode,
			Model model) throws Exception {
		logger.debug(" === orderFormPOST() 실행 === ");
		
		String userid = (String)session.getAttribute("user_id");
		
		CartVO cvo = new CartVO();
		
		// 주문 할 상품 리스트
		List<CartVO> cartList = new ArrayList<CartVO>();
		if(strCheckList != null && cartCode == null) {
			int[] checkList = new int[strCheckList.length];
			for (int i = 0; i < strCheckList.length; i++) {
				checkList[i] = Integer.parseInt(strCheckList[i]);
				logger.debug(" checkList " + checkList[i]);
				cvo = oService.selectProductInfo(checkList[i]);
				cartList.add(cvo);
			}
		}
		if(cartCode != null && strCheckList == null) {
			cvo = oService.selectProductInfo(Integer.parseInt(cartCode));
			cartList.add(cvo);
		}
		
		// 적립금
		oService.selectUserPoint(userid);
		
		
		model.addAttribute("cartList",cartList);
		
	}

}
