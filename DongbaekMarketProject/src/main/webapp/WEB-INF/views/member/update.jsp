<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<style>
    fieldset {
    	font-family: 'Gowun Dodum', sans-serif;
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
    .update, input[type="text"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
    }
    .update {
        background-color: #007bff;
        color: #fff;
        cursor: pointer;
    }
    .update:hover {
        background-color: #0056b3;
    }
</style>
	
	<fieldset>
		<legend>회원정보 수정</legend>
		<form action="" method="post">
			아이디 : <input type="text" name="user_id" value="${userinfo.user_id }" readonly="readonly"> <br>
			전화번호 : <input type="text" name="phone" value="${userinfo.phone }"> <br>
			이름 : <input type="text" name="user_name" value="${userinfo.user_name }"> <br>
			주소 : <input type="text" name="addr1" value="${userinfo.addr1 }"> <br>
			상세주소 : <input type="text" name="addr2" value="${userinfo.addr2 }"> <br>
			<input type="submit" value="회원정보 수정" class="update">
		</form>
	</fieldset>
<%@include file="../include/footer.jsp" %>