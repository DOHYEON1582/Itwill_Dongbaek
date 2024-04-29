package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.UserVO;

public interface UserDAO {
	// 회원가입
	public void insertUser(UserVO uvo) throws Exception;
	public void authUser(UserVO uvo) throws Exception;
	
	public UserVO loginUser(UserVO uvo) throws Exception;
	
	// 회원정보 조회
	public UserVO userInfo(String user_id) throws Exception;
	
	// 회원정보 수정
	public int updateUser(UserVO uvo) throws Exception;
	
	public String getPass(String user_id) throws Exception;
	
	// 회원정보 삭제
	public int deleteUser(UserVO uvo) throws Exception;
	
	
	// 찜 목록 조회
	public List<ProductVO> wishList(String user_id) throws Exception;
	// 찜 상품 삭제 (개별)
	public int deleteWish(int product_code) throws Exception;
	
	
}
