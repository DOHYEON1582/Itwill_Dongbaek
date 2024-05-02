package com.itwillbs.domain;

import lombok.Data;

@Data
public class CartVO {

	private int cart_code;				// 장바구니코드
	private String user_id;				// 회원아이디
	private int product_code;			// 상품코드
	private String bundle_code;			// 묶음번호
	private int count;					// 상품 수량
	private String price;				// 상품 가격
	private String delivery_fee;		// 배달비
	private String states;				// 주문현황(장바구니, 결제완료, 배달준비, 배달중, 배달완료, 주문취소(가게주인 취소 or 사용자가 배달 준비 전 취소), 반품접수, 환불대기중, 환불완료)
	private String refund_reason;		// 환불 사유... 
	
	////////////////////////////////
	private String user_name;			// 회원명
	private int store_code;				// 가게코드
	private String store_name;			// 가게명
	private String product_name;		// 상품명
	private String img1;				// 대표이미지... 
	private int max_account;			// 구매수량제한
	
	
}
