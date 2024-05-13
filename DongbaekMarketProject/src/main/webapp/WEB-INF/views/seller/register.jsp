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
        $("#seller_id").on("focusout", function(event){
            var seller_id = $("#seller_id").val();
            
            $.ajax({
                url : "./seller/confirm",
                data : {seller_id : seller_id},
                type : "POST",
                dataType : "json",
                success : function(result) {
                    if(result == 0){
                        $("#label1").css("color", "black").text("사용 가능한 ID 입니다.");
                    }
                    else {
                        $("#label1").css("color", "red").text("사용 불가능한 ID 입니다.");
                        $("#seller_id").val('');
                    }
                }
            });
            event.preventDefault();
        });
    });    
    
    $(document).ready(function(){
	    $("#seller_pw2").on("focusout", function(event){
	    	var seller_pw = $("#seller_pw").val();
	    	var seller_pw2 = $("#seller_pw2").val();
	    	if(user_pw == user_pw2){
	    		$("#label2").css("color", "black").text("비밀번호가 같습니다.");
	    	} else {
	    		$("#label2").css("color", "red").text("비밀번호가 다릅니다");
	    		$("#seller_pw").val('');
	    		$("#seller_pw2").val('');
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
					<input class="loginInput" type="text"  name="seller_id" id="seller_id" placeholder="아이디" required><br>
					<label id="label1"></label>
					<input class="loginInput" type="password"  name="seller_pw" id="seller_pw" placeholder="비밀번호" required><br>
					<input class="loginInput" type="password" id="seller_pw2" placeholder="비밀번호확인" required><br>
					<label id="label2"></label>
					<input class="loginInput" type="text"  name="seller_phone" id="seller_phone" placeholder="휴대폰 번호" required>
					<input class="loginInput" type="text"  name="seller_name" id="seller_name" placeholder="성명" required>
					<input class="loginInput" type="text"  name="store_addr1" id="store_addr1" placeholder="주소" required>
					<input class="loginInput" type="text"  name="store_addr2" id="store_addr2" placeholder="상세 주소" required>
					<input class="loginInput" type="text"  name="store_code" id="store_code" placeholder="가게 번호" required>
					<input class="loginInput" type="text"  name="bank" id="bank" placeholder="은행" required>
					<input class="loginInput" type="text"  name="account" id="account" placeholder="계좌 번호" required>
					<input  id="loginBtn" type="submit" value="가입"/>
				</form>
			</div>
		</div>
		
		<div id="loginBox2">
			계정이 있으신가요? <a href="/seller/login">로그인</a>
		</div>
	</div>

</body>
</html>