package com.itwillbs.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.Order_infoVO;

@Repository
public class DeliveryDAOImpl implements DeliveryDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.itwillbs.mapper.DeliveryMapper";
	
	@Override
    public Order_infoVO getDeliveryInfo(int order_code) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".getDeliveryInfo", order_code);
    }
	
	

    @Override
    public int updateDeliveryInfo(Order_infoVO order_code) throws Exception {
        return sqlSession.update(NAMESPACE + ".updateDeliveryInfo", order_code);
    }
}