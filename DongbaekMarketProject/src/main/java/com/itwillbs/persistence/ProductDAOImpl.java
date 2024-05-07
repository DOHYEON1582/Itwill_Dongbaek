package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.ProductVO;

@Repository
public class ProductDAOImpl implements ProductDAO{

	@Inject
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);
	private static final String NAMESPACE = "com.itwillbs.mapper.ProductMapper";
	
	@Override
	public List<ProductVO> lateProduct(int store_code) throws Exception {
		return sqlSession.selectList(NAMESPACE+".late", store_code);
	}
	@Override
	public List<ProductVO> popProduct(int store_code) throws Exception {
		return sqlSession.selectList(NAMESPACE+".pop", store_code);
	}
	@Override
	public List<ProductVO> highPrice(int store_code) throws Exception {
		return sqlSession.selectList(NAMESPACE+".highPrice", store_code);
	}
	@Override
	public List<ProductVO> lowPrice(int store_code) throws Exception {
		return sqlSession.selectList(NAMESPACE+".lowPrice", store_code);
	}
	
	

	
	
	
}
