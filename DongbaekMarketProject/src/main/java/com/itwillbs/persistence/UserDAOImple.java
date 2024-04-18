package com.itwillbs.persistence;

import java.security.MessageDigest;
import java.security.SecureRandom;

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
		logger.debug(" insertUser(UserVO uvo) 호출 ");
		
		sql.insert(NAMESPACE + ".insertUser", uvo);
	}

	@Override
	public String createSalt() throws Exception {
		logger.debug(" createSalt() 호출 ");
		// 랜덤 값 생성
		SecureRandom ran = new SecureRandom();
		
		byte[] temp = new byte[10];
		ran.nextBytes(temp);
		
		StringBuilder sb = new StringBuilder();
		for(byte a : temp) {
			sb.append(String.format("%02x", a));
		}
		return sb.toString();
	}

	@Override
	public String hashPass(String pass, String salt) throws Exception {
		logger.debug(" hashPass(String pass, String salt) 실행");
		byte[] bPass = pass.getBytes();
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		for(int i = 0; i<1000; i++) {
			
			String Spass = salt+pass;
			md.update(Spass.getBytes());
			
			bPass = md.digest();
		}
		
		StringBuilder sb = new StringBuilder();
		for(byte a : bPass) {
			sb.append(String.format("%02x", a));
		}
		
		return sb.toString();
	}

	@Override
	public String hashPass(UserVO uvo) throws Exception {
		logger.debug(" hashPass(UserVO uvo) 호출");
		byte[] bPass = uvo.getUser_pw().getBytes();
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		for(int i = 0; i<1000; i++) {
			
			String Spass = uvo.getSalt() + uvo.getUser_pw();
			md.update(Spass.getBytes());
			
			bPass = md.digest();
		}
		
		StringBuilder sb = new StringBuilder();
		for(byte a : bPass) {
			sb.append(String.format("%02x", a));
		}
		
		return sb.toString();
	}

	@Override
	public String getSalt(UserVO uvo) throws Exception {
		logger.debug(" getSalt(UserVO uvo) 호출 ");
		
		return  sql.selectOne(NAMESPACE+".getSalt", uvo);
	}

	@Override
	public UserVO loginUser(UserVO uvo) throws Exception {
		logger.debug(" loginUser(UserVO uvo) 호출 ");
		
		return sql.selectOne(NAMESPACE + ".loginUser", uvo);
	}

	
	
}