<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Simple Chat</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <div>
        <button type="button" id="joinBtn">대화방 참여</button>
        <button type="button" id="leaveBtn">대회방 나가기</button>
    	<br/><br/><br/>
  		메세지 입력 : 
        <input type="text" id="sender" value="${sessionScope.id}" style="display: none;">
        <input type="text" id="messageinput">
        방번호 : 
        <input type="text" id="room_idx">
        <button type="button" id="sendBtn">메세지 전송</button>
        <button type="button" id="clearBtn">대화내용 지우기</button>
    </div>
    <!-- Server responses get written here -->
    <div id="messages">
    </div>
    <!-- websocket javascript -->
    <script type="text/javascript">
        var ws;
        var messages = $("#messages");
        
        $(document).ready(function() {
            $("#joinBtn").click(openSocket);
            $("#leaveBtn").click(closeSocket);
            $("#sendBtn").click(send);
            $("#clearBtn").click(clearText);
        });
        
        function openSocket(){
        	var room_idx = $("#room_idx").val();
        	console.log(room_idx);
        	
            if(ws != undefined && ws.readyState != WebSocket.CLOSED ){
                writeResponse("WebSocket is already opened.");
                return;
            }
            //웹소켓 객체 만드는 코드
            ws = new WebSocket("ws://localhost:8088/chat/"+room_idx+"/"+$("#sender").val());
            
            
            ws.onopen = function(event){
                if(event.data === undefined){
              		return;
                }
                writeResponse(event.data);
            };
            
            ws.onmessage = function(event){
                console.log('writeResponse');
                console.log(event.data)
                writeResponse(event.data);
            };
            
            ws.onclose = function(event){
                writeResponse("대화 종료");
            }
            
        }
        
        function send(){
            var text = $("#messageinput").val() + "," + $("#sender").val();
            ws.send(text);
            $("#messageinput").val("");
        }
        
        function closeSocket(){
            ws.close();
        }
        
        function writeResponse(text){
            messages.append("<br/>" + text);
        }

        function clearText(){
            messages.empty();
      	}
  </script>
</body>
</html>