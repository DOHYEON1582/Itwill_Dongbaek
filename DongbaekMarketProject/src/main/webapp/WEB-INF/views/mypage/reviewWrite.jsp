<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../include/header.jsp"%>

<!-- 별점 추가해야함 -->

<div class="container">
	
	<table>
		<tr>
			<td rowspamn="3">${cvo.img1 }</td>
			<td>${cvo.store_name }</td>
		</tr>
		<tr>
			<td>${cvo.product_name }</td>
		</tr>
		<tr>
			<td>${cvo.price }</td>
		</tr>
	</table>
	
	
	<form id="" name="" method="POST" enctype="multipart/form-data">
	<input type="hidden" name="" value="${param.order_code }">
	<input type="hidden" name="" value="${param.product_code }">
	<input type="hidden" name="" value="${sessionScope.user_id }">
	<table> 
		<tr>
			<td>제목</td>
			<td><input type="text" id="" name="title"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea rows="3" cols="20" id="" name="content"></textarea></td>
		</tr>
		<tr>
			<td>이미지첨부</td>
			<td><input multiple="multiple" type="file" id="" name="img1"  accept="image/*"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="작성하기"></td>
			<td><input type="reset"></td>
		</tr>
	</table>
	</form>
	
</div>

<%@ include file="../include/footer.jsp"%>