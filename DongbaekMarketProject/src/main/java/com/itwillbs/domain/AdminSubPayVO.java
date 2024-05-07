package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class AdminSubPayVO {

	private int subs_pay_code;
	private String user_id;
	private String card_num;
	private String pay_price;
	private Timestamp pay_date;
	private Timestamp pick_date;
	
}
