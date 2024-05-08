package com.itwillbs.domain;

import lombok.Data;

@Data
public class Cart2VO {

	private int cart_code;				// 장바구니코드
	private String user_id;				// 회원아이디
	private int product_code;			// 상품코드
	private String bundle_code;			// 묶음번호
	private int count;					// 상품 수량
	private String price;				// 상품 가격
	private String delivery_fee;		// 배달비
	private String states;				// 주문현황
	private String refund_reason;		// 환불 사유... 
	
	////////////////////////////////
	private String user_name;			// 회원명
	private int store_code;				// 가게코드
	private String store_name;			// 가게명
	private String product_name;		// 상품명
	private String img1;				// 대표이미지... 
	private int max_account;			// 구매수량제한
	
	
}
