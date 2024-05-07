package com.itwillbs.service;

import java.util.List;



import com.itwillbs.domain.ReviewVO;


public interface ReviewReplyService {

	public List<ReviewVO> getAllReviews() throws Exception; // 모든 리뷰 목록 가져오기
	
	public ReviewVO getReviewByCode(int review_code) throws Exception; // 특정 리뷰 상세 정보 가져오기
	
	public int addReplyToReview(ReviewVO rvo) throws Exception; // 답글 추가
	
	
	
}
