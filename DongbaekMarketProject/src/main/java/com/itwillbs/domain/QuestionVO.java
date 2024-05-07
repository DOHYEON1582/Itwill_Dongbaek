package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class QuestionVO {

	private int q_code;
	private String user_id;
	private String user_name;
	private Timestamp regdate;
	private String title;
	private String content;
	private int q_type;
	private int product_code;
	
}
