package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReviewVO {
	private int review_code; //리뷰 코드
	private int product_code; //상품 코드
	private Timestamp regdate; //작성일
	private String title; //리뷰 제목
	private String content; //리뷰 내용
	private int star; //별점
	private int user_id; //회원아이디
	private String img1; //이미지1
	private String img2; //이미지2
	private String img3; //이미지3
	private int order_code; //주문 코드
	private int re_lev; //답글(레벨) 
	private int re_ref; //답글(그룹번호) 
	private int re_seq; //답글(순서) 
}
