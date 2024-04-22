package com.itwillbs.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.AdminCartVO;
import com.itwillbs.domain.AdminOrderVO;
import com.itwillbs.domain.UserVO;
import com.itwillbs.persistence.AdminDAO;

@Service
public class AdminServiceImpl implements AdminService {

	@Inject
	private AdminDAO adao;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Override
	public UserVO getUserInfo(UserVO vo) throws Exception {
		logger.debug(" getUserInfo(UserVO vo) 실행 ");
		
		return adao.getUserInfo(vo);
	}

	@Override
	public List<AdminOrderVO> getUserOrder(UserVO vo) throws Exception {
		logger.debug(" getUserOrder(UserVO vo) 실행 ");
		return adao.getUserOrder(vo);
	}

	@Override
	public List<AdminCartVO> getUserCart(int order_code) throws Exception {
		logger.debug(" getUserCart(int order_code) 실행 ");
		return adao.getUserCart(order_code);
	}


	
	
}//endImpl
