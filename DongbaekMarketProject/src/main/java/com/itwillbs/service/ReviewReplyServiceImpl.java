package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.ReviewVO;
import com.itwillbs.persistence.ReviewReplyDAO;
import com.itwillbs.persistence.ReviewReplyDAOImpl;

@Repository
public class ReviewReplyServiceImpl implements ReviewReplyService {

	@Inject
	private ReviewReplyDAO rdao;

	private static final Logger logger = LoggerFactory.getLogger(ReviewReplyServiceImpl.class);
	
	@Override
	public List<ReviewVO> getAllReviews() throws Exception {
		logger.debug(" getAllReviews() 호출 ");
		return rdao.getAllReviews();
	}

	@Override
	public ReviewVO getReviewByCode(int review_code) throws Exception {
		logger.debug(" getReviewByCode(int review_code) 호출 ");
		return rdao.getReviewByCode(review_code);
	}

	@Override
	public int addReplyToReview(ReviewVO rvo) throws Exception {
		logger.debug(" addReplyToReview(ReviewVO rvo) 호출 ");
		return rdao.insertReview(rvo);
	}
	
	
	
}
