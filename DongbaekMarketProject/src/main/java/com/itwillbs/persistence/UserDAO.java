package com.itwillbs.persistence;

import com.itwillbs.domain.AuthVO;
import com.itwillbs.domain.UserVO;

public interface UserDAO {
	
	public void insertUser(UserVO uvo) throws Exception;
	
	public String createSalt() throws Exception;
	
	public String hashPass(String pass, String salt) throws Exception;
	
	public String hashPass(UserVO uvo) throws Exception;
	
	public String getSalt(UserVO uvo) throws Exception;
	
	public UserVO getUser(UserVO uvo) throws Exception;
	
	public AuthVO getAuth(String user_id) throws Exception;
	
}
