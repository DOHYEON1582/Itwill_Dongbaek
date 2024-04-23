package com.itwillbs.service;

import com.itwillbs.domain.AuthVO;
import com.itwillbs.domain.UserVO;

public interface UserService {
	// 회원가입
	public void userInsert(UserVO uvo) throws Exception;
	
	// 로그인
	public AuthVO loginUser(UserVO uvo) throws Exception;
	
	// 회원정보 조회(마이페이지)
	public UserVO userInfo(String user_id) throws Exception;
	
	// 회원정보 수정
	public int userUpdate(UserVO uvo) throws Exception;
	
	// 비밀번호 조회
	public String getPass(String user_id) throws Exception;
	
	// 회원정보 삭제
	public int deleteUser(UserVO uvo) throws Exception;
	
	
	
}
