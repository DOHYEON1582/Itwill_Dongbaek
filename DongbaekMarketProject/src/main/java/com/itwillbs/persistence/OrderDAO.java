package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.AdminOrderVO;
import com.itwillbs.domain.AdminStoreVO;
import com.itwillbs.domain.Order_infoVO;
import com.itwillbs.domain.StoreVO;

public interface OrderDAO {
	
	// 업체 주문 정보(list)가져오기
	public List<Order_infoVO> getOrderList(String con, String search,String startDate,String endDate)throws Exception;
	
	// 주문 확정 처리
    public int confirmOrder(String order_code) throws Exception;
    
    // 주문 취소 처리
    public int cancelOrder(String order_code) throws Exception;
    
    // 주문 환불 처리
    public int refundOrder(String order_code) throws Exception;
	
}

