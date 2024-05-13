package com.itwillbs.service;

import com.itwillbs.domain.Order_infoVO;

public interface DeliveryService {
	// 배송 정보 조회
    public Order_infoVO getDeliveryInfo(int order_code) throws Exception;

    // 배송 정보 업데이트
    public int updateDeliveryInfo(Order_infoVO order_code) throws Exception;
}
