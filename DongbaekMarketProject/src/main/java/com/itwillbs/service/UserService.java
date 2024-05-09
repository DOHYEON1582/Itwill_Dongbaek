package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.CartVO;
import com.itwillbs.domain.MarkVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.domain.StoreVO;
import com.itwillbs.domain.UserVO;
import com.itwillbs.domain.WishVO;

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
	public List<ProductVO> wishListAll(String user_id) throws Exception;
	public List<ProductVO> wishList(String orderBy, String user_id) throws Exception;
	// 찜 상품 삭제(개별)
	public int deleteWish(int product_code) throws Exception;
	// 찜 상품 삭제(전체)
	public int deleteWishAll(WishVO wvo) throws Exception;
	// 즐겨찾기(가게) 조회
	public List<MarkVO> getMark(String user_id) throws Exception;
	// 즐겨찾기 삭제 (개별)
	public int deleteMark(int store_code) throws Exception;
	// 즐겨찾기 삭제 (전체)
	public int deleteMarkAll(String user_id) throws Exception;
	
	
	// 제품 정렬
	public List<ProductVO> getProductOrderBy(String orderBy) throws Exception;
	
	// 가게 목록 전체 조회
	public List<StoreVO> getStore(StoreVO svo) throws Exception;
	
	
	// 리뷰
	public List<ReviewVO> getReview(int product_code) throws Exception;
	// 제품
	//public List<ProductVO> getProduct(int product_code) throws Exception;
	public List<ProductVO> getProduct(int product_code, String orderBy) throws Exception;

	// 0509 cart session 생성을 위한 코드
	// 장바구니 상품 갯수 조회
	public int selectCountCart(String user_id) throws Exception;
		
	// bundle_code 가져오기
	public CartVO selectBundleCode(String user_id) throws Exception;
	
	
	
}
