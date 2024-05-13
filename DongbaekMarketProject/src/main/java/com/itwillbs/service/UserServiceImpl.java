package com.itwillbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.CartVO;
import com.itwillbs.domain.MarkVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.domain.StoreVO;
import com.itwillbs.domain.SubscribeProductVO;
import com.itwillbs.domain.UserVO;
import com.itwillbs.domain.WishVO;
import com.itwillbs.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {
	
	@Inject
	private UserDAO udao;
	@Inject
	private PasswordEncoder pwEncoder;
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	// 회원가입
	public void userInsert(UserVO uvo) throws Exception {
	    logger.debug(" userInsert(UserVO uvo) 호출 ");
	    
	    // 비밀번호 암호화
	    String encodedPassword = pwEncoder.encode(uvo.getUser_pw());
	    uvo.setUser_pw(encodedPassword);
	    
	    udao.insertUser(uvo);
	    logger.debug(" uvo.getid : " + uvo.getUser_id());
	    if(uvo.getUser_id().equals("admin")) {
	    	udao.adminAuth(uvo);
	    } else {
	    	udao.authUser(uvo);
	    }
	    logger.debug(" 회원가입 완료! ");
	}
	
	@Override
	public void userKakaoInsert(UserVO uvo) throws Exception {
		logger.debug(" userKakaoInsert(UserVO uvo) 실행");
		
		uvo.setUser_pw("1234");
		String encodedPassword = pwEncoder.encode(uvo.getUser_pw());
		uvo.setUser_pw(encodedPassword);
		udao.insertKakao(uvo);
		udao.authUser(uvo);
	}

	@Override
	public UserVO kakaoInfo(String code) throws Exception {
		logger.debug(" kakaoInfo(String code) 실행 ");
		String token = udao.getToken(code);
		logger.debug(" token : " + token);
		return udao.getUserInfo(token);
	}

	@Override
	public UserVO getUser(UserVO uvo) throws Exception {
		logger.debug(" getUser(UserVO uvo) 실행 ");
		return udao.getUser(uvo);
	}

	@Override
	public UserVO kakaoUserGet(UserVO uvo) throws Exception {
		logger.debug(" kakaoUserGet(UserVO uvo) 실행 ");
		return udao.getUser(uvo);
	}

	@Override
	public int checkId(String user_id) throws Exception {
		logger.debug(" checkId(String user_id) 실행 ");
		return udao.checkId(user_id);
	}


	@Override
    public UserVO loginUser(UserVO uvo) throws Exception {
        logger.debug(" loginUser(UserVO uvo) 실행 ");
        return udao.loginUser(uvo); 
    }

	@Override
	public UserVO userInfo(String user_id) throws Exception {
		logger.debug(" userInfo(String user_id) 실행 ");
		return udao.userInfo(user_id);
	}

	@Override
	public int userUpdate(UserVO uvo) throws Exception {
		logger.debug(" userUpdate(UserVO uvo) 실행 ");
		return udao.updateUser(uvo);
	}

	@Override
	public String getPass(String user_id) throws Exception {
		logger.debug(" getPass(String user_id) 실행 ");
		return udao.getPass(user_id);
	}

	@Override
	public int deleteUser(UserVO uvo) throws Exception {
		logger.debug(" deleteUser(UserVO uvo) 실행 ");
		return udao.deleteUser(uvo);
	}

	// 찜 목록 조회
	@Override
	public List<ProductVO> wishListAll(String user_id) throws Exception {
		logger.debug(" wishList(String user_id) 실행 ");
		return udao.wishListAll(user_id);
	}
	@Override
	public List<ProductVO> wishList(String orderBy, String user_id) throws Exception {
		logger.debug(" wishList(String orderBy, String user_id) 실행 ");
		Map<String, Object> map = new HashMap<>();
		map.put("orderBy", orderBy);
		map.put("user_id", user_id);
		return udao.wishList(map);
	}

	@Override
	public int deleteWish(int product_code) throws Exception {
		logger.debug(" deleteWish(int product_code) 실행 ");
		logger.debug("product_code : " + product_code);
		return udao.deleteWish(product_code);
	}

	@Override
	public List<ReviewVO> getReview(int product_code) throws Exception {
		logger.debug(" getReview(int product_code) 실행");
		return udao.getReview(product_code);
	}

	@Override
	public List<ProductVO> getProduct(int product_code, String orderBy) throws Exception {
	    logger.debug(" getProduct(int product_code) 실행 ");
	    return udao.productList(product_code, orderBy); // orderBy를 DAO로 전달
	}

	// 가게 목록 전체 조회
	@Override
	public List<StoreVO> getStore(StoreVO svo) throws Exception {
		logger.debug(" getStore(StoreVO svo) 실행 ");
		return udao.getStore(svo);
	}

	@Override
	public int deleteWishAll(WishVO wvo) throws Exception {
		logger.debug(" deleteWishAll(WishVO wvo) 실행 ");
		return udao.deleteWishAll(wvo);
	}

	// 즐겨찾기(가게) 조회
	@Override
	public List<MarkVO> getMark(String user_id) throws Exception {
		logger.debug(" getMark(MarkVO mvo) 실행 ");
		return udao.getMark(user_id);
	}

	@Override
	public int deleteMark(int store_code) throws Exception {
		logger.debug(" deleteMark(int store_code) 실행 ");
		return udao.deleteMark(store_code);
	}

	@Override
	public int deleteMarkAll(String user_id) throws Exception {
		logger.debug(" deleteMarkAll(String user_id) 실행 ");
		return udao.deleteMarkAll(user_id);
	}

	@Override
	public List<ProductVO> getProductOrderBy(String orderBy) throws Exception {
		logger.debug(" getProductOrderBy(String orderBy, String user_id) 실행 ");
		Map<String, Object> map = new HashMap<>();
		map.put("orderBy", orderBy);
		return udao.selectProductOrderBy(map);
	}

	// 구독상품 조회
	@Override
	public List<SubscribeProductVO> showsub(String user_id) throws Exception {
		logger.debug(" showsub(String user_id) 실행 ");
		return udao.showsub(user_id);
	}
	// 구독상품 정렬
	@Override
	public List<SubscribeProductVO> sortSub(String orderBy, String user_id) throws Exception {
		logger.debug(" sortSub(String orderBy, String user_id) 실행 ");
		Map<String, Object> map = new HashMap<>();
		map.put("orderBy", orderBy);
		map.put("user_id", user_id);
		return udao.sortSubOrderBy(map);
	}
	// 구독상품 삭제 - 개별
	@Override
	public int deleteSub(int product_code, String user_id) throws Exception {
		logger.debug(" deleteSub() 실행 ");
		Map<String, Object> map = new HashMap<>();
		map.put("product_code", product_code);
		map.put("user_id", user_id);
		return udao.deleteSub(map);
	}
	// 구동상품 삭제 - 전체
	@Override
	public int deleteSubAll(String user_id) throws Exception {
		logger.debug(" deleteSubAll(String user_id) 실행 ");
		return udao.deleteSubAll(user_id);
	}
	
	

	
	
	// 0509 cart session 생성을 위한 코드
	// 장바구니 상품 갯수 조회
	@Override
	public int selectCountCart(String user_id) throws Exception {
		return udao.selectCountCart(user_id);
	}

	// bundle_code 가져오기
	@Override
	public CartVO selectBundleCode(String user_id) throws Exception {
		return udao.selectBundleCode(user_id);
	}

}
