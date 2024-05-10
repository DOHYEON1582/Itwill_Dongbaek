package com.itwillbs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.itwillbs.service.AdminService;

@Controller
@ServerEndpoint(value="/chat/{room_idx}/{sender}")
public class WebSocketChat {  
	
	@Inject
	private AdminService aService;
    
	private static final Map<Integer, List<Session>> rooms = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(WebSocketChat.class);
    
    public WebSocketChat() {
        // TODO Auto-generated constructor stub
    	logger.debug("웹소켓(서버) 객체생성");
    }
    
    @OnOpen
    public void onOpen(Session session, @PathParam("room_idx") Integer room_idx, @PathParam("sender")String sender) {
    	logger.debug("Session " + session.getId() + " connected to room " + room_idx);
    	rooms.computeIfAbsent(room_idx, k -> new ArrayList<>()).add(session);
    	
    	
    	try {
            final Basic basic = session.getBasicRemote();
            	if(!sender.equals("admin")) {
            		basic.sendText("<td style='text-align: left; width: 501px; color:blue; border:none;'>"+sender+"님 안녕하세요!");
            		basic.sendText("<td style='text-align: left; width: 501px; color:blue; border:none;'>잠시 기다려 주시면 상담사 연결해 드리겠습니다.");
            	}else {
            		basic.sendText("<td style='text-align: left; width: 501px; color:blue; border:none;'>상담이 연결되었습니다.");
            		sender = "상담사";
            		sendAllSessionToMessage(session, room_idx, "안녕하세요, 무엇을 도와드릴까요?", sender);
            	}
        }catch (Exception e) {
        	logger.debug(e.getMessage());
        }
        
    }
    
    /*
     * 모든 사용자에게 메시지를 전달한다.
     * @param self
     * @param sender
     * @param message
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("room_idx") Integer room_idx) {
        logger.debug("보낸이 : " + session.getId() + " 방번호 : " + room_idx + " 메세지 : " + message);
        
      
        String sender = message.split(",")[1];
    	message = message.split(",")[0];
    	
        try {
            final Basic basic = session.getBasicRemote();
            basic.sendText("<td style='text-align: right; width: 501px; border:none;'>"+message);
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        sendAllSessionToMessage(session, room_idx, message, sender);
    }

    private void sendAllSessionToMessage(Session self, Integer room_idx, String message, String sender) {
        List<Session> roomSessions = rooms.get(room_idx);
        if(sender.equals("admin")) {
        	sender = "상담사";
        }
        
        if (roomSessions != null) {
            for (Session session : roomSessions) {
                if (!self.getId().equals(session.getId())) {
                    try {
                    	logger.debug("<td style='text-align: left; width: 501px; border:none;'>"+sender + " : " + message);
                        session.getBasicRemote().sendText( "<td style='text-align: left; width: 501px; border:none;'>"+sender + " : " + message);
                    } catch (Exception e) {
                        logger.debug(e.getMessage());
                    }
                }
            }
        }
    }
    
    @OnError
    public void onError(Throwable e,Session session) {
        
    }
    
    @OnClose
    public void onClose(Session session, @PathParam("room_idx") Integer room_idx) {
        logger.debug("Session " + session.getId() + " in room " + room_idx + " 나가기");
        
        List<Session> roomSessions = rooms.get(room_idx);
        if (roomSessions != null) {
            roomSessions.remove(session);
            if (roomSessions.isEmpty()) {
                rooms.remove(room_idx);
                
            }
        }
    }
}
