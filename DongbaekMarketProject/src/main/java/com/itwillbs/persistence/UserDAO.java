package com.itwillbs.persistence;

import com.itwillbs.domain.UserVO;

public interface UserDAO {
	
	public void insertUser(UserVO uvo) throws Exception;
	
	
}
