package com.itwillbs.domain;

import lombok.Data;

@Data
public class ReviewVO {

	private int review_code;
	private int product_code;
	private String regdate;
	private String title;
	private String content;
	private int start;
	private String img1;
	private String img2;
	private String img3;
	private int order_code;
	private String user_id;
	private int req_lev;
	private int re_ref;
	private int re_seq;
}
