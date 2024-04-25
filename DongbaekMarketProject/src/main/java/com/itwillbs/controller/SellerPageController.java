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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	public void product(Model model, @RequestParam(value = "page", defaultValue = "1") int page) throws Exception{
		logger.debug(" product() 실행 ");
		int perPageNum = 9; //페이지당 보여줄 상품 수 설정
		int startRow = (page - 1) * perPageNum; //시작행 계산
		
		List<ProductVO> productList = pService.productList("seller_id", startRow, perPageNum);
		
		 model.addAttribute("productList", productList);
		
		
	}
		
	// 판매자 상품페이지(상품등록)
	//	http://localhost:8088/seller/productregist
	@RequestMapping(value = "/productregist",method = RequestMethod.GET)
	public void productregist() throws Exception{
		logger.debug(" productregist() 실행 ");
	}
	
	// 판매자 상품페이지(상품등록)
	// http://localhost:8088/seller/productregist
	@RequestMapping(value = "/productregist", method = RequestMethod.POST)
	public String productregistSubmit(ProductVO product, @RequestParam("imageFiles") MultipartFile[] imageFiles, HttpServletRequest request) {
	    try {
	        // 상품 등록 서비스 호출
	        pService.productRegist(product);

	        // 이미지 저장 경로 설정
	        String uploadDir = request.getServletContext().getRealPath("/resources/images/");

	        // 상품 코드별 폴더 생성
	        String productDirPath = uploadDir + File.separator + product.getProduct_code();
	        File productDir = new File(productDirPath);
	        if (!productDir.exists()) {
	            productDir.mkdirs(); // 상품 코드별 폴더 생성
	        }

	        // 이미지 저장
	        for (int i = 0; i < imageFiles.length; i++) {
	            MultipartFile imageFile = imageFiles[i];
	            if (imageFile != null && !imageFile.isEmpty()) {
	                String originalFileName = imageFile.getOriginalFilename();
	                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
	                String savedFileName = UUID.randomUUID().toString() + fileExtension; // 파일명 중복 방지를 위한 UUID 생성

	                Path path = Paths.get(productDirPath + File.separator + savedFileName);
	                try {
	                    // 이미지 파일 저장
	                    Files.write(path, imageFile.getBytes());
	                    logger.debug("이미지 저장 성공");
	                } catch (IOException e) {
	                    e.printStackTrace();
	                    logger.debug("이미지 저장 실패");
	                    // 이미지 저장 실패 처리
	                }
	            }
	        }
	        logger.debug("상품 등록 성공");
	    } catch (Exception e) {
	        e.printStackTrace();
	        // 예외 처리
	        logger.debug("상품 등록에 실패함");
	        return "redirect:/seller/productregist";
	    }
	    return "redirect:/seller/product"; // 등록 후 상품 목록 페이지로 리다이렉트
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
