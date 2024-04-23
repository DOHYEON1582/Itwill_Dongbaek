package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class AnswerVO {
	private int answer_code; //답변 코드
	private int q_code; //문의 코드
	private String answer_content; //답변 내용
	private String writer; //작성자
	private Timestamp regdate; //작성일
}
