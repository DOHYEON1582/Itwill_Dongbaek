package com.itwillbs.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.AnswerVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.QuestionVO;

@Repository
public class QuestionDAOImpl implements QuestionDAO {
	@Inject
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(QuestionDAOImpl.class);
	private static final String NAMESPACE = "com.itwillbs.mapper.QuestionMapper";
	
	@Override
	public List<QuestionVO> getQuestion(Map<String, Object> paramMap) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".selectQuestion", paramMap);
	}

	@Override
	public int questionCount() throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".totalCount");
	}
	@Override
	public ProductVO eachProduct(int product_code) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".eachProduct", product_code);
	}
	@Override
	public List<QuestionVO> questionListPage(int page) throws Exception {
		page = (page - 1) * 5;
		return sqlSession.selectList(NAMESPACE + ".questionPage", page);
	}

	@Override
	public List<QuestionVO> newQuestion(int product_code) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".newQuestion", product_code);
	}

	@Override
	public List<QuestionVO> questionDetail(int q_code) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".questionDetail", q_code);
	}
	
	public void qAnswer(AnswerVO avo) throws Exception {
		logger.debug(" qAnswer(AnswerVO avo) 호출 ");
		sqlSession.insert(NAMESPACE + ".qAnswer", avo);
	}

	@Override
	public List<AnswerVO> selectAnswer(int q_code) throws Exception {
		logger.debug(" selectAnswer(int q_code) 호출 ");
		return sqlSession.selectList(NAMESPACE + ".selectAnswer", q_code);
	}

	@Override
	public int checkDuplicateAnswer(int q_code) throws Exception {
		logger.debug(" checkDuplicateAnswer(int q_code) 호출");
		logger.debug(" q_code : " + q_code);
		return sqlSession.selectOne(NAMESPACE + ".checkDuplicateAnswer", q_code);
	}
	
	
	
}
