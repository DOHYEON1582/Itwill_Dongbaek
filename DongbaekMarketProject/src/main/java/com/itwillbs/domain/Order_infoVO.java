package com.itwillbs.domain;


import java.sql.Timestamp;

import lombok.Data;

@Data
public class Order_infoVO {
	private int order_code; //주문코드
	private String user_id; //회원 아이디
	private String bundle_code; //묶음번호
	private int store_code; //가게 코드
	private Timestamp order_date; //주문 날짜
	private String rcv_name; //수취인 이름
	private String rcv_phone; //수취인 전화번호
	private String rcv_zip; //수취인 우편번호
	private String rcv_addr1; //수취인 주소1
	private String rcv_addr2; //수취인 주소2
	private String rcv_msg; //배달 요청 사항
	private String pay_method; //결제 수단
	private String delivery_fee; //배달비
	private String total_price; //총 결제금액
	private String cal_check; //정산 여부
	private Timestamp reservation; //예약날짜
	private String reduce_point; //사용될 포인트
	private String add_point; //추가될 포인트
}
