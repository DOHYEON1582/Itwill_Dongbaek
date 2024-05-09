package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.ReviewCri;
import com.itwillbs.domain.ReviewVO;

@Repository
public class ReviewReplyDAOImpl implements ReviewReplyDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(ReviewReplyDAOImpl.class);
	private static final String NAMESPACE = "com.itwillbs.mapper.ReviewMapper";
	@Override
    public List<ReviewVO> getAllReviews(ReviewCri cri) throws Exception{
		logger.debug(" getAllReviews() 실행 ");
        return sqlSession.selectList(NAMESPACE + ".getAllReviews", cri);
    }

	@Override
    public int countReviews() throws Exception {
        logger.debug(" countReviews() 호출 ");
        return sqlSession.selectOne(NAMESPACE + ".countReviews");
    }
	
    @Override
    public ReviewVO getReviewByCode(int review_code) throws Exception{
    	logger.debug(" getReviewByCode(int review_code) 실행 ");
        return sqlSession.selectOne(NAMESPACE + ".getReviewByCode", review_code);
    }

    @Override
    public int insertReview(ReviewVO review) throws Exception{
    	logger.debug(" insertReview(ReviewVO review) 실행 ");
        int result = sqlSession.insert(NAMESPACE + ".insertReview", review);
        return result;
    }

    @Override
    public void addReply(ReviewVO rvo) throws Exception{
    	logger.debug(" addReply(ReviewVO rvo) 실행 ");
    	
        // 기존 리뷰의 re_ref 값을 사용하여 그룹 번호 설정
        rvo.setRe_ref(rvo.getReview_code());
        // 기존 리뷰의 re_lev 값에 1을 더하여 계층 설정
        rvo.setRe_lev(rvo.getRe_lev() + 1);
        // 기존 리뷰의 re_seq 값에 1을 더하여 순서 설정
        rvo.setRe_seq(rvo.getRe_seq() + 1);
        
        // 매퍼를 통해 리뷰 답글 추가
        sqlSession.insert(NAMESPACE+".addReply", rvo);
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
