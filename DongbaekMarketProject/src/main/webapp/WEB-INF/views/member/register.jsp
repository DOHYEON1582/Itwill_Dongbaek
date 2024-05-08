<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="/resources/css/projectCSS.css" rel="stylesheet" type="text/css" />
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div id="container">
		<div id="loginBox">
			<img id="mainImg" src="/resources/images/logo1.png">
			<div>
				<form action="" method="post">
					<input class="loginInput" type="text"  name="user_id" id="user_id" placeholder="아이디">
					<input class="loginInput" type="password"  name="user_pw" id="user_pw" placeholder="비밀번호"><br>
					<input class="loginInput" type="text"  name="phone" id="phone" placeholder="휴대폰 번호">
					<input class="loginInput" type="text"  name="user_name" id="user_name" placeholder="성명">
					<input class="loginInput" type="text"  name="addr1" id="addr1" placeholder="주소">
					<input class="loginInput" type="text"  name="addr2" id="addr2" placeholder="상세 주소">
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