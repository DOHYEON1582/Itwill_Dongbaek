package com.itwillbs.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.ProductCri;
import com.itwillbs.domain.ProductPagingVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.ReviewCri;
import com.itwillbs.domain.ReviewPagingVO;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.service.ProductService;
import com.itwillbs.service.ReviewReplyService;

@Controller
@RequestMapping(value = "/seller/*")
public class SellerPageController {//판매자 페이지 컨트롤러

    @Inject
    private ProductService pService; // 상품 관련 서비스 가져오기
    
    @Inject
    private ReviewReplyService rService;
    
    // 파일 업로드 디렉토리
    @Value("${file.upload.directory}")
    private String FILE_DIRECTORY;
    
    
    
    private static final Logger logger = LoggerFactory.getLogger(SellerPageController.class);
    
    // 판매자 메인페이지
    //  http://localhost:8088/seller/sellermain
    @RequestMapping(value = "/sellermain",method = RequestMethod.GET)
    public void main() throws Exception{
        logger.debug(" main() 실행 ");
    }
    
    // 판매자 상품페이지(상품목록)
    //  http://localhost:8088/seller/product
    @RequestMapping(value = "/product",method = RequestMethod.GET)
    public String productList(Model model, ProductCri cri, HttpSession session) throws Exception {
        // 세션에서 가게 코드를 가져옴
        // Integer store_code = (Integer) session.getAttribute("store_code");
        // if (store_code == null) {
        //     // 가게 코드가 없으면 로그인 페이지로 리다이렉트 또는 다른 처리를 수행할 수 있음
        //     return "redirect:/login";
        // }

        // 가게 코드를 설정
        cri.setStore_code(2024042501);

        
        ProductPagingVO pagingVO = new ProductPagingVO();
        pagingVO.setCri(cri);
        pagingVO.setTotalCount(pService.getTotalCount(2024042501)); // 총 상품 수를 ProductService를 통해 가져옴

        logger.debug(""+pagingVO);
        
        List<ProductVO> product = pService.getProductPage(cri);
        
        model.addAttribute("product", product);
        
        model.addAttribute("cri", cri);
        
        model.addAttribute("pagingVO", pagingVO);

        return "/seller/product";
    }
        
    // 상품 상세 페이지를 보여주는 메서드
    @RequestMapping(value = "/productDetail", method = RequestMethod.GET)
    public String showProductDetail(@RequestParam("product_code") int product_code, Model model) {
        // 상품 정보 조회
        ProductVO product = pService.getProductById(product_code);
        
        // 조회된 상품 정보를 모델에 추가하여 뷰로 전달
        model.addAttribute("product", product);
        
        // 상품 상세 페이지로 이동
        return "/seller/productDetail";
    }
    
    // 판매자 상품페이지(상품등록)
    //  http://localhost:8088/seller/productregist
    @RequestMapping(value = "/productregist", method = RequestMethod.GET)
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
            try {
                String img1Filename = saveImage(img1);
                String img2Filename = saveImage(img2);
                String img3Filename = saveImage(img3);

                ProductVO product = new ProductVO();
                product.setCategory(category);
                product.setProduct_name(product_name);
                product.setUnit(unit);
                product.setPrice(price);
                product.setProduct_explain(product_explain);
                product.setMax_account(max_account);
                product.setCountry(country);
                product.setStore_code(store_code);
                saveImagePaths(product, img1Filename, img2Filename, img3Filename);
                pService.productRegist(product);

                logger.debug("등록 성공");

                return "redirect:/seller/productregist";
            } catch (IOException e) {
                e.printStackTrace();
                rttr.addFlashAttribute("f", "이미지 파일 업로드에 실패했습니다.");
                return "redirect:/seller/productregist";
            }
        }
     
     // 이미지 파일의 경로를 상품 객체에 저장하는 메서드
     private void saveImagePaths(ProductVO product, String img1Filename, String img2Filename, String img3Filename) {
    	    product.setImg1(img1Filename);
    	    product.setImg2(img2Filename);
    	    product.setImg3(img3Filename);
    	}

    // 이미지 파일을 저장하는 메서드
    private String saveImage(MultipartFile img) throws IOException {
        if (!img.isEmpty()) {
            String originalFilename = img.getOriginalFilename(); // 이미지 원본 이름
            String uuid = UUID.randomUUID().toString();
            String encodedFilename = URLEncoder.encode(originalFilename, StandardCharsets.UTF_8);
            String filename = uuid + "_" + encodedFilename;
            String filePath = FILE_DIRECTORY + filename;

            // 실제 파일 시스템 경로로 저장
            Path path = Paths.get(FILE_DIRECTORY);
            if (!Files.exists(path)) {
                Files.createDirectories(path); // 디렉토리가 없는 경우 생성
            }
            Files.copy(img.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

            return filename;
        }
        return null;
    }

    // 이미지 파일을 제공하는 메서드 수정
    @RequestMapping(value = "/image/{imageName:.+}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] getProductImage(@PathVariable("imageName") String imageName, @RequestParam("product_code") int product_code) throws Exception {
        // 상품 코드를 사용하여 이미지 파일 경로를 DB에서 가져옴
        String imagePath = pService.getImagePathByProductCode(product_code);
        
        // 이미지 파일 경로에 이미지 파일 이름을 추가하여 완전한 이미지 파일 경로를 생성
        String fullImagePath = imagePath + "/" + imageName;
        
        // 이미지 파일 경로를 이용하여 이미지 파일을 읽어옴
        Path path = Paths.get(fullImagePath);
        return Files.readAllBytes(path);
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
	// http://localhost:8088/seller/review
	@RequestMapping(value = "/review", method = RequestMethod.GET)
	public String getAllReviews(@ModelAttribute("cri") ReviewCri cri, Model model) throws Exception {
	    // 페이지 번호와 페이지당 표시할 리뷰 수를 설정한 ReviewCri 객체를 이용하여 페이징된 리뷰 목록을 가져옴
	    List<ReviewVO> reviewList = rService.getAllReviews(cri);

	    // 페이징 처리된 리뷰 목록을 모델에 추가
	    model.addAttribute("reviews", reviewList);

	    // 페이징 정보도 함께 모델에 추가하여 화면에 전달
	    ReviewPagingVO pagingVO = new ReviewPagingVO();
	    pagingVO.setCri(cri);
	    pagingVO.setTotalCount(rService.countReviews()); // 리뷰 총 개수를 가져와 설정
	    model.addAttribute("pagingVO", pagingVO);

	    return "/seller/review"; // 리뷰 목록 페이지로 이동
	}


	// 판매자 리뷰페이지 상세페이지
	// http://localhost:8088/seller/reviewDetail
	@RequestMapping(value = "/reviewDetail", method = RequestMethod.GET)
	public String getReviewDetail(ReviewCri cri,@RequestParam("review_code") int review_code, Model model, HttpSession session) throws Exception {
		logger.debug(" /seller/reviewDetail 호출 ");
		
		// 전달 정보 저장
		logger.debug(" id : "+review_code);
	    // 특정 리뷰의 상세 정보를 가져옴
	    ReviewVO review = rService.getReviewByCode(review_code);
	    model.addAttribute("review", review);
	    
	    model.addAttribute("cri", cri);

	    return "seller/reviewDetail"; // 리뷰 상세 페이지로 이동
	}
	
	// 리뷰에 대한 답글 작성 페이지로 이동
	// http://localhost:8088/seller/reviewReply
    @RequestMapping(value = "/reviewReply", method = RequestMethod.GET)
    public String reviewReplyPage(int review_code, Model model) throws Exception {
        // 특정 리뷰에 대한 답글 작성 페이지로 이동
        model.addAttribute("review", rService.getReviewByCode(review_code));
        return "seller/reviewReply";
    }


    // 리뷰에 대한 답글 작성 처리
    @ResponseBody
    @RequestMapping(value = "/reviewReplySubmit", method = RequestMethod.POST)
    public String reviewReplySubmit(ReviewVO rvo, HttpSession session) throws Exception {
    	logger.debug("asdass13213213213231");
        try {
            // 부모 리뷰의 코드를 세션에서 가져와서 설정
            //Integer parentReviewCode = (Integer) session.getAttribute("parentReviewCode");
            Integer parentReviewCode = rvo.getReview_code();
            
            // 부모 리뷰의 코드가 유효한지 확인
            if (parentReviewCode != null && parentReviewCode > 0) {
                // 부모 리뷰의 코드를 설정
                rvo.setReview_code(parentReviewCode);

                // 답글 작성 서비스 호출
                rService.addReply(rvo);
                logger.debug("답글 작성 성공");

                // 리뷰 상세 페이지로 리다이렉트
//                return "redirect:/seller/reviewDetail?review_code=" + parentReviewCode;
                return "/seller/reviewDetail?review_code=" + parentReviewCode;
            } else { 
                logger.debug("답글 작성 실패: 부모 리뷰 코드가 유효하지 않음");
              
               return "/seller/review";
//               return "redirect:/seller/review";
            }
        } catch (Exception e) {
            logger.error("답글 작성 실패: " + e.getMessage());
            return "/seller/review";
//            return "redirect:/seller/review";
        }
    }


    // 리뷰 답글 수정 처리
    @ResponseBody
    @RequestMapping(value = "/ReplyModify", method = RequestMethod.POST)
    public String ReplyModify(@RequestBody ReviewVO rvo) {
        try {
            rService.replyModify(rvo);
            return "success"; // 수정 성공
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // 수정 실패
        }
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
