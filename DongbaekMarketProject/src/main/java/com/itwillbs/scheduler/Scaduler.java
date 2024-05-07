package com.itwillbs.scheduler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.itwillbs.domain.SchedulerVO;
import com.itwillbs.service.AdminService;

@Component
public class Scaduler {
	
	@Inject
	private SqlSession sql;
	
	
	private static final Logger logger = LoggerFactory.getLogger(Scaduler.class);
	
	private static final String NAMESPACE = "com.itwillbs.mapper.AdminMapper";
	
	@Scheduled(cron = "0 01 10 * * *")
	public void calJob() throws Exception {
		logger.debug(" 정산 시작 ");
		
		Calendar day = new GregorianCalendar();
		SimpleDateFormat formatDay = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatDay.format(day.getTime());
		logger.debug("@@@@@@@@"+ today);
		
		day.add(Calendar.DATE, -1);
		String yesterday = formatDay.format(day.getTime());
		logger.debug("!!!!!!!!!"+yesterday);
		
		SchedulerVO svo = new SchedulerVO();
		svo.setCalDate(today);
		svo.setYesterday(yesterday);
		
		sql.update(NAMESPACE+".updateCal", svo);
		
	}
}
