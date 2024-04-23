package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.ProductVO;
import com.itwillbs.service.ProductService;

@RestController
public class ProductRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductRestController.class);
	
	@Inject
	private ProductService pService;
	
	@PostMapping("/product/sortProduct")
	public List<ProductVO> sortProduct(@RequestParam("store_code") int store_code, @RequestParam("sortType") String sortType) {
		
		List<ProductVO> productList = null;
		try {
            // 정렬 방법에 따라 서비스 계층의 메서드 호출
            if ("latest".equals(sortType)) {
                productList = pService.lateProduct(store_code);
            } else if ("popularity".equals(sortType)) {
                productList = pService.popProduct(store_code);
            } else if ("lowPrice".equals(sortType)) {
                productList = pService.lowPrice(store_code);
            } else if ("highPrice".equals(sortType)) {
                productList = pService.highPrice(store_code);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return productList;
		
	}
	
	
}
