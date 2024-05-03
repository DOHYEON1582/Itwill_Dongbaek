<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../include/header.jsp"%>

<style>
	
</style>

<div class="container">
	
	<table>
		<caption>주문정보</caption>
		<tr>
			<th>주문번호</th>
			<td>${ovo.order_code }</td>
		</tr>
		<tr>
			<td>주문일자</td>
			<td>${ovo.ordr_date }</td>
		</tr>
		<tr>
			<td>주문자</td>
			<td>${ovo.user_name }</td>
		</tr>
		<tr>
			<td>주문처리상태</td>
			<td>${ovo.states }</td>
		</tr>
	</table>
	
	<table>
		<caption>결제정보</caption>
		<tr>
			<th>총 주문금액</th>
			<td>${ovo.total_price }</td>
		</tr>
		<tr>
			<th>결제수단</th>
			<td>${ovo.pay_method }</td>
		</tr>
	</table>
	
	<table>
	<caption>주문 상품 정보</caption>
		<thead>
			<tr>
				<th colspan="2">상품정보</th>
				<th>수량</th>
				<th>상품구매금액</th>
				<th>주문처리상태</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${productList }">
			<tr>
				<td><img alt="" src=""></td><!-- 경로확인 후 값 넣기 -->
				<td>${list.store_name}<br>${list.product_name}</td><!-- 가게명, 상품명 -->
				<td>${list.count }</td>
				<td>${list.price * list.count }</td>
				<td>${list.states}</td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="5"></td><!-- 상품 가격 합 + 배송비 -->
			</tr>
		</tbody>
	</table>
	
	<table>
		<caption>배송지정보</caption>
		<tr>
			<th>받으시는분</th>
			<td>${ovo.rcv_name }</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${ovo.rcv_zip }</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${ovo.rcv_addr1 }&nbsp;${ovo.rcv_addr2 }</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${ovo.rcv_phone }</td>
		</tr>
		<tr>
			<th>배송메세지</th>
			<td>${ovo.rcv_msg }</td>
		</tr>	
	</table>
	

</div>

<%@ include file="../include/footer.jsp"%>