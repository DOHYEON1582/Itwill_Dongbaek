package com.itwillbs.persistence;

import java.util.List;
import java.util.Map;

import com.itwillbs.domain.CartVO;
import com.itwillbs.domain.MarkVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.domain.StoreVO;
import com.itwillbs.domain.SubscribeProductVO;
import com.itwillbs.domain.UserVO;
import com.itwillbs.domain.WishVO;

public interface UserDAO {
	// 회원가입
	public void insertUser(UserVO uvo) throws Exception;
	// 카카오 로그인
	public void insertKakao(UserVO uvo) throws Exception;
	public String getToken(String code) throws Exception;
	public UserVO getUserInfo(String token) throws Exception;
	public UserVO getUser(UserVO uvo) throws Exception;
	
	// 아이디 중복체크
	public int checkId(String user_id) throws Exception;
	// 권한주기
	public void authUser(UserVO uvo) throws Exception;
	// 관리자권한
	public void adminAuth(UserVO uvo) throws Exception;
	
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
	
	// 가게 목록 전체 조회
	public List<StoreVO> getStore(StoreVO svo) throws Exception;
	
	// 리뷰
	public List<ReviewVO> getReview(int product_code) throws Exception;
	// 상품
	public List<ProductVO> productList(int product_code, String orderBy) throws Exception;

	// 구독상품 보여주기
	public List<SubscribeProductVO> showsub(String user_id) throws Exception;
	// 구독상품 정렬
	public List<SubscribeProductVO> sortSubOrderBy(Map<String, Object> map) throws Exception;
	// 구독상품 삭제 - 개별
	public int deleteSub(Map<String, Object> map) throws Exception;
	// 구독상품 삭제 - 전체
	public int deleteSubAll(String user_id) throws Exception;
	
	// cart session 생성을 위한 코드
	// 장바구니 상품 갯수 조회
	public int selectCountCart(String user_id) throws Exception;
		
	// bundle_code 가져오기
	public CartVO selectBundleCode(String user_id) throws Exception;

		
}
