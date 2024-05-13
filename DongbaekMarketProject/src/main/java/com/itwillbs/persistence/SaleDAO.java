package com.itwillbs.persistence;

import java.util.List;
import java.util.Map;

public interface SaleDAO {
	// 일자별 매출액 조회
    public List<Map<String, Object>> getDailySales(Map<String, Object> map) throws Exception;
    
    // 월별 매출액 조회
    public List<Map<String, Object>> getMonthlySales(Map<String, Object> map) throws Exception;

    // 연도별 매출액 조회
    public List<Map<String, Object>> getYearlySales(Map<String, Object> map) throws Exception;
}
