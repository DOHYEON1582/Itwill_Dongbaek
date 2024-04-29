package com.itwillbs.persistence;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.AuthVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.UserVO;
import com.itwillbs.domain.WishVO;

@Repository
public class UserDAOImple implements UserDAO {

	@Inject
	private SqlSession sql; 
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImple.class);
	
	private static final String NAMESPACE = "com.itwillbs.mapper.UserMapper";
	
	@Override
	public void insertUser(UserVO uvo) throws Exception {
		logger.debug(" insertUser(UserVO uvo) 호출 ");
		
		sql.insert(NAMESPACE + ".insertUser", uvo);
	}

	@Override
	public void authUser(UserVO uvo) throws Exception {
		logger.debug(" authUser(String user_id) 호출 ");
		sql.insert(NAMESPACE +".authUser", uvo);
	}

	@Override
	public UserVO loginUser(UserVO uvo) throws Exception {
		logger.debug(" loginUser(UserVO uvo) 호출 ");
		UserVO user = sql.selectOne(NAMESPACE + ".userInfo", uvo);
		if(user != null) {
			String encodedPassword = user.getUser_pw();
            if (passwordEncoder.matches(uvo.getUser_pw(), encodedPassword)) {
                // 비밀번호가 일치하는 경우
            	logger.debug(" 일치 ");
            	uvo.setUser_pw(encodedPassword);
                return sql.selectOne(NAMESPACE + ".loginUser", uvo);
            }
		}
		return null; // 인증 실패
	}

	@Override
	public UserVO userInfo(String user_id) throws Exception {
		logger.debug(" userInfo(String user_id) 호출 ");
		
		return sql.selectOne(NAMESPACE + ".userInfo", user_id);
	}

	@Override
	public int updateUser(UserVO uvo) throws Exception {
		logger.debug(" updateUser(UserVO uvo) 호출 ");
		logger.debug(" dao uvo " + uvo);
		return sql.update(NAMESPACE + ".updateUser", uvo);
	}

	@Override
	public String getPass(String user_id) throws Exception {
		logger.debug(" getPass(String user_id) 호출 ");
		return sql.selectOne(NAMESPACE + ".getPass", user_id);
	}

	@Override
	public int deleteUser(UserVO uvo) throws Exception {
		logger.debug(" deleteUser(UserVO uvo) 호출 ");
		logger.debug(" uvo " + uvo);
		UserVO user = sql.selectOne(NAMESPACE + ".userInfo", uvo);
		if(user != null) {
			String encodedPassword = user.getUser_pw();
            if (passwordEncoder.matches(uvo.getUser_pw(), encodedPassword)) {
                // 비밀번호가 일치하는 경우
            	logger.debug(" 일치 ");
            	uvo.setUser_pw(encodedPassword);
            	return sql.delete(NAMESPACE+ ".deleteUser", uvo);
            }
		}
		return 0; // 인증 실패
	}

	@Override
	public List<ProductVO> wishList(String user_id) throws Exception {
		logger.debug(" wishList(String user_id) 호출 ");
		logger.debug(" user_id " + user_id);
		return sql.selectList(NAMESPACE + ".getWish", user_id);
	}

	@Override
	public int deleteWish(int product_code) throws Exception {
		logger.debug(" deleteWish(String product_code) 호출 ");
		return sql.delete(NAMESPACE + "deleteWish", product_code);
	}

	

	
	
	
	
	
	
	
}