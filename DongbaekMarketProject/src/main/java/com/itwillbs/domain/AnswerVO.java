package com.itwillbs.domain;

import java.sql.Timestamp;

public class AnswerVO {
	private int answer_code; //답변 코드
	private int q_code; //문의 코드
	private String answer_content; //답변 내용
	private String writer; //작성자
	private Timestamp regdate; //작성일
	public int getAnswer_code() {
		return answer_code;
	}
	public void setAnswer_code(int answer_code) {
		this.answer_code = answer_code;
	}
	public int getQ_code() {
		return q_code;
	}
	public void setQ_code(int q_code) {
		this.q_code = q_code;
	}
	public String getAnswer_content() {
		return answer_content;
	}
	public void setAnswer_content(String answer_content) {
		this.answer_content = answer_content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
}
