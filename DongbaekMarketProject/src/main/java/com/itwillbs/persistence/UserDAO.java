package com.itwillbs.persistence;

import com.itwillbs.domain.UserVO;

public interface UserDAO {
	
	public void insertUser(UserVO uvo) throws Exception;
	
	public String createSalt() throws Exception;
	
	public String hashPass(String pass, String salt) throws Exception;
	
	public String hashPass(UserVO uvo) throws Exception;
	
	public String getSalt(UserVO uvo) throws Exception;
	
	public UserVO loginUser(UserVO uvo) throws Exception;
	
}
