package com.itwillbs.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.AuthVO;
import com.itwillbs.domain.UserVO;
import com.itwillbs.service.UserService;

@Controller
@RequestMapping(value = "/member/*")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	private UserService uService;
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET() throws Exception{
		logger.debug(" registerGET() 호출 ");
		
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(UserVO uvo) throws Exception{
		logger.debug(" registerPOST(UserVO uvo) 호출 ");
		logger.debug(" 회원가입 정보 : " + uvo);
		
		uService.userInsert(uvo);
		return "redirect:/member/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET() throws Exception{
		logger.debug(" loginGET() 호출");
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPOST(UserVO uvo, HttpSession session) throws Exception{
		logger.debug(" loginPOST() 호출 ");
		AuthVO authVO = uService.loginUser(uvo);
		logger.debug(" 로그인 정보 : " + authVO);
		
		try {
			if(!authVO.getAuth().isEmpty()) {
				session.setAttribute("authVO", authVO);
				
				return "redirect:/";
			}
		}catch(Exception e) {}
		logger.debug(" 로그인 실패 ");
		
		return "/member/login";
	}
	
	@GetMapping(value = "/logout")
	public String logoutGET(HttpSession session) throws Exception{
		logger.debug(" logoutGET(HttpSession session) 실행 ");
		session.invalidate();
		return "redirect:/member/login";
	}
	
	
	
	
	
	
	
	
	
	
	
}
