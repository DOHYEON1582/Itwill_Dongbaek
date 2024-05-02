package com.itwillbs.domain;

import lombok.Data;

@Data
public class OrderInfoVO {

	private int order_code;			// 주문번호
	private String user_id;			// 회원아이디
	private String bundel_code;		// 묶음번호(장바구니 이름)
	private int store_code;			// 가게 번호
	private String ordr_date;		// 주문 날짜
	private String rcv_zip;			// 수취인 우편번호
	private String rcv_name;		// 수취인명
	private String rcv_phone;		// 수취인 전화번호
	private String rcv_addr1;		// 수취인 주소
	private String rcv_addr2;		// 수취인 상세주소	
	private String rcv_msg;			// 배송메세지
	private String pay_method;		// 결제방법
	private String deleivery_fee;	// 배달비
	private String total_price;		// 총 결제금액
	private String point;			// ????
	private String cal_check;		// 정산여부
	private String reservation; 	// 예약날짜, 시간 
	private String reduce_point;	// 사용한 적립금
	private String add_point;		// 적립된 적립금
	
	////////////////////////////////////////////
	private String store_name;		// 가게 이름
	private String img1;			// 상품 이미지
	private String product_name;	// 상품명
	
}
