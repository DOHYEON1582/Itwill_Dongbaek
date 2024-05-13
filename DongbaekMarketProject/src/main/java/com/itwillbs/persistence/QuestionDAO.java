package com.itwillbs.persistence;

import java.util.List;
import java.util.Map;

import com.itwillbs.domain.AnswerVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.QuestionVO;

public interface QuestionDAO {
		// 상품별 문의 글 불러오기
		public List<QuestionVO> getQuestion(Map<String, Object> paramMap) throws Exception;
		// 문의 글 개수 계산
		public int questionCount() throws Exception;
		// 페이징 처리
		public List<QuestionVO> questionListPage(int page) throws Exception;
		// 각 상품 조회
		public ProductVO eachProduct(int product_code) throws Exception;
		// 최근 문의글 10개 불러오기
		public List<QuestionVO> newQuestion(int product_code) throws Exception;
		
		// 문의 상세정보 
		public List<QuestionVO> questionDetail(int q_code) throws Exception;
		// 문의 답글 달기
		public void qAnswer(AnswerVO avo) throws Exception;
		// 문의 답글 조회
		public List<AnswerVO> selectAnswer(int q_code) throws Exception;
		// 문의 답글 중복체크
		public int checkDuplicateAnswer(int q_code) throws Exception;

		
}