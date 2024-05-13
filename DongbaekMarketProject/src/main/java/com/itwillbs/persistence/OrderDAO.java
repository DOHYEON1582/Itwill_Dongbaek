package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.CartVO;
import com.itwillbs.domain.OrderInfoVO;
import com.itwillbs.domain.Order_infoVO;

public interface OrderDAO {
	
	// 장바구니 목록 가져오기
	public List<CartVO> selectCartList(CartVO vo) throws Exception;
	
	// 장바구니 상품 수 세어오기
	public int selectCountCart(CartVO vo) throws Exception; 
		
	// 주문 할 상품 정보 가져오기
	public CartVO selectProductInfo(int cart_code) throws Exception;
	
	// 적립금 가져오기
	public String selectUserPoint(String user_id) throws Exception;
	
	// 주문 번호 최대값 가져오기
	public int selectMaxOrderCode() throws Exception;
	
	// 주문 정보 입력
	public void insertOrderInfo(OrderInfoVO vo) throws Exception;
	
	// cart 주문현황 변경 
	public void updateStates(String bundle_code) throws Exception;
	
	// 업체 주문 정보(list)가져오기
	public List<Order_infoVO> getOrderList(String con, String search,String startDate,String endDate)throws Exception;
	
	// 주문 확정 처리
    public int confirmOrder(String order_code) throws Exception;
    
    // 주문 취소 처리
    public int cancelOrder(String order_code) throws Exception;
    
    // 주문 환불 처리
    public int refundOrder(String order_code) throws Exception;
}
