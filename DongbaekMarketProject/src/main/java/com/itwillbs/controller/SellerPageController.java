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
import java.util.HashMap;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.Criteria;
import com.itwillbs.domain.Order_infoVO;
import com.itwillbs.domain.PageVO;
import com.itwillbs.domain.ProductCri;
import com.itwillbs.domain.ProductPagingVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.QuestionVO;
import com.itwillbs.domain.ReviewCri;
import com.itwillbs.domain.ReviewPagingVO;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.domain.SellerVO;
import com.itwillbs.domain.UserVO;
import com.itwillbs.service.DeliveryService;
import com.itwillbs.service.OrderService;
import com.itwillbs.service.ProductService;
import com.itwillbs.service.QuestionService;
import com.itwillbs.service.ReviewReplyService;
import com.itwillbs.service.SaleService;
import com.itwillbs.service.UserService;

import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping(value = "/seller/*")
public class SellerPageController {//판매자 페이지 컨트롤러

    @Inject
    private ProductService pService; // 상품 관련 서비스 가져오기
    
    @Inject
    private ReviewReplyService rService;
    
    @Inject
    private UserService uService;
   
    @Inject
    private OrderService oService;
    
    @Inject
    private DeliveryService dService;
    
    @Inject
    private SaleService sService;
    
    @Inject
    private QuestionService qService;
    // 파일 업로드 디렉토리
//    @Value("${file.upload.directory}")
//    private String FILE_DIRECTORY = "C:\\images";
    
    private static final Logger logger = LoggerFactory.getLogger(SellerPageController.class);
    
    
    @RequestMapping(value = "seller/register", method = RequestMethod.GET)
	public void registerGET() throws Exception{
		logger.debug(" registerGET() 호출 ");
		
	}
    @RequestMapping(value = "seller/register", method = RequestMethod.POST)
	public String registerPOST(SellerVO svo) throws Exception{
		logger.debug(" registerPOST(UserVO uvo) 호출 ");
		logger.debug(" 회원가입 정보 : " + svo);
		
		pService.SellerInsert(svo);
		
		return "redirect:/seller/login";
	}
    
    @RequestMapping(value = "/seller/login", method = RequestMethod.GET)
    public void sellerLogin()throws Exception {
    	logger.debug(" sellerLogin() 호출 "); 
    	
    }
    @RequestMapping(value = "/seller/login", method = RequestMethod.POST)
	public String loginPOST(SellerVO svo, HttpSession session) throws Exception{
		logger.debug(" loginPOST() 호출 ");
		
		SellerVO sellerVO = pService.loginSeller(svo);
		logger.debug(" 로그인 정보 : " + sellerVO);
		
		if (sellerVO != null) {
            session.setAttribute("seller_id", sellerVO);
            return "redirect:/seller/sellermain";
        } else {
            return "/seller/login";
        }
	}
    @RequestMapping(value="/seller/callBack", method=RequestMethod.GET)
	public String callBack(){
		logger.debug(" callBack() 호출 ");
		return "/seller/callBack";
	}
    
    @ResponseBody
	@RequestMapping(value = "/seller/confirm", method = RequestMethod.POST)
	public ResponseEntity<Integer> idCheckPOST(String seller_id) throws Exception {
	    logger.debug("idCheckPOST(String seller_id) 호출");
	    int result = 0;
	    if(seller_id != null && !seller_id.isEmpty()) {
	        logger.debug("조회된 아이디2 : " + pService.checkSellerId(seller_id));
	        result = pService.checkSellerId(seller_id);
	    }
	    return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
    
    // 판매자 정보
    @RequestMapping(value = "seller/info", method = RequestMethod.GET)
	public void infoGET(Model model, HttpSession session) throws Exception {
		logger.debug(" infoGET() 실행 ");
		SellerVO sellerVO = (SellerVO) session.getAttribute("seller_id");
		String seller_id = sellerVO.getSeller_id();
		logger.debug(" id : " + seller_id);
		model.addAttribute("Sellerinfo", pService.sellerInfo(seller_id));
	}
   
    // 정보 수정
    @RequestMapping(value = "seller/update", method = RequestMethod.GET)
	public void sellerUpdateGET(Model model, HttpSession session) throws Exception {
		logger.debug(" userUpdateGET() 실행 ");
		SellerVO sellerVO = (SellerVO) session.getAttribute("seller_id");
		String seller_id = sellerVO.getSeller_id();
		logger.debug(" id : " + seller_id);
		model.addAttribute("SellerInfo", pService.sellerInfo(seller_id));
	}
    @RequestMapping(value = "seller/update", method = RequestMethod.POST)
	public String sellerUpdatePOST(SellerVO svo) throws Exception {
		logger.debug(" sellerUpdatePOST(SellerVO svo) 호출 ");
		logger.debug("svo : " + svo);
		int result = pService.sellerUpdate(svo);
		if(result == 1) {
			logger.debug(" 수정완료! ");
			return "redirect:/";
		}
		logger.debug(" 수정실패! ");
		return "redirect:/seller/update";
	}
    
    // 로그아웃
    @GetMapping(value = "seller/logout")
	public String logoutGET(HttpSession session) throws Exception{
		logger.debug(" logoutGET(HttpSession session) 실행 ");
		session.invalidate();
		return "redirect:/seller/login";
	}
    
  //회원정보 삭제
  	@RequestMapping(value = "seller/delete", method = RequestMethod.GET)
  	public String deleteSellerGET() throws Exception {
  		logger.debug(" deleteSellerGET() 호출 ");
  		
  		return "/seller/delete";
  	}
  	@RequestMapping(value = "seller/delete", method = RequestMethod.POST)
  	public String deleteSellerPOST(SellerVO svo, HttpSession session) throws Exception {
  		logger.debug(" deleteSellerPOST() 호출 ");
  		logger.debug(" 삭제할 정보 : " + svo);
  		int result = pService.deleteSeller(svo);
  		logger.debug("result : " + result);
  		if(result == 1) {
  			session.invalidate();
  			return "redirect:/";
  		}
  		logger.debug(" 비밀번호 오류 ");
  		return "redirect:/seller/delete";
  	}
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    // 판매자 메인페이지
    //  http://localhost:8088/seller/sellermain
    @RequestMapping(value = "/sellermain",method = RequestMethod.GET)
    public void main() throws Exception{
        logger.debug(" sellermain() 실행 ");
    }
    // 판매자 상품페이지(상품목록)
    //  http://localhost:8088/seller/product
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String productList(Model model, ProductCri cri, HttpSession session) throws Exception {
        // 세션에서 판매자 정보 가져오기
        SellerVO sellerVO = (SellerVO) session.getAttribute("seller_id");
        if (sellerVO == null) {
            // 판매자 정보가 세션에 없으면 로그인 페이지로 리다이렉트 또는 다른 처리를 수행할 수 있음
            return "redirect:/seller/login";
        }
        
        // 판매자 정보로부터 가게 코드 가져오기
        Integer store_code = sellerVO.getStore_code();
        if (store_code == null) {
            // 가게 코드가 없으면 다른 처리를 수행할 수 있음
            return "redirect:/error";
        }

        // 가게 코드를 설정
        cri.setStore_code(Integer.valueOf(store_code));


        // 총 상품 수 가져오기
        int totalCount = pService.getTotalCount(sellerVO.getSeller_id());

        ProductPagingVO pagingVO = new ProductPagingVO();
        pagingVO.setCri(cri);
        pagingVO.setTotalCount(totalCount);

        logger.debug("" + pagingVO);

        List<ProductVO> product = pService.getProductPage(cri);

        model.addAttribute("product", product);
        model.addAttribute("cri", cri);
        model.addAttribute("pagingVO", pagingVO);

        return "seller/product"; // 뷰의 경로 수정
    }


        
    // 상품 상세 페이지를 보여주는 메서드
    @RequestMapping(value = "/productDetail", method = RequestMethod.GET)
    public String showProductDetail(@RequestParam("product_code") int product_code, Model model) {
        // 상품 정보 조회
        ProductVO product = pService.getProductById(product_code);
        
        // 조회된 상품 정보를 모델에 추가하여 뷰로 전달
        model.addAttribute("product", product);
        
        // 상품 상세 페이지로 이동
        return "seller/productDetail"; // 뷰의 경로 수정
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
    public ResponseEntity productregistSubmit(@RequestParam("product_name") String product_name,
                                               @RequestParam("price") int price,
                                               @RequestParam("country") String country,
                                               @RequestParam("max_account") String max_account,
                                               @RequestParam("unit") String unit,
                                               @RequestParam("category") String category,
                                               @RequestParam("product_explain") String product_explain,
                                               @RequestParam(value = "store_code", defaultValue = "0") int store_code,
                                               @RequestParam("img1") MultipartFile img1,
                                               @RequestParam("img2") MultipartFile img2,
                                               @RequestParam("img3") MultipartFile img3,                                            
                                               @RequestParam("market_code") int market_code,
                                               HttpServletRequest request) throws Exception{
        logger.debug("productregistSubmit 호출");

        String uniqueFileName1 = "";
        String uniqueFileName2 = "";
        String uniqueFileName3 = "";

        try {
          
            String realPath = request.getSession().getServletContext().getRealPath("/resources/upload1");
            logger.debug("실제 경로 : " + realPath);

            uniqueFileName1 = saveFile(img1, realPath);
            uniqueFileName2 = saveFile(img2, realPath);
            uniqueFileName3 = saveFile(img3, realPath);


            ProductVO product = new ProductVO();
            product.getProduct_code();
            product.setProduct_name(product_name);
            product.setPrice(price);
            product.setCountry(country);
            product.setMax_account(Integer.parseInt(max_account));
            product.setUnit(unit);
            product.setCategory(category);
            product.setProduct_explain(product_explain);
            product.setSeller_id("seller");
            product.setStore_code(store_code);
            product.setImg1(uniqueFileName1);
            product.setImg2(uniqueFileName2);
            product.setImg3(uniqueFileName3);

            logger.debug("작성한 상품: " + product.toString());

            int success = 0;
    		success = pService.productRegist(product);

            HttpHeaders respHeaders = new HttpHeaders();
            respHeaders.add("Content-Type", "text/html; charset=utf-8");

            if (success == 1) {
                String result = "<script>";
                result += " alert('상품 등록 완료!'); ";
                result += " location.href='http://localhost:8088/seller/product';";
                result += "</script>";
                return new ResponseEntity(result, respHeaders, HttpStatus.OK);
            } else {
                String result = "<script>";
                result += " alert('상품 등록 실패!'); ";
                result += " location.href='http://localhost:8088/seller/productregist';";
                result += "</script>";
                return new ResponseEntity(result, respHeaders, HttpStatus.OK);
            }
        } catch (IOException e) {
            e.printStackTrace();
            String result = "<script>";
            result += " alert('이미지 파일 업로드에 실패했습니다.'); ";
            result += " location.href='http://localhost:8088/seller/productregist';";
            result += "</script>";
            HttpHeaders respHeaders = new HttpHeaders();
            respHeaders.add("Content-Type", "text/html; charset=utf-8");
            return new ResponseEntity(result, respHeaders, HttpStatus.OK);
        }
    }


    private String saveFile(MultipartFile file, String realPath) throws IOException {
        String uniqueFileName = "";

        if (!file.isEmpty()) {
            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            uniqueFileName = UUID.randomUUID().toString() + fileExtension; // 중복방지를 위해 파일이름 랜덤값 변경
            String filePath = realPath + File.separator + uniqueFileName;
            File dest = new File(filePath);

            // 업로드 디렉토리가 존재하지 않으면 생성
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            file.transferTo(dest);
        }

        return uniqueFileName;
    }
     
     

//	 private List<String> saveImage(MultipartHttpServletRequest multiRequest) throws Exception{
//			logger.debug(" fileProcess() - 파일 업로드 처리 시작 ");
//			
//			// 업로드 파일의 정보를 저장
//			List<String> fileList = new ArrayList<String>();
//			
//			Iterator<String> fileNames = multiRequest.getFileNames(); //파일의 파라메터명을 가져온다
//			while(fileNames.hasNext()) {
//				String fileName = fileNames.next(); 
//				MultipartFile mFile = multiRequest.getFile(fileName);
//				String oFileName = mFile.getOriginalFilename();
//				logger.debug(" fileName: "+fileName+", oFileName : "+oFileName);
//				
//				fileList.add(oFileName); // 파일의 이름을 저장
//				
//				// 파일 업로드 처리
//				File file = new File("C:\\images\\"+oFileName);
//				
//				if(mFile.getSize() != 0) { //첨부파일이 있는지 없는지 체크
//					if( !file.exists() ) { // exists()- 해당파일이 있을때 t,없을때 f
//						// => 해당파일이 없을때
//						if(file.getParentFile().mkdirs()) { //해당 파일의 폴더를 생성
//							file.createNewFile();						
//						}
//					}//exists()
//					
//					//mFile.transferTo(file);
//					mFile.transferTo(file);
//				}//getSize()
//				
//			}//while
//			
//			logger.debug(" fileProcess() - 파일 업로드 처리 끝 ");
//			
//			return fileList;
//		}
//	 
//	 
//	 // 파일 다운로드 :  업로드 해놓은 파일의 위치, 다운로드할 파일 이름
//	 @RequestMapping(value = "/download", method = RequestMethod.GET)
//	 public void fileDownloadGET(@RequestParam("fileName") String fileName,
//	                             HttpServletResponse resp) throws Exception {
//	     logger.debug("fileDownloadGET() 호출");
//
//	     String downLoadPath = "C:\\images\\";
//	     logger.debug("다운로드 할 fileName: " + fileName);
//
//	     // 다운로드할 파일
//	     File file = new File(downLoadPath + fileName);
//
//	     // 데이터(첨부파일)를 전송하는 통로
//	     OutputStream out = resp.getOutputStream();
//
//	     // 모든 파일의 다운로드 형태를 통일
//	     resp.setHeader("Cache-Control", "no-cache");
//	     resp.addHeader("Content-disposition", "attachment; fileName=" + (URLEncoder.encode(fileName, "UTF-8")));
//
//	     // 파일 데이터를 읽기
//	     FileInputStream fis = new FileInputStream(file);
//
//	     byte[] buffer = new byte[1024 * 8]; // 8KB
//
//	     int data = 0;
//	     while ((data = fis.read(buffer)) != -1) { // -1 파일의 끝(EOF)
//	         // 다운로드 출력
//	         out.write(buffer, 0, data);
//	     }
//
//	     out.flush(); // 버퍼의 여백을 공백을 채움
//	     out.close();
//	     fis.close();
//
//	     logger.debug("파일 다운로드 완료!");
//	 }



   


 






 // 판매자 상품 수정 페이지
    @GetMapping("/productmodify")
    public String showProductModifyForm(@RequestParam("product_code") int product_code, Model model) throws Exception {
        try {
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
        } catch (Exception e) {
            // 상품을 찾을 수 없는 경우 오류 처리
            model.addAttribute("error", "상품을 찾을 수 없습니다.");
            return "/error";
        }
    }

    // 판매자 상품 수정 처리
    @PostMapping("/productmodifySubmit")
    public ResponseEntity productModifySubmit(@RequestParam("product_name") String product_name,
                                               @RequestParam("price") int price,
                                               @RequestParam("country") String country,
                                               @RequestParam("max_account") String max_account,
                                               @RequestParam("unit") String unit,
                                               @RequestParam("category") String category,
                                               @RequestParam("product_explain") String product_explain,
                                               @RequestParam("img1") MultipartFile img1,
                                               @RequestParam("img2") MultipartFile img2,
                                               @RequestParam("img3") MultipartFile img3,
                                               HttpServletRequest request) throws Exception{
    	logger.debug("productmodify 호출");

        String uniqueFileName1 = "";
        String uniqueFileName2 = "";
        String uniqueFileName3 = "";

        try {
          
            String realPath = request.getSession().getServletContext().getRealPath("/resources/upload1");
            logger.debug("실제 경로 : " + realPath);

            uniqueFileName1 = saveFile(img1, realPath);
            uniqueFileName2 = saveFile(img2, realPath);
            uniqueFileName3 = saveFile(img3, realPath);


            ProductVO product = new ProductVO();
            product.getProduct_code();
            product.setProduct_name(product_name);
            product.setPrice(price);
            product.setCountry(country);
            product.setMax_account(Integer.parseInt(max_account));
            product.setUnit(unit);
            product.setCategory(category);
            product.setProduct_explain(product_explain);
            product.setSeller_id("seller");
            product.setImg1(uniqueFileName1);
            product.setImg2(uniqueFileName2);
            product.setImg3(uniqueFileName3);

            logger.debug("작성한 상품: " + product.toString());

            int success = 0;
    		success = pService.updateProduct(product);

            HttpHeaders respHeaders = new HttpHeaders();
            respHeaders.add("Content-Type", "text/html; charset=utf-8");

            if (success == 1) {
                String result = "<script>";
                result += " alert('상품 수정 완료!'); ";
                result += " location.href='http://localhost:8088/seller/product';";
                result += "</script>";
                return new ResponseEntity(result, respHeaders, HttpStatus.OK);
            } else {
                String result = "<script>";
                result += " alert('상품 수정 실패!'); ";
                result += " location.href='http://localhost:8088/seller/productmodify';";
                result += "</script>";
                return new ResponseEntity(result, respHeaders, HttpStatus.OK);
            }
        } catch (IOException e) {
            e.printStackTrace();
            String result = "<script>";
            result += " alert('이미지 파일 업로드에 실패했습니다.'); ";
            result += " location.href='http://localhost:8088/seller/productmodify';";
            result += "</script>";
            HttpHeaders respHeaders = new HttpHeaders();
            respHeaders.add("Content-Type", "text/html; charset=utf-8");
            return new ResponseEntity(result, respHeaders, HttpStatus.OK);
        }
    }

    private String saveFile(MultipartFile file, HttpServletRequest request) throws IOException {
        String uniqueFileName = "";

        if (!file.isEmpty()) {
            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            uniqueFileName = UUID.randomUUID().toString() + fileExtension; // 중복방지를 위해 파일이름 랜덤값 변경
            String realPath = request.getSession().getServletContext().getRealPath("/resources/upload1");
            String filePath = realPath + File.separator + uniqueFileName;
            File dest = new File(filePath);

            // 업로드 디렉토리가 존재하지 않으면 생성
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            file.transferTo(dest);
        }

        return uniqueFileName;
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




	////////////////////////////////////////////////////
	
	
	
	
	
	
	// 판매자 주문페이지(주문목록)
	//	http://localhost:8088/seller/orderlist
	@RequestMapping(value = "/orderlist",method = RequestMethod.GET)
	public void orderlist() throws Exception{
		logger.debug(" orderlist() 실행 ");
		
	}
	
	// 주문 확정 컨트롤러
	@RequestMapping(value = "/confirmOrder", method = RequestMethod.POST)
	public String confirmOrder(@RequestParam("order_code") String order_code) {
	    // 주문을 확정하는 로직을 구현합니다.
	    // orderId를 사용하여 해당 주문을 확정합니다.
	    return "redirect:/seller/orderlist"; // 주문 목록 페이지로 리다이렉트합니다.
	}

	// 주문 취소 컨트롤러
	@RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
	public String cancelOrder(@RequestParam("order_code") String order_code) {
	    // 주문을 취소하는 로직을 구현합니다.
	    // orderId를 사용하여 해당 주문을 취소합니다.
	    return "redirect:/seller/orderlist"; // 주문 목록 페이지로 리다이렉트합니다.
	}

	// 주문 환불 컨트롤러
	@RequestMapping(value = "/refundOrder", method = RequestMethod.POST)
	public String refundOrder(@RequestParam("order_code") String order_code) {
	    // 주문을 환불하는 로직을 구현합니다.
	    // orderId를 사용하여 해당 주문을 환불합니다.
	    return "redirect:/seller/orderlist"; // 주문 목록 페이지로 리다이렉트합니다.
	}

	
	
	
	
	
	
	
	
	
	////////////////////////////////////////////////
	
	
	
	
	
	// 판매자 배송페이지
	//	http://localhost:8088/seller/delivery
	@RequestMapping(value = "/delivery", method = RequestMethod.GET)
	public String delivery(@RequestParam("order_code") int order_code,Model model) throws Exception{
        logger.debug("delivery() 실행");
        try {
            // 배송 정보 조회
            Order_infoVO deliveryInfo = dService.getDeliveryInfo(order_code); // order_code는 실제로 사용하는 값으로 수정해야 합니다.
            model.addAttribute("deliveryInfo", deliveryInfo);
        } catch (Exception e) {
            logger.error("배송 정보 조회 중 오류 발생: {}", e.getMessage());
            // 오류 페이지로 이동하거나 예외 처리를 위한 코드 추가
        }
        return "seller/delivery";
    }

	// 배송 정보 업데이트
    @PostMapping("/updateDeliveryInfo")
    public String updateDeliveryInfo(Order_infoVO deliveryInfo) {
        try {
            dService.updateDeliveryInfo(deliveryInfo);
        } catch (Exception e) {
            logger.error("배송 정보 업데이트 중 오류 발생: {}", e.getMessage());
            // 오류 페이지로 이동하거나 예외 처리를 위한 코드 추가
        }
        return "redirect:/seller/delivery?order_code=" + deliveryInfo.getOrder_code();
    }
	
	
	
	
	
	
	
	////////////////////////////////////////////////////////
	
	
	
	
	
	
	// 판매자 리뷰페이지
		// http://localhost:8088/seller/review
		// 리뷰 목록을 보여주는 페이지
		@GetMapping("/review")
		public String reviewList(Model model, @RequestParam(name = "review_code", required = false) Integer review_code, HttpSession session) throws Exception {
		    if (review_code != null) {
		        model.addAttribute("review", rService.getReview(review_code));
		        return "seller/review_detail"; // 리뷰 상세 페이지로 이동
		    } else {
		        model.addAttribute("reviews", rService.getReviewList(null));
		        return "seller/review"; // 리뷰 목록 페이지로 이동
		    }
		}


		// 리뷰 답글을 작성하는 페이지로 이동
	    @GetMapping("/reviewReply")
	    public String reviewReply(@RequestParam("review_code") int review_code, Model model) throws Exception{
	        // review_code를 이용하여 해당 리뷰 정보를 가져옴
	        ReviewVO review = rService.getReview(review_code);
	        model.addAttribute("review", review);
	        return "seller/reviewReply"; // 리뷰 답글을 작성하는 폼이 있는 JSP 페이지
	    }

	    // 리뷰 답글을 작성하는 기능
	    @PostMapping("/reply")
	    public String reply(ReviewVO reply) throws Exception {
	        // 작성된 리뷰 답글을 저장
	        rService.addReply(reply);
	        // 작성한 리뷰 답글이 포함된 리뷰 목록 페이지로 이동
	        return "redirect:/seller/review";
	    }


	// 판매자 리뷰페이지 상세페이지
	// http://localhost:8088/seller/reviewDetail
	@RequestMapping(value = "/reviewDetail", method = RequestMethod.GET)
	public String getReviewDetail(ReviewCri cri,@RequestParam("review_code") int review_code, Model model, HttpSession session) throws Exception {
		logger.debug(" /seller/reviewDetail 호출 ");
		
		// 전달 정보 저장
		logger.debug(" id : "+review_code);
	    // 특정 리뷰의 상세 정보를 가져옴
	    ReviewVO review = rService.getReview(review_code);
	    model.addAttribute("review", review);
	    
	    model.addAttribute("cri", cri);

	    return "seller/reviewDetail"; // 리뷰 상세 페이지로 이동
	}
	
	
/////////////////////////////////////////////////////////////////////

    
		
		
    
    
    
    
    
    
    
    
    
    
	// 판매자 매출페이지
	//	http://localhost:8088/seller/sales
    @RequestMapping(value = "/sales", method = RequestMethod.GET)
    public String getAllSales(Model model) throws Exception {
        // 시작일과 종료일을 담은 Map 생성
        Map<String, Object> dateRange = new HashMap<>();
        // 여기서 시작일과 종료일을 설정하십시오
        // 예를 들어, dateRange.put("startDate", "2024-01-01");
        // dateRange.put("endDate", "2024-12-31");
        
        // 일별 매출 조회
        List<Map<String, Object>> dailySales = sService.getDailySales(dateRange);
        // 월별 매출 조회
        List<Map<String, Object>> monthlySales = sService.getMonthlySales(dateRange);
        // 연도별 매출 조회
        List<Map<String, Object>> yearlySales = sService.getYearlySales(dateRange);

        // 조회 결과를 모델에 추가하여 JSP로 전달
        model.addAttribute("dailySales", dailySales);
        model.addAttribute("monthlySales", monthlySales);
        model.addAttribute("yearlySales", yearlySales);

        // sales.jsp 페이지로 이동
        return "seller/sales";
    }
	
	// 판매자 문의페이지
	//	http://localhost:8088/seller/question
    @RequestMapping(value = "/question", method = RequestMethod.GET)
	public void questionMain(@RequestParam("product_code") int product_code, Criteria cri, Model model) throws Exception {
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(qService.questionCount());
		logger.debug("pagevo" + pageVO);
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("product_code", product_code);
		ProductVO product = qService.eachProduct(product_code);
		paramMap.put("cri", cri);
		
		logger.debug("paramMap" + paramMap);
	
		List<QuestionVO> question = qService.getQuestion(paramMap);
		model.addAttribute("question", question);
		model.addAttribute("product", product);
		model.addAttribute("cri", cri);
		model.addAttribute("pageVO", pageVO);
	}
}
