package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.AdminOrderVO;
import com.itwillbs.domain.AdminStoreVO;
import com.itwillbs.domain.Order_infoVO;
import com.itwillbs.domain.StoreVO;
import com.itwillbs.domain.UserVO;

@Repository
public class OrderDAOImpl implements OrderDAO{
	@Inject
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(OrderDAOImpl.class);
	
	private static final String NAMESPACE = "com.itwillbs.mapper.OrderMapper";
	
	
	
	@Override
	public List<Order_infoVO> getOrderList(String con, String search,String startDate,String endDate) throws Exception {
		logger.debug(" getOrderList(String con, String search) 실행 ");
		Map<String, String> map = new HashMap<String, String>();
		map.put("con", con);
		map.put("search", search);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		
		return sqlSession.selectList(NAMESPACE+".getStoreOrderList", map);
	}
	
	@Override
    public int confirmOrder(String order_code) throws Exception {
        return sqlSession.update(NAMESPACE + ".confirmOrder", order_code);
    }

    @Override
    public int cancelOrder(String order_code) throws Exception {
        return sqlSession.update(NAMESPACE + ".cancelOrder", order_code);
    }

    @Override
    public int refundOrder(String order_code) throws Exception {
        return sqlSession.update(NAMESPACE + ".refundOrder", order_code);
    }
}
