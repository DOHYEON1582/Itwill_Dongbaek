package com.itwillbs.domain;


public class CartVO {
	private int cart_code; //장바구니 번호
	private String user_id; //회원 아이디
	private int product_code; //상품 코드
	private String bundle_code; //묶음 번호
	private int count; //수량
	private String price; //가격
	private String delivery_fee; //배달비
	private String states; //주문 현황
	private String refund_reason; //환불사유
	public int getCart_code() {
		return cart_code;
	}
	public void setCart_code(int cart_code) {
		this.cart_code = cart_code;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getProduct_code() {
		return product_code;
	}
	public void setProduct_code(int product_code) {
		this.product_code = product_code;
	}
	public String getBundle_code() {
		return bundle_code;
	}
	public void setBundle_code(String bundle_code) {
		this.bundle_code = bundle_code;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDelivery_fee() {
		return delivery_fee;
	}
	public void setDelivery_fee(String delivery_fee) {
		this.delivery_fee = delivery_fee;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public String getRefund_reason() {
		return refund_reason;
	}
	public void setRefund_reason(String refund_reason) {
		this.refund_reason = refund_reason;
	}
}
