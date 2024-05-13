package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.ProductCri;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.SellerVO;
import com.itwillbs.domain.UserVO;

@Repository
public class ProductDAOImpl implements ProductDAO {
	@Inject
	private SqlSession sqlSession; 
	
	
	private static final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);
	private static final String NAMESPACE = "com.itwillbs.mapper.ProductMapper";
	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	
	
	

	@Override
	public void SellerInsert(SellerVO svo) throws Exception {
		logger.debug(" SellerInsert(SellerVO svo) 호출 ");
		sqlSession.insert(NAMESPACE + ".insertSeller", svo);
	}

	@Override
	public int updateSeller(SellerVO svo) throws Exception {
		logger.debug(" updateSeller(SellerVO svo) 호출 ");
		logger.debug(" dao svo " + svo);
		return sqlSession.update(NAMESPACE + ".updateSeller", svo);
	}

	
	
	@Override
	public int deleteSeller(SellerVO svo) throws Exception {
		logger.debug(" deleteSeller(SellerVO svo) 호출 ");
		logger.debug(" svo " + svo);
		UserVO user = sqlSession.selectOne(NAMESPACE + ".SellerInfo", svo);
		if(user != null) {
			String encodedPassword = user.getUser_pw();
            if (passwordEncoder.matches(svo.getSeller_pw(), encodedPassword)) {
                // 비밀번호가 일치하는 경우
            	logger.debug(" 일치 ");
            	svo.setSeller_pw(encodedPassword);
            	return sqlSession.delete(NAMESPACE+ ".deleteSeller", svo);
            }
		}
		return 0; // 인증 실패
	}

	@Override
	public void sellerAuth(String salt) throws Exception {
		logger.debug(" sellerAuth(String salt) 호출 ");
		sqlSession.insert(NAMESPACE + ".SellerAuth", salt);
	}

	@Override
	public ProductVO getProductById(int product_code) {
		return sqlSession.selectOne(NAMESPACE+".getProductById",product_code);
	}

	@Override
    public int getTotalCount(String seller_id) {
        return sqlSession.selectOne(NAMESPACE + ".getTotalCount", seller_id);
    }
	
	@Override
    public List<ProductVO> getProductPage(Map<String, Object> map) {
        return sqlSession.selectList(NAMESPACE + ".getProductPage", map);
    }

	@Override
	public int productRegist(ProductVO pvo) throws Exception {
		logger.debug(" productregist(ProductVO product) 실행 ");
		
		return sqlSession.insert(NAMESPACE+".productRegist",pvo);
		
	}
	@Override
	public int checkSellerId(String seller_id) throws Exception {
		logger.debug(" checkSellerId(String user_id) 호출 ");
		return sqlSession.selectOne(NAMESPACE + ".checkSellerId", seller_id);
	}
	
	@Override
	public SellerVO loginSeller(SellerVO svo) throws Exception {
		logger.debug(" loginSeller(SellerVO svo) 호출 ");
		SellerVO seller = sqlSession.selectOne(NAMESPACE + ".SellerInfo", svo);
		if(seller != null) {
			String encodedPassword = seller.getSeller_pw();
            if (passwordEncoder.matches(svo.getSeller_pw(), encodedPassword)) {
                // 비밀번호가 일치하는 경우
            	logger.debug(" 일치 ");
            	svo.setSeller_pw(encodedPassword);
                return sqlSession.selectOne(NAMESPACE + ".loginSeller", svo);
            }
		}
		return null; // 인증 실패
	}
	
	@Override
	public SellerVO sellerInfo(String seller_id) throws Exception {
		logger.debug(" sellerInfo(String seller_id) 호출 ");
		
		return sqlSession.selectOne(NAMESPACE + ".SellerInfo", seller_id);
	}
//	@Override
//    public void setFile(String originalFilename, String savedFilename, int product_code, String filePath) throws Exception {
//        logger.debug(" setFile() 실행 ");
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("originalFilename", originalFilename);
//        paramMap.put("savedFilename", savedFilename);
//        paramMap.put("product_code", product_code);
//        paramMap.put("filePath", filePath);
//        sqlSession.insert(NAMESPACE + ".setFile", paramMap);
//    }
	
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
    public int updateProduct(ProductVO product) throws Exception {
		logger.debug(" updateProduct(ProductVO product) 실행 ");
        return sqlSession.update(NAMESPACE + ".updateProduct", product);
    }

	@Override
	public void deleteProduct(int product_code) throws Exception {
		logger.debug(" deleteProduct(int product_code) 실행 ");
		sqlSession.delete(NAMESPACE+".deleteProduct", product_code);
		
	}
	
	
}
