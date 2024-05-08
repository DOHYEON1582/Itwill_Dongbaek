package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.ProductVO;
import com.itwillbs.persistence.ProductDAO;

@Service
public class ProductServiceImpl implements ProductService{

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Inject
	private ProductDAO pdao;

	@Override
	public List<ProductVO> lateProduct(int store_code) throws Exception {
		return pdao.lateProduct(store_code);
	}

	@Override
	public List<ProductVO> popProduct(int store_code) throws Exception {
		return pdao.popProduct(store_code);
	}

	@Override
	public List<ProductVO> highPrice(int store_code) throws Exception {
		return pdao.highPrice(store_code);
	}

	@Override
	public List<ProductVO> lowPrice(int store_code) throws Exception {
		return pdao.lowPrice(store_code);
	}
	
	
}
