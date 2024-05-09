package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class AdminMessageVO {

	private int message_idx;
	private int room_idx;
	private String user_id;
	private String context;
	private Timestamp regdate;
}
