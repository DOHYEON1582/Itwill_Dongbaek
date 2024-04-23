package com.itwillbs.domain;

import lombok.Data;

@Data
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
}
