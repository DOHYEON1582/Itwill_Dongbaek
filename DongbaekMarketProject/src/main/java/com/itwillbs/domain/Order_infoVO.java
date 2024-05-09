package com.itwillbs.domain;

import java.sql.Timestamp;

public class Order_infoVO {
	private int order_code; // 주문코드
	private String user_id; // 회원 아이디
	private String bundle_code; // 묶음번호
	private int store_code; // 가게 코드
	private Timestamp order_date; // 주문 날짜
	private String rcv_name; // 수취인 이름
	private String rcv_phone; // 수취인 전화번호
	private String rcv_zip; // 수취인 우편번호
	private String rcv_addr1; // 수취인 주소1
	private String rcv_addr2; // 수취인 주소2
	private String rcv_msg; // 배달 요청 사항
	private String pay_method; // 결제 수단
	private String delivery_fee; // 배달비
	private String total_price; // 총 결제금액
	private String cal_check; // 정산 여부
	private Timestamp reservation; // 예약날짜
	private String reduce_point; // 사용될 포인트
	private String add_point; // 추가될 포인트
	public int getOrder_code() {
		return order_code;
	}
	public void setOrder_code(int order_code) {
		this.order_code = order_code;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getBundle_code() {
		return bundle_code;
	}
	public void setBundle_code(String bundle_code) {
		this.bundle_code = bundle_code;
	}
	public int getStore_code() {
		return store_code;
	}
	public void setStore_code(int store_code) {
		this.store_code = store_code;
	}
	public Timestamp getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}
	public String getRcv_name() {
		return rcv_name;
	}
	public void setRcv_name(String rcv_name) {
		this.rcv_name = rcv_name;
	}
	public String getRcv_phone() {
		return rcv_phone;
	}
	public void setRcv_phone(String rcv_phone) {
		this.rcv_phone = rcv_phone;
	}
	public String getRcv_zip() {
		return rcv_zip;
	}
	public void setRcv_zip(String rcv_zip) {
		this.rcv_zip = rcv_zip;
	}
	public String getRcv_addr1() {
		return rcv_addr1;
	}
	public void setRcv_addr1(String rcv_addr1) {
		this.rcv_addr1 = rcv_addr1;
	}
	public String getRcv_addr2() {
		return rcv_addr2;
	}
	public void setRcv_addr2(String rcv_addr2) {
		this.rcv_addr2 = rcv_addr2;
	}
	public String getRcv_msg() {
		return rcv_msg;
	}
	public void setRcv_msg(String rcv_msg) {
		this.rcv_msg = rcv_msg;
	}
	public String getPay_method() {
		return pay_method;
	}
	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}
	public String getDelivery_fee() {
		return delivery_fee;
	}
	public void setDelivery_fee(String delivery_fee) {
		this.delivery_fee = delivery_fee;
	}
	public String getTotal_price() {
		return total_price;
	}
	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}
	public String getCal_check() {
		return cal_check;
	}
	public void setCal_check(String cal_check) {
		this.cal_check = cal_check;
	}
	public Timestamp getReservation() {
		return reservation;
	}
	public void setReservation(Timestamp reservation) {
		this.reservation = reservation;
	}
	public String getReduce_point() {
		return reduce_point;
	}
	public void setReduce_point(String reduce_point) {
		this.reduce_point = reduce_point;
	}
	public String getAdd_point() {
		return add_point;
	}
	public void setAdd_point(String add_point) {
		this.add_point = add_point;
	}
}
