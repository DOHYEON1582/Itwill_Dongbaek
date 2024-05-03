<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<link href="/resources/css/projectCSS.css" rel="stylesheet" type="text/css" />
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>

<meta charset="UTF-8">
<title>동백마켓</title>
</head>
<body>

<div id="container">
	<div id="loginBox" style="margin-top: 100px;">
		<img id="mainImg" src="/resources/images/logo1.png">	
	
	<div>
		<form action="/admin/loginAction" method="post">
			<input class="loginInput" type="text"  name="user_id" placeholder="아이디">
			<input class="loginInput" type="text"  name="user_pw" placeholder="비밀번호"><br>
			<input  id="loginBtn" type="submit" value="로그인"/>
		</form>
	</div>
	<br>
	<hr>
	

</div>
	<div id="loginBox2">
		계정이 없으신가요? <a href="/admin/join">가입하기</a>
	</div>
	
</div>

<script type="text/javascript">

	$(document).ready(function(){
		
		
		
	});//document.ready


</script>

</body>
</html>