package com.itwillbs.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.persistence.SaleDAO;

@Service
public class SaleServiceImpl implements SaleService {

	@Inject
    private SaleDAO sdao;
	
	@Override
    public List<Map<String, Object>> getDailySales(Map<String, Object> map) throws Exception {
        return sdao.getDailySales(map);
    }

    @Override
    public List<Map<String, Object>> getMonthlySales(Map<String, Object> map) throws Exception {
        return sdao.getMonthlySales(map);
    }

    @Override
    public List<Map<String, Object>> getYearlySales(Map<String, Object> map) throws Exception {
        return sdao.getYearlySales(map);
    }
}