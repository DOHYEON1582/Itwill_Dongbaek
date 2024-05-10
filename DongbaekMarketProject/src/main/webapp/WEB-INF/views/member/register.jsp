<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="/resources/css/projectCSS.css" rel="stylesheet" type="text/css" />
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
<meta charset="UTF-8">

<title> 회원가입 </title>

<script type="text/javascript">
    $(document).ready(function(){
        $("#user_id").on("focusout", function(event){
            var user_id = $("#user_id").val();
            
            $.ajax({
                url : "./confirm",
                data : {user_id : user_id},
                type : "POST",
                dataType : "json",
                success : function(result) {
                    if(result == 0){
                        $("#label1").css("color", "black").text("사용 가능한 ID 입니다.");
                    }
                    else {
                        $("#label1").css("color", "red").text("사용 불가능한 ID 입니다.");
                        $("#user_id").val('');
                    }
                }
            });
            event.preventDefault();
        });
    });    
    
    $(document).ready(function(){
	    $("#user_pw2").on("focusout", function(event){
	    	var user_pw = $("#user_pw").val();
	    	var user_pw2 = $("#user_pw2").val();
	    	if(user_pw == user_pw2){
	    		$("#label2").css("color", "black").text("비밀번호가 같습니다.");
	    	} else {
	    		$("#label2").css("color", "red").text("비밀번호가 다릅니다");
	    		$("#user_pw").val('');
	    		$("#user_pw2").val('');
	    	}
	    });
    });
    
</script>

</head>
<body>

	<div id="container">
		<div id="loginBox">
			<img id="mainImg" src="/resources/images/logo1.png">
			<div>
				<form action="" method="post">
					<input class="loginInput" type="text"  name="user_id" id="user_id" placeholder="아이디" required><br>
					<label id="label1"></label>
					<input class="loginInput" type="password"  name="user_pw" id="user_pw" placeholder="비밀번호" required><br>
					<input class="loginInput" type="password" id="user_pw2" placeholder="비밀번호확인" required><br>
					<label id="label2"></label>
					<input class="loginInput" type="text"  name="phone" id="phone" placeholder="휴대폰 번호" required>
					<input class="loginInput" type="text"  name="user_name" id="user_name" placeholder="성명" required>
					<input class="loginInput" type="text"  name="addr1" id="addr1" placeholder="주소" required>
					<input class="loginInput" type="text"  name="addr2" id="addr2" placeholder="상세 주소" required>
					<input  id="loginBtn" type="submit" value="가입"/>
				</form>
			</div>
		</div>
		
		<div id="loginBox2">
			계정이 있으신가요? <a href="/member/login">로그인</a>
		</div>
	</div>

</body>
</html>