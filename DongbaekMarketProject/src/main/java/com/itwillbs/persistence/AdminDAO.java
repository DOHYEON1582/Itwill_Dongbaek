package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.AdminCartVO;
import com.itwillbs.domain.AdminOrderVO;
import com.itwillbs.domain.AdminReviewVO;
import com.itwillbs.domain.AdminStoreVO;
import com.itwillbs.domain.AdminSubPayVO;
import com.itwillbs.domain.AdminSubProductVO;
import com.itwillbs.domain.SchedulerVO;
import com.itwillbs.domain.UserVO;

public interface AdminDAO {
	
	// 고객 상세정보 가져오기
	public UserVO getUserInfo(UserVO vo)throws Exception;
	
	// 고객 주문정보(list) 가져오기
	public List<AdminOrderVO> getUserOrder(UserVO vo)throws Exception; 
	
	// 고객 선택한 주문정보 가져오기
	public List<AdminCartVO> getUserCart(int order_code)throws Exception;
	
	// 고객 리뷰(list) 가져오기
	public List<AdminReviewVO> getReviewList(UserVO vo)throws Exception;

	// 고객 선택한 리뷰 가져오기
	public AdminReviewVO getReview(int review_code)throws Exception;
	
	// 업체 정보(list) 가져오기
	public List<AdminStoreVO> getStoreList(AdminStoreVO vo)throws Exception;
	
	// 업체 정보 가져오기
	public AdminStoreVO getStore(String store_number)throws Exception;
	
	// 업체 주문 정보(list)가져오기
	public List<AdminOrderVO> getOrderList(String con, String search,String startDate,String endDate)throws Exception;
	
	// 회원 구독 정보 가져오기
	public AdminSubPayVO getUserSubInfo(UserVO vo)throws Exception;
	
	// 회원 구독 제품 리스트 가져오기
	public List<AdminSubProductVO> getUserSubPro(String user_id)throws Exception;
	

}//endDAO
