package com.itwillbs.controller;

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

import com.itwillbs.domain.AuthVO;
import com.itwillbs.domain.MarketVO;
import com.itwillbs.domain.UserVO;
import com.itwillbs.persistence.UserDAO;
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
	private UserDAO udao;
	
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
	
	@RequestMapping(value = "member/login", method = RequestMethod.GET)
	public void loginGET() throws Exception{
		logger.debug(" loginGET() 호출");
		
	}
	
	@RequestMapping(value = "member/login", method = RequestMethod.POST)
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
		String user_id = authVO.getId();
		logger.debug(" id : " + user_id);
		model.addAttribute("userinfo", uService.userInfo(user_id));
	}
	
	@RequestMapping(value = "member/update", method = RequestMethod.GET)
	public void userUpdateGET(Model model, HttpSession session) throws Exception {
		logger.debug(" userUpdateGET() 실행 ");
		AuthVO authVO = (AuthVO)session.getAttribute("authVO");
		String user_id = authVO.getId();
		logger.debug(" id : " + user_id);
		model.addAttribute("userinfo", uService.userInfo(user_id));
	}
	
//	@RequestMapping(value = "member/update", method = RequestMethod.POST)
//	public String userUpdatePOST(UserVO uvo) throws Exception {
//		logger.debug(" userUpdatePOST(UserVO uvo) 실행 ");
//		logger.debug(" 수정할 정보 : " + uvo);
//		
//		int result = uService.userUpdate(uvo);
//		if(result == 1) {
//			logger.debug(" 수정완료!! ");
//			return "redirect:/";
//		}
//		logger.debug(" 수정실패!! ");
//		return "redirect:/member/update";
//	}
	@RequestMapping(value = "member/update", method = RequestMethod.POST)
	public String userUpdatePOST(UserVO uvo, Model model) throws Exception {
		// 입력된 비밀번호를 가져와서 해싱합니다.
		String inputPassword = uvo.getUser_pw();
		String salt = udao.getSalt(uvo);
		String hashedPassword = udao.hashPass(inputPassword, salt);
		
		// 데이터베이스에서 현재 사용자의 비밀번호를 가져옵니다.
		String currentPassword = uService.getPass(uvo.getUser_id());
		logger.debug("inputPassword : " + inputPassword);
		logger.debug("salt " + salt);
		logger.debug("hashedPassword : " + hashedPassword);
		logger.debug("currentPassword : " + currentPassword);
		logger.debug("uvo : " + uvo);
		// 입력된 비밀번호와 데이터베이스에 저장된 비밀번호를 비교하여 일치하는지 확인합니다.
		if (hashedPassword.equals(currentPassword)) {
		    // 비밀번호가 일치하면 회원 정보를 업데이트합니다.
			logger.debug("비밀번호 일치");
		    int result = uService.userUpdate(uvo);
		    logger.debug("Inuvo : " + uvo);
		    logger.debug("result : " + result);
		    if (result == 1) {
		        logger.debug(" 회원 정보 수정 완료 ");
		        return "redirect:/";
		    } else {
		        logger.debug(" 회원 정보 수정 실패 ");
		        model.addAttribute("error", "회원 정보 수정에 실패하였습니다.");
		        return "member/update";
		    }
		} else {
		    // 비밀번호가 일치하지 않을 경우, 다시 회원 정보 수정 페이지로 이동합니다.
		    logger.debug(" 비밀번호가 일치하지 않습니다. ");
		    model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
		    return "member/update";
		}
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
