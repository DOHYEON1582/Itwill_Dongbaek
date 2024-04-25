package com.itwillbs.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.AdminCartVO;
import com.itwillbs.domain.AdminOrderVO;
import com.itwillbs.domain.AdminReviewVO;
import com.itwillbs.domain.AdminStoreVO;
import com.itwillbs.domain.SchedulerVO;
import com.itwillbs.domain.UserVO;
import com.itwillbs.persistence.AdminDAO;

@Service
public class AdminServiceImpl implements AdminService {

	@Inject
	private AdminDAO adao;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

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
	public void calCheck(SchedulerVO svo) throws Exception {
		logger.debug(" calCheck(SchedulerVO svo) 호출 ");
		adao.calCheck(svo);
	}
	
	
	
	
	
}//endImpl
