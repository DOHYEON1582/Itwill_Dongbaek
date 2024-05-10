<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->

<style>
    fieldset {
    	margin-top:100px;
    	font-family: 'Hahmlet', sans-serif ; 
    	font-size: 22px;
    	font-weight: bold;
        width: 30%;
        margin: 0 auto; 
        border: 2px solid #ccc;
        border-radius: 10px;
        padding: 20px;
        background-color: #fff;
    }
    legend {
    	text-align: center;
        font-size: 28px;
        font-weight: bold;
        margin-bottom: 20px;
    }
    .deletebox{
    height: 400px;
    
    }
</style>

	
	<fieldset>
		<legend>회원 탈퇴</legend>
		<form action="" method="post">
			<input type="hidden" name="user_id" value="${sessionScope.userVO.user_id }">
			비밀번호 : <input type="password" name="user_pw" placeholder="비밀번호를 입력하세요.">
			<input type="submit" value="삭제하기">
		</form>
	</fieldset>

<div class="deletebox">







</div>









	
<%@include file="../include/footer.jsp" %>