package com.itwillbs.persistence;

import java.util.List;
import java.util.Map;

import com.itwillbs.domain.MarkVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.domain.StoreVO;
import com.itwillbs.domain.UserVO;
import com.itwillbs.domain.WishVO;

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
	public List<ProductVO> wishListAll(String user_id) throws Exception;
	public List<ProductVO> wishList(Map<String, Object> map) throws Exception;
	// 찜 상품 삭제 (개별)
	public int deleteWish(int product_code) throws Exception;
	// 찜 상품 삭제 (전체)
	public int deleteWishAll(WishVO wvo) throws Exception;
	// 즐겨찾기(가게) 조회
	public List<MarkVO> getMark(String user_id) throws Exception;
	// 즐겨찾기 삭제 (개별)
	public int deleteMark(int store_code) throws Exception;
	// 즐겨찾기 삭제 (전체)
	public int deleteMarkAll(String user_id) throws Exception;
	
	
	
	// 제품 정렬
	public List<ProductVO> selectProductOrderBy(Map<String, Object> map) throws Exception;
	// 제품 조회
	public List<ProductVO> selectProductAll(ProductVO pvo) throws Exception;
	
	
	// 가게 목록 전체 조회
	public List<StoreVO> getStore(StoreVO svo) throws Exception;
	
	
	
	// 리뷰
	public List<ReviewVO> getReview(int product_code) throws Exception;
	// 상품
	//public List<ProductVO> productList(int product_code) throws Exception;
	public List<ProductVO> productList(int product_code, String orderBy) throws Exception;

}
