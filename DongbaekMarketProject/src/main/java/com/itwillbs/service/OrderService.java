package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.Order_infoVO;

public interface OrderService {
	// 업체 주문 정보(list)가져오기
	public List<Order_infoVO> getOrderList(String con, String search,String startDate,String endDate)throws Exception;
	
	// 주문 확정 처리
    public void confirmOrder(String order_code) throws Exception;
    
    // 주문 취소 처리
    public void cancelOrder(String order_code) throws Exception;
    
    // 주문 환불 처리
    public void refundOrder(String order_code) throws Exception;
}
