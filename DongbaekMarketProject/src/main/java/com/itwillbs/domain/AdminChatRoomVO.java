package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class AdminChatRoomVO {
	private int room_idx;
	private String user_id;
	private String to_id;
	private Timestamp regdate;

}
