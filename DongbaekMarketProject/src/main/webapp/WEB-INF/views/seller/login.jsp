<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<!DOCTYPE html>
<html>
<head>

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->

<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.7.0/kakao.min.js"
  integrity="sha384-l+xbElFSnPZ2rOaPrU//2FF5B4LB8FiX5q4fXYTlfcG4PGpMkE1vcL7kNXI6Cci0" crossorigin="anonymous"></script>
<script type="text/javascript">
	Kakao.init('1137347e2c05112b4ea16cdc004303b2');
	function loginWithKakao() {
 	    Kakao.Auth.authorize({
 	      redirectUri: 'http://localhost:8088/registerkakao',
 	    });
 	  }
</script>

 
<meta charset="UTF-8">
<title>동백마켓</title>

</head>
<body>

<div id="container">
	<div id="loginBox" style="margin-top: 100px;">
		<img id="mainImg" src="/resources/images/logo1.png">	
	<div>
		<form action="/seller/login" method="post">
			<input class="loginInput" type="text"  name="seller_id" placeholder="아이디">
			<input class="loginInput" type="password"  name="seller_pw" placeholder="비밀번호"><br>
			<input  id="loginBtn" type="submit" value="로그인"/>
		</form>
	</div>
	<br>
	<hr>
	<div class="social-icons" style="height: 100px;">
		<a id="kakao-login-btn" href="javascript:loginWithKakao()">
			<img src="https://cs.kakao.com/img/cskakaocom/pc/thumb/thumb_kakaotalk.png" alt="kakao" style="width: 100px; height: 100px;">
		</a> 
		<div id="naver_id_login" style="text-align:center"><a href="${url}"><img width="223" src="${pageContext.request.contextPath}/resources/img/naver_Bn_Green.PNG"/></a></div>
		<br>
	</div>
	
</div>
	<div id="loginBox2">
		계정이 없으신가요? <a href="/seller/register">가입하기</a>
	</div>
</div>

</body>
</html>