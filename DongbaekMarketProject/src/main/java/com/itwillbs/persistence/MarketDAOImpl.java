package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MarketVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.StoreVO;


@Repository
public class MarketDAOImpl implements MarketDAO{

	@Inject
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(MarketDAOImpl.class);
	private static final String NAMESPACE = "com.itwillbs.mapper.MarketMapper";

	@Override
	public MarketVO getMarketList() throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".marketList");
	}

	@Override
	public List<StoreVO> getStoreList() throws Exception {
		return sqlSession.selectList(NAMESPACE + ".storeList");
	}

	@Override
	public List<MarketVO> getMarketListCode(int market_code) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".marketListCode", market_code);
	}

	@Override
	public List<ProductVO> getProductList() throws Exception {
		return sqlSession.selectList(NAMESPACE + ".selectProduct");
	}
	
	
}
