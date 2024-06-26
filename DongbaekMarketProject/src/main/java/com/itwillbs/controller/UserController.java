package com.itwillbs.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.domain.CartVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.domain.MarkVO;
import com.itwillbs.domain.MarketVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.domain.SubscribeProductVO;
import com.itwillbs.domain.UserVO;
import com.itwillbs.domain.WishVO;
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
	public String home(@ModelAttribute("criteria")Criteria cri, Model model, MarketVO mvo) throws Exception {
		logger.info("home");
		model.addAttribute("getMarket", mService.getMarket(mvo));
		return "main";
	}
	
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String search(@RequestParam("query") String query, @RequestParam("type") String type,
			Model model)
	        throws Exception {
	    logger.debug("search() 호출");
	    if (type.equals("market")) {
	        if (query.contains("자갈치")) {
	            return "redirect:/market/marketMain?market_code=2";
	        } else if (query.contains("구포")) {
	            return "redirect:/market/marketMain?market_code=1";
	        } else {
	            return "redirect:/";
	        }
	    } else if (type.equals("product")) {
	        return "redirect:/market/product";
	    }
	    return "redirect:/";
	}

	@ResponseBody
	@PostMapping(value = "/get")
	public MarketVO getMarketPOST(@RequestParam("market_code") int market_code) throws Exception{
		logger.debug("getMarketPOST(@RequestParam(\"market_code\") String marketCode) 호출");
		
		MarketVO marketInfo = mService.getMarketCode(market_code);

	    return marketInfo;
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
	
	// 0509 로그인 cart session 추가
	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	public String loginPOST(UserVO uvo, HttpSession session) throws Exception{
		logger.debug(" loginPOST() 호출 ");
		
		UserVO userVO = uService.loginUser(uvo);
		logger.debug(" 로그인 정보 : " + userVO);
		
		// 장바구니한 상품이 있는지 없는지 조회
		String userid = uvo.getUser_id();
		int cartNum = uService.selectCountCart(userid);
		logger.debug("userid : " + userid);
		logger.debug("cartNum : " + cartNum);
		
		CartVO cvo = null;
		String bundleCode = "";

		if (cartNum != 0) { // 장바구니에 상품이 있는 경우
			cvo = uService.selectBundleCode(userid);
			// bundleCode 생성
			bundleCode = cvo.getBundle_code();
		} else if (cartNum == 0) { // 장바구니에 상품이 없는 경우
			// 오늘 날짜 불러오기
			LocalDate today = LocalDate.now();
			// 날짜 형식 변환
			DateTimeFormatter dateFomatter = DateTimeFormatter.ofPattern("yyMMdd");
			String formatDate = today.format(dateFomatter);

			// 난수
			Random random = new Random();
			// 자릿수
			int letter = 6;
			// 형변환 후 담을 변수
			String resultNum = "";
			// 난수 생성 반복문
			for (int i = 0; i < letter; i++) {
				int createNum = random.nextInt(6);
				Integer.toString(createNum);
				resultNum += createNum;
			}
			// bundleCode 생성
			bundleCode = formatDate + resultNum;
		}
		// bundleCode 확인
		logger.debug("bundleCode : " + bundleCode);

		if (userVO != null) { // 로그인 성공시 session 생성
            session.setAttribute("userVO", userVO);
            session.setAttribute("cart", bundleCode);
            return "redirect:/";
        } else {
            return "/member/login";
        }

	}
	
	@RequestMapping(value="/member/callBack", method=RequestMethod.GET)
	public String callBack(){
		logger.debug(" callBack() 호출 ");
		return "/member/callBack";
	}
	
	@ResponseBody
	@RequestMapping(value = "/member/confirm", method = RequestMethod.POST)
	public ResponseEntity<Integer> idCheckPOST(String user_id) throws Exception {
	    logger.debug("idCheckPOST(String user_id) 호출");
	    int result = 0;
	    if(user_id != null && !user_id.isEmpty()) {
	        logger.debug("조회된 아이디2 : " + uService.checkId(user_id));
	        result = uService.checkId(user_id);
	    }
	    return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	// 카카오 로그인
	@RequestMapping(value = "/registerkakao", method = {RequestMethod.GET,  RequestMethod.POST})
	public String registerKakaoGET(@RequestParam("code") String code, HttpSession session) throws Exception {
		logger.debug(" registerKakaoGET() 호출");
		logger.debug(" code : " + code);
		UserVO uvo = uService.kakaoInfo(code);
		logger.debug(" kakao info : " + uvo);
		UserVO cvo = uService.getUser(uvo);
		UserVO userVO = uService.loginUser(uvo);
		session.setAttribute("userVO", userVO);
		logger.debug(" 로그인 정보 : " + userVO);
		if(cvo == null) {
			uService.userKakaoInsert(uvo);
			logger.debug(" 카카오 회원가입 성공! ");
			cvo = uService.kakaoUserGet(uvo);
			session.setAttribute("userVO", userVO);
		}
		
		return "redirect:/";
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
	public void wishGET(@RequestParam(name = "orderBy", required = false, defaultValue = "popularity") String orderBy,
			HttpSession session, Model model) throws Exception {
		logger.debug(" wishGET() 호출 ");
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String user_id = userVO.getUser_id();
		List<ProductVO> wishListAll = uService.wishListAll(user_id);
		List<ProductVO> wishList = uService.wishList(orderBy, user_id);
		model.addAttribute("wishList", wishList);
		model.addAttribute("wishListAll", wishListAll);
	}
	
	// 찜 상품 삭제 - 개별
	@RequestMapping(value = "member/deleteWish", method = RequestMethod.POST)
	public void deleteWish(int product_code) throws Exception {
		logger.debug(" deleteWish() 호출 ");
		logger.debug("product_code " + product_code);
		uService.deleteWish(product_code);
	}
	// 찜 상품 삭제 - 전체
	@RequestMapping(value = "member/deleteWishAll", method = RequestMethod.POST)
	public void deleteWishAll(WishVO wvo) throws Exception {
		logger.debug(" deleteWishAll(WishVO wvo) 호출 ");
		
		uService.deleteWishAll(wvo);
		logger.debug(" 찜 전체 삭제 완료 ");
	}
	
	// 즐겨찾기
	@RequestMapping(value = "member/mark", method = RequestMethod.GET)
	public void cartGET(HttpSession session, Model model) throws Exception {
		logger.debug(" cartGET() 호출 ");
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String user_id = userVO.getUser_id();
		List<MarkVO> markList = uService.getMark(user_id);
		model.addAttribute("markList", markList);
	}
	
	// 즐겨찾기 삭제 (개별)
	@RequestMapping(value = "member/deleteMark", method = RequestMethod.POST)
	public void deleteMark(int store_code) throws Exception {
		logger.debug(" deleteMark(int store_code) 호출 ");
		uService.deleteMark(store_code);
	}
	// 즐겨찾기 삭제 (전체)
	@RequestMapping(value = "member/deleteMarkAll", method = RequestMethod.POST)
	public void deleteMarkAll(HttpSession session) throws Exception {
		logger.debug(" deleteMarkAll(String user_id) 호출 ");
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String user_id = userVO.getUser_id();
		uService.deleteMarkAll(user_id);
	}
	
	@RequestMapping(value = "member/review", method = RequestMethod.GET)
	public void reviewList(@RequestParam("product_code") int product_code,
	                       @RequestParam(value = "orderBy", required = false, defaultValue = "latest") String orderBy,
	                       Model model) throws Exception {
	    logger.debug(" review() 호출 ");
	    List<ProductVO> product = uService.getProduct(product_code, orderBy); // orderBy를 파라미터로 전달
	    model.addAttribute("product", product);
	    model.addAttribute("orderBy", orderBy); // orderBy를 모델에 추가
	    List<ReviewVO> review = uService.getReview(product_code);
	    model.addAttribute("review", review);
	}
	
	// 구독상품 조회
	@RequestMapping(value = "/member/subscribe", method = RequestMethod.GET)
	public void subscribe(@RequestParam(name = "orderBy", required = false, defaultValue = "popularity") String orderBy,
			HttpSession session, Model model) throws Exception{
		logger.debug(" subscribe 구독상품페이지 호출 ");
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String user_id = userVO.getUser_id();
		List<SubscribeProductVO> sub = uService.showsub(user_id);
		List<SubscribeProductVO> subSort = uService.sortSub(orderBy, user_id);
		logger.debug("subSort " + subSort);
		model.addAttribute("sub", sub);
		model.addAttribute("subSort", subSort);
	}
	
	// 구독상품 삭제 (개별)
	@ResponseBody
	@RequestMapping(value = "member/deleteSub", method = RequestMethod.POST)
	public void deleteSub(int product_code, HttpSession session) throws Exception{
		logger.debug(" deleteSub() 실행 ");
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String user_id = userVO.getUser_id();
		uService.deleteSub(product_code, user_id);
	}
	// 구독상품 삭제 (전체)
	@ResponseBody
	@RequestMapping(value = "member/deleteSubAll", method = RequestMethod.POST)
	public void deleteSubAll(HttpSession session) throws Exception {
		logger.debug(" deleteSubAll() 실행 ");
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String user_id = userVO.getUser_id();
		uService.deleteSubAll(user_id);
	}
	
	
	
	
	
	
}