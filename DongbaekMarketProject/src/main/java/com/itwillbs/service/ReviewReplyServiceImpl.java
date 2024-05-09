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
		// 기존 리뷰의 re_ref 값을 사용하여 그룹 번호 설정
        rvo.setRe_ref(rvo.getReview_code());
        // 기존 리뷰의 re_lev 값에 1을 더하여 계층 설정
        rvo.setRe_lev(rvo.getRe_lev() + 1);
        // 기존 리뷰의 re_seq 값에 1을 더하여 순서 설정
        rvo.setRe_seq(rvo.getRe_seq() + 1);
		rdao.addReply(rvo);
	}

	@Override
    public void replyModify(ReviewVO rvo) throws Exception {
		logger.debug(" replyModify(ReviewVO rvo) 호출 ");
        rdao.replyModify(rvo);
    }

	@Override
    public ReviewVO getReviewByParams(String userId, int productCode, int orderCode) throws Exception{
        return rdao.getReviewByParams(userId, productCode, orderCode);
    }
	
	
}
