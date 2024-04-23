<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원정보 수정 페이지 입니다.</h1>
	
	<fieldset>
		<legend>회원정보 수정</legend>
		<form action="" method="post">
			아이디 : <input type="text" name="user_id" value="${userinfo.user_id }" readonly="readonly"> <br>
			비밀번호 : <input type="password" name="user_pw"> <br>
			전화번호 : <input type="text" name="phone" value="${userinfo.phone }"> <br>
			이름 : <input type="text" name="user_name" value="${userinfo.user_name }"> <br>
			주소 : <input type="text" name="addr1" value="${userinfo.addr1 }"> <br>
			상세주소 : <input type="text" name="addr2" value="${userinfo.addr2 }"> <br>
			<input type="submit" value="회원정보 수정">
		</form>
	</fieldset>
</body>
</html>