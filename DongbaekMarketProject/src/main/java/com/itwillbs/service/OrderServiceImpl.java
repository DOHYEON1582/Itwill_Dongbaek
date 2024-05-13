package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.AdminOrderVO;
import com.itwillbs.domain.Order_infoVO;
import com.itwillbs.persistence.OrderDAO;

@Service
public class OrderServiceImpl implements OrderService{
	@Inject
	private OrderDAO odao;
	
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Override
	public List<Order_infoVO> getOrderList(String con, String search,String startDate,String endDate) throws Exception {
		logger.debug(" getOrderList(String con, String search)  호출 ");
	
		return odao.getOrderList(con, search,startDate,endDate);
	}
	
	@Override
    public void confirmOrder(String order_code) throws Exception {
        odao.confirmOrder(order_code);
    }

    @Override
    public void cancelOrder(String order_code) throws Exception {
    	odao.cancelOrder(order_code);
    }

    @Override
    public void refundOrder(String order_code) throws Exception {
    	odao.refundOrder(order_code);
    }
}
