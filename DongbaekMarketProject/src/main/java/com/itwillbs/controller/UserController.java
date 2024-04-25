package com.itwillbs.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.domain.AuthVO;
import com.itwillbs.domain.MarketVO;
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
	@Inject
	private PasswordEncoder pwEncoder;
	
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
		AuthVO authVO = uService.loginUser(uvo);
		logger.debug(" 로그인 정보 : " + authVO);
		
		if (authVO != null) {
            session.setAttribute("authVO", authVO);
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
		AuthVO authVO = (AuthVO)session.getAttribute("authVO");
		String user_id = authVO.getUser_id();
		logger.debug(" id : " + user_id);
		model.addAttribute("userinfo", uService.userInfo(user_id));
	}
	
	@RequestMapping(value = "member/update", method = RequestMethod.GET)
	public void userUpdateGET(Model model, HttpSession session) throws Exception {
		logger.debug(" userUpdateGET() 실행 ");
		AuthVO authVO = (AuthVO)session.getAttribute("authVO");
		String user_id = authVO.getUser_id();
		logger.debug(" id : " + user_id);
		model.addAttribute("userinfo", uService.userInfo(user_id));
	}
	@RequestMapping(value = "/member/update", method = RequestMethod.POST)
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
	@RequestMapping(value = "/member/delete", method = RequestMethod.GET)
	public String deleteUserGET() throws Exception {
		logger.debug(" deleteUserGET() 호출 ");
		
		return "/member/delete";
	}
	@RequestMapping(value = "/member/delete", method = RequestMethod.POST)
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
	
	@RequestMapping(value = "/member/cart", method = RequestMethod.GET)
	public void cart() throws Exception {
		logger.debug(" cart() 호출 ");
		
	}
	
	// 회원정보 수정시 비밀번호 확인
	@RequestMapping(value = "/checkPassword", method = RequestMethod.POST)
	public String checkPassPost(@RequestParam("user_pw") String user_pw, Authentication authentication) throws Exception {
	    logger.debug("checkPassPost() 실행");

	    // 사용자 인증 확인
	    if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
	        logger.debug("인증되지 않은 사용자");
	        return "redirect:/login"; // 로그인 페이지로 이동
	    }

	    // 사용자 ID 가져오기
	    String user_id = authentication.getName();
	    if (user_id == null) {
	        logger.debug("ID가 없습니다.");
	        return "redirect:/";
	    }

	    // 사용자의 저장된 비밀번호 가져오기
	    String storedPw = uService.getPass(user_id);

	    // 입력된 비밀번호와 저장된 비밀번호를 암호화하여 비교합니다.
	    if (pwEncoder.matches(user_pw, storedPw)) {
	        logger.debug("비밀번호 일치");
	        return "redirect:/modifyPage"; // 수정 페이지로 이동
	    } else {
	        logger.debug("비밀번호 불일치");
	        return "redirect:/incorrectPassword"; // 비밀번호가 일치하지 않을 때의 페이지로 이동
	    }

		
//		AuthVO authVO = (AuthVO)session.getAttribute("authVO");
//		String user_id = authVO.getUser_id();
//		if(user_id == null) {
//			logger.debug(" id가 없다.");
//			return "redirect:/";
//		}
//		String storedPw = uService.getPass(user_id);
	    
		// 입력된 비밀번호와 저장된 비밀번호를 암호화하여 비교합니다.
//		if(pwEncoder.matches(user_pw, storedPw)) {
//			logger.debug(" user_pw : " + user_pw);
//			logger.debug(" 비밀번호 일치 : " + storedPw);
//			return "success";
//		}
//		logger.debug(" user_pw : " + user_pw);
//		logger.debug(" 비밀번호 일치 하지 않음 : " + storedPw);
//		return "false";
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
