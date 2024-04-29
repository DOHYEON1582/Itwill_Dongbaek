package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.UserVO;

public interface UserService {
	// 회원가입
	public void userInsert(UserVO uvo) throws Exception;
	
	// 로그인
	public UserVO loginUser(UserVO uvo) throws Exception;
	
	// 회원정보 조회(마이페이지)
	public UserVO userInfo(String user_id) throws Exception;
	
	// 회원정보 수정
	public int userUpdate(UserVO uvo) throws Exception;
	
	// 비밀번호 조회
	public String getPass(String user_id) throws Exception;
	
	// 회원정보 삭제
	public int deleteUser(UserVO uvo) throws Exception;
	
	
	// 찜 목록 조회
	public List<ProductVO> wishList(String user_id) throws Exception;
	// 찜 상품 삭제(개별)
	public int deleteWish(int product_code) throws Exception;
	
	
	
	
	
	
	
	
}
