package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class SellerVO {
	private String seller_id;
	private String store_code;
	private String salt;
	private String seller_phone;
	private String seller_name;
	private String store_addr1;
	private String store_addr2;
	private String bank;
	private String account;
	private String store_number;
	private String mos_number;
	private Timestamp regdate;
}
