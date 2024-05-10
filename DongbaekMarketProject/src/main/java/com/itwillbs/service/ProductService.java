package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.ProductCri;
import com.itwillbs.domain.ProductVO;

public interface ProductService {

    public ProductVO getProductById(int product_code); // 상품 ID를 기준으로 상품 정보를 조회하는 메서드

    public int getTotalCount(int store_code) throws Exception; // 전체 상품 수 조회

    public List<ProductVO> getProductPage(ProductCri cri) throws Exception; // 페이징된 상품 목록 조회

    public void productRegist(ProductVO product) throws Exception; // 상품 등록

    public void setFile(String originalFilename, String savedFilename, int product_code, String filePath) throws Exception; // 이미지 파일 저장

    public int getProductCode() throws Exception; // 이미지와 같이 들어온 상품 코드

    public String getImagePathByProductCode(int product_code) throws Exception; // 이미지 파일 경로를 저장하고 조회하는 메서드
    
    public void updateProduct(ProductVO product) throws Exception; // 상품 수정
    
    public void deleteProduct(int product_code) throws Exception; // 상품 삭제
}
