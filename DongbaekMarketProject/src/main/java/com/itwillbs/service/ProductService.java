package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.ProductVO;

public interface ProductService {

	public List<ProductVO> lateProduct(int store_code) throws Exception;
	
	public List<ProductVO> popProduct(int store_code) throws Exception;
	
	public List<ProductVO> highPrice(int store_code) throws Exception;
	
	public List<ProductVO> lowPrice(int store_code) throws Exception;
	
	
}
