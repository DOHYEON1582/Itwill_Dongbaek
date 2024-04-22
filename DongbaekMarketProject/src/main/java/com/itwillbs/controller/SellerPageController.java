package com.itwillbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/seller/*")
public class SellerPageController {//판매자 페이지 컨트롤러

	
	private static final Logger logger = LoggerFactory.getLogger(SellerPageController.class);
	
	// 판매자 메인페이지
    //	http://localhost:8088/seller/sellermain
	@RequestMapping(value = "/sellermain",method = RequestMethod.GET)
	public void main() throws Exception{
		logger.debug(" main() 실행 ");
	}
	
	// 판매자 상품페이지(상품목록)
	//	http://localhost:8088/seller/product
	@RequestMapping(value = "/product",method = RequestMethod.GET)
	public void product() throws Exception{
		logger.debug(" product() 실행 ");
	}
	
	// 판매자 상품페이지(상품등록)
	//	http://localhost:8088/seller/productregist
	@RequestMapping(value = "/productregist",method = RequestMethod.GET)
	public void productregist() throws Exception{
		logger.debug(" productregist() 실행 ");
	}
	
	// 판매자 상품페이지(상품수정)
	//	http://localhost:8088/seller/productmodify
	@RequestMapping(value = "/productmodify",method = RequestMethod.GET)
	public void productmodify() throws Exception{
		logger.debug(" productmodify() 실행 ");
	}
	
	// 판매자 주문페이지(주문목록)
	//	http://localhost:8088/seller/orderlist
	@RequestMapping(value = "/orderlist",method = RequestMethod.GET)
	public void orderlist() throws Exception{
		logger.debug(" orderlist() 실행 ");
	}
	
	// 판매자 주문페이지(신규주문)
	//	http://localhost:8088/seller/neworder
	@RequestMapping(value = "/neworder",method = RequestMethod.GET)
	public void neworder() throws Exception{
		logger.debug(" neworder() 실행 ");
	}
	
	// 판매자 주문페이지(주문 확정)
	//	http://localhost:8088/seller/orderconfirm
	@RequestMapping(value = "/orderconfirm",method = RequestMethod.GET)
	public void orderconfirm() throws Exception{
		logger.debug(" orderconfirm() 실행 ");
	}
	
	// 판매자 주문페이지(주문취소/환불)
	//	http://localhost:8088/seller/ordercancel
	@RequestMapping(value = "/ordercancel",method = RequestMethod.GET)
	public void ordercancel() throws Exception{
		logger.debug(" ordercancel() 실행 ");
	}

	// 판매자 배송페이지
	//	http://localhost:8088/seller/dilivery
	@RequestMapping(value = "/dilivery",method = RequestMethod.GET)
	public void dilivery() throws Exception{
		logger.debug(" dilivery() 실행 ");
	}

	// 판매자 리뷰페이지
	//	http://localhost:8088/seller/review
	@RequestMapping(value = "/review",method = RequestMethod.GET)
	public void review() throws Exception{
		logger.debug(" review() 실행 ");
	}
	
	// 판매자 매출페이지
	//	http://localhost:8088/seller/sales
	@RequestMapping(value = "/sales",method = RequestMethod.GET)
	public void sales() throws Exception{
		logger.debug(" sales() 실행 ");
	}
	
	// 판매자 문의페이지
	//	http://localhost:8088/seller/question
	@RequestMapping(value = "/question",method = RequestMethod.GET)
	public void question() throws Exception{
		logger.debug(" question() 실행 ");
	}
}
