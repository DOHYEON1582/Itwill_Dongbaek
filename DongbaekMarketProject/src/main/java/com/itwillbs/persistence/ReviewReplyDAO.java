package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.ReviewCri;
import com.itwillbs.domain.ReviewVO;
import com.itwillbs.domain.UserVO;

public interface ReviewReplyDAO {
	
	public List<ReviewVO> getReviewList(UserVO uvo) throws Exception; // 모든 리뷰 목록 가져오기
	
	public int countReviews() throws Exception; // 리뷰 총 갯수
	public int checkReplyExist(int review_code) throws Exception;
	public ReviewVO getReview(int review_code)throws Exception; // 리뷰 조회
	
	public void addReply(ReviewVO rvo) throws Exception; // 리뷰 답글 작성
    
	public void replyModify(ReviewVO rvo) throws Exception; // 리뷰 답글 수정
	
	public ReviewVO getReviewByParams(String userId, int productCode, int orderCode) throws Exception;
	
	
}
