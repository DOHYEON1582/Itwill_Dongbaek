package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class QuestionVO {
	private int q_code; //문의코드
	private String user_id; //회원 아이디
	private String user_name; //회원 이름
	private Timestamp regdate; //작성일
	private String title; //문의 제목
	private String content; //문의 내용
	private int q_type; //문의 종류
	private int product_code; //상품 코드
}
