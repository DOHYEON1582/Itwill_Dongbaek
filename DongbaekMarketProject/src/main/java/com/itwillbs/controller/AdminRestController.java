package com.itwillbs.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.AdminCartVO;
import com.itwillbs.domain.AdminOrderVO;
import com.itwillbs.domain.UserVO;
import com.itwillbs.service.AdminService;

@RestController
@RequestMapping(value = "/admin")
public class AdminRestController {

	@Inject
	private AdminService aService;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminRestController.class);
	
	@PostMapping(value = "/customer")
	public ResponseEntity<UserVO> userInfo(@RequestBody UserVO vo)throws Exception{
		logger.debug(" vo : "+vo);
		UserVO uvo = aService.getUserInfo(vo);
		logger.debug(" uvo : "+uvo);
	
		return new ResponseEntity<UserVO>(uvo, HttpStatus.OK);
	}
	
	@PostMapping(value = "/customerorder")
	public ResponseEntity<List<AdminOrderVO>> orderInfo(@RequestBody UserVO vo)throws Exception{
		logger.debug(" orderInfo() 호출 ");
		//logger.debug("#########"+aService.getUserOrder(vo).size());
		
		return new ResponseEntity<List<AdminOrderVO>>(aService.getUserOrder(vo), HttpStatus.OK);
	}
	
	@GetMapping(value = "/orderlist/{order_code}")
	public ResponseEntity<List<AdminCartVO>> orderList(@PathVariable("order_code") String order_code)throws Exception{
		logger.debug(" orderList() 호출 ");
		logger.debug("order_code : "+order_code);
		int code = Integer.parseInt(order_code);
		
		return new ResponseEntity<List<AdminCartVO>>(aService.getUserCart(code), HttpStatus.OK);
	}
	
	
	
}//endController
