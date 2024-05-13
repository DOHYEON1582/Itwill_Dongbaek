package com.itwillbs.service;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.Order_infoVO;
import com.itwillbs.persistence.DeliveryDAO;

@Service
public class DeliveryServiceImpl implements DeliveryService {

	@Inject
	private DeliveryDAO ddao;
	
	@Override
    public Order_infoVO getDeliveryInfo(int order_code) throws Exception {
        return ddao.getDeliveryInfo(order_code);
    }

    @Override
    public int updateDeliveryInfo(Order_infoVO order_code) throws Exception {
        return ddao.updateDeliveryInfo(order_code);
    }
}