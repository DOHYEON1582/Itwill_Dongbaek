package com.itwillbs.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.service.OrderService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@RestController
//@RequestMapping("/pay/*")
public class OrderRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderRestController.class);
	
	@Inject
	private OrderService oService;
	
	private IamportClient api;
	
	public OrderRestController() {
		// 토큰 발급
		this.api = new IamportClient("4412056010508705","t7oYBzO2MlZ3YgFYXDpwwD40JbHJnOmnMWuClc6nvcmZfKipioc8gF9t0igZSoj0qrtvLYgPww8AgOr9");
	}
	
	@PostMapping(value="/verifyiamport/{imp_uid}")
	public IamportResponse<Payment> paymentByImpUid(Model model, Locale locale, HttpSession session
			, @PathVariable(value= "imp_uid") String imp_uid) throws IamportResponseException, IOException{	
		
			return api.paymentByImpUid(imp_uid);
	}
	
	//결제 완료 시 DB에 결제 완료 처리
	@RequestMapping(value="/paySuccess", method=RequestMethod.POST)
	public void paySuccess() {
		

		
	}	

}
