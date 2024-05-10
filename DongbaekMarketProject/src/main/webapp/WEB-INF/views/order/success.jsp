<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../include/header.jsp"%>


<div class="container">
	<h1>주 문 완 료</h1>
	<h3>주문번호 : ${orderCode } </h3>

	<table>
		<c:forEach var="list" items="${cartList }">
		
				<tr>
					<td><img src="${pageContext.request.contextPath}/resources/images/product/${list.img1}" alt="Product Thumbnail" class="tab-image" style="width : 180px"></td>
					<td>${list.store_name}<br>${list.product_name}</td><!-- 가게명, 상품명 -->
					<td><%-- <fmt:formatNumber value="${list.price }" pattern="#,###"/> --%>${list.price }</td>
					<td>
						${list.count }<!-- <input type="number" id="count" name="count" value=""> -->
					</td>
					<c:set var="total" value="${list.price * list.count }"/>
					<td><%-- <fmt:formatNumber value="${total}" pattern="#,###"/> --%>${total}</td>
				</tr>
		
		</c:forEach>
	</table>
	
	<button onclick="location.href='/mypage/orderlist'">주문내역</button>
	<button onclick="location.href='/'">메인 </button>
</div>

<%@ include file="../include/footer.jsp"%>