package com.itwillbs.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.UserVO;
import com.itwillbs.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {
	
	@Inject
	private UserDAO udao;
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public void userInsert(UserVO uvo) throws Exception {
		logger.debug(" userInsert(UserVO uvo) 호출 ");
		
		uvo.setSalt(udao.createSalt());
		
		uvo.setUser_pw(udao.hashPass(uvo.getUser_pw(), uvo.getSalt()));
		
		udao.insertUser(uvo);
		
		logger.debug(" 회원가입 완료! ");
	}

}
