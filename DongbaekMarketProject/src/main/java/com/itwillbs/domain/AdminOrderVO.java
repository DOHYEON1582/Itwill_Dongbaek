package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class AdminOrderVO {
	
	private int order_code;
	private String user_id;
	private String bundle_code;
	private int store_code;
	private String rcv_zip;
	private String rcv_name;
	private String rcv_phone;
	private String rcv_addr1;
	private String rcv_addr2;
	private String rcv_meg;
	private String pay_method;
	private int delivery_fee;
	private int total_price;
	private String cal_check;
	private String reduce_point;
	private String add_point;

	private Timestamp reservation;
	private Timestamp order_date;

}
