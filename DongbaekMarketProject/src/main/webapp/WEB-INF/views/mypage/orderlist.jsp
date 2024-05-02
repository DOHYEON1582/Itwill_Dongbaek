<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../include/header.jsp"%>
<style>
	.productList {
		width: 1300px;
	}
	
	.productList th {
		border: 1px solid black;
	}
	
	.productList td {
		border: 1px solid black;
	}
</style>

<div class="container">

	<!-- 기간선택 -->
	<table>
		<tr>
			<td>
				<input type="date">
				<input type="date">
			</td>
		</tr>
	</table>

	<table border="1">
		<tbody>
			<c:forEach items="#{orderList }" var="list">
				<tr>
					<td colspan="2">주문상태 표시</td><!-- 주문상태표시 -->
				</tr>
				<tr>
					<td rowspan="3">이미지</td> <!-- 상품 이미지 -->
					<td>주문날짜</td><!-- 주문날짜 -->
				</tr>
				<tr>
					<td>가게명</td><!-- 가게명 -->
				</tr>
				<tr>
					<td>상품명</td><!-- 상품명 -->
				</tr>
				<tr>
					<td>가격</td><!-- 가격 -->
				</tr>
				<tr>	
					<td>주문상세</td><!-- 주문상세 버튼 -->
					<td>주문내역삭제</td><!-- 주문내역 삭제 버튼 -->
					<c:if test="">
					<td>주문상세</td><!-- 주문상세 버튼 -->
					<td>주문내역삭제</td><!-- 주문내역 삭제 버튼 -->
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
<!-- 페이징 시작  -->
<table border=1>
		<tr>
			<c:if test="${pageMaker.prev }">
			<td><a href="cart${pageMaker.makeQuery(pageMaker.startPage - 1)}">&laquo;</a></td>
			</c:if>
			<c:forEach var="idx" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
				<td>
					<c:if test="${pageMaker.cri.page == idx}"><b></c:if>
					<a href="cart${pageMaker.makeQuery(idx)}">${idx}</a>
					<c:if test="${pageMaker.cri.page == idx}"></b></c:if>
				</td>
			</c:forEach>
			<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
				<td><a href="cart${pageMaker.makeQuery(pageMaker.endPage +1)}">&raquo;</a></td>
			</c:if>	
		</tr>
	</table>
<!-- 페이징 끝  -->

</div>
	

<%@ include file="../include/footer.jsp"%>