package com.itwillbs.domain;

import lombok.Data;

@Data
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
}
