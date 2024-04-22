package com.itwillbs.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.AdminCartVO;
import com.itwillbs.domain.AdminOrderVO;
import com.itwillbs.domain.UserVO;

@Repository
public class AdminDAOImpl implements AdminDAO {
	
	@Inject
	private SqlSession sql;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminDAOImpl.class);
	
	private static final String NAMESPACE = "com.itwillbs.mapper.AdminMapper";

	@Override
	public UserVO getUserInfo(UserVO vo) throws Exception {
		logger.debug(" getUserInfo(UserVO vo) 실행 ");
		
		return sql.selectOne(NAMESPACE+".getUser", vo);
	}

	@Override
	public List<AdminOrderVO> getUserOrder(UserVO vo) throws Exception {
		logger.debug(" getUserOrder(AdminOrderVO ovo) 실행 ");
		
		return sql.selectList(NAMESPACE+".getUserOrder", vo);
	}

	@Override
	public List<AdminCartVO> getUserCart(int order_code) throws Exception {
		logger.debug(" getUserCart(UserVO vo) 실행 ");
		
		return sql.selectList(NAMESPACE+".orderCart", order_code);
	}
	
	

}//endImpl
