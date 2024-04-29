package com.itwillbs.domain;


public class ProductVO {
	private int product_code; //상품 코드
	private int store_code; //가게 코드
	private String price; //상품 가격
	private String product_name; //상품 이름
	private int viewcnt; //조회수
	private String img1; //이미지1
	private String img2; //이미지2
	private String img3; //이미지3
	private String product_explain; //상품 설명
	private String seller_id; //판매자 아이디
	private String country; //원산지
	private int max_account; //최대 구매 수량
	private String category; //카테고리
	private String sub_product; //구독 상품체크
	private String unit; //단위
	public int getProduct_code() {
		return product_code;
	}
	public void setProduct_code(int product_code) {
		this.product_code = product_code;
	}
	public int getStore_code() {
		return store_code;
	}
	public void setStore_code(int store_code) {
		this.store_code = store_code;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	public String getImg1() {
		return img1;
	}
	public void setImg1(String img1) {
		this.img1 = img1;
	}
	public String getImg2() {
		return img2;
	}
	public void setImg2(String img2) {
		this.img2 = img2;
	}
	public String getImg3() {
		return img3;
	}
	public void setImg3(String img3) {
		this.img3 = img3;
	}
	public String getProduct_explain() {
		return product_explain;
	}
	public void setProduct_explain(String product_explain) {
		this.product_explain = product_explain;
	}
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getMax_account() {
		return max_account;
	}
	public void setMax_account(int max_account) {
		this.max_account = max_account;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSub_product() {
		return sub_product;
	}
	public void setSub_product(String sub_product) {
		this.sub_product = sub_product;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
}
