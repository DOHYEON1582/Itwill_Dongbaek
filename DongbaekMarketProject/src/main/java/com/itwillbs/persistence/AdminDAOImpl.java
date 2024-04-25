package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.AdminCartVO;
import com.itwillbs.domain.AdminOrderVO;
import com.itwillbs.domain.AdminReviewVO;
import com.itwillbs.domain.AdminStoreVO;
import com.itwillbs.domain.SchedulerVO;
import com.itwillbs.domain.UserVO;

@Repository
public class AdminDAOImpl implements AdminDAO {
	
	@Inject
	private SqlSession sql;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminDAOImpl.class);
	
	private static final String NAMESPACE = "com.itwillbs.mapper.AdminMapper";

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
	public void calCheck(SchedulerVO svo) throws Exception {
		logger.debug(" calCheck(SchedulerVO svo) 실행");
		sql.update(NAMESPACE+".updateCal", svo);
	}
	
	
	

}//endImpl
