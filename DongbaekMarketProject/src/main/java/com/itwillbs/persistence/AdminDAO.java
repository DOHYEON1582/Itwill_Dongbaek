package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.AdminCartVO;
import com.itwillbs.domain.AdminNoticeVO;
import com.itwillbs.domain.AdminOrderVO;
import com.itwillbs.domain.AdminProductVO;
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
	
	// 구독 물품 업로드하기
	public int insertSubProduct(AdminProductVO vo)throws Exception;
	
	// 구독 물품 검색하기
	public List<AdminProductVO> getSubProductList(AdminProductVO vo)throws Exception;
	
	// 구독 물품 상세보기
	public AdminProductVO getSubProductInfo(int product_code)throws Exception;
	
	// 공지사항 작성하기
	public int insertNotice(AdminNoticeVO vo)throws Exception;
	
	// 공지사항 검색하기
	public List<AdminNoticeVO> searchNotice(AdminNoticeVO vo)throws Exception;
	
	// 공지사항 상세보기
	public AdminNoticeVO noticeInfo(int q_code)throws Exception;

}//endDAO
