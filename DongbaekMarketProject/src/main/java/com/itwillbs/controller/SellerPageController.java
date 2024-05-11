package com.itwillbs.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.ProductCri;
import com.itwillbs.domain.ProductPagingVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.ReviewCri;
import com.itwillbs.domain.ReviewPagingVO;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.domain.UserVO;
import com.itwillbs.service.ProductService;
import com.itwillbs.service.ReviewReplyService;
import com.itwillbs.service.UserService;

import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping(value = "/seller/*")
public class SellerPageController {//판매자 페이지 컨트롤러

    @Inject
    private ProductService pService; // 상품 관련 서비스 가져오기
    
    @Inject
    private ReviewReplyService rService; // 리뷰 관련 서비스 가져오기
    
    @Inject
    private UserService uService; // 유저 관련 서비스 가져오기
    
    
    // 파일 업로드 디렉토리
    @Value("${file.upload.directory}")
    private String FILE_DIRECTORY = "/resources/upload1";
    
    private static final Logger logger = LoggerFactory.getLogger(SellerPageController.class);
    
    // 판매자 메인페이지
    //  http://localhost:8088/seller/sellermain
    @RequestMapping(value = "/sellermain",method = RequestMethod.GET)
    public void main() throws Exception{
        logger.debug(" main() 실행 ");
    }
    
    // 판매자 상품페이지(상품목록)
    //  http://localhost:8088/seller/product
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String productList(Model model, ProductCri cri, HttpSession session) throws Exception {
        // 세션에서 유저 아이디를 가져옴
        String user_id = (String) session.getAttribute("user_id");
        if (user_id == null) {
            // 유저 아이디가 없으면 로그인 페이지로 리다이렉트 또는 다른 처리를 수행할 수 있음
            return "redirect:/member/login";
        }

        // 사용자 권한 체크
        UserVO user = uService.getUser(new UserVO());
        user.setUser_id(user_id);
        user = uService.getUser(user);
        if (user == null || !"seller".equals(user.getUser_id())) {
            // 판매자가 아니면 권한이 없는 페이지로 리다이렉트 또는 다른 처리를 수행할 수 있음
            return "redirect:/"; // 예시: 권한이 없는 페이지로 이동
        }
        
        // 유저 아이디 받아오기
        cri.setUser_id(user_id);

        ProductPagingVO pagingVO = new ProductPagingVO();
        pagingVO.setCri(cri);
        pagingVO.setTotalCount(pService.getTotalCount((String) user_id)); // 총 상품 수를 ProductService를 통해 가져옴

        logger.debug("" + pagingVO);

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
	 public String productregistSubmit(MultipartHttpServletRequest multiRequest,
			 						   @RequestParam(value = "market_code", defaultValue = "1") int market_code,
	                                   @RequestParam(value = "category", defaultValue = "기본 카테고리") String category,
	                                   @RequestParam(value = "product_name", defaultValue = "상품명 없음") String product_name,
	                                   @RequestParam(value = "unit", defaultValue = "개") String unit,
	                                   @RequestParam(value = "price", defaultValue = "0") int price,
	                                   @RequestParam(value = "product_explain", defaultValue = "설명 없음") String product_explain,
	                                   @RequestParam(value = "max_account", defaultValue = "0") int max_account,
	                                   @RequestParam(value = "country", defaultValue = "원산지 없음") String country,
	                                   @RequestParam(value = "store_code", defaultValue = "0") int store_code,
	                                   RedirectAttributes rttr) throws Exception {
	     try {
	         // 파일 업로드 처리
	         List<String> fileNames = saveImage(multiRequest);
	
	         // 상품 정보 설정
	         ProductVO product = new ProductVO();
	         product.setCategory(category);
	         product.setProduct_name(product_name);
	         product.setUnit(unit);
	         product.setPrice(price);
	         product.setProduct_explain(product_explain);
	         product.setMax_account(max_account);
	         product.setCountry(country);
	         product.setStore_code(store_code);
	
	         // 이미지 파일 이름 설정
	         if (fileNames.size() >= 3) {
	             product.setImg1(fileNames.get(0));
	             product.setImg2(fileNames.get(1));
	             product.setImg3(fileNames.get(2));
	         }
	
	         // 상품 등록
	         pService.productRegist(product);
	
	         logger.debug("등록 성공");
	
	         return "redirect:/seller/productregist";
	     } catch (IOException e) {
	         e.printStackTrace();
	         rttr.addFlashAttribute("f", "이미지 파일 업로드에 실패했습니다.");
	         return "redirect:/seller/productregist";
	     }
	 }

     
     

	 private List<String> saveImage(MultipartHttpServletRequest multiRequest) throws Exception{
			logger.debug(" fileProcess() - 파일 업로드 처리 시작 ");
			
			// 업로드 파일의 정보를 저장
			List<String> fileList = new ArrayList<String>();
			
			Iterator<String> fileNames = multiRequest.getFileNames(); //파일의 파라메터명을 가져온다
			while(fileNames.hasNext()) {
				String fileName = fileNames.next(); 
				MultipartFile mFile = multiRequest.getFile(fileName);
				String oFileName = mFile.getOriginalFilename();
				logger.debug(" fileName: "+fileName+", oFileName : "+oFileName);
				
				fileList.add(oFileName); // 파일의 이름을 저장
				
				// 파일 업로드 처리
				File file = new File("/resources/upload1/"+oFileName);
				
				if(mFile.getSize() != 0) { //첨부파일이 있는지 없는지 체크
					if( !file.exists() ) { // exists()- 해당파일이 있을때 t,없을때 f
						// => 해당파일이 없을때
						if(file.getParentFile().mkdirs()) { //해당 파일의 폴더를 생성
							file.createNewFile();						
						}
					}//exists()
					
					//mFile.transferTo(file);
					mFile.transferTo(file);
				}//getSize()
				
			}//while
			
			logger.debug(" fileProcess() - 파일 업로드 처리 끝 ");
			
			return fileList;
		}
	 
	 
	 // 파일 다운로드 :  업로드 해놓은 파일의 위치, 다운로드할 파일 이름
	 @RequestMapping(value = "/download", method = RequestMethod.GET)
	 public void fileDownloadGET(@RequestParam("fileName") String fileName,
	                             HttpServletResponse resp) throws Exception {
	     logger.debug("fileDownloadGET() 호출");

	     String downLoadPath = "C:/resources/upload1/";
	     logger.debug("다운로드 할 fileName: " + fileName);

	     // 다운로드할 파일
	     File file = new File(downLoadPath + fileName);

	     // 데이터(첨부파일)를 전송하는 통로
	     OutputStream out = resp.getOutputStream();

	     // 모든 파일의 다운로드 형태를 통일
	     resp.setHeader("Cache-Control", "no-cache");
	     resp.addHeader("Content-disposition", "attachment; fileName=" + (URLEncoder.encode(fileName, "UTF-8")));

	     // 파일 데이터를 읽기
	     FileInputStream fis = new FileInputStream(file);

	     byte[] buffer = new byte[1024 * 8]; // 8KB

	     int data = 0;
	     while ((data = fis.read(buffer)) != -1) { // -1 파일의 끝(EOF)
	         // 다운로드 출력
	         out.write(buffer, 0, data);
	     }

	     out.flush(); // 버퍼의 여백을 공백을 채움
	     out.close();
	     fis.close();

	     logger.debug("파일 다운로드 완료!");
	 }



   


 






	// 판매자 상품 수정 페이지
	 @RequestMapping(value = "/productmodify", method = RequestMethod.GET)
	 public String showProductModifyForm(@RequestParam("product_code") int product_code, Model model) throws Exception {
	     
	    	// 상품 정보 조회
	    	    ProductVO product = pService.getProductById(product_code);
	    	    
	    	    // 이미지 경로 가져오기
	    	    String imagePath1 = product.getImg1();
	    	    String imagePath2 = product.getImg2();
	    	    String imagePath3 = product.getImg3();
	    	    
	    	    // 모델에 상품 정보 및 이미지 경로 추가
	    	    model.addAttribute("product", product);
	    	    model.addAttribute("imagePath1", imagePath1);
	    	    model.addAttribute("imagePath2", imagePath2);
	    	    model.addAttribute("imagePath3", imagePath3);
	    	    // 상품 수정 페이지로 이동
	    	    return "/seller/productmodify";
	     
	 }

	 // 판매자 상품 수정 처리
	 @RequestMapping(value = "/productmodifySubmit", method = RequestMethod.POST)
	 public String handleProductModifySubmit(MultipartHttpServletRequest multiRequest,
	                                         @RequestParam("product_code") int product_code,
	                                         @RequestParam(value = "category", defaultValue = "기본 카테고리") String category,
	                                         @RequestParam(value = "product_name", defaultValue = "상품명 없음") String product_name,
	                                         @RequestParam(value = "unit", defaultValue = "개") String unit,
	                                         @RequestParam(value = "price", defaultValue = "0") int price,
	                                         @RequestParam(value = "product_explain", defaultValue = "설명 없음") String product_explain,
	                                         @RequestParam(value = "max_account", defaultValue = "0") int max_account,
	                                         @RequestParam(value = "country", defaultValue = "원산지 없음") String country,
	                                         @RequestParam(value = "store_code", defaultValue = "0") int store_code,
	                                         RedirectAttributes rttr) {
	     try {
	         // 파일 업로드 처리
	         List<String> fileNames = saveImage(multiRequest);

	         // 상품 정보 설정
	         ProductVO product = new ProductVO();
	         product.setProduct_code(product_code); // 상품 코드 설정
	         product.setCategory(category);
	         product.setProduct_name(product_name);
	         product.setUnit(unit);
	         product.setPrice(price);
	         product.setProduct_explain(product_explain);
	         product.setMax_account(max_account);
	         product.setCountry(country);
	         product.setStore_code(store_code);

	         // 이미지 파일 이름 설정
	         if (fileNames.size() >= 3) {
	             product.setImg1(fileNames.get(0));
	             product.setImg2(fileNames.get(1));
	             product.setImg3(fileNames.get(2));
	         }

	         // 상품 수정
	         pService.updateProduct(product);

	         // 상품 상세 페이지로 이동
	         return "redirect:/seller/productDetail?product_code=" + product_code;
	     } catch (IOException e) {
	         e.printStackTrace();
	         rttr.addFlashAttribute("f", "이미지 파일 업로드에 실패했습니다.");
	         // 상품 수정 페이지로 다시 이동하거나 적절한 처리를 수행합니다.
	         return "redirect:/seller/productmodify?product_code=" + product_code;
	     } catch (Exception e) {
	         e.printStackTrace();
	         // 오류 발생시 오류 페이지로 이동하거나 적절한 처리를 수행합니다.
	         return "/error";
	     }
	 }

	 @RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
	 public String deleteProductConfirm(@RequestParam("product_code") int productCode, Model model) throws Exception {
	     // 삭제할 상품 정보를 조회하여 모델에 추가
	     ProductVO product = pService.getProductById(productCode);
	     logger.debug(" asdad ");
	     model.addAttribute("product", product);
	     logger.debug(" 1231231");
	     return "seller/deleteProductConfirm";
	 }

	 @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
	 public String deleteProduct(@RequestParam("product_code") int product_code) throws Exception{
	     // 실제로 상품 삭제 로직을 수행
	     pService.deleteProduct(product_code);
	     logger.debug(" 삭제 성공 ");
	     // 삭제 후 상품 목록 페이지로 리다이렉트
	     return "redirect:/seller/product";
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
    public String reviewReplyPage(@RequestParam("user_id") String user_id,
            @RequestParam("product_code") int product_code,
            @RequestParam("order_code") int order_code,
            Model model) throws Exception{
	ReviewVO parentReview = rService.getReviewByParams(user_id, product_code, order_code);
	model.addAttribute("parentReview", parentReview);
	return "seller/reviewReply"; // 답글 작성 페이지로 이동
	}


    // 리뷰에 대한 답글 작성 처리
    @ResponseBody
    @RequestMapping(value = "/reviewReplySubmit", method = RequestMethod.POST)
    public String replySubmit(ReviewVO rvo) throws Exception{
        rService.addReply(rvo);
        return "redirect:/seller/reviewDetail?review_code=" + rvo.getReview_code(); // 답글이 추가된 리뷰 상세 페이지로 이동
    }
//    @ResponseBody
//    @RequestMapping(value = "/reviewReplySubmit", method = RequestMethod.POST)
//    public String reviewReplySubmit(ReviewVO rvo, HttpSession session) throws Exception {
//    	logger.debug("asdass13213213213231");
//        try {
//            // 부모 리뷰의 코드를 세션에서 가져와서 설정
            //Integer parentReviewCode = (Integer) session.getAttribute("parentReviewCode");
//            Integer parentReviewCode = rvo.getReview_code();
            
            // 부모 리뷰의 코드가 유효한지 확인
//            if (parentReviewCode != null && parentReviewCode > 0) {
                // 부모 리뷰의 코드를 설정
//                rvo.setReview_code(parentReviewCode);

                // 답글 작성 서비스 호출
//                rService.addReply(rvo);
//                logger.debug("답글 작성 성공");

                // 리뷰 상세 페이지로 리다이렉트
//                return "redirect:/seller/reviewDetail?review_code=" + parentReviewCode;
//                return "/seller/reviewDetail?review_code=" + parentReviewCode;
//            } else { 
//                logger.debug("답글 작성 실패: 부모 리뷰 코드가 유효하지 않음");
//              
//               return "/seller/review";
//               return "redirect:/seller/review";
//            }
//        } catch (Exception e) {
//            logger.error("답글 작성 실패: " + e.getMessage());
//            return "/seller/review";
//            return "redirect:/seller/review";
//        }
//    }


    // 리뷰 답글 수정 처리
    @ResponseBody
    @RequestMapping(value = "/replymodify", method = RequestMethod.POST)
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
