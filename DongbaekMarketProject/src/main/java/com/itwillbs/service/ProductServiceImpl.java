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
    public int getTotalCount(int store_code) throws Exception {
        logger.debug(" getTotalCount() 호출 ");
        return pdao.getTotalCount(store_code);
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
	@Override
    public void SellerInsert(SellerVO svo) throws Exception {
        logger.debug(" sellerInsert(SellerVO svo) 호출 ");

        // 임의의 salt 생성
        String salt = generateSalt();

        // 비밀번호에 salt를 결합하여 해싱
        String hashedPassword = hashPassword(svo.getSeller_pw(), salt);

        // 암호화된 비밀번호와 salt를 설정
        svo.setSeller_pw(hashedPassword);
        svo.setSalt(salt);

        // 데이터베이스에 저장
        pdao.SellerInsert(svo);
        
        logger.debug(" 회원가입 완료! ");
    }
	// 임의의 salt 생성 메서드
    private String generateSalt() {
        // 임의의 salt를 생성하는 코드를 작성합니다.
        // 이 예제에서는 단순히 무작위 문자열을 생성하는 것으로 대체합니다.
        return "random_salt"; // 실제로는 더 강력한 방법으로 임의의 salt를 생성해야 합니다.
    }

    // 비밀번호 해싱 메서드
    private String hashPassword(String password, String salt) {
        String combinedPassword = password + salt;
        // 여기에 해싱 알고리즘을 사용하여 비밀번호를 해싱하는 코드를 추가합니다.
        // 이 예제에서는 해싱 알고리즘을 사용하지 않았기 때문에 구체적인 해싱 알고리즘은 여러분의 요구에 따라 선택하셔야 합니다.
        // 아래는 간단한 예시입니다.
        return hashFunction(combinedPassword);
    }

    // 여기에 사용할 해싱 함수를 구현하세요.
    private String hashFunction(String input) {
        // 입력값을 해싱하는 코드를 작성합니다.
        // 실제로는 안전한 해싱 알고리즘을 사용해야 합니다. (예: BCrypt, PBKDF2 등)
        return input; // 이 부분을 실제 해싱 알고리즘의 결과로 대체해야 합니다.
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
