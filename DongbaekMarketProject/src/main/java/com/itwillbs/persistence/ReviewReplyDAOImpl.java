package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.ReviewVO;

@Repository
public class ReviewReplyDAOImpl implements ReviewReplyDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(ReviewReplyDAOImpl.class);
	private static final String NAMESPACE = "com.itwillbs.mapper.ReviewMapper";
	@Override
    public List<ReviewVO> getAllReviews() throws Exception{
		logger.debug(" getAllReviews() 실행 ");
        return sqlSession.selectList(NAMESPACE + ".getAllReviews");
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
	public void updateReplyOrder(int re_ref, int re_seq) throws Exception {
		logger.debug(" updateReplyOrder(int re_ref, int re_seq) 실행 ");
		// 답글 순서를 조정하는 SQL 쿼리 실행
        sqlSession.update(NAMESPACE + ".updateReplyOrder", re_ref);
	}

	
	
	
	
}
