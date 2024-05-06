package com.itwillbs.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.AdminCartVO;
import com.itwillbs.domain.AdminChatRoomVO;
import com.itwillbs.domain.AdminNoticeVO;
import com.itwillbs.domain.AdminOrderVO;
import com.itwillbs.domain.AdminProductVO;
import com.itwillbs.domain.AdminReviewVO;
import com.itwillbs.domain.AdminStoreVO;
import com.itwillbs.domain.AdminSubPayVO;
import com.itwillbs.domain.AdminSubProductVO;
import com.itwillbs.domain.SchedulerVO;
import com.itwillbs.domain.UserVO;
import com.itwillbs.persistence.AdminDAO;

@Service
public class AdminServiceImpl implements AdminService {

	@Inject
	private AdminDAO adao;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	

	@Override
	public String adminLogin(UserVO vo) throws Exception {
		logger.debug(" adminLogin(UserVO vo) 호출 ");
		
		return adao.adminLogin(vo);
	}

	@Override
	public UserVO getUserInfo(UserVO vo) throws Exception {
		logger.debug(" getUserInfo(UserVO vo) 실행 ");
		
		return adao.getUserInfo(vo);
	}

	@Override
	public List<AdminOrderVO> getUserOrder(UserVO vo) throws Exception {
		logger.debug(" getUserOrder(UserVO vo) 실행 ");
		
		return adao.getUserOrder(vo);
	}

	@Override
	public List<AdminCartVO> getUserCart(int order_code) throws Exception {
		logger.debug(" getUserCart(int order_code) 실행 ");
		
		return adao.getUserCart(order_code);
	}

	@Override
	public List<AdminReviewVO> getReviewList(UserVO vo) throws Exception {
		logger.debug(" getReviewList(UserVO vo) 실행 ");
		
		return adao.getReviewList(vo);
	}

	@Override
	public AdminReviewVO getReview(int review_code) throws Exception {
		logger.debug(" getReview(int review_code) 실행 ");
		
		return adao.getReview(review_code);
	}

	@Override
	public List<AdminStoreVO> getStoreList(AdminStoreVO vo) throws Exception {
		logger.debug(" getStoreList(AdminStoreVO vo) 실행 ");
		
		return adao.getStoreList(vo);
	}

	@Override
	public AdminStoreVO getStore(String store_number) throws Exception {
		logger.debug(" getStore(String store_number) 호출 ");
		
		return adao.getStore(store_number);
	}

	@Override
	public List<AdminOrderVO> getOrderList(String con, String search,String startDate,String endDate) throws Exception {
		logger.debug(" getOrderList(String con, String search)  호출 ");
	
		return adao.getOrderList(con, search,startDate,endDate);
	}

	@Override
	public AdminSubPayVO getUserSubInfo(UserVO vo) throws Exception {
		logger.debug(" getUserSubInfo(UserVO vo) 호출 ");
		
		return adao.getUserSubInfo(vo);
	}

	@Override
	public List<AdminSubProductVO> getUserSubPro(String user_id) throws Exception {
		logger.debug(" getUserSubPro(String user_id) 호출 ");
		
		return adao.getUserSubPro(user_id);
	}

	@Override
	public int insertSubProduct(AdminProductVO vo) throws Exception {
		logger.debug(" insertSubProduct(AdminProductVO vo) 호출 ");
		
		return adao.insertSubProduct(vo);
	}

	@Override
	public List<AdminProductVO> getSubProductList(AdminProductVO vo) throws Exception {
		logger.debug(" getSubProductList(AdminProductVO vo) 호출 ");
		
		return adao.getSubProductList(vo);
	}

	@Override
	public AdminProductVO getSubProductInfo(int product_code) throws Exception {
		logger.debug(" getSubProductInfo(int product_code) 호출 ");
		
		return adao.getSubProductInfo(product_code);
	}

	@Override
	public int insertNotice(AdminNoticeVO vo) throws Exception {
		logger.debug(" insertNotice(AdminNoticeVO vo) 호출 ");
		
		return adao.insertNotice(vo);
	}

	@Override
	public List<AdminNoticeVO> searchNotice(AdminNoticeVO vo) throws Exception {
		logger.debug(" searchNotice(AdminNoticeVO vo) 호출 ");
		
		return adao.searchNotice(vo);
	}

	@Override
	public AdminNoticeVO noticeInfo(int q_code) throws Exception {
		logger.debug(" noticeInfo(int q_code) 호출 ");
		
		return adao.noticeInfo(q_code);
	}

	@Override
	public List<AdminChatRoomVO> chatRoomList(UserVO vo) throws Exception {
		logger.debug(" chatRoomList(UserVO vo) 호출 ");
		
		return adao.chatRoomList(vo);
	}

	@Override
	public int createChatRoom(String user_id) throws Exception {
		logger.debug(" createChatRoom(String user_id) 호출 ");
		adao.createChatRoom(user_id);
		
		return adao.lastChatRoom(user_id);
	}

	@Override
	public Map<String, Object> chartSellCount() throws Exception {
		logger.debug(" chartSellCount() 호출 ");
		
		List<String> dayList = new ArrayList<String>();
		Map<String, Object> chartList = new LinkedHashMap<String, Object>();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		for(int i = 6; i > 0; i-- ){
			Calendar c1 = Calendar.getInstance(); 
		    c1.add(Calendar.DATE, -i);
		    dayList.add(dateFormat.format(c1.getTime()));
		}
		logger.debug("날짜 리스트"+dayList);
		
		for(String date : dayList) {
			chartList.put(date, adao.mainChartData(date));
		}
	    logger.debug("###########"+chartList);
		
		return chartList;
	}

	@Override
	public Map<String, Object> mainUserData() throws Exception {
		logger.debug(" mainUserData(String date) 호출 ");
		
		List<String> dayList = new ArrayList<String>();
		Map<String, Object> chartList = new LinkedHashMap<String, Object>();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		for(int i = 6; i > 0; i-- ){
			Calendar c1 = Calendar.getInstance(); 
		    c1.add(Calendar.DATE, -i);
		    dayList.add(dateFormat.format(c1.getTime()));
		}
		logger.debug("날짜 리스트"+dayList);
		
		for(String date : dayList) {
			chartList.put(date, adao.mainUserData(date));
		}
	    logger.debug("###########"+chartList);
		
		return chartList;
	}

	@Override
	public Map<String, Object> mainSellerData() throws Exception {
		logger.debug(" mainSellerData(String date) 호출 ");
		
		List<String> dayList = new ArrayList<String>();
		Map<String, Object> chartList = new LinkedHashMap<String, Object>();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		for(int i = 6; i > 0; i-- ){
			Calendar c1 = Calendar.getInstance(); 
		    c1.add(Calendar.DATE, -i);
		    dayList.add(dateFormat.format(c1.getTime()));
		}
		logger.debug("날짜 리스트"+dayList);
		
		for(String date : dayList) {
			chartList.put(date, adao.mainSellerData(date));
		}
	    logger.debug("###########"+chartList);
		
		return chartList;
	}

	
	
	
	
	
	
}//endImpl
