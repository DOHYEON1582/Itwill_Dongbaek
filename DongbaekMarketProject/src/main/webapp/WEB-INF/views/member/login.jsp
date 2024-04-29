<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> customLogin.jsp </h1>
	
	<form action="/member/login" method="post">
		아이디 : <input type="text" name="user_id" > <br>
		비밀번호 : <input type="password" name="user_pw" > <br>
		<button type="submit">Login</button>
	</form>
	
	<a href="/"> 메인으로 </a>
</body>
</html>