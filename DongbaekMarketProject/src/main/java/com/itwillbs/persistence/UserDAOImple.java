package com.itwillbs.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.UserVO;

@Repository
public class UserDAOImple implements UserDAO {

	@Inject
	private SqlSession sql; 
	
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImple.class);
	
	private static final String NAMESPACE = "com.itwillbs.mapper.userMapper";
	
	@Override
	public void insertUser(UserVO uvo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}