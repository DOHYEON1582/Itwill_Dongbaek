package com.itwillbs.persistence;

import com.itwillbs.domain.Order_infoVO;

public interface DeliveryDAO {
	// 배송 정보 조회
    public Order_infoVO getDeliveryInfo(int order_code) throws Exception;

    // 배송 정보 업데이트
    public int updateDeliveryInfo(Order_infoVO order_code) throws Exception;

}
