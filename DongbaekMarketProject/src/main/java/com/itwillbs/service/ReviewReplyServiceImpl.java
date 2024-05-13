package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.itwillbs.domain.AdminReviewVO;
import com.itwillbs.domain.ReviewCri;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.domain.UserVO;
import com.itwillbs.persistence.ReviewReplyDAO;

@Service
public class ReviewReplyServiceImpl implements ReviewReplyService {

	@Inject
	private ReviewReplyDAO rdao;

	private static final Logger logger = LoggerFactory.getLogger(ReviewReplyServiceImpl.class);
	
	@Override
	public List<ReviewVO> getReviewList(UserVO uvo) throws Exception {
		logger.debug(" getAllReviews() 호출 ");
		return rdao.getReviewList(uvo);
	}

	@Override
    public int countReviews() throws Exception {
        logger.debug(" countReviews() 호출 ");
        return rdao.countReviews();
    }
	
	 public ReviewVO getReview(int review_code) throws Exception {
	        logger.debug(" getReview(int review_code) 호출 ");
	        return rdao.getReview(review_code);
	   }

	
	
	@Override
	public int checkReplyExist(int review_code) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addReply(ReviewVO rvo) throws Exception {
	    logger.debug("addReply(ReviewVO reply) 호출");
	    rvo.setRe_ref(rvo.getReview_code());
	    rvo.setRe_lev(rvo.getRe_lev() + 1);
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
