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
			아이디 : <input type="text" name="seller_id" value="${sellerinfo.seller_id }" readonly="readonly"> <br>
			전화번호 : <input type="text" name="seller_phone" value="${sellerinfo.seller_phone }"> <br>
			이름 : <input type="text" name="seller_name" value="${sellerinfo.seller_name }"> <br>
			주소 : <input type="text" name="store_addr1" value="${sellerinfo.store_addr1 }"> <br>
			상세주소 : <input type="text" name="store_addr2" value="${sellerinfo.store_addr2 }"> <br>
			<input type="submit" value="회원정보 수정" class="update">
		</form>
	</fieldset>
<%@include file="../include/footer.jsp" %>