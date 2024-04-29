package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.AuthVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.UserVO;
import com.itwillbs.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {
	
	@Inject
	private UserDAO udao;
	@Inject
	private PasswordEncoder pwEncoder;
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	// 회원가입
	public void userInsert(UserVO uvo) throws Exception {
	    logger.debug(" userInsert(UserVO uvo) 호출 ");
	    
	    // 비밀번호 암호화
	    String encodedPassword = pwEncoder.encode(uvo.getUser_pw());
	    uvo.setUser_pw(encodedPassword);
	    
	    udao.insertUser(uvo);
	    udao.authUser(uvo);
	    logger.debug(" 회원가입 완료! ");
	}

	@Override
    public UserVO loginUser(UserVO uvo) throws Exception {
        logger.debug(" loginUser(UserVO uvo) 실행 ");
        return udao.loginUser(uvo); 
    }

	@Override
	public UserVO userInfo(String user_id) throws Exception {
		logger.debug(" userInfo(String user_id) 실행 ");
		return udao.userInfo(user_id);
	}

	@Override
	public int userUpdate(UserVO uvo) throws Exception {
		logger.debug(" userUpdate(UserVO uvo) 실행 ");
		return udao.updateUser(uvo);
	}

	@Override
	public String getPass(String user_id) throws Exception {
		logger.debug(" getPass(String user_id) 실행 ");
		return udao.getPass(user_id);
	}

	@Override
	public int deleteUser(UserVO uvo) throws Exception {
		logger.debug(" deleteUser(UserVO uvo) 실행 ");
		return udao.deleteUser(uvo);
	}

	@Override
	public List<ProductVO> wishList(String user_id) throws Exception {
		logger.debug(" wishList(String user_id) 실행 ");
		logger.debug(" user_id " + user_id);
		return udao.wishList(user_id);
	}

	@Override
	public int deleteWish(int product_code) throws Exception {
		logger.debug(" deleteWish(String product_code) 실행 ");
		return udao.deleteWish(product_code);
	}

	

	
}
