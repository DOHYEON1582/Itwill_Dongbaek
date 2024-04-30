package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.ProductVO;

public interface ProductDAO {
	
	public List<ProductVO> productList(String seller_id, int startRow, int perPageNum) throws Exception; //상품 목록 불러오기
	
	public void productRegist(ProductVO product) throws Exception; //상품 등록
	
	public void setFile(String originalFilename, String savedFilename, int product_code, String filePath) throws Exception; // 이미지 파일 저장
	
	public int getProductCode() throws Exception; // 이미지와 같이 들어온 상품 코드
}
