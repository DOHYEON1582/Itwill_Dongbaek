package com.itwillbs.service;

import com.itwillbs.domain.AuthVO;
import com.itwillbs.domain.UserVO;

public interface UserService {
	// 회원가입
	public void userInsert(UserVO uvo) throws Exception;
	
	// 로그인
	public AuthVO loginUser(UserVO uvo) throws Exception;
	
	
	
}
