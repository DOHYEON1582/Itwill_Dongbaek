<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../include/header.jsp"%>
<style>
	.productList{
	
		/* border: 1px solid black; */
		width: 700px;
	    border-collapse: collapse;
	    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	    margin-bottom: 40px;
	}

	
	.productList td {
		/* border: 1px solid black; */
		
	}
	
	.g{
		background-color: yellow;
		border-collapse: collapse;
	    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	    margin-bottom: 40px;
	}
	
	.bbbtn {
		width: 700px;
		/* border: 1px solid black; */
		text-align: center;
		vertical-align: middle;
		margin-bottom: 80px;
	}
	
	.bbbtn button {
		
		background: lightyellow;
		border-style: none;
	}

</style>

<div class="container">
	
	

	<table class="productList" align=center>
		<tr>
			<td height=30px></td>
		</tr>
		<tr>
			<td colspan="5" style="text-align:center;">
			<h1>주문이 완료 되었습니다.</h1>
			<h3>주문번호 : ${orderCode } </h3>
		</td>
		</tr>
		<tr>
			<td height=50px></td>
		</tr>
		<c:forEach var="list" items="${cartList }">
				<tr>
					<td rowspan="3" width="150" height="100" style="text-align:center;"><img src="${pageContext.request.contextPath}/resources/images/product/${list.img1}" alt="Product Thumbnail" class="tab-image" style="width : 120px; height:120px;"></td>
					<td><%-- ${list.store_name}<br> --%><!-- <span style="color : black; font-size:20px"> -->${list.product_name}</span>&nbsp;&nbsp;<fmt:formatNumber value="${list.price }" pattern="#,###"/>원</td><!-- 가게명, 상품명 -->
				</tr>
				<tr>	
					<td>
						수량 : ${list.count }개<!-- <input type="number" id="count" name="count" value=""> -->
					</td>
				</tr>
				<tr>	
					<c:set var="total" value="${list.price * list.count }"/>
					<td><fmt:formatNumber value="${total}" pattern="#,###"/>원</td>
				</tr>
				<!-- <tr>
					<td colspan="5" height=20px>
						<hr>
					</td>
				</tr> -->
				<tr>
					<td height=15px></td>
				</tr>
			
		</c:forEach>
		
			<tr>
				<td></td>
			</tr>
	</table>
	
	<table class="bbbtn" align=center>
	<tr>
		<td>
			<!-- <button onclick="location.href='/mypage/orderlist'">주문내역</button> -->
			<button onclick="location.href='/'">메인 </button>
		</td>
	</tr>
	</table>
	
</div>

<%@ include file="../include/footer.jsp"%>