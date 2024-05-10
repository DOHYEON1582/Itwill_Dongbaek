<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> -->

<!-- <link href="/resources/css/projectCSS2.css" rel="stylesheet" type="text/css" /> -->
    <style>
        .infoBody {
        text-align: center;
        margin: 50px auto; /* Adjust as needed */
        padding: 10px;
        border-radius: 10px;
        width: 50%; /* Adjust width as needed */
        }

        .info-table {
            margin: 0 auto;
            font-family: 'Gowun Dodum', sans-serif;
            font-weight: bold;
            width: 69%;
            margin: 20px auto;
            border-collapse: collapse;
            border: 1px solid #ddd;
        }
        .info-table th {
            background-color: #f2f2f2;
            border: 1px solid #ddd; /* 수정된 부분 */
            font-size: 25px;
        }
        .info-table td {
            border: 1px solid #ddd; /* 수정된 부분 */
            font-size: 22px;
        }

        .info-row{
        	padding: 10px;
        }
	.info-link {
	    margin-left: 20px; /* Adjust as needed */
	    margin-right: 10px; /* Add margin between links */
	    padding: 10px; /* Add padding for better clickability */
	    font-size: 18px;
	    text-decoration: none;
	    color: black;
	    background-color: lightyellow; /* Add background color */
	    border-radius: 5px; /* Add border radius for rounded corners */
	}
	
    </style>
</head>
<body>
<div class="infoBody">
    <h1 style="">My Page</h1>
    <table class="info-table">
        <tr class="info-row"> 
            <td>아이디</td>
            <td>${userinfo.user_id}</td>
        </tr>
        <tr class="info-row">
            <td>이름</td>
            <td>${userinfo.user_name}</td>
        </tr>
        <tr class="info-row"> 
            <td>전화번호</td>
            <td>${userinfo.phone}</td>
        </tr>
        <tr class="info-row"> 
            <td>주소</td>
            <td>${userinfo.addr1}</td>
        </tr>
        <tr class="info-row"> 
            <td>상세주소</td>
            <td>${userinfo.addr2}</td>
        </tr>
        <tr class="info-row"> 
            <td>회원가입일</td>
            <td>${userinfo.regdate}</td>
        </tr>
        <tr class="info-row"> 
            <td>회원 수정일</td>
            <td>${userinfo.update_date}</td>
        </tr>
    </table>
	    <a href="/member/update" class="info-link">회원정보 수정</a>
	    <a href="/member/delete" class="info-link">회원정보 삭제</a>
	    <a href="/member/wish" class="info-link">찜 목록</a>
	    <a href="/member/mark" class="info-link">즐겨찾기</a>
	    <a href="/member/subscribe" class="info-link">구독 정보 조회</a>
	    <button id="chat-modal-open" class="info-link" style="border: none">1:1문의하기</button>
</div>

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

<script>
$(document).ready(function(){
	$('#chat-modal-open').click(function(){
		console.log("클릭됨");
		
		firstOpenSocket();
		
		$('#modal-table1 thead').append(
			  `<input type="text" id="sender" value="${sessionScope.id}" style="display: none;">
			   <tr style="height: 10%;">
					<td><h6 style="margin: 10px;">상담사 연결</h6></td>
				</tr>`
		);

		$('#chocolat-content-0').attr('class','chocolat-wrapper chocolat-visible');
		$('#second').attr('class','chocolat-overlay chocolat-visible');
		
	});
	
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
	
	
});

</script>

<script>
//소켓 함수 모음
$(document).ready(function() {
            //$("#joinBtn").click(openSocket);
            //$("#leaveBtn").click(closeSocket);
            $("#sendChat").click(send);
            //$("#clearBtn").click(clearText);
        });
		
		console.log("!!!!!!!!!!${sessionScope.userVO.user_id}");
	 var ws;
	 var messages = $("#messages");
	 var room_idx;
	 var user_id = "${sessionScope.userVO.user_id}";

		// 처음 방 생성
		function firstOpenSocket(){
			
			$.ajax({
				url : "/admin/createRoom/${sessionScope.userVO.user_id}",
				type : "GET",
				success : function(data){
					
				room_idx = data;
				console.log(room_idx);
					
		         if(ws != undefined && ws.readyState != WebSocket.CLOSED ){
		             writeResponse("연결 되어 있습니다.");
		             return;
		         }
		         //웹소켓 객체 만드는 코드
		         ws = new WebSocket("ws://localhost:8088/chat/"+room_idx+"/${sessionScope.userVO.user_id}");
		         
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
		 ws = new WebSocket("ws://localhost:8088/chat/"+room_idx+"/${sessionScope.userVO.user_id}");
		 
		 
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
		 var text = $("#key-chat").val() + ",${sessionScope.userVO.user_id}";
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
	    
	    const apiEndpoint = 'https://api.openai.com/v1/chat/completions'
	    // ChatGPT API 요청
	    async function fetchAIResponse(prompt) {
	        
	        const requestOptions = {
	            method: 'POST',
	            // API 요청의 헤더를 설정
	            headers: {
	            	'Content-Type': 'application/json',
	                'Authorization': ``
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
				"<tr style='border:none;'><td colspan='7' style='text-align: right; width: 501px; border:none;'>"+message+"</td></tr>"
			);
	        
	        $('#chatBot-message').val("");
	        const aiResponse = await fetchAIResponse(message);
	        console.log(aiResponse);
	        $('#modal-table1 tbody').append(
					"<tr style='border:none;'><td colspan='7' style='text-align: left; width: 501px; border:none;'><label style='color:blue'>동백AI</label>:"
					+aiResponse+"<br><label style='color: red;'>주문하시려면 상품조회 클릭!</label></td></tr>"
				);
	        $('#modal-table1 tbody').scrollTop($('#modal-table1 tbody')[0].scrollHeight);
	        
	        });
	    
	    $('#product-search').on('click',function(){
	    	const product = $('#chatBot-message').val();
	    	if (product.length === 0) return;
	        console.log(product);
	        
	        $.ajax({
	        	url : "/admin/prosearch/"+product,
	        	type : "GET",
	        	contentType : "charset=UTF-8",
	        	success : function(data){
	        		$('#chatBot-message').val("");
	        		//alert(product);
	        		console.log(data);
	        		
	        		$(data).each(function(idx,item){
		        		$('#modal-table1 tbody').append(
		        				"<tr style='border:none; width: 501px;'><td style= 'border:none;'>"+item.product_code
								 +"</td><td style= 'border:none;'>"+item.product_name
								 +"</td><td style= 'border:none;'><a href='http://localhost:8088/'><img style='width: 40px; height: 40px;' src='/resources/upload1/"+item.img1+"'></a>"
								 +"</td><td style= 'border:none;'>"+item.price+"원"
								 +"</td><td style= 'border:none;'>"+item.unit
								 +"</td><td style= 'border:none;'>"+item.country
								 +"</td><td style= 'border:none;'>"+item.category
								 +"</td></tr>");
	        		});
	        		
	        		$('#modal-table1 tbody').scrollTop($('#modal-table1 tbody')[0].scrollHeight);
	        	}
	        });
	    });




// 챗봇 함수 모음


</script>


<%@include file="../include/footer.jsp" %>
