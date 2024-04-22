package com.itwillbs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.domain.AdminCartVO;
import com.itwillbs.domain.UserVO;
import com.itwillbs.persistence.AdminDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProjectTest {

	@Inject
	private AdminDAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectTest.class);
	
	
	//@Test
	public void 유저정보테스트()throws Exception{
		UserVO vo = new UserVO();
		vo.setUser_id("admin");
		vo = dao.getUserInfo(vo);
		logger.info(" 결과 : "+vo);
	}
	
	//@Test
	public void 주문정보테스트()throws Exception{
		UserVO vo = new UserVO();
		vo.setUser_id("admin");
		vo.setStartDate("2024-04-21");
		
		logger.info("!!!!!!!!!!!!!!"+vo);
		logger.info(" 결과 : "+dao.getUserOrder(vo).size());
	}
	
	@Test
	public void 상세주문테스트()throws Exception{
		int order_code = 1;
		
		logger.info("@@@@@@@@@@@"+dao.getUserCart(order_code));
	}
	
	
	
	
}//endTest
