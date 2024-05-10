package com.itwillbs.controller;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.domain.CartVO;
import com.itwillbs.domain.UserVO;
import com.itwillbs.service.OrderService;
import com.siot.IamportRestClient.IamportClient;

@Controller
@RequestMapping(value = "/order/*")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Inject
	private OrderService oService;

	private IamportClient api;

	public OrderController() {
		// 토큰 발급
		this.api = new IamportClient("4412056010508705",
				"t7oYBzO2MlZ3YgFYXDpwwD40JbHJnOmnMWuClc6nvcmZfKipioc8gF9t0igZSoj0qrtvLYgPww8AgOr9");
	}

	@GetMapping(value = "/ordersession")
	public void orderSessionGET() throws Exception {
		logger.debug(" === orderSessionGET() 실행 === ");
	}

	// 주문페이지
	@PostMapping(value = "/ordersession")
	public String orderSessionPOST(HttpSession session,
			@RequestParam(required = false, value = "checkList") String[] strCheckList,
			@RequestParam(required = false) String cartCode, Model model) throws Exception {
		logger.debug(" === orderFormPOST() 실행 === ");

		logger.debug(" strCheckList " + strCheckList);
		logger.debug(" cartCode " + cartCode);

		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String user_id = userVO.getUser_id();

		List<CartVO> cartList = new ArrayList<>();
		int productNum = 0;
		
		CartVO cartVO = new CartVO();
		cartVO.getStore_code();
		// 개별 주문 상품 정보
		if (strCheckList != null && cartCode == null) {
			int[] checkList = new int[strCheckList.length];
			for (int i = 0; i < strCheckList.length; i++) {
				checkList[i] = Integer.parseInt(strCheckList[i]);
				logger.debug(" checkList " + checkList[i]);
				cartVO = oService.selectProductInfo(checkList[i]);
				logger.debug(" cartVO :  " + cartVO);

				cartList.add(cartVO);
				productNum = strCheckList.length;
			}
		} else if (cartCode != null && strCheckList == null) { // 상품 선택 주문
			cartVO = oService.selectProductInfo(Integer.parseInt(cartCode));
			logger.debug(" cartVO :  " + cartVO);
			cartList.add(cartVO);
			productNum = 1;
		} else if (strCheckList != null && cartCode != null) { // 상품 전체 주문
			cartVO = oService.selectProductInfo(Integer.parseInt(cartCode));
			logger.debug(" cartVO :  " + cartVO);
			cartList.add(cartVO);
			productNum = oService.selectCountCart(cartVO);
		}

		logger.debug(" post cartList " + cartList);
		logger.debug(" post productNum " + productNum);

		// 선택한 상품 정보를 세션에 담음
		session.setAttribute("selected_cartList", cartList);
		session.setAttribute("selected_productNum", productNum);

		return "redirect:/order/orderform";
	}

	@GetMapping(value = "/orderform")
	public String orderFormGET(HttpSession session, Model model) throws Exception {
		logger.debug(" === orderFormGET() 실행 === ");

		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String user_id = userVO.getUser_id();

		// 세션에서 선택한 상품 정보 가져오기
		List<CartVO> cartList = (List<CartVO>) session.getAttribute("selected_cartList");
		int productNum = (int) session.getAttribute("selected_productNum");

		// 적립금
		String point = oService.selectUserPoint(user_id);

		logger.debug(" === cartList === : " + cartList);
		logger.debug(" === productNum === : " + productNum);
		logger.debug(" === point === : " + point);

		model.addAttribute("cartList", cartList);
		model.addAttribute("productNum", productNum);
		model.addAttribute("point", point);

		return "/order/orderform";
	}

	// 주문 번호 생성
	@PostMapping(value = "/orderCode")
	@ResponseBody
	public int orderCodePOST() throws Exception {
		logger.debug(" === orderCodePOST() 실행 === ");

		/* 주문번호 생성 */
		// 오늘 날짜 가져오기
		LocalDate now = LocalDate.now();
		// 포맷 정의
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
		// 포맷 적용
		String today = now.format(formatter);
		// 숫자 출력 서식 정하기
		DecimalFormat dc = new DecimalFormat("000");
		int orderCode = 0;
		int result = oService.selectMaxOrderCode();
		if (result == 0) {
			logger.debug(" ===order_code=== : " + today + "001");
			orderCode = (Integer.parseInt(today + "001"));
		} else if (result != 0) {
			logger.debug(" ===result=== : " + result);
			logger.debug(" ===result + 1=== : " + (result + 1));
			orderCode = (result + 1);
		}
		logger.debug(" ===orderCode=== : " + orderCode);

		return orderCode;
	}

	@GetMapping(value = "/pay")
	public String orderPayGET(@RequestParam Map<String, Object> orderInfo) throws Exception {
		logger.debug("ㅇㅅㅇ??");
		
		return null;
	}

}
