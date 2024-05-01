package com.itwillbs.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.ProductCri;
import com.itwillbs.domain.ProductPagingVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.service.ProductService;

@Controller
@RequestMapping(value = "/seller/*")
public class SellerPageController {//판매자 페이지 컨트롤러

	@Inject
	private ProductService pService; //서비스 가져오기
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(SellerPageController.class);
	
	// 판매자 메인페이지
    //	http://localhost:8088/seller/sellermain
	@RequestMapping(value = "/sellermain",method = RequestMethod.GET)
	public void main() throws Exception{
		logger.debug(" main() 실행 ");
	}
	
	// 판매자 상품페이지(상품목록)
	//	http://localhost:8088/seller/product
	@RequestMapping(value = "/product",method = RequestMethod.GET)
	public String productList(Model model, @ModelAttribute("cri") ProductCri cri, HttpSession session) throws Exception {
	    // 세션에서 가게 코드를 가져옴
	    Integer store_code = (Integer) session.getAttribute("store_code");
	    //if (store_code == null) {
	        // 가게 코드가 없으면 로그인 페이지로 리다이렉트 또는 다른 처리를 수행할 수 있음
	    //   return "redirect:/login";
	    //}

	    // 가게 코드를 설정
	    cri.setStore_code(2024042501);

	    List<ProductVO> productList = pService.getProductPage(cri);
	    int totalCount = pService.getTotalCount(2024042501); // 총 상품 수를 ProductService를 통해 가져옴

	    ProductPagingVO pagingVO = new ProductPagingVO();
	    pagingVO.setCri(cri);
	    pagingVO.setTotalCount(totalCount);

	    model.addAttribute("productList", productList);
	    model.addAttribute("pagingVO", pagingVO);

	    return "/seller/product";
	}
		
	// 판매자 상품페이지(상품등록)
	//	http://localhost:8088/seller/productregist
	@RequestMapping(value = "/productregist",method = RequestMethod.GET)
	public void productregist(Model model) throws Exception{
		model.addAttribute("product", new ProductVO());
		logger.debug(" productregist() 실행 ");
	}
	
	// 판매자 상품페이지(상품등록)
	// http://localhost:8088/seller/productregistSubmit
	@RequestMapping(value = "/productregistSubmit", method = RequestMethod.POST)
	public String productregistSubmit(@RequestParam("img1") MultipartFile img1,
	                                  @RequestParam("img2") MultipartFile img2,
	                                  @RequestParam("img3") MultipartFile img3,
	                                  @RequestParam(value = "category", defaultValue = "기본 카테고리") String category,
	                                  @RequestParam(value = "product_name", defaultValue = "상품명 없음") String product_name,
	                                  @RequestParam(value = "unit", defaultValue = "개") String unit,
	                                  @RequestParam(value = "price", defaultValue = "0") String price,
	                                  @RequestParam(value = "product_explain", defaultValue = "설명 없음") String product_explain,
	                                  @RequestParam(value = "max_account", defaultValue = "0") int max_account,
	                                  @RequestParam(value = "country", defaultValue = "원산지 없음") String country,
	                                  @RequestParam(value = "store_code", defaultValue = "0") int store_code,
	                                  RedirectAttributes rttr) throws Exception {
	    // 이미지 파일 저장
	    String fileDirectory = "C:\\Users\\ITWILL\\git\\Itwill_Dongbaek\\DongbaekMarketProject\\src\\main\\webapp\\resources\\images"; // 이미지 파일이 저장될 디렉토리 경로 수정 필요
	    String img1Filename = saveImage(img1, fileDirectory, rttr);
	    String img2Filename = saveImage(img2, fileDirectory, rttr);
	    String img3Filename = saveImage(img3, fileDirectory, rttr);

	    // 상품 정보 저장
	    ProductVO prod = new ProductVO();
	    prod.setCategory(category);
	    prod.setProduct_name(product_name);
	    prod.setUnit(unit);
	    prod.setPrice(price);
	    prod.setProduct_explain(product_explain);
	    prod.setMax_account(max_account);
	    prod.setCountry(country);
	    prod.setStore_code(store_code);
	    prod.setImg1(img1Filename);
	    prod.setImg2(img2Filename);
	    prod.setImg3(img3Filename);
	    pService.productRegist(prod);

	    logger.debug(" 등록 성공 ");

	    return "redirect:/seller/productregist";
	}

	private String saveImage(MultipartFile img, String fileDirectory, RedirectAttributes rttr) throws Exception {
	    if (!img.isEmpty()) {
	        String filerealname = img.getOriginalFilename(); // 원본 파일명
	        // UUID로 고유한 파일명 생성
	        String uuid = UUID.randomUUID().toString();
	        String filename = uuid + "_" + filerealname;
	        String filePath = fileDirectory + File.separator + filename;
	        try {
	            // 이미지 파일 저장
	            img.transferTo(new File(filePath));
	            return filename; // 저장된 파일명 반환
	        } catch (IOException e) {
	            e.printStackTrace();
	            rttr.addFlashAttribute("f", "이미지 파일 업로드에 실패했습니다.");
	            return null;
	        }
	    }
	    return null;
	}






	
	
	
	
	
	
	// 판매자 상품페이지(상품수정)
	//	http://localhost:8088/seller/productmodify
	@RequestMapping(value = "/productmodify",method = RequestMethod.GET)
	public void productmodify() throws Exception{
		logger.debug(" productmodify() 실행 ");
	}
	
	// 판매자 주문페이지(주문목록)
	//	http://localhost:8088/seller/orderlist
	@RequestMapping(value = "/orderlist",method = RequestMethod.GET)
	public void orderlist() throws Exception{
		logger.debug(" orderlist() 실행 ");
	}
	
	// 판매자 주문페이지(신규주문)
	//	http://localhost:8088/seller/neworder
	@RequestMapping(value = "/neworder",method = RequestMethod.GET)
	public void neworder() throws Exception{
		logger.debug(" neworder() 실행 ");
	}
	
	// 판매자 주문페이지(주문 확정)
	//	http://localhost:8088/seller/orderconfirm
	@RequestMapping(value = "/orderconfirm",method = RequestMethod.GET)
	public void orderconfirm() throws Exception{
		logger.debug(" orderconfirm() 실행 ");
	}
	
	// 판매자 주문페이지(주문취소/환불)
	//	http://localhost:8088/seller/ordercancel
	@RequestMapping(value = "/ordercancel",method = RequestMethod.GET)
	public void ordercancel() throws Exception{
		logger.debug(" ordercancel() 실행 ");
	}

	// 판매자 배송페이지
	//	http://localhost:8088/seller/dilivery
	@RequestMapping(value = "/dilivery",method = RequestMethod.GET)
	public void dilivery() throws Exception{
		logger.debug(" dilivery() 실행 ");
	}

	// 판매자 리뷰페이지
	//	http://localhost:8088/seller/review
	@RequestMapping(value = "/review",method = RequestMethod.GET)
	public void review() throws Exception{
		logger.debug(" review() 실행 ");
	}
	
	// 판매자 매출페이지
	//	http://localhost:8088/seller/sales
	@RequestMapping(value = "/sales",method = RequestMethod.GET)
	public void sales() throws Exception{
		logger.debug(" sales() 실행 ");
	}
	
	// 판매자 문의페이지
	//	http://localhost:8088/seller/question
	@RequestMapping(value = "/question",method = RequestMethod.GET)
	public void question() throws Exception{
		logger.debug(" question() 실행 ");
	}
}
