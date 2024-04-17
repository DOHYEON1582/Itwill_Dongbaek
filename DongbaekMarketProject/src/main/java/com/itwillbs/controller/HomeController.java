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
	public void mainAdmin()throws Exception {
		logger.debug(" main() 호출 "); 


	}
	
}//endController
