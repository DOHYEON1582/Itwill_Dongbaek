<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>

	<table>
		<thead>
			<tr>
				<th><input type="checkbox"></td>
				<th>상품정보</th>
				<th>판매가</th>
				<th>수량</th>
				<th>합계</th>
			</tr>
		</thead>
		<tbody>
			<!-- 반복문 시작 -->
			<tr>
				<td><input type="checkbox"></td>
				<td>이미지</td>
				<td colspan="2">상품명 옵션</td>
				<td>판매가</td>
				<td>수량</td>
				<td>합계</td>
			</tr>
			<!-- 반복문 끝 -->
		</tbody>
	</table>
	
<!-- 페이징 시작  -->
<!-- 페이징 끝  -->

<%@ include file="../include/footer.jsp"%>