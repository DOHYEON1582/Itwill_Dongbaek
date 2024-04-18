package com.itwillbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/seller/*")
public class SellerPageController {

	
	private static final Logger logger = LoggerFactory.getLogger(SellerPageController.class);
	
    //	http://localhost:8088/seller/sellermain
	@RequestMapping(value = "/sellermain",method = RequestMethod.GET)
	public void main() throws Exception{
		logger.debug(" main() 실행 ");
	}
}
