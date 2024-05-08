<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/adminHeader.jsp"%>

<script>
	$(document).ready(function(){
		
		$('.col-1').on('mouseover',function() {
	        $(this).css('cursor','pointer');
	    });
		
		$('.col-1').click(function(){
			$('#list-tbody').empty();
			var title = $('#selectBox option:selected').val();
			var value = $('#searchText').val();
			
 			if(title == "user_id"){
				var user = {
						"user_id" : value,
				};
			}else{
				var user = {
						"user_name" : value,
				};
			}
			
 			$.ajax({
				url : "/admin/qna",
				type : "POST",
				data : JSON.stringify(user),
				contentType : "application/json; charset=UTF-8",
				success : function(data){
					if(data == ''){
						alert("상담정보가 없습니다!");
					}else{
						console.log(data);
						
						$(data).each(function(idx,item){
							
							var currentDate = new Date(item.regdate);
							var year = currentDate.getFullYear();
							var month = ('0' + (currentDate.getMonth() + 1)).slice(-2);
							var day = ('0' + currentDate.getDate()).slice(-2);
							var formatDate = year + '-' + month + '-' + day;
							
							var uservo = item.userVO
							$('#list-tbody').append(
											 `<tr><td>`+item.room_idx
											 +`</td><td>`+uservo.user_id
											 +`</td><td>`+uservo.user_name
											 +`</td><td>`+formatDate
											 +`</td></tr>`);
							
						});
					}
					
				}
			}); 
			
		});// 검색 이벤트
	
		$('#list-tbody').on('mouseover', 'tr', function() {
	        $(this).css('background-color','rgb(250,250,250)');
	        $(this).css('cursor','pointer');
	    });
		
	    $('#list-tbody').on('mouseout', 'tr', function() {
	        $(this).css('background-color','white');
	    });// 리스트 선택 이벤트
		
	    $('#list-tbody').on('click', 'tr', function() {
			
					var room_idx = $(this).find('td:eq(0)').text();
					
					openSocket(room_idx);
					
					$('#modal-table1 thead').append(
						  `<input type="text" id="sender" value="${sessionScope.id}" style="display: none;">
						   <tr style="height: 10%;">
								<td><h6>상담사 연결</h6></td>
							</tr>`
					);
			
					$('#chocolat-content-0').attr('class','chocolat-wrapper chocolat-visible');
					$('#second').attr('class','chocolat-overlay chocolat-visible');
				
	
			
		});// 채팅방 모달 오픈
		
		$('#addProduct').click(function(){
			
			firstOpenSocket();
			
			$('#modal-table1 thead').append(
				  `<input type="text" id="sender" value="${sessionScope.id}" style="display: none;">
				   <tr style="height: 10%;">
						<td><h6 style="margin: 10px;">상담사 연결</h6></td>
					</tr>`
			);
	
			$('#chocolat-content-0').attr('class','chocolat-wrapper chocolat-visible');
			$('#second').attr('class','chocolat-overlay chocolat-visible');
			
		});// 채팅하기 모달 오픈
		
		$('#chatBot').click(function(){
			
			$('#modal-table1 thead').append(
				   `<tr style="height: 10%;">
						<td><h6 style="margin: 10px;">챗봇 물어보기</h6></td>
					</tr>`
			);
	
			$('#chocolat-content-1').attr('class','chocolat-wrapper chocolat-visible');
			$('#first').attr('class','chocolat-overlay chocolat-visible');
			
		});// 챗봇 모달 오픈
		
		
		$('.chocolat-close').click(function(){
			$('.custom_closeModal').css('display','block');
			$('#chocolat-content-0').attr('class','chocolat-wrapper');
			$('#chocolat-content-1').attr('class','chocolat-wrapper');
			$('#second').attr('class','chocolat-overlay');
			$('#first').attr('class','chocolat-overlay');
		});// 모달 클로즈
		
		$('#closeCheck_body2').click(function(){
			$('.custom_closeModal').css('display','none');
			$('#modal-table1 thead').empty();
			$('#modal-table1 tbody').empty();
			$('#modal-table2 tbody').empty();
			$('#modal-table2 tfoot').empty();
			closeSocket();
			
			$.ajax({
				url : "/admin/deleteroom/"+room_idx,
				type : "GET",
				success : function(data){
					if(data == 1){
						alert("상담 종료!");
					}else{
						alert("상담중");
					}
					$(location).prop("href", location.href);
				}
				
			});
			
		});//클로즈 확인
		
		
		$('#closeCheck_body3').click(function(){
			$('.custom_closeModal').css('display','none');
			$('#chocolat-content-0').attr('class','chocolat-wrapper chocolat-visible');
			$('#second').attr('class','chocolat-overlay chocolat-visible');
		});//클로즈 취소

		
		

	});//document
</script>

<!-- 모달 -->
<div id="chocolat-content-0" class="chocolat-wrapper">
	<div id="second" class="chocolat-overlay"></div>
	<div class="chocolat-loader"></div>
	<div class="chocolat-layout">
		<div class="chocolat-top">
			<span class="chocolat-close"></span>
		</div>
		<div class="chocolat-center">
			<div class="chocolat-image-canvas chocolat-visible" >
				<div class="chocolat-image-wrapper" style="width: 868px; height: 868px; background-image:url(/resources/images/modal_back.jpg); text-align: center; color: black; padding: 20px;">
					<h1>상담사문의</h1>
					<div style="width: 100%; height: 100%; height: 750px; overflow: scroll; overflow-x:hidden; ">
						<form id="uploadForm" action=""  method="post" enctype="multipart/form-data">
							<table id="modal-table1" class="modal-table"  style="border:1px solid black; background-color: rgb(255, 220, 220); width: 400px; height:100%; margin: auto;">
								<thead style="height: 50px;">
								</thead>
								
								<tbody style="font-size:13px; display: block; height:540px; max-height: 540px; overflow-y: auto; flex-direction:column_reverse; background-color: white;">
								</tbody>
								
								<tfoot>
									<tr style="padding: 0px;">
										<td><input id="key-chat" type="text"style="width: 300px; height: 100px; float: left; text-align: left;"><button type="button" id="sendChat">보내기</button></td>
									</tr>
								</tfoot>
							</table>
						</form>
						<table id="modal-table2" class="modal-table" style="margin-top: 30px;">
							<thead>
							</thead>
							
							<tbody>
							</tbody>
							
							<tfoot>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="chocolat-bottom">
			<span class="chocolat-description">Calm Before The Storm (One Shoe Photography Ltd.)</span><span class="chocolat-pagination">1/1</span><span class="chocolat-set-title"></span><span class="chocolat-fullscreen"></span>
		</div>
	</div>
</div>

<!-- 챗봇 모달 -->
<div id="chocolat-content-1" class="chocolat-wrapper">
	<div id="first" class="chocolat-overlay"></div>
	<div class="chocolat-loader"></div>
	<div class="chocolat-layout">
		<div class="chocolat-top">
			<span class="chocolat-close"></span>
		</div>
		<div class="chocolat-center">
			<div class="chocolat-image-canvas chocolat-visible" >
				<div class="chocolat-image-wrapper" style="width: 868px; height: 868px; background-image:url(/resources/images/modal_back.jpg); text-align: center; color: black; padding: 20px;">
					<h1>챗봇 상품 문의</h1>
					<div style="width: 100%; height: 100%; height: 750px; overflow: scroll; overflow-x:hidden; ">
						<form id="uploadForm" action=""  method="post" enctype="multipart/form-data">
							<table id="modal-table1" class="modal-table"  style="border:2px solid black; background-color: rgb(255, 220, 220); width: 400px; height:100%; margin: auto;">
								<thead style="height: 50px; overflow: scroll; overflow-x:hidden;">
								</thead>
								
								<tbody style="font-size:13px; display: block; height:540px; max-height: 540px; overflow-y: auto; flex-direction:column_reverse; background-color: white;">
								</tbody>
								
								<tfoot>
									<tr style="padding: 0px;">
										<td><input id="chatBot-message" type="text"style="width: 300px; height: 100px; float: left; text-align: left; margin-right: 3px;"><button type="button" id="chatBot-send" style="margin:5px 0px 10px 0px">물어보기</button><button type="button" id="produce-search">상품조회</button></td>
									</tr>
								</tfoot>
							</table>
						</form>
						<table id="modal-table2" class="modal-table" style="margin-top: 30px;">
							<thead>
							</thead>
							
							<tbody>
							</tbody>
							
							<tfoot>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="chocolat-bottom">
			<span class="chocolat-description">Calm Before The Storm (One Shoe Photography Ltd.)</span><span class="chocolat-pagination">1/1</span><span class="chocolat-set-title"></span><span class="chocolat-fullscreen"></span>
		</div>
	</div>
</div>

<!-- 게시물 작성 취소 확인 모달 -->
<div class="custom_closeModal">
	<div class="closeCheck">
		<div class="closeCheck_body">
			<div id="closeCheck_body1">상담을 종료 하시겠어요?</div>
			<div id="closeCheck_body2">종료</div>
			<div id="closeCheck_body3">취소</div>
		</div>
	</div>

</div>

<div id="main">
<div id="main-first">
<!-- 내용 -->
<div class="notice">
	<div style="text-align: left; padding: 10px;">
		<h2>고객상담<button id="addProduct" type="button" class="btn btn-dark rounded-pill" style="margin-left: 30px;">상담하기</button>
					<button id="chatBot" type="button" class="btn btn-dark rounded-pill" style="margin-left: 30px;">챗봇열기</button></h2>
			<div class="col-sm-6 offset-sm-2 offset-md-0 col-lg-5 d-none d-lg-block">
            <div class="search-bar row bg-light p-2 my-2 rounded-4">
              <div class="col-md-4 d-none d-md-block">
                <select id="selectBox" class="form-select border-0 bg-transparent">
                  <option value="user_id">회원ID</option>
                  <option value="user_name">회원이름</option>
                </select>
              </div>
              <div class="col-11 col-md-7">
                <form id="search-form" class="text-center" action="index.html" method="post">
                  <input id="searchText" type="text" class="form-control border-0 bg-transparent" placeholder="Search for">
                </form>
              </div>
	          <div class="col-1">
	          
	                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="currentColor" d="M21.71 20.29L18 16.61A9 9 0 1 0 16.61 18l3.68 3.68a1 1 0 0 0 1.42 0a1 1 0 0 0 0-1.39ZM11 18a7 7 0 1 1 7-7a7 7 0 0 1-7 7Z"></path></svg>
	          </div>
            </div>
          </div>
      </div>
</div>


<table class="listTable" style="width: 99%; border-collapse: collapse; margin-left: 19px; background-color: white;">
    <thead>
        <tr>
        	<th>채팅방번호</th>
            <th>아이디</th>
            <th>이름</th>
            <th>문의날짜</th>
        </tr>
    </thead>
    <tbody id="list-tbody">
       
    </tbody>
</table>    



<!-- 내용 -->

</div>
<ul class="pagination d-flex justify-content-center">
	<li class="page-item disabled">
		<a class="page-link bg-none border-0" href="#" aria-label="Previous">
		<span aria-hidden="true">«</span>
		</a>
	</li>
	<li class="page-item active" aria-current="page"><a class="page-link border-0" href="#">1</a></li>
	<li class="page-item"><a class="page-link border-0" href="#">2</a></li>
	<li class="page-item"><a class="page-link border-0" href="#">3</a></li>
	<li class="page-item">
		<a class="page-link border-0" href="#" aria-label="Next">
		<span aria-hidden="true">»</span>
		</a>
	</li>
</ul>     
</div>

<script>
//소켓 함수 모음

	 $(document).ready(function() {
            //$("#joinBtn").click(openSocket);
            //$("#leaveBtn").click(closeSocket);
            $("#sendChat").click(send);
            //$("#clearBtn").click(clearText);
        });
		
	 var ws;
	 var messages = $("#messages");
	 var room_idx;
	 var user_id = "${sessionScope.user_id}";

		// 처음 방 생성
		function firstOpenSocket(){
			
			$.ajax({
				url : "/admin/createRoom/${sessionScope.user_id}",
				type : "GET",
				success : function(data){
					
				room_idx = data;
				console.log(room_idx);
					
		         if(ws != undefined && ws.readyState != WebSocket.CLOSED ){
		             writeResponse("연결 되어 있습니다.");
		             return;
		         }
		         //웹소켓 객체 만드는 코드
		         ws = new WebSocket("ws://localhost:8088/chat/"+room_idx+"/${sessionScope.user_id}");
		         
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
			});
		 
		}
		
 		function openSocket(room_idx){
		console.log(room_idx);
		 if(ws != undefined && ws.readyState != WebSocket.CLOSED ){
		     writeResponse("WebSocket is already opened.");
		     return;
		 }
		 //웹소켓 객체 만드는 코드
		 ws = new WebSocket("ws://localhost:8088/chat/"+room_idx+"/${sessionScope.user_id}");
		 
		 
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
		 var text = $("#key-chat").val() + ",${sessionScope.user_id}";
		 ws.send(text);
		 $("#key-chat").val("");
		 $('#key-chat').focus();
		}
		
		function closeSocket(){
		 ws.close();
		}
		
		function writeResponse(text){
			$('#modal-table1 tbody').append(
				"<tr style='border:none;'>"+text+"</td></tr>"
			);
			
			$('#modal-table1 tbody').scrollTop($('#modal-table1 tbody')[0].scrollHeight); // 스크롤 맨 밑으로
		}
		
		function clearText(){
		 messages.empty();
		}
		
		
//소켓 함수 모음

// 챗봇 함수 모음
	    const userInput = $('#chatBot-message');
	    const apiKey = 'sk-proj-e7YSQUgIZKE2B2P7vuERT3BlbkFJyckkhRKXgLe9bDIE7YFN';
	    const apiEndpoint = 'https://api.openai.com/v1/chat/completions'
	    // ChatGPT API 요청
	    async function fetchAIResponse(prompt) {
	        
	        const requestOptions = {
	            method: 'POST',
	            // API 요청의 헤더를 설정
	            headers: {
	            	'Content-Type': 'application/json',
	                'Authorization': `Bearer sk-proj-e7YSQUgIZKE2B2P7vuERT3BlbkFJyckkhRKXgLe9bDIE7YFN`
	            },
	            body: JSON.stringify({
	                model: "gpt-3.5-turbo", 
	                messages: [{
	                    role: "user",
	                    content: prompt
	                }, ],
	                temperature: 0.8,
	                max_tokens: 1024,
	                top_p: 1, 
	                frequency_penalty: 0.5, 
	                presence_penalty: 0.5, 
	                 
	            }),
	        };
	        // API 요청후 응답 처리
	        try {
	            const response = await fetch(apiEndpoint, requestOptions);
	            const data = await response.json();
	            const aiResponse = data.choices[0].message.content;
	            return aiResponse;
	        } catch (error) {
	    		console.error('OpenAI API 호출 중 오류 발생:', error);
	            return 'OpenAI API 호출 중 오류 발생';
	        }
	    }
	    	// 전송 버튼 클릭 이벤트
	    $('#chatBot-send').on('click', async function(){
	        
	        const message = $('#chatBot-message').val();
	        if (message.length === 0) return;
	        console.log(message);
	        $('#modal-table1 tbody').append(
				"<tr style='border:none;'><td style='text-align: right; width: 501px; border:none;'>"+message+"</td></tr>"
			);
	        
	        $('#chatBot-message').val(" ");
	        const aiResponse = await fetchAIResponse(message);
	        console.log(aiResponse);
	        $('#modal-table1 tbody').append(
					"<tr style='border:none;'><td style='text-align: left; width: 501px; border:none;'><label style='color:blue'>동백AI</label>:"
					+aiResponse+"<br><label style='color: red;'>주문하시려면 상품조회 클릭!</label></td></tr>"
				);
	        
	    });




// 챗봇 함수 모음


</script>







<%@ include file="../include/adminFooter.jsp"%>