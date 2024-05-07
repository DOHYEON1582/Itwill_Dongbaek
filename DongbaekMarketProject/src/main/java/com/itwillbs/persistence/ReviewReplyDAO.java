package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.ReviewVO;

public interface ReviewReplyDAO {
	
	public List<ReviewVO> getAllReviews() throws Exception; // 모든 리뷰 목록 가져오기
	
	public ReviewVO getReviewByCode(int review_code) throws Exception; // 특정 리뷰 상세 정보 가져오기
	
	public int insertReview(ReviewVO rvo) throws Exception; // 리뷰 추가하기
	
	public void updateReplyOrder(int re_ref, int re_seq) throws Exception; // 답글 순서 조정을 위한 메서드


	
}