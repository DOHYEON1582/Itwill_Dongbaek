package com.itwillbs.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
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

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.itwillbs.domain.MarkVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.domain.StoreVO;
import com.itwillbs.domain.SubscribeProductVO;
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
	public void insertKakao(UserVO uvo) throws Exception {
		logger.debug(" insertKakao(UserVO uvo) 호출");
		sql.insert(NAMESPACE + ".insertKakao" , uvo);
	}

	@Override
	public String getToken(String code) throws Exception {
		logger.debug(" getToken(String code) 호출 ");
		String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";
        
        try {
        	URL url = new URL(reqURL);
        	HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        	conn.setRequestMethod("POST");
        	conn.setDoOutput(true);
        	
        	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=809ade7f524b849d9b617cc2835efc68"); // 개인 REST IP
            sb.append("&redirect_uri=http://localhost:8088/registerkakao");
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();
            
            int responseCode = conn.getResponseCode();
            logger.debug("responseCode : " + responseCode);
            //요청을 통해 얻은 JSON 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            logger.debug("response body : " + result);

            //Gson으로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            
            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            //refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            logger.debug("access_token : " + access_Token);
            logger.debug("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return access_Token;
	}

	@Override
	public UserVO getUserInfo(String token) throws Exception {
		logger.debug(" getUserInfo(String token) 호출 ");
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		UserVO uvo = new UserVO();
		
		uvo.setUser_pw("1234");
		// access_token을 이용하여 사용자 정보 조회
	    try {
	       URL url = new URL(reqURL);
	       HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	       conn.setRequestMethod("POST");
	       conn.setDoOutput(true);
	       conn.setRequestProperty("Authorization", "Bearer " + token);

	       int responseCode = conn.getResponseCode();
	       logger.debug("responseCode : " + responseCode);

	       // 요청을 통해 얻은 JSON 메세지 읽어오기
	       BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	       String line = "";
	       String result = "";

	       while ((line = br.readLine()) != null) {
	           result += line;
	       }
	       logger.debug("response body : " + result);
	       
	       //Gson 라이브러리로 JSON파싱
	       JsonParser parser = new JsonParser();
	       JsonElement element = parser.parse(result);
	  
	       uvo.setUser_id(Integer.toString(element.getAsJsonObject().get("id").getAsInt()));
	       
//	       uvo.setUser_name(element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("name").getAsString());
	       
	       boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
	       if(hasEmail){
	    	   uvo.setSns_email(element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString());
	       }
	       
//	       boolean hasPhoneNumber = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_phone_number").getAsBoolean();
//	       if(hasPhoneNumber){
//	    	   uvo.setPhone(element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("phone_number").getAsString());
//	       }
	       br.close();

	       } catch (IOException e) {
	            e.printStackTrace();
	       }
		return uvo;
	}

	@Override
	public UserVO getUser(UserVO uvo) throws Exception {
		logger.debug(" getUser(UserVO uvo) 호출 ");
		return sql.selectOne(NAMESPACE + ".getUser", uvo);
	}

	@Override
	public int checkId(String user_id) throws Exception {
		logger.debug(" checkId(String user_id) 호출 ");
		return sql.selectOne(NAMESPACE + ".checkId", user_id);
	}

	@Override
	public void authUser(UserVO uvo) throws Exception {
		logger.debug(" authUser(String user_id) 호출 ");
		sql.insert(NAMESPACE +".authUser", uvo);
	}

	@Override
	public void adminAuth(UserVO uvo) throws Exception {
		logger.debug(" adminAuth(UserVO uvo) 호출 ");
		sql.insert(NAMESPACE + ".adminAuth", uvo);
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

	// 구독 제품 조회
	@Override
	public List<SubscribeProductVO> showsub(String user_id) throws Exception {
		logger.debug(" showsub(String user_id) 호출 ");
		return sql.selectList(NAMESPACE + ".showsub", user_id);
	}

	// 구독 제품 정렬
	@Override
	public List<SubscribeProductVO> sortSubOrderBy(Map<String, Object> map) throws Exception {
		logger.debug(" sortSubOrderBy(Map<String, Object> map) 호출 ");
		String orderBy = (String) map.get("orderBy");
		map.put("orderBy", orderBy);
		return sql.selectList(NAMESPACE + ".sortSub", map);
	}
	// 구독 제품 삭제 - 개별
	@Override
	public int deleteSub(Map<String, Object> map) throws Exception {
		logger.debug(" deleteSub() 호출 ");
		String product_code = (String) map.get("product_code");
		map.put("product_code", product_code);
		return sql.delete(NAMESPACE + ".deleteSub", map);
	}

	// 구독 제품 삭제 - 전체
	@Override
	public int deleteSubAll(String user_id) throws Exception {
		logger.debug(" deleteSubAll(String user_id) 호출 ");
		return sql.delete(NAMESPACE + ".deleteSubAll", user_id);
	}
	
	

	
	

	
	
	
	
	
	
	
	
	
	
}