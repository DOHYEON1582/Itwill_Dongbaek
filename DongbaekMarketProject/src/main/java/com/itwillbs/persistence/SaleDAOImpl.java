package com.itwillbs.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SaleDAOImpl implements SaleDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(SaleDAOImpl.class);
	private static final String NAMESPACE = "com.itwillbs.mapper.SaleMapper";
	
	@Override
    public List<Map<String, Object>> getDailySales(Map<String, Object> map) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".getDailySales", map);
    }

    @Override
    public List<Map<String, Object>> getMonthlySales(Map<String, Object> map) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".getMonthlySales", map);
    }

    @Override
    public List<Map<String, Object>> getYearlySales(Map<String, Object> map) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".getYearlySales", map);
    }
}