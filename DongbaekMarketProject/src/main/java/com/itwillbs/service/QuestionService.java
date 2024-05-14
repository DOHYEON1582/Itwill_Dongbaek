package com.itwillbs.service;

import java.util.List;
import java.util.Map;

import com.itwillbs.domain.AnswerVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.QuestionVO;

public interface QuestionService {
	// 각 상품 정보 들고오기
		public ProductVO eachProduct(int product_code) throws Exception;	
	// 상품별 문의글 들고오기
		public List<QuestionVO> getQuestion(Map<String, Object> paramMap) throws Exception;
		
		// 상품 글 개수 계산
		public int questionCount() throws Exception;
		
		// 최근 문의글 10개 들고오기
		public List<QuestionVO> newQuestion(int product_code) throws Exception;
		public List<QuestionVO> questionDetail(int q_code) throws Exception;
		// 문의 답글 달기
		public void qAnswer(AnswerVO avo) throws Exception;
		
		// 문의 답글 조회
		public List<AnswerVO> selectAnswer(int q_code) throws Exception;
		
		// 문의 답글 중복체크
		public boolean isDuplicateAnswer(int q_code) throws Exception;
}
