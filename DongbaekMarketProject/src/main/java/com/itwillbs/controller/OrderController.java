package com.itwillbs.controller;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.domain.CartVO;
import com.itwillbs.service.OrderService;
import com.siot.IamportRestClient.IamportClient;

@Controller
@RequestMapping(value = "/order/*")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Inject
	private OrderService oService;
	
	private IamportClient api;

	public OrderController(){		
		//토큰 발급
		this.api = new IamportClient("4412056010508705","t7oYBzO2MlZ3YgFYXDpwwD40JbHJnOmnMWuClc6nvcmZfKipioc8gF9t0igZSoj0qrtvLYgPww8AgOr9");
	}
	
	// 240429 같은 가게 상품만 주문하기!!!!!
	// 주문페이지
	@GetMapping(value = "/orderform")
	public void orderFormGET(HttpSession session,
			@RequestParam(required = false, value = "checkList") String[] strCheckList,
			@RequestParam(required = false) String cartCode, Model model) throws Exception {
		logger.debug(" === orderFormPOST() 실행 === ");

		String userid = (String) session.getAttribute("user_id");

		CartVO cvo = new CartVO();
		
		// 상품갯수
		int productNum = 0;
		
		/* 주문 할 상품 리스트 */
		// 상품 선택 주문
		List<CartVO> cartList = new ArrayList<CartVO>();
		if (strCheckList != null && cartCode == null) {
			int[] checkList = new int[strCheckList.length];
			for (int i = 0; i < strCheckList.length; i++) {
				checkList[i] = Integer.parseInt(strCheckList[i]);
				logger.debug(" checkList " + checkList[i]);
				cvo = oService.selectProductInfo(checkList[i]);
				cartList.add(cvo);
				productNum = strCheckList.length;
			}
		}
		// 상품 개별 주문
		if (cartCode != null && strCheckList == null) {
			cvo = oService.selectProductInfo(Integer.parseInt(cartCode));
			cartList.add(cvo);
			productNum = 1;
		}
		// 상품 전체 주문
		if (strCheckList != null && cartCode != null) {
			cartList = oService.selectCartList(cvo);
			productNum = oService.selectCountCart(cvo);
		}
		// 적립금
		String point = oService.selectUserPoint(userid);

		model.addAttribute("cartList", cartList);
		model.addAttribute("productNum", productNum);
		model.addAttribute("point", point);
	}

	// 주문 번호 생성
	@PostMapping(value = "/pay")
	public int orderPayPOST() throws Exception {
		logger.debug(" === orderPayPOST() 실행 === ");
		
		/* 주문번호 생성 */
		// 오늘 날짜 가져오기
		LocalDate now = LocalDate.now();
		// 포맷 정의
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
		// 포맷 적용
		String today = now.format(formatter);
		// 숫자 출력 서식 정하기
		DecimalFormat dc = new DecimalFormat("0000");
		int orderCode = 0;
		int result = oService.selectMaxOrderCode();
		if(result == 0) {
			logger.debug(" ===order_code=== : " + today+"0001");
			orderCode = (Integer.parseInt(today+"0001"));
		} else if(result != 0) {
			logger.debug(" ===result=== : " + result);
			logger.debug(" ===result + 1=== : " + (result+1));
			orderCode = (result+1);
		}
		logger.debug(" ===orderCode=== : " + orderCode);
		
		return orderCode;
	}
	

}
