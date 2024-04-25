package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.ProductVO;

@Repository
public class ProductDAOImpl implements ProductDAO {
	@Inject
	private SqlSession sqlSession; 
	
	
	private static final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);
	private static final String NAMESPACE = "com.itwillbs.mapper.ProductMapper";
	
	@Override
	public List<ProductVO> productList(String seller_id, int startRow, int perPageNum) throws Exception {
		logger.debug(" productList(String seller_id, int startRow, int perPageNum) 실행 ");
	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("seller_id", seller_id);
	    paramMap.put("startRow", startRow);
	    paramMap.put("perPageNum", perPageNum);
	    return sqlSession.selectList(NAMESPACE + ".productList", paramMap);
	}

	@Override
	public void productRegist(ProductVO product) throws Exception {
		logger.debug(" productregist(ProductVO product) 실행 ");
		sqlSession.insert(NAMESPACE+".productRegist",product);
		
	}
	
	
	
}
