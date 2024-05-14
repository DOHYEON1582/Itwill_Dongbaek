package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.CartVO;
import com.itwillbs.domain.OrderInfoVO;
import com.itwillbs.domain.Order_infoVO;

@Repository
public class OrderDAOImpl implements OrderDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(OrderDAOImpl.class);

	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.itwillbs.mapper.OrderMapper";
	
	// 장바구니 목록 가져오기
	@Override
	public List<CartVO> selectCartList(CartVO vo) throws Exception {
		logger.debug(" === D : selectCartList(CartVO vo) 실행 === ");
		return sqlSession.selectList(NAMESPACE+".selectProductInfo", vo);
	}
	
	// 장바구니 상품 수 세어오기
	@Override
	public int selectCountCart(CartVO vo) throws Exception {
		logger.debug(" === D : selectCountCart(CartVO vo) 실행 === ");
		return sqlSession.selectOne(NAMESPACE+".selectCountCart",vo);
	}
	
	// 주문 할 상품 정보 가져오기
	@Override
	public CartVO selectProductInfo(int cart_code) throws Exception {
		logger.debug(" === D : selectProductInfo(int cart_code) 실행 === ");
		return sqlSession.selectOne(NAMESPACE+".selectProductInfo",cart_code);
	}

	// 적립금 가져오기
	@Override
	public String selectUserPoint(String user_id) throws Exception {
		logger.debug(" === D : selectUserPoint(String user_id) 실행 === ");
		return sqlSession.selectOne(NAMESPACE+".selectUserPoint",user_id);
	}

	// 주문 번호 최대값 가져오기
	@Override
	public int selectMaxOrderCode() throws Exception {
		logger.debug(" === D : selectMaxOrderCode() 실행 === ");
		return sqlSession.selectOne(NAMESPACE+".selectMaxOrderCode");
	}

	// 주문 정보 입력
	@Override
	public void insertOrderInfo(OrderInfoVO vo) throws Exception {
		logger.debug(" === D : insertOrderInfo() 실행 === ");
		sqlSession.insert(NAMESPACE+".insertOrderInfo",vo);
	}

	// cart 주문현황 변경
	@Override
	public void updateStates(String bundle_code) throws Exception {
		logger.debug(" === D : updateStates(String bundle_code) 실행 === ");
		sqlSession.update(NAMESPACE+".updateStates",bundle_code);
		
	}

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

