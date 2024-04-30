package com.itwillbs.controller;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	

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
	
	@RequestMapping(value = "/admin/sub", method = RequestMethod.GET)
	public void sub()throws Exception {
		logger.debug(" sub() 호출 "); 
		
		
	}	

	@RequestMapping(value = "/admin/suborder", method = RequestMethod.GET)
	public void subOrder()throws Exception {
		logger.debug(" subOrder() 호출 "); 
		
		
	}	
	
	@RequestMapping(value = "/admin/sublist", method = RequestMethod.GET)
	public void subList()throws Exception {
		logger.debug(" subList() 호출 "); 
		
		
	}	
	
	@RequestMapping(value = "/admin/sublist", method = RequestMethod.POST)
	public void uploadProduct(@RequestParam("img1") MultipartFile file, HttpServletRequest request)throws Exception{
		logger.debug(" uploadProduct(AdminProductVO vo) 호출 ");
		
		String realPath = request.getSession().getServletContext().getRealPath("/upload");
		logger.debug("realPath : "+realPath);
		

		//MultipartRequest multi = new MultipartRequest(
				   //request,
				   //realPath,
				   //maxSize,
				   //"UTF-8",
				   //new DefaultFileRenamePolicy()
				  // ); 
		
		
	}
	
}//endController
