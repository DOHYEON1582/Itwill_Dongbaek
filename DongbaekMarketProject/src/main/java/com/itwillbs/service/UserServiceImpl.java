package com.itwillbs.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.AuthVO;
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

	@Override
	public AuthVO loginUser(UserVO uvo) throws Exception {
		logger.debug(" loginUser(UserVO uvo) 호출 ");
		
		uvo.setSalt(udao.getSalt(uvo));
		logger.debug(" salt 정보 : " + uvo.getSalt());
		
		String pass = udao.hashPass(uvo);
		logger.debug(" 입력한 비밀번호 해싱값 : " + pass);
		
		uvo = udao.getUser(uvo);
		
		logger.debug(" @@@@@@@ : " + uvo);
		
		if(pass.equals(uvo.getUser_pw())) {
			logger.debug(" 비밀번호 일치 ");
			return udao.getAuth(uvo.getUser_id());
		} else logger.debug(" 비밀번호 불일치 ");
		
		return null;
	}

	

	
}
