package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.ReviewCri;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.persistence.ReviewReplyDAO;

@Repository
public class ReviewReplyServiceImpl implements ReviewReplyService {

	@Inject
	private ReviewReplyDAO rdao;

	private static final Logger logger = LoggerFactory.getLogger(ReviewReplyServiceImpl.class);
	
	@Override
	public List<ReviewVO> getAllReviews(ReviewCri cri) throws Exception {
		logger.debug(" getAllReviews() 호출 ");
		return rdao.getAllReviews(cri);
	}

	@Override
    public int countReviews() throws Exception {
        logger.debug(" countReviews() 호출 ");
        return rdao.countReviews();
    }
	
	@Override
	public ReviewVO getReviewByCode(int review_code) throws Exception {
		logger.debug(" getReviewByCode(int review_code) 호출 ");
		return rdao.getReviewByCode(review_code);
	}

	@Override
	public void addReply(ReviewVO rvo) throws Exception {
		logger.debug(" addReply(ReviewVO reply) 호출 ");
		rdao.addReply(rvo);
	}

	@Override
    public void replyModify(ReviewVO rvo) throws Exception {
		logger.debug(" replyModify(ReviewVO rvo) 호출 ");
        rdao.replyModify(rvo);
    }

	
	
	
}
