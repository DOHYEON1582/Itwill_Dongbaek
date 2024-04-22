package com.itwillbs.persistence;

import java.util.List;
import java.util.Map;

import com.itwillbs.domain.AdminCartVO;
import com.itwillbs.domain.AdminOrderVO;
import com.itwillbs.domain.AdminProductVO;
import com.itwillbs.domain.UserVO;

public interface AdminDAO {
	
	// 고객 상세정보 가져오기
	public UserVO getUserInfo(UserVO vo)throws Exception;
	
	// 고객 주문정보 가져오기
	public List<AdminOrderVO> getUserOrder(UserVO vo)throws Exception; 
	
	// 고객 선택한 주문정보 가져오기
	public List<AdminCartVO> getUserCart(int order_code)throws Exception;
	

	
	
	

}//endDAO
