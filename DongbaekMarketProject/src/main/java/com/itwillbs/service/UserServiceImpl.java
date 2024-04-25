package com.itwillbs.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.AuthVO;
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
    public AuthVO loginUser(UserVO uvo) throws Exception {
        String userId = uvo.getUser_id();
        String inputPassword = uvo.getUser_pw();
        
        UserVO user = udao.getUser(uvo);
        if (user != null) {
            String encodedPassword = user.getUser_pw(); // DB에 저장된 암호화된 비밀번호
            if (pwEncoder.matches(inputPassword, encodedPassword)) {
                // 비밀번호가 일치하는 경우
                return udao.getAuth(userId);
            }
        }
        return null; // 인증 실패
    }

	@Override
	public UserVO userInfo(String user_id) throws Exception {
		logger.debug(" userInfo(String user_id) 실행 ");
		
		return udao.userInfo(user_id);
	}

	@Override
	public int userUpdate(UserVO uvo) throws Exception {
		logger.debug(" userUpdate(UserVO uvo) 실행 ");
		logger.debug("service uvo " + uvo);
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

	

	
}
