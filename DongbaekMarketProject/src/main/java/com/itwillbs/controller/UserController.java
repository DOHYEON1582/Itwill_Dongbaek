package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.domain.MarketVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.UserVO;
import com.itwillbs.service.MainService;
import com.itwillbs.service.UserService;

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	private UserService uService;
	@Inject
	private MainService mService;
	
	// http://localhost:8088/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, MarketVO mvo) throws Exception {
		logger.info("home");
		
		model.addAttribute("getMarket", mService.getMarket(mvo));
		return "main";
	}
	
	@ResponseBody
	@PostMapping(value = "/get")
	public MarketVO getMarketPOST(@RequestParam("market_code") int market_code) throws Exception{
		logger.debug("getMarketPOST(@RequestParam(\"market_code\") String marketCode) 호출");
		
		MarketVO marketInfo = mService.getMarketCode(market_code);

	    return marketInfo;
	}
	
	@RequestMapping(value = "/introduce", method = RequestMethod.GET)
	public void introMarket() throws Exception {
		logger.debug(" introMarket() 호출");
	}
	
	@RequestMapping(value = "member/register", method = RequestMethod.GET)
	public void registerGET() throws Exception{
		logger.debug(" registerGET() 호출 ");
		
	}
	
	@RequestMapping(value = "member/register", method = RequestMethod.POST)
	public String registerPOST(UserVO uvo) throws Exception{
		logger.debug(" registerPOST(UserVO uvo) 호출 ");
		logger.debug(" 회원가입 정보 : " + uvo);
		
		uService.userInsert(uvo);
		return "redirect:/member/login";
	}
	
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public void loginGET() throws Exception{
		logger.debug(" loginGET() 호출");
	}
	
	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	public String loginPOST(UserVO uvo, HttpSession session) throws Exception{
		logger.debug(" loginPOST() 호출 ");
		
		UserVO userVO = uService.loginUser(uvo);
		logger.debug(" 로그인 정보 : " + userVO);
		
		if (userVO != null) {
            session.setAttribute("userVO", userVO);
            return "redirect:/";
        } else {
            return "/member/login";
        }
	}
	
	@GetMapping(value = "member/logout")
	public String logoutGET(HttpSession session) throws Exception{
		logger.debug(" logoutGET(HttpSession session) 실행 ");
		session.invalidate();
		return "redirect:/member/login";
	}
	
	@RequestMapping(value = "member/info", method = RequestMethod.GET)
	public void infoGET(Model model, HttpSession session) throws Exception {
		logger.debug(" infoGET() 실행 ");
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String user_id = userVO.getUser_id();
		logger.debug(" id : " + user_id);
		model.addAttribute("userinfo", uService.userInfo(user_id));
	}
	
	@RequestMapping(value = "member/update", method = RequestMethod.GET)
	public void userUpdateGET(Model model, HttpSession session) throws Exception {
		logger.debug(" userUpdateGET() 실행 ");
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String user_id = userVO.getUser_id();
		logger.debug(" id : " + user_id);
		model.addAttribute("userinfo", uService.userInfo(user_id));
	}
	@RequestMapping(value = "member/update", method = RequestMethod.POST)
	public String userUpdatePOST(UserVO uvo) throws Exception {
		logger.debug(" userUpdatePOST(UserVO uvo) 호출 ");
		logger.debug("uvo : " + uvo);
		int result = uService.userUpdate(uvo);
		if(result == 1) {
			logger.debug(" 수정완료! ");
			return "redirect:/";
		}
		logger.debug(" 수정실패! ");
		return "redirect:/member/update";
	}
	
	//회원정보 삭제
	@RequestMapping(value = "member/delete", method = RequestMethod.GET)
	public String deleteUserGET() throws Exception {
		logger.debug(" deleteUserGET() 호출 ");
		
		return "/member/delete";
	}
	@RequestMapping(value = "member/delete", method = RequestMethod.POST)
	public String deleteUserPOST(UserVO uvo, HttpSession session) throws Exception {
		logger.debug(" deleteUserPOST() 호출 ");
		logger.debug(" 삭제할 정보 : " + uvo);
		int result = uService.deleteUser(uvo);
		logger.debug("result : " + result);
		if(result == 1) {
			session.invalidate();
			return "redirect:/";
		}
		logger.debug(" 비밀번호 오류 ");
		return "redirect:/member/delete";
	}
	
	@RequestMapping(value = "member/wish", method = RequestMethod.GET)
	public void wishGET(HttpSession session, Model model) throws Exception {
		logger.debug(" wishGET() 호출 ");
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String user_id = userVO.getUser_id();
		logger.debug(" user_id : " + user_id);
		List<ProductVO> wishList = uService.wishList(user_id);
		logger.debug("wishList : " + wishList);
		model.addAttribute("wishList", wishList);
	}
	
	@RequestMapping(value = "member/deleteWish", method = RequestMethod.POST)
	public String deleteWish(int product_code) throws Exception {
		logger.debug(" deleteWish() 호출 ");
		logger.debug("product_code " + product_code);
		uService.deleteWish(product_code);
		
		return "redirect:/member/wish";
	}
	
	
	
	
	
	
	
}