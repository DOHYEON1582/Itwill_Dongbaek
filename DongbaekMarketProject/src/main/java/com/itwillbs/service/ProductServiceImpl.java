package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.ProductCri;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.persistence.ProductDAO;

@Repository
public class ProductServiceImpl implements ProductService {
	
	@Inject
	private ProductDAO pdao;
	
	
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	
	

	@Override
	public ProductVO getProductById(int product_code) {
		logger.debug(" getProductById(int product_code) ");
		return pdao.getProductById(product_code);
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
    public void updateProduct(ProductVO product) throws Exception {
		logger.debug(" updateProduct(ProductVO product) 호출");
        pdao.updateProduct(product);
    }


	@Override
	public void deleteProduct(int product_code) throws Exception {
		logger.debug(" deleteProduct(int product_code) 호출");
		pdao.deleteProduct(product_code);
		
	}
	
	
	
}
