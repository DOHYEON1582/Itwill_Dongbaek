
package com.itwillbs.service;

import java.util.List;


import com.itwillbs.domain.ReviewCri;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.domain.UserVO;


public interface ReviewReplyService {

public List<ReviewVO> getAllReviews(ReviewCri cri) throws Exception; // 모든 리뷰 목록 가져오기
	
	public int countReviews() throws Exception; // 리뷰 총 갯수
	
	public ReviewVO getReviewByCode(int review_code) throws Exception; // 특정 리뷰 상세 정보 가져오기
	
	public void addReply(ReviewVO rvo) throws Exception; // 리뷰 답글 작성
	
	public void replyModify(ReviewVO rvo) throws Exception; // 리뷰 답글 수정
	
	public ReviewVO getReviewByParams(String userId, int productCode, int orderCode) throws Exception;
	
	
}
