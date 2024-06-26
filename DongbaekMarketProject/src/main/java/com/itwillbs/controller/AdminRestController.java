package com.itwillbs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.itwillbs.domain.AdminCartVO;
import com.itwillbs.domain.AdminChatRoomVO;
import com.itwillbs.domain.AdminNoticeVO;
import com.itwillbs.domain.AdminOrderVO;
import com.itwillbs.domain.AdminProductVO;
import com.itwillbs.domain.AdminReviewVO;
import com.itwillbs.domain.AdminSellerVO;
import com.itwillbs.domain.AdminStoreVO;
import com.itwillbs.domain.AdminSubPayVO;
import com.itwillbs.domain.AdminSubProductVO;
import com.itwillbs.domain.UserVO;
import com.itwillbs.service.AdminService;

@RestController
@RequestMapping(value = "/admin")
public class AdminRestController {

	@Inject
	private AdminService aService;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminRestController.class);
	
	@PostMapping(value = "/customer")
	public ResponseEntity<UserVO> userInfo(@RequestBody UserVO vo)throws Exception{
		logger.debug(" vo : "+vo);
		UserVO uvo = aService.getUserInfo(vo);
		logger.debug(" uvo : "+uvo);
	
		return new ResponseEntity<UserVO>(uvo, HttpStatus.OK);
	}
	
	@PostMapping(value = "/customerorder")
	public ResponseEntity<List<AdminOrderVO>> orderInfo(@RequestBody UserVO vo)throws Exception{
		logger.debug(" orderInfo() 호출 ");
		//logger.debug("#########"+aService.getUserOrder(vo).size());
		
		return new ResponseEntity<List<AdminOrderVO>>(aService.getUserOrder(vo), HttpStatus.OK);
	}
	
	@GetMapping(value = "/orderlist/{order_code}")
	public ResponseEntity<Map<String, Object>> orderList(@PathVariable("order_code") int order_code)throws Exception{
		logger.debug(" orderList() 호출 ");
		logger.debug("order_code : "+order_code);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		UserVO vo = new UserVO();
		vo.setOrder_code(order_code);
		map.put("orderList", aService.getUserOrder(vo));
		
		map.put("cartList", aService.getUserCart(order_code));

		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@PostMapping(value = "/customerreview")
	public ResponseEntity<List<AdminReviewVO>> reviewList(@RequestBody UserVO vo)throws Exception{
		logger.debug(" reviewList() 호출 ");
		
		return new ResponseEntity<List<AdminReviewVO>>(aService.getReviewList(vo), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/customerreview/{review_code}")
	public ResponseEntity<AdminReviewVO> getReview(@PathVariable("review_code") int review_code)throws Exception{
		logger.debug(" getReview(@PathVariable(\"review_code\") 호출 ");
		
		return new ResponseEntity<AdminReviewVO>(aService.getReview(review_code), HttpStatus.OK);
	}
	
	@PostMapping(value = "/shop")
	public ResponseEntity<List<AdminStoreVO>> getStoreList(@RequestBody AdminStoreVO vo)throws Exception{
		logger.debug(" getStoreList(String search) 호출 ");
		//logger.debug("!!!!!!!!!"+vo);
		
		return new ResponseEntity<List<AdminStoreVO>>(aService.getStoreList(vo), HttpStatus.OK);
	}
	
	@GetMapping(value = "/shop/{store_number}")
	public ResponseEntity<AdminStoreVO> getStore(@PathVariable("store_number")String store_number)throws Exception{
		logger.debug(" getStore() 호출 ");
		//logger.debug("store_number : "+store_number);
		
		
		return new ResponseEntity<AdminStoreVO>(aService.getStore(store_number), HttpStatus.OK);
	}
	
	@GetMapping(value = "/shoporder/{con}/{search}/{startDate}/{endDate}")
	public ResponseEntity<List<AdminOrderVO>> getShopOrderList(@PathVariable("con")String con, @PathVariable("search")String search,@PathVariable("startDate")String startDate,@PathVariable("endDate")String endDate)throws Exception{
		logger.debug(" getShopOrderList() 호출 ");
		logger.debug(" 데이터 : "+con+search+startDate+endDate);
		if(startDate.equals("0000-00-00")) {
			startDate = "";
		}
		if(endDate.equals("9999-99-99")) {
			endDate = "";
		}
		logger.debug(" 데이터 : "+con+search+startDate+endDate);
		return new ResponseEntity<List<AdminOrderVO>>(aService.getOrderList(con, search,startDate,endDate), HttpStatus.OK);
	}
	
	@GetMapping(value = "/shoporder/{order_code}")
	public ResponseEntity<Map<String,Object>> getShopOrder(@PathVariable("order_code")int order_code)throws Exception{
		logger.debug(" getShopOrder(@PathVariable(\"order_code\")int order_code) 호출 ");
		Map<String, Object> map = new HashMap<String, Object>();
		
		UserVO vo = new UserVO();
		vo.setOrder_code(order_code);
		map.put("orderList", aService.getUserOrder(vo));
		
		map.put("cartList", aService.getUserCart(order_code));

		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@PostMapping(value = "/sub")
	public ResponseEntity<AdminSubPayVO> getSubInfo(UserVO vo)throws Exception{
		logger.debug(" getSubInfo(UserVO vo) 호출 ");
		
		return new ResponseEntity<AdminSubPayVO>(aService.getUserSubInfo(vo), HttpStatus.OK);
	}
	
	@GetMapping(value = "/sub/{user_id}")
	public ResponseEntity<Map<String, Object>> getSubProduct(@PathVariable("user_id")String user_id)throws Exception{
		logger.debug(" getSubProduct(@PathVariable(\"user_id\")String user_id) 호출 ");
		Map<String, Object> map = new HashMap<String, Object>();
		UserVO vo = new UserVO();
		vo.setUser_id(user_id);
		
		map.put("subInfo", aService.getUserSubInfo(vo));
		map.put("subList", aService.getUserSubPro(user_id));
		
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@PostMapping(value = "/sublistget")
	public ResponseEntity<List<AdminProductVO>> subProductList(@RequestBody AdminProductVO vo)throws Exception{
		logger.debug(" subProductList() 호출 ");
		
		return new ResponseEntity<List<AdminProductVO>>(aService.getSubProductList(vo), HttpStatus.OK);
	}

	@GetMapping(value = "/sublist/{product_code}")
	public ResponseEntity<AdminProductVO> subProductInfo(@PathVariable("product_code")int product_code )throws Exception{
		logger.debug(" subProductInfo() 호출 ");
		
		return new ResponseEntity<AdminProductVO>(aService.getSubProductInfo(product_code), HttpStatus.OK);
	}
	
	@PostMapping(value = "/notice")
	public ResponseEntity<List<AdminNoticeVO>> searchNotice(@RequestBody AdminNoticeVO vo)throws Exception{
		logger.debug(" searchNotice() 호출 ");
		
		return new ResponseEntity<List<AdminNoticeVO>>(aService.searchNotice(vo), HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/notice/{q_code}")
	public ResponseEntity<AdminNoticeVO> noticeInfo(@PathVariable("q_code")int q_code)throws Exception{
		logger.debug(" noticeInfo(@PathVariable(\"q_code\")int q_code) 호출 ");
		
		return new ResponseEntity<AdminNoticeVO>(aService.noticeInfo(q_code), HttpStatus.OK);
	}
	
	@PostMapping(value = "/qna")
	public ResponseEntity<List<AdminChatRoomVO>> qnaList(@RequestBody UserVO vo)throws Exception{
		logger.debug(" qnaList()호출 ");
		
		return new ResponseEntity<List<AdminChatRoomVO>>(aService.chatRoomList(vo), HttpStatus.OK);
	}
	
	@GetMapping(value = "/createRoom/{user_id}")
	public int createRoom(@PathVariable("user_id") String user_id)throws Exception{
		logger.debug(" createRoom(@PathVariable(\"user_id\") String user_id) 호출 ");
		int result = aService.createChatRoom(user_id);
		
		return result;
	}
	
	@PostMapping(value = "/idcheck")
	public int idCheck(@RequestBody AdminSellerVO vo)throws Exception{
		logger.debug(" idCheck(@RequestBody AdminSellerVO vo) 호출 ");
		logger.debug("idcheck : "+vo.getSeller_id());
		
		int result  = aService.idCheck(vo);
		
		return result;
	}
	
	@GetMapping(value = "/deleteroom/{room_idx}")
	public int deleteRoom(@PathVariable("room_idx") int room_idx)throws Exception{
		logger.debug(" deleteRoom(@RequestBody int room_idx) 호출 ");
		
		int result = aService.deleteChatRoom(room_idx);
		
		return result;
	}
	
	@GetMapping(value = "/prosearch/{product_name}")
	public ResponseEntity<List<AdminProductVO>> productSearch(@PathVariable("product_name")String product_name)throws Exception{
		logger.debug(" productSearch() 호출 ");
		logger.debug(" 상품이름 : "+product_name);
		
		return new ResponseEntity<List<AdminProductVO>>(aService.searchProduct(product_name),HttpStatus.OK);
	}
	
	@GetMapping(value = "/shopdelete/{store_number}")
	public int deleteStore(@PathVariable("store_number")int store_number)throws Exception{
		logger.debug(" deleteStore(@PathVariable(\"store_number\")int store_number) 호출 ");
		logger.debug("!!!!"+ store_number);
		
		int result = aService.deleteStore(store_number);
		
		return result;
	}
	
	
	
}//endController
