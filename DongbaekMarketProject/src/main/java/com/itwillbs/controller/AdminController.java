package com.itwillbs.controller;


import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itwillbs.domain.AdminNoticeVO;
import com.itwillbs.domain.AdminProductVO;
import com.itwillbs.domain.AdminSellerVO;
import com.itwillbs.domain.AdminStoreVO;
import com.itwillbs.domain.UserVO;
import com.itwillbs.service.AdminService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminController {
	
	@Inject
	private AdminService aService;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping(value = "/admin/login", method = RequestMethod.GET)
	public void adminLogin()throws Exception {
		logger.debug(" adminLogin() 호출 "); 

	}

	@RequestMapping(value = "/admin/join", method = RequestMethod.GET)
	public void adminJoin()throws Exception {
		logger.debug(" adminJoin() 호출 "); 

	}
	
	@PostMapping(value = "/admin/loginAction")
	public String loginAction(UserVO vo, HttpSession session)throws Exception{
		logger.debug(" loginAction(UserVO vo) 호출 ");
		String pass = aService.adminLogin(vo);
		
		
		if(vo.getUser_pw().equals(pass)) {
			logger.debug(" 로그인 성공! ");
			session.setAttribute("user_id", vo.getUser_id());
			
			return "redirect:/admin/main";
		}
			return "redirect:/admin/login";
		
	}
	
	
	@RequestMapping(value = "/admin/main", method = RequestMethod.GET)
	public void adminMain(Model model)throws Exception {
		logger.debug(" adminMain() 호출 "); 
		ObjectMapper objectMapper = new ObjectMapper();// map형식을 json형식으로
		String sellList = objectMapper.writeValueAsString(aService.chartSellCount());
		String userCountList = objectMapper.writeValueAsString(aService.mainUserData());
		String sellerCountList = objectMapper.writeValueAsString(aService.mainSellerData());
		
		AdminNoticeVO vo = new AdminNoticeVO();
		vo.setContent("");
			
		model.addAttribute("sellList", sellList);
		model.addAttribute("userCountList", userCountList);
		model.addAttribute("sellerCountList", sellerCountList);
		model.addAttribute("noticeList", aService.searchNotice(vo));
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
	
	@RequestMapping(value = "/admin/qna", method = RequestMethod.GET)
	public void Question()throws Exception {
		logger.debug(" Question() 호출 "); 
		
		
	}	
	
	//구독 물품 업로드
	@RequestMapping(value = "/admin/sublist", method = RequestMethod.POST)
	public ResponseEntity uploadProduct(@RequestParam("product_name") String product_name,
							  @RequestParam("price") String price,
							  @RequestParam("country") String country,
							  @RequestParam("max_account") String max_account,
							  @RequestParam("unit") String unit,
							  @RequestParam("category") String category,
							  @RequestParam("product_explain") String product_explain, 
							  @RequestParam("img1") MultipartFile file, @RequestParam("img2") MultipartFile file1, @RequestParam("img3") MultipartFile file2, HttpServletRequest request)throws Exception{
		logger.debug(" uploadProduct(AdminProductVO vo) 호출 ");
		
		String uniqueFileName = "";
		String uniqueFileName1 = "";
		String uniqueFileName2 = "";
		String realPath = request.getSession().getServletContext().getRealPath("/resources/upload1");
		logger.debug("realPath : "+realPath);
		
		if(!file.isEmpty()) {
				String originalFileName = file.getOriginalFilename();
		        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
		        uniqueFileName = UUID.randomUUID().toString() + fileExtension; // 중복방지를 위해 파일이름 랜덤값 변경
		        String filePath = realPath + File.separator + uniqueFileName;
		        File dest = new File(filePath);
		        file.transferTo(dest);
			
		}
		
		if(!file1.isEmpty()) {
			String originalFileName = file1.getOriginalFilename();
	        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
	        uniqueFileName1 = UUID.randomUUID().toString() + fileExtension; // 중복방지를 위해 파일이름 랜덤값 변경
	        String filePath = realPath + File.separator + uniqueFileName;
	        File dest = new File(filePath);
	        file1.transferTo(dest);
		
		}
		
		if(!file2.isEmpty()) {
			String originalFileName = file2.getOriginalFilename();
	        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
	        uniqueFileName2 = UUID.randomUUID().toString() + fileExtension; // 중복방지를 위해 파일이름 랜덤값 변경
	        String filePath = realPath + File.separator + uniqueFileName;
	        File dest = new File(filePath);
	        file2.transferTo(dest);
		
		}
		
		AdminProductVO pvo = new AdminProductVO();
		
		pvo.setProduct_name(product_name);
		pvo.setStore_code(1);
		pvo.setPrice(Integer.parseInt(price));
		pvo.setCountry(country);
		pvo.setMax_account(Integer.parseInt(max_account));
		pvo.setUnit(unit);
		pvo.setCategory(category);
		pvo.setProduct_explain(product_explain);
		pvo.setSeller_id("admin");
		pvo.setImg1(uniqueFileName);
		pvo.setImg2(uniqueFileName1);
		pvo.setImg3(uniqueFileName2);
		pvo.setSub_product("구독");
		
		logger.debug(" 작성글 : "+pvo.toString());
		int success = 0;
		success = aService.insertSubProduct(pvo);
		String result = "";
		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		if(success == 1) {
			result = "<script>";
			result += " alert('상품 등록 완료!'); ";
			result += " location.href='http://localhost:8088/admin/sublist';";
			result += "</script>";
		}else {
			result = "<script>";
			result += " alert('상품 등록 실패!'); ";
			result += " location.href='http://localhost:8088/admin/sublist';";
			result += "</script>";
		}
		
		return new ResponseEntity(result,respHeaders,HttpStatus.OK);
	}
	
	@PostMapping(value = "/admin/insertnotice")
	public ResponseEntity insertNotice(String title, String content)throws Exception{
		logger.debug(" insertNotice(String title, String content) 호출 ");
		AdminNoticeVO vo = new AdminNoticeVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setUser_id("admin");
		vo.setUser_name("admin");
		vo.setQ_type(100);
		
		int success = 0;
		success = aService.insertNotice(vo);
		String result = "";
		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		if(success == 1) {
			result = "<script>";
			result += " alert('공지사항 등록 완료!'); ";
			result += " location.href='http://localhost:8088/admin/notice';";
			result += "</script>";
		}else {
			result = "<script>";
			result += " alert('공지사항 등록 실패!'); ";
			result += " location.href='http://localhost:8088/admin/notice';";
			result += "</script>";
		}
		
		return new ResponseEntity(result,respHeaders,HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/admin/insertupload")
	public ResponseEntity insertSeller(AdminSellerVO vo,AdminStoreVO svo)throws Exception{
		logger.debug(" insertSeller(AdminSellerVO vo) 호출 ");
		logger.debug(" seller값 : "+vo);
		logger.debug("store값 : "+svo);
		
		int success = 0; 
		String result = "";
		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		vo.setStore_code(aService.getStoreCode());
		svo.setStore_code(vo.getStore_code());
		
		success += aService.insertSeller(vo);
		success += aService.insertStore(svo);
		
		
		if(success == 2) {
			result = "<script>";
			result += " alert('사업자회원 등록 완료!'); ";
			result += " location.href='http://localhost:8088/admin/shop';";
			result += "</script>";
		}else {
			result = "<script>";
			result += " alert('사업자회원 등록 실패!'); ";
			result += " location.href='http://localhost:8088/admin/shop';";
			result += "</script>";
		}
		
		return new ResponseEntity(result,respHeaders,HttpStatus.OK);
	}
	
	@PostMapping(value = "/admin/updatestore")
	public ResponseEntity updateStore(AdminSellerVO vo,AdminStoreVO svo)throws Exception{
		logger.debug(" updateStore(AdminSellerVO vo,AdminStoreVO svo) 호출 ");
		logger.debug(" seller값 : "+vo);
		logger.debug("store값 : "+svo);
		
		int success = 0; 
		String result = "";
		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		success += aService.updateSeller(vo);
		success += aService.updateStore(svo);
		
		if(success == 2) {
			result = "<script>";
			result += " alert('사업자회원 수정 완료!'); ";
			result += " location.href='http://localhost:8088/admin/shop';";
			result += "</script>";
		}else {
			result = "<script>";
			result += " alert('사업자회원 수정 실패!'); ";
			result += " location.href='http://localhost:8088/admin/shop';";
			result += "</script>";
		}
		
		return new ResponseEntity(result,respHeaders,HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/admin/chatbot")
	public void chatbot()throws Exception {
		
	}
	
}//endController
