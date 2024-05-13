package com.itwillbs.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.AnswerVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.QuestionVO;
import com.itwillbs.persistence.QuestionDAO;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	private static final Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);

	@Inject
	private QuestionDAO qdao;
	
	@Override
	public ProductVO eachProduct(int product_code) throws Exception {
		return qdao.eachProduct(product_code);
	
	}
	@Override
	public int questionCount() throws Exception {
		return qdao.questionCount();
	}

	@Override
	public List<QuestionVO> getQuestion(Map<String, Object> paramMap) throws Exception {
		return qdao.getQuestion(paramMap);
	}

	@Override
	public List<QuestionVO> newQuestion(int product_code) throws Exception {
		return qdao.newQuestion(product_code);
	}

	@Override
	public List<QuestionVO> questionDetail(int q_code) throws Exception {
		return qdao.questionDetail(q_code);
	}
	public void qAnswer(AnswerVO avo) throws Exception {
		logger.debug(" qAnswer(AnswerVO avo) 실행 ");
		qdao.qAnswer(avo);
	}

	@Override
	public List<AnswerVO> selectAnswer(int q_code) throws Exception {
		logger.debug(" selectAnswer(int q_code) 실행 ");
		return qdao.selectAnswer(q_code);
	}

	@Override
	public boolean isDuplicateAnswer(int q_code) throws Exception {
		logger.debug(" isDuplicateAnswer(int q_code) 실행 ");
		int count = qdao.checkDuplicateAnswer(q_code);
		logger.debug(" q_code : " + q_code);
		logger.debug(" count : " + count);
		return count > 0;
  }
}