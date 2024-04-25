package com.itwillbs.scheduler;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.itwillbs.domain.SchedulerVO;
import com.itwillbs.service.AdminService;

@Component
public class Scaduler {
	
	@Inject
	private AdminService aService;
	
	//@Scheduled(cron = "0 42 16 * * *")
	public void job() throws Exception {
		System.out.println("스케줄러 실행");
		
		SchedulerVO svo = new SchedulerVO();
		svo.setCalDate("2024-04-22");
		svo.setYesterday("2024-04-21");
		
		aService.calCheck(svo);
		
	}
}
