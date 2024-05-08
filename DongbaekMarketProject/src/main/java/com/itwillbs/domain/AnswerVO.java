package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class AnswerVO {
	private int answer_code;
	private int q_code;
	private String answer_content;
	private String writer;
	private Timestamp regdate;
}
