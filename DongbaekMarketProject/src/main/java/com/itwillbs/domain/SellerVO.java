package com.itwillbs.domain;

import java.sql.Timestamp;


public class SellerVO {
	private String seller_id; //판매자 아이디
	private int store_code; //가게 코드
	private String seller_pw; //판매자 비밀번호
	private String salt; //인증코드
	private String seller_phone; //판매자 폰번호
	private String seller_name; //판매자 이름
	private String store_addr1; //가게 주소1
	private String store_addr2; //가게 주소2
	private String bank; //은행
	private String account; //계좌
	private String store_number; //사업자등록번호
	private String mos_number; //통신판매업등록번호
	private Timestamp regdate; //작성일
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	public int getStore_code() {
		return store_code;
	}
	public void setStore_code(int store_code) {
		this.store_code = store_code;
	}
	public String getSeller_pw() {
		return seller_pw;
	}
	public void setSeller_pw(String seller_pw) {
		this.seller_pw = seller_pw;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getSeller_phone() {
		return seller_phone;
	}
	public void setSeller_phone(String seller_phone) {
		this.seller_phone = seller_phone;
	}
	public String getSeller_name() {
		return seller_name;
	}
	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}
	public String getStore_addr1() {
		return store_addr1;
	}
	public void setStore_addr1(String store_addr1) {
		this.store_addr1 = store_addr1;
	}
	public String getStore_addr2() {
		return store_addr2;
	}
	public void setStore_addr2(String store_addr2) {
		this.store_addr2 = store_addr2;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getStore_number() {
		return store_number;
	}
	public void setStore_number(String store_number) {
		this.store_number = store_number;
	}
	public String getMos_number() {
		return mos_number;
	}
	public void setMos_number(String mos_number) {
		this.mos_number = mos_number;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
}
