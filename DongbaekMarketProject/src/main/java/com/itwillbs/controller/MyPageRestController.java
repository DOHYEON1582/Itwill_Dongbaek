package com.itwillbs.controller;



import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.service.MyPageService;

@RestController
public class MyPageRestController {

	private static final Logger logger = LoggerFactory.getLogger(MyPageRestController.class);

	@Inject
	private MyPageService cService;
	

}
