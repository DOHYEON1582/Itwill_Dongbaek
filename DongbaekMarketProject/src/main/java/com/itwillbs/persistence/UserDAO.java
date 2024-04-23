package com.itwillbs.persistence;

import com.itwillbs.domain.AuthVO;
import com.itwillbs.domain.UserVO;

public interface UserDAO {
	// 회원가입
	public void insertUser(UserVO uvo) throws Exception;
	public void authUser(UserVO uvo) throws Exception;
	
	public String createSalt() throws Exception;
	
	public String hashPass(String pass, String salt) throws Exception;
	
	public String hashPass(UserVO uvo) throws Exception;
	
	public String getSalt(UserVO uvo) throws Exception;
	
	public UserVO getUser(UserVO uvo) throws Exception;
	
	public AuthVO getAuth(String user_id) throws Exception;

	// 회원정보 조회
	public UserVO userInfo(String user_id) throws Exception;
	
	// 회원정보 수정
	public int updateUser(UserVO uvo) throws Exception;
	
	public String getPass(String user_id) throws Exception;
	
	// 회원정보 삭제
	public int deleteUser(UserVO uvo) throws Exception;
	
}
