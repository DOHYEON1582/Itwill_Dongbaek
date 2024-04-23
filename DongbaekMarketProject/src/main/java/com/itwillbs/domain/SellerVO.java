package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class SellerVO {
	private String seller_id; //판매자 아이디
	private int store_code; //가게 코드
	private String seller_pw; //판매자 비밀번호
	private String salt; //인증코드
	private String seller_phone; //판매자 폰번호
	private String seller_name; //판매자 이름
	private String store_addr1; //가게 주소1
	private String store_addr2; //가게 주소2
	private String bank; //은행
	private String account; //계좌
	private String store_number; //사업자등록번호
	private String mos_number; //통신판매업등록번호
	private Timestamp regdate; //작성일
}
