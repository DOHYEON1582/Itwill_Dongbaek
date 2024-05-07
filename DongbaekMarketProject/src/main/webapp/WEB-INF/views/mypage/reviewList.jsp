<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<%@ include file="../include/header.jsp"%>
<style>
	.reviewList {
		width: 1300px;
	}
	
	.reviewList th {
		border: 1px solid black;
	}
	
	.reviewList td {
		border: 1px solid black;
	}
</style>
<div class="container">

	<form id="searchForm">
		<table>
			<tr>
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
	
	
	<table class="reviewList">
		<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성일</th>
		</tr>
		</thead>
		<tbody>
		<c:set var="num" value="${pageMaker.totalCount - (pageMaker.cri.page - 1) * pageMaker.cri.perPageNum }" />
		<c:forEach var="list" items="${reviewList}" varStatus="status">
			<tr>
				<td>${num - status.index}</td>
				<td>${list.title }</td>
				<td>${list.regdate }</td>
			</tr>
		<c:set var="num" value="${number -1 }"/>
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
		
		var url = "/mypage/review/list?";
		var params = [];
	
		if (startDate.trim() !== "") {
			params.push("startDate=" + startDate);
		}
		if (endDate.trim() !== "") {
			params.push("endDate=" + endDate);
		}
		
		// 파라미터가 있는 경우에만 URL에 추가
		if (params.length > 0) {
			url += params.join("&");
		}
	
		// 페이지 이동
		window.location.href = url;
	}
	
	// 폼 서브밋 이벤트 핸들러
	document.getElementById("searchForm").addEventListener("submit",
			function(event) {
				event.preventDefault(); // 기본 동작인 폼 서브밋 방지
				search(); // 검색 함수 호출
	});
		
	const seartchParams = new URLSearhParams(location.search);
</script>

<%@ include file="../include/footer.jsp"%>