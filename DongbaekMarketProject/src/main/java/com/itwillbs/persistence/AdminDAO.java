package com.itwillbs.persistence;

import com.itwillbs.domain.UserVO;

public interface AdminDAO {
	
	public UserVO getUserInfo(UserVO vo)throws Exception;

}//endDAO
