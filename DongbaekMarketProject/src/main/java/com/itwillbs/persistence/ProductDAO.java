package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.ProductVO;

public interface ProductDAO {

	public List<ProductVO> lateProduct(int store_code) throws Exception;
	
	public List<ProductVO> popProduct(int store_code) throws Exception;
	
	public List<ProductVO> highPrice(int store_code) throws Exception;
	
	public List<ProductVO> lowPrice(int store_code) throws Exception;
	
	
	
	
	
	
	
	
}
