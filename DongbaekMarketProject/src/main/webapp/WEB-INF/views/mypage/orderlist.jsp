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
	<form id="searchFrom">
	<table>
		<tr>
			<td>
				<select id="states" name="states">
					<option>=주문상태=</option>
					<option>결제완료</option>
					<option>배달준비</option>
					<option>배달중</option>
					<option>배달완료</option>
					<option>주문취소</option>
					<option>반품접수</option>
					<option>환불대기중</option>
					<option>환불완료</option>
				</select>
			</td>
			<td>
				<input type="date" id="startDate" name="startDate">
				<input type="date" id="endDate" name="endDate">
			</td>
			<td>
				<button onclick="search()">조회</button>
			</td>
		</tr>
	</table>
	</form>

	<table border="1">
		<tbody>
			<c:forEach items="#{orderList }" var="list">
				<tr>
					<td colspan="2">${list.states }</td><!-- 주문상태표시 -->
				</tr>
				<tr>
					<td rowspan="3">#{list.img1 }</td> <!-- 상품 이미지 -->
					<td>${list.ordr_date }</td><!-- 주문날짜 -->
				</tr>
				<tr>
					<td>${list.store_name }</td><!-- 가게명 -->
				</tr>
				<tr>
					<td>${list.product_name}</td><!-- 상품명 -->
				</tr>
				<tr>
					<td>${list.product_name }</td><!-- 가격 -->
				</tr>
				<tr>	
					<td><button id="" onclick="location.href='/mypage/orderdetail'">주문상세</button></td><!-- 주문상세 버튼 -->
					<c:if test="${list.states == '배달완료' }">
					<td><button id="" onclick="location.href=''">주문상세</button></td><!-- 주문상세 버튼 -->
					<td><button id="" onclick="location.href='/mypage/orderdelete'">주문내역삭제</button></td><!-- 주문내역 삭제 버튼 -->
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
<script>
//검색 
function search() {
	var startDate = document.getElementById("startDate").value;
	var endDate = document.getElementById("endDate").value;
	var department = document.getElementById("states").value;
	
	var url = "/mypage/orderlist?";
	var params = [];

	if (startDate.trim() !== "") {
		params.push("startDate=" + startDate);
	}
	if (endDate.trim() !== "") {
		params.push("endDate=" + endDate);
	}
	if (states.trim() !== "" && states.trim() !== "=주문상태=") {
		params.push("states=" + states);
	}

	// 파라미터가 있는 경우에만 URL에 추가
	if (params.length > 0) {
		url += params.join("&");
	}

	// 페이지 이동
	window.location.href = url;
}

// 폼 서브밋 이벤트 핸들러
document.getElementById("searchFrom").addEventListener("submit",
		function(event) {
			event.preventDefault(); // 기본 동작인 폼 서브밋 방지
			search(); // 검색 함수 호출
		});

</script>
<%@ include file="../include/footer.jsp"%>