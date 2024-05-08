package com.itwillbs.domain;

import java.sql.Timestamp;

public class UserVO {

	public String user_id; // 유저 아이디
	public String user_pw; // 유저 비밀번호
	public String salt; // 유저 인증
	public String phone; // 유저 폰 번호
	public String user_name; // 유저 이름
	public String addr1; // 유저 주소
	public String addr2; // 유저 상세주소
	public String sns_email; // 유저 sns 이메일
	public String point; // 유저 포인트
	public String identity; // 유저 정보
	public Timestamp regdate; // 회원가입일
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getSns_email() {
		return sns_email;
	}
	public void setSns_email(String sns_email) {
		this.sns_email = sns_email;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	
	
	
	
	
}
