package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.ProductCri;
import com.itwillbs.domain.ProductVO;

@Repository
public class ProductDAOImpl implements ProductDAO {
	@Inject
	private SqlSession sqlSession; 
	
	
	private static final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);
	private static final String NAMESPACE = "com.itwillbs.mapper.ProductMapper";
	
	
	
	
	@Override
	public ProductVO getProductById(int product_code) {
		return sqlSession.selectOne(NAMESPACE+".getProductById",product_code);
	}

	@Override
    public int getTotalCount(String user_id) {
        return sqlSession.selectOne(NAMESPACE + ".getTotalCount", user_id);
    }
	
	@Override
    public List<ProductVO> getProductPage(Map<String, Object> map) {
        return sqlSession.selectList(NAMESPACE + ".getProductPage", map);
    }

	@Override
	public void productRegist(ProductVO product) throws Exception {
		logger.debug(" productregist(ProductVO product) 실행 ");
		sqlSession.insert(NAMESPACE+".productRegist",product);
		
	}
	
	@Override
    public void setFile(String originalFilename, String savedFilename, int product_code, String filePath) throws Exception {
        logger.debug(" setFile() 실행 ");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("originalFilename", originalFilename);
        paramMap.put("savedFilename", savedFilename);
        paramMap.put("product_code", product_code);
        paramMap.put("filePath", filePath);
        sqlSession.insert(NAMESPACE + ".setFile", paramMap);
    }
	
	@Override
    public int getProductCode() throws Exception {
		logger.debug(" getProductCode() 실행 ");
        return sqlSession.selectOne(NAMESPACE + ".getProductCode");
    }
	
	@Override
    public String getImagePathByProductCode(int product_code) throws Exception {
		logger.debug(" getImagePathByProductCode(int product_code) 실행 ");
        return sqlSession.selectOne(NAMESPACE + ".getImagePathByProductCode", product_code);
    }
	
	@Override
    public void updateProduct(ProductVO product) throws Exception {
		logger.debug(" updateProduct(ProductVO product) 실행 ");
        sqlSession.update(NAMESPACE + ".updateProduct", product);
    }

	@Override
	public void deleteProduct(int product_code) throws Exception {
		logger.debug(" deleteProduct(int product_code) 실행 ");
		sqlSession.delete(NAMESPACE+".deleteProduct", product_code);
		
	}
	
	
}
