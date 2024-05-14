package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.itwillbs.domain.ProductCri;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.SellerVO;
import com.itwillbs.domain.UserVO;
import com.itwillbs.persistence.ProductDAO;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Inject
	private ProductDAO pdao;
	
	
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Inject
	private PasswordEncoder pwEncoder;
	

	
	
	
	
	
	
	@Override
	public ProductVO getProductById(int product_code) {
		logger.debug(" getProductById(int product_code) ");
		return pdao.getProductById(product_code);
	}


	
	
	@Override
	public int sellerUpdate(SellerVO svo) throws Exception {
		logger.debug(" sellerUpdate(SellerVO svo) 실행 ");
		return pdao.updateSeller(svo);
	}

	@Override
	public int deleteSeller(SellerVO svo) throws Exception {
		logger.debug(" deleteSeller(SellerVO svo) 실행 ");
		return pdao.deleteSeller(svo);
	}


	@Override
    public int getTotalCount(String seller_id) throws Exception {
        logger.debug(" getTotalCount() 호출 ");
        return pdao.getTotalCount(seller_id);
    }


	@Override
	public List<ProductVO> getProductPage(ProductCri cri) throws Exception {
		logger.debug(" getProductPage(ProductCri cri) 호출 ");
		Map<String, Object> map = new HashMap<>();
        map.put("store_code", cri.getStore_code());
        map.put("startPage", cri.getStartPage());
        map.put("pageSize", cri.getPageSize());
		return pdao.getProductPage(map);
	}


	@Override
	public int productRegist(ProductVO pvo) throws Exception {
		logger.debug(" productRegist(ProductVO product) 호출 ");
		return pdao.productRegist(pvo);
		
	}


//	@Override
//	public void setFile(String originalFilename, String savedFilename, int product_code, String filePath) throws Exception {
//		logger.debug(" setFile(String originalFilename, String savedFilename, int product_code, String filePath) 호출 ");
//		pdao.setFile(originalFilename, savedFilename, product_code, filePath);
//	}
	
	
		// 회원가입
		public void SellerInsert(SellerVO svo) throws Exception {
		    logger.debug(" sellerInsert(SellerVO svo) 호출 ");
		    
		    // 비밀번호 암호화
		    String encodedPassword = pwEncoder.encode(svo.getSeller_pw());
		    svo.setSeller_pw(encodedPassword);
		    
		    pdao.SellerInsert(svo);
		    logger.debug(" svo.getid : " + svo.getSeller_id());
		    if(svo.getSeller_id().equals("seller")) {
		    	String salt = svo.getSalt();
		    	pdao.sellerAuth(salt);
		    } else {
		    	
		    }
		    logger.debug(" 회원가입 완료! ");
		}
	
	@Override
	public int checkSellerId(String seller_id) throws Exception {
		logger.debug(" checkId(String user_id) 실행 ");
		return pdao.checkSellerId(seller_id);
	}


	@Override
    public SellerVO loginSeller(SellerVO svo) throws Exception {
        logger.debug(" loginUser(sellerVO svo) 실행 ");
        return pdao.loginSeller(svo); 
    }

	@Override
	public SellerVO sellerInfo(String seller_id) throws Exception {
		logger.debug(" sellerInfo(String seller_id) 실행 ");
		return pdao.sellerInfo(seller_id);
	}
	
	@Override
	public int getProductCode() throws Exception {
		logger.debug(" getProductCode() 호출 " );
		return pdao.getProductCode();
	}

	@Override
    public String getImagePathByProductCode(int product_code) throws Exception {
        logger.debug(" getImagePathByProductCode(int product_code) 호출");
        return pdao.getImagePathByProductCode(product_code);
    }
	
	@Override
    public int updateProduct(ProductVO product) throws Exception {
		logger.debug(" updateProduct(ProductVO product) 호출");
        return pdao.updateProduct(product);
    }


	@Override
	public void deleteProduct(int product_code) throws Exception {
		logger.debug(" deleteProduct(int product_code) 호출");
		pdao.deleteProduct(product_code);
		
	}
	
}
