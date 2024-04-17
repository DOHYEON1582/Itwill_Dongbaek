package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class AnswerVO {
	private String answer_code;
	private String answer_content;
	private String writer;
	private Timestamp regdate;
}
