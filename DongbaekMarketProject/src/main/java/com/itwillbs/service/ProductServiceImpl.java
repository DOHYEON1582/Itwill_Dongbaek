package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.ProductVO;
import com.itwillbs.persistence.ProductDAO;

@Repository
public class ProductServiceImpl implements ProductService {
	
	@Inject
	private ProductDAO pdao;
	
	
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);


	@Override
	public List<ProductVO> productList(String seller_id, int startRow, int perPageNum) throws Exception{
		logger.debug(" productList(String seller_id, int startRow, int perPageNum) 호출 ");
		return pdao.productList(seller_id, startRow, perPageNum);
	}


	@Override
	public void productRegist(ProductVO product) throws Exception {
		logger.debug(" productRegist(ProductVO product) 호출 ");
		pdao.productRegist(product);
		
	}

	
	
}
