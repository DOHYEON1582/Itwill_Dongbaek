package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.AdminReviewVO;
import com.itwillbs.domain.ReviewCri;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.domain.UserVO;

@Repository
public class ReviewReplyDAOImpl implements ReviewReplyDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(ReviewReplyDAOImpl.class);
	private static final String NAMESPACE = "com.itwillbs.mapper.ReviewMapper";
	
	@Override
    public List<ReviewVO> getReviewList(UserVO uvo) throws Exception{
		logger.debug(" getReviewList(UserVO uvo) 실행 ");
        return sqlSession.selectList(NAMESPACE + ".getReviewList", uvo);
    }

	@Override
    public int countReviews() throws Exception {
        logger.debug(" countReviews() 호출 ");
        return sqlSession.selectOne(NAMESPACE + ".countReviews");
    }
	
	@Override
	public ReviewVO getReview(int review_code) throws Exception {
	    logger.debug("getReview(int review_code 실행");
	    return sqlSession.selectOne(NAMESPACE + ".getReview", review_code);
	}
	@Override
	public int checkReplyExist(int review_code) throws Exception {
	    return sqlSession.selectOne(NAMESPACE + ".checkReplyExist", review_code);
	}

	@Override
	public void addReply(ReviewVO rvo) throws Exception {
	    logger.debug("addReply(ReviewVO reply) 호출");
	    sqlSession.insert(NAMESPACE+".addReply",rvo);
	}

	@Override
	public void replyModify(ReviewVO rvo) throws Exception {
		logger.debug(" replyModify(ReviewVO rvo) 실행 ");
		sqlSession.update(NAMESPACE + ".replyModify", rvo);
	}

	@Override
    public ReviewVO getReviewByParams(String userId, int productCode, int orderCode) throws Exception{
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", userId);
        params.put("product_code", productCode);
        params.put("order_code", orderCode);
        return sqlSession.selectOne(NAMESPACE+".getReviewByParams", params);
    }
	
}
