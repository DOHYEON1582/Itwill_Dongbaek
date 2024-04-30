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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.domain.CartVO;
import com.itwillbs.domain.OrderInfoVO;
import com.itwillbs.dto.OrderInfoDTO;
import com.itwillbs.service.MyPageService;
import com.itwillbs.service.OrderService;

@Controller
@RequestMapping(value = "/order/*")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Inject
	private OrderService oService;

	// 240429 같은 가게 상품만 주문하기!!!!!
	// 주문페이지
	@GetMapping(value = "/orderform")
	public void orderFormGET(HttpSession session,
			@RequestParam(required = false, value = "checkList") String[] strCheckList,
			@RequestParam(required = false) String cartCode, Model model) throws Exception {
		logger.debug(" === orderFormPOST() 실행 === ");

		String userid = (String) session.getAttribute("user_id");

		CartVO cvo = new CartVO();
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
			}
		}
		// 상품 개별 주문
		if (cartCode != null && strCheckList == null) {
			cvo = oService.selectProductInfo(Integer.parseInt(cartCode));
			cartList.add(cvo);
		}
		// 상품 전체 주문
		if (strCheckList != null && cartCode != null) {
			cartList = oService.selectCartList(cvo);
		}
		// 적립금
		String point = oService.selectUserPoint(userid);

		model.addAttribute("cartList", cartList);
		model.addAttribute("point", point);
	}

	// 결제하기
	@ResponseBody
	@PostMapping(value = "/pay")
	public OrderInfoVO orderPayPOST(@ModelAttribute OrderInfoVO ovo) throws Exception {
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
		int result = oService.selectMaxOrderCode();
		if(result == 0) {
			logger.debug(" ===order_code=== : " + today+"0001");
			ovo.setOrder_code(Integer.parseInt(today+"0001"));
		} else if(result != 0) {
			logger.debug(" ===result=== : " + result);
			logger.debug(" ===result + 1=== : " + (result+1));
			ovo.setOrder_code(result+1);
		}
		logger.debug(" ===ovo=== : " + ovo);
		
		// 주문 정보 입력
		oService.insertOrderInfo(ovo);
	
		return ovo;
	}

}
