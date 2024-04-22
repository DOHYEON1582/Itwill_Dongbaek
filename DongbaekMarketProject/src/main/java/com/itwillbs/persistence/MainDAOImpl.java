package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MarketVO;

@Repository
public class MainDAOImpl implements MainDAO {
	
	@Inject
	private SqlSession sql; 
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImple.class);
	
	private static final String NAMESPACE = "com.itwillbs.mapper.UserMapper";
	
	// 메인페이지 시장정보 가져오기
	@Override
	public List<MarketVO> getMarket(MarketVO mvo) throws Exception {
		logger.debug(" getMarket() 호출 ");
		
		return sql.selectList(NAMESPACE + ".getMarket", mvo);
	}

	@Override
	public MarketVO getMarketCode(int market_code) throws Exception {
		logger.debug(" getMarketCode(int market_code) 호출");
		return sql.selectOne(NAMESPACE + ".getMarketCode", market_code);
	}
	
	
}
