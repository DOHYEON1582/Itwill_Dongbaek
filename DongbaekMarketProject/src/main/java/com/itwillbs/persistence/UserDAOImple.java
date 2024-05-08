package com.itwillbs.persistence;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.AuthVO;
import com.itwillbs.domain.MarkVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.domain.StoreVO;
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
	public List<ProductVO> wishListAll(String user_id) throws Exception {
		logger.debug(" wishList(String user_id) 호출 ");
		logger.debug(" user_id " + user_id);
		return sql.selectList(NAMESPACE + ".getWishList", user_id);
	}
	@Override
	public List<ProductVO> wishList(Map<String, Object> map) throws Exception {
		logger.debug(" wishList(Map<String, Object> map) 호출 ");
		String orderBy = (String) map.get("orderBy");
		map.put("orderBy", orderBy);
		logger.debug(" map: " + map);
		return sql.selectList(NAMESPACE + ".getWish", map);
	}

	@Override
	public int deleteWish(int product_code) throws Exception {
		logger.debug(" deleteWish(String product_code) 호출 ");
		return sql.delete(NAMESPACE + ".deleteWish", product_code);
	}

	@Override
	public List<ReviewVO> getReview(int product_code) throws Exception {
		logger.debug(" getReview(int product_code) 호출");
		return sql.selectList(NAMESPACE + ".getReview", product_code);
	}

	@Override
	public List<ProductVO> productList(int product_code, String orderBy) throws Exception {
	    logger.debug(" productList(ProductVO pvo) 호출 ");
	    Map<String, Object> params = new HashMap<>();
	    params.put("product_code", product_code);
	    params.put("orderBy", orderBy); // orderBy를 매퍼에 전달
	    return sql.selectList(NAMESPACE + ".getProduct", params);
	}

	// 가게 목록 전체 조회
	@Override
	public List<StoreVO> getStore(StoreVO svo) throws Exception {
		logger.debug(" getStore(StoreVO svo) 호출 ");
		return sql.selectList(NAMESPACE + ".getStore", svo);
	}

	@Override
	public int deleteWishAll(WishVO wvo) throws Exception {
		logger.debug(" deleteWishAll(WishVO wvo) 호출 ");
		return sql.delete(NAMESPACE + ".deleteWishAll", wvo);
	}

	// 즐겨찾기 (가게) 조회
	@Override
	public List<MarkVO> getMark(String user_id) throws Exception {
		logger.debug(" getMark(MarkVO mvo) 호출 ");
		return sql.selectList(NAMESPACE + ".getMark", user_id);
	}

	@Override
	public int deleteMark(int store_code) throws Exception {
		logger.debug(" deleteMark(int store_code) 호출 ");
		return sql.delete(NAMESPACE + ".deleteMark", store_code);
	}

	@Override
	public int deleteMarkAll(String user_id) throws Exception {
		logger.debug(" deleteMarkAll(String user_id) 호출 ");
		return sql.delete(NAMESPACE + ".deleteMarkAll",user_id);
	}

	public List<ProductVO> selectProductOrderBy(Map<String, Object> map) {
	    logger.debug("selectProductOrderBy(Map<String, Object> map) 호출");

	    // DAO 메서드 파라미터로 전달된 매개 변수 가져오기
	    String orderBy = (String) map.get("orderBy");

	    // SQL 쿼리 실행을 위해 적절한 매개 변수 설정
	    map.put("orderBy", orderBy);

	    // SQL 쿼리 실행
	    return sql.selectList(NAMESPACE + ".selectProduct", map);
	}
	
	

	
	
	
	
	
	
	
	
	
	
}