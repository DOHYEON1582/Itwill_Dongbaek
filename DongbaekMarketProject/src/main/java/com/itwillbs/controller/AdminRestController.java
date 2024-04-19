package com.itwillbs.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	
	
}//endController
