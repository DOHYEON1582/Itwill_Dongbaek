package com.itwillbs.persistence;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

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
import com.itwillbs.domain.SchedulerVO;
import com.itwillbs.domain.UserVO;

@Repository
public class AdminDAOImpl implements AdminDAO {
	
	@Inject
	private SqlSession sql;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminDAOImpl.class);
	
	private static final String NAMESPACE = "com.itwillbs.mapper.AdminMapper";
	
	

	@Override
	public String adminLogin(UserVO vo) throws Exception {
		logger.debug(" adminLogin(UserVO vo) 호출 ");
		
		return sql.selectOne(NAMESPACE+".login", vo);
	}

	@Override
	public UserVO getUserInfo(UserVO vo) throws Exception {
		logger.debug(" getUserInfo(UserVO vo) 실행 ");
		
		return sql.selectOne(NAMESPACE+".getUser", vo);
	}

	@Override
	public List<AdminOrderVO> getUserOrder(UserVO vo) throws Exception {
		logger.debug(" getUserOrder(AdminOrderVO ovo) 실행 ");
		
		return sql.selectList(NAMESPACE+".getUserOrder", vo);
	}

	@Override
	public List<AdminCartVO> getUserCart(int order_code) throws Exception {
		logger.debug(" getUserCart(int order_code) 실행 ");
		
		return sql.selectList(NAMESPACE+".orderCart", order_code);
	}

	@Override
	public List<AdminReviewVO> getReviewList(UserVO vo) throws Exception {
		logger.debug(" getReviewList(UserVO vo) 실행 ");
		
		return sql.selectList(NAMESPACE+".getReviewList", vo);
	}

	@Override
	public AdminReviewVO getReview(int review_code) throws Exception {
		logger.debug(" getReview(int review_code) 실행 ");
		
		return sql.selectOne(NAMESPACE+".getReview", review_code);
	}

	@Override
	public List<AdminStoreVO> getStoreList(AdminStoreVO vo) throws Exception {
		logger.debug(" getStoreList(AdminStoreVO vo) 실행 ");
		
		return sql.selectList(NAMESPACE+".getStoreList", vo);
	}

	@Override
	public AdminStoreVO getStore(String store_number) throws Exception {
		logger.debug(" getStore(String store_number) 실행 ");
		
		return sql.selectOne(NAMESPACE+".getStore", store_number);
	}

	@Override
	public List<AdminOrderVO> getOrderList(String con, String search,String startDate,String endDate) throws Exception {
		logger.debug(" getOrderList(String con, String search) 실행 ");
		Map<String, String> map = new HashMap<String, String>();
		map.put("con", con);
		map.put("search", search);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		
		return sql.selectList(NAMESPACE+".getStoreOrderList", map);
	}

	@Override
	public AdminSubPayVO getUserSubInfo(UserVO vo) throws Exception {
		logger.debug(" getUserSubInfo(UserVO vo) 실행 ");
		
		return sql.selectOne(NAMESPACE+".subInfo", vo);
	}

	@Override
	public List<AdminSubProductVO> getUserSubPro(String user_id) throws Exception {
		logger.debug(" getUserSubPro(String user_id) 실행 ");
		
		return sql.selectList(NAMESPACE+".subList", user_id);
	}

	@Override
	public int insertSubProduct(AdminProductVO vo) throws Exception {
		logger.debug(" insertSubProduct(AdminProductVO vo) 실행 ");
		
		return sql.insert(NAMESPACE+".insertSubProduct", vo);
	}

	@Override
	public List<AdminProductVO> getSubProductList(AdminProductVO vo) throws Exception {
		logger.debug(" getSubProductList(AdminProductVO vo) 실행 ");
		
		return sql.selectList(NAMESPACE+".getSubProduct", vo);
	}

	@Override
	public AdminProductVO getSubProductInfo(int product_code) throws Exception {
		logger.debug(" getSubProductInfo(int product_code) 호출 ");
		
		return sql.selectOne(NAMESPACE+".getSubProInfo", product_code);
	}

	@Override
	public int insertNotice(AdminNoticeVO vo) throws Exception {
		logger.debug(" insertNotice(AdminNoticeVO vo) 호출 ");
		
		return sql.insert(NAMESPACE+".insertNotice", vo);
	}

	@Override
	public List<AdminNoticeVO> searchNotice(AdminNoticeVO vo) throws Exception {
		logger.debug(" searchNotice(AdminNoticeVO vo) 호출 ");
		
		return sql.selectList(NAMESPACE+".searchNotice", vo);
	}

	@Override
	public AdminNoticeVO noticeInfo(int q_code) throws Exception {
		logger.debug(" noticeInfo(int q_code) 호출 ");
		
		return sql.selectOne(NAMESPACE+".noticeInfo", q_code);
	}

	@Override
	public List<AdminChatRoomVO> chatRoomList(UserVO vo) throws Exception {
		logger.debug(" chatRoomList(UserVO vo) ");
		
		return sql.selectList(NAMESPACE+".chatRoomList", vo);
	}

	@Override
	public int createChatRoom(String user_id) throws Exception {
		logger.debug(" createChatRoom(String user_id) 호출 ");
		
		return sql.insert(NAMESPACE+".createRoom", user_id);
	}

	@Override
	public int lastChatRoom(String user_id) throws Exception {
		logger.debug(" lastChatRoom(String user_id) 호출  ");
		
		return sql.selectOne(NAMESPACE+".lastRoom", user_id);
	}

	@Override
	public String mainChartData(String date) throws Exception {
		logger.debug(" mainChartData() 호출 ");
		
		return sql.selectOne(NAMESPACE+".totalsell", date);
	}

	@Override
	public String mainUserData(String date) throws Exception {
		logger.debug(" mainUserData(String date) 호출 ");
		
		return sql.selectOne(NAMESPACE+".totalUser", date);
	}

	@Override
	public String mainSellerData(String date) throws Exception {
		logger.debug(" mainSellerData(String date) 호출 ");
		
		return sql.selectOne(NAMESPACE+".totalSeller", date);
	}

	@Override
	public int idCheck(AdminSellerVO vo) throws Exception {
		logger.debug(" idCheck(AdminSellerVO vo) 호출 ");
		
		return sql.selectOne(NAMESPACE+".idCheck", vo);
	}
	
	

	
	

}//endImpl
