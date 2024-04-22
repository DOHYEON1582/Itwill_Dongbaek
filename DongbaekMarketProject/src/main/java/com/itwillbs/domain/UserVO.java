package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;


@Data
public class UserVO {
	private String user_id;
	private String user_pw;
	private String salt;
	private String phone;
	private String user_name;
	private String addr1;
	private String addr2;
	private String sns_email;
	private String point;
	private String identity;
	private Timestamp regdate;
	private String order_code;
	
	private String startDate;
	private String endDate;

}
