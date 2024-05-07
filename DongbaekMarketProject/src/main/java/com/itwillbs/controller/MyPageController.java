package com.itwillbs.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.itwillbs.domain.CartVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.domain.OrderInfoVO;
import com.itwillbs.domain.PageMaker;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.domain.SearchCriteria;
import com.itwillbs.service.MyPageService;

@Controller
@RequestMapping(value = "/mypage/*")
public class MyPageController {

	private static final Logger logger = LoggerFactory.getLogger(MyPageController.class);

	@Inject
	private MyPageService mService;

	/* 장바구니 */
	// 장바구니 상품 목록
	@GetMapping(value = "/cart")
	public String cartListGET(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		logger.debug(" === cartListGET() 실행 ===");

		CartVO cvo = new CartVO();

		// 세션 가져오기
		String userid = (String) session.getAttribute("user_id");
		String username = (String) session.getAttribute("user_name");
		String bundleCode = (String) session.getAttribute("cart");

		// cvo 담기...
		cvo.setUser_id(userid);
		cvo.setBundle_code(bundleCode);

		// 장바구니 리스트
		List<CartVO> cartList = mService.selectCartList(cvo);

		PageMaker pageMaker = new PageMaker();

		pageMaker.setCri(cri);
		pageMaker.setTotalCount(mService.selectCountCart(cvo));

		logger.debug("pageMaker : " + pageMaker);
		logger.debug("cri : " + cri);
		model.addAttribute("cartList", cartList);
		model.addAttribute("pageMaker", pageMaker);

		return "/mypage/cart";
	}

	// 장바구니 상품 선택 삭제
	@PostMapping(value = "/cart/deleteChecked")
	public String deleteCheckedCartProducts(@RequestParam("checkList") String[] strCheckList) throws Exception {
		logger.debug(" === deleteCheckedCartProducts() 실행 ===");

		int[] checkList = new int[strCheckList.length];
		for (int i = 0; i < strCheckList.length; i++) {
			checkList[i] = Integer.parseInt(strCheckList[i]);
			logger.debug(" checkList " + checkList[i]);
			mService.deleteCartProduct(checkList[i]);
		}
		return "redirect:/mypage/cart";
	}

	// 장바구니 상품 전체 삭제
	@PostMapping(value = "/cart/deleteAll")
	public String deleteAllCartProducts(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug(" === deleteAllCartProducts() 실행 ===");

		CartVO cvo = new CartVO();
		// 세션 가져오기
		String userid = (String) session.getAttribute("user_id");
		String bundleCode = (String) session.getAttribute("cart");

		cvo.setUser_id(userid);
		cvo.setBundle_code(bundleCode);

		mService.deleteCartAllProduct(cvo);

		return "redirect:/mypage/cart";
	}

	// 장바구니 상품 개별 삭제
	@PostMapping(value = "/cart/delete")
	public String deleteCartProducts(@RequestParam("ap_check") String cart_code) throws Exception {
		logger.debug(" === deleteCartProducts() 실행 ===");

		mService.deleteCartProduct(Integer.parseInt(cart_code));

		return "redirect:/mypage/cart";
	}

	// 장바구니 상품 수량 변경
	@PostMapping(value = "/cart/updateCount")
	public String updateCartProductsCount(@RequestParam("cartCode") int cartCode,
			@RequestParam("newCount") int newCount) throws Exception {
		logger.debug(" === updateCartProductsCount() 실행 ===");

		CartVO cvo = new CartVO();
		cvo.setCart_code(cartCode);
		cvo.setCount(newCount);

		mService.updateProductCount(cvo);

		return "redirect:/mypage/cart";
	}

	/* 주문내역 */
	// 주문내역
	@GetMapping(value = "orderlist")
	public void orderListGET(HttpSession session, @ModelAttribute("cri") Criteria cri,
			@RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate,
			@RequestParam(required = false) String states, Model model) throws Exception {
		logger.debug(" === orderListGET() 실행 ===");

		String userid = (String) session.getAttribute("user_id"); // 수정필요 240502

		// 검색
		SearchCriteria searchCri = new SearchCriteria();
		searchCri.setStartDate(startDate);
		searchCri.setEndDate(endDate);
		searchCri.setEndDate(states);
		searchCri.setUser_id(userid);

		List<OrderInfoVO> orderList = mService.selectUserOrderList(searchCri);

		PageMaker pageMaker = new PageMaker();

		pageMaker.setCri(cri);
		pageMaker.setTotalCount(mService.selectCountOrder(userid));

		model.addAttribute("orderList", orderList);
		model.addAttribute("pageMaker", pageMaker);

	}

	// 주문 상세내역
	@GetMapping(value = "orderdetail")
	public void orderDetailGET(@ModelAttribute("cri") Criteria cri, @RequestParam String order_code, Model model)
			throws Exception {
		logger.debug(" === orderDetailGET() 실행 ===");

		// 주문 정보 보여주기
		OrderInfoVO ovo = mService.selectOrderInfo(Integer.parseInt(order_code));

		// 주문 상품 정보
		List<CartVO> productList = mService.selectOrderProduct(Integer.parseInt(order_code));

		model.addAttribute("ovo", ovo);
		model.addAttribute("productList", productList);

	}

	/* 리뷰 */
	// 리뷰 작성
	@GetMapping(value = "/review/write")
	public String reviewWriteGET(@RequestParam String order_code, @RequestParam String product_code, Model model)
			throws Exception {
		logger.debug(" === reviewWriteGET(Model model) 실행 === ");

		// 리뷰 작성 할 상품 정보 불러오기
		CartVO cvo = mService.selectReviewProduct(product_code); 

		model.addAttribute("cvo", cvo); 

		return "/mypage/reviewWrite";
	}

	// 오토인크리먼트 해제 완료
	@PostMapping(value = "/review/write")
	public String reviewWritePOST(
			@RequestParam("order_code") String order_code,
	        @RequestParam("product_code") String product_code,
			@RequestParam("user_id") String user_id, 
	        @RequestParam("title") String title,
	        @RequestParam("content") String content,
	        @RequestParam("images") MultipartFile[] images,
	        HttpServletRequest request)
	        throws Exception {
	    logger.debug(" === reviewWritePOST() 실행 === ");
	    
	    ReviewVO rvo = new ReviewVO();
	    
	    // 리뷰 번호 생성
	    int result = mService.selectReviewMaxCount();
	    if(result == 0) {
	        rvo.setReview_code(1);
	    } 
	    if(result != 0){
	        rvo.setReview_code(result + 1);
	    }

	    // 이미지 파일 업로드
	    String realPath = request.getSession().getServletContext().getRealPath("/resources/upload1");
	    logger.debug("realPath : " + realPath);

	    if (images != null && images.length > 0) {
	        String[] uniqueFileNames = new String[3];
	        for (int i = 0; i < Math.min(images.length, 3); i++) {
	            MultipartFile imgFile = images[i];
	            if (!imgFile.isEmpty()) {
	                String originalFileName = imgFile.getOriginalFilename();
	                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
	                uniqueFileNames[i] = UUID.randomUUID().toString() + fileExtension;
	                String filePath = realPath + File.separator + uniqueFileNames[i];
	                File dest = new File(filePath);
	                imgFile.transferTo(dest);
	            }
	        }
	        // 이미지 파일 경로 설정
	        rvo.setImg1(uniqueFileNames[0]);
	        rvo.setImg2(uniqueFileNames[1]);
	        rvo.setImg3(uniqueFileNames[2]);
	    }

	    // 답변 그룹번호
	    rvo.setRe_ref(result+1);
	    
	    logger.debug("===rvo=== : " + rvo);
	    mService.insertReview(rvo);

	    return "redirect:/review/list";
	}

	// 내가 쓴 리뷰
	@GetMapping(value = "/review/list")
	public void reviewListGET(HttpSession session, 
			@ModelAttribute("cri") Criteria cri,
			@RequestParam(required = false) String startDate, 
			@RequestParam(required = false) String endDate,
			Model model) throws Exception {
		logger.debug(" === reviewListGET() 실행 === ");
		
		String userid = (String)session.getAttribute("user_id");
		
		// 검색
		SearchCriteria searchCri = new SearchCriteria();
		searchCri.setStartDate(startDate);
		searchCri.setEndDate(endDate);
		searchCri.setUser_id(userid);

		List<ReviewVO> reviewList = mService.selectReview(searchCri);
		
		PageMaker pageMaker = new PageMaker();
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(mService.selectCountReview(searchCri));
		
		model.addAttribute("reviewList",reviewList);
		model.addAttribute("pageMaker", pageMaker);
	}

}
