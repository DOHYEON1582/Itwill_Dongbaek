package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.ProductVO;

public interface ProductDAO {
	
	public List<ProductVO> productList(String seller_id, int startRow, int perPageNum) throws Exception; //상품 목록 불러오기
	
	
	
	
}
