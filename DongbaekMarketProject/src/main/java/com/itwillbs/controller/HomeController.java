package com.itwillbs.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void main()throws Exception {
		logger.debug(" main() 호출 "); 


	}
	
	@RequestMapping(value = "/admin/main", method = RequestMethod.GET)
	public void adminMain()throws Exception {
		logger.debug(" adminMain() 호출 "); 


	}
	
	@RequestMapping(value = "/admin/notice", method = RequestMethod.GET)
	public void adminNotice()throws Exception {
		logger.debug(" adminNotice() 호출 "); 


	}
	
	@RequestMapping(value = "/admin/customer", method = RequestMethod.GET)
	public void adminCustomer()throws Exception {
		logger.debug(" adminCustomer() 호출 "); 


	}
	
	@RequestMapping(value = "/admin/customerorder", method = RequestMethod.GET)
	public void customerOrder()throws Exception {
		logger.debug(" customerOrder() 호출 "); 


	}
	
	@RequestMapping(value = "/admin/customerreview", method = RequestMethod.GET)
	public void customerReview()throws Exception {
		logger.debug(" customerReview() 호출 "); 


	}
	
	@RequestMapping(value = "/admin/shop", method = RequestMethod.GET)
	public void shop()throws Exception {
		logger.debug(" shop() 호출 "); 


	}

	@RequestMapping(value = "/admin/shoporder", method = RequestMethod.GET)
	public void shopOrder()throws Exception {
		logger.debug(" shopOrder() 호출 "); 


	}
	
	@RequestMapping(value = "/admin/shopcal", method = RequestMethod.GET)
	public void shopCal()throws Exception {
		logger.debug(" shopCal() 호출 "); 
		
		
	}
	
	
	
	
}//endController
