<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<%@ include file="../include/header.jsp"%>

<div class="container">

	<table>
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성일</td>
		</tr>
		<c:forEach var="list" items="${reviewList}">
			<tr>
				<td>????</td>
				<td>${list.title }</td>
				<td>${list.regdate }</td>
			</tr>
		</c:forEach>
	</table>
	
</div>

<%@ include file="../include/footer.jsp"%>