<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<!-- 제이쿼리 -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>

<div class="container">

	<h2>(o゜▽゜)o☆</h2>
	<%-- page:${cri.page}
	page:${pageMaker.cri.page } --%>
	
	<form method="POST" id="cartList" name="cartList">
	<input type="hidden" id="checkList" name="checkList">
	<table id="productList" class="productList" border=1>
		<thead>
			<tr>
				<th><input type="checkbox" id="allCheck" name="allCheck" onclick="checkAll()"></td>
				<th colspan="2">상품정보</th>
				<th>판매가</th>
				<th>수량</th>
				<th>합계</th>
				<th>선택</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="num" value="${pageMaker.totalCount - (pageMaker.cri.page - 1) * pageMaker.cri.perPageNum }" />
			<c:forEach var="list" items="${cartList }">
			<tr>
				<td><input type="checkbox" id="ap_check" name="ap_check" value="${list.cart_code }"></td>
				<td><img alt="" src="">경로확인 후 값 넣기</td>
				<td>${list.store_name}<br>${list.product_name}</td><!-- 가게명, 상품명 -->
				<td>${list.price }</td>
				<td>
					<input type="number" id="count" name="count" value="${list.count }">
					<button id="countUpdate" name="countUpdate">변경</button>
				</td>
				<c:set var="total" value="${list.price * list.count }"/>
				<td>${total}</td>
				<td>
					<button type="button" id="orderProduct" onclick="orderProduct(this)">주문하기(주문하기 페이지로)</button><br> 
					<button type="button" id="addWish" onclick="addWish(this)">관심상품등록(위시리스트로)</button><br> 
					<button type="button" id="deleteProduct" onclick="deleteProduct(this)">삭제(삭제)</button><br> 
				</td>
			</tr>
			<c:set var="num" value="${number -1 }"/>
			</c:forEach>
		</tbody>
	</table>
	<table>
		<tr>
			<td><button onclick="locaion.href='/mypage/cartDeleteAll'">장바구니 비우기</button></td>
			<td><button onclick="deleteChecked()">선택삭제</button></td>
		</tr>
	</table>
	
	
	<table border=1>
		<tr>
			<td>총 상품금액</td>
			<td>배송비</td>
			<td>결제예정금액</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>
	
	<button onclick="locaion.href='/order/orderForm'">전체주문하기</button>
	<button >선택주문하기</button>
	
	
	</form>
	
	<!-- 페이징 시작 -->
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
	<!-- 페이징 끝 -->
	
</div>
	
 <%@ include file="../include/footer.jsp"%>
 
 <script>
 	// 체크박스 전체 선택, 해제
 	function checkAll(){
 		if(cartList.allCheck.checked){
 			for(i = 1; i < (document.cartList.length); i++){
 				document.cartList.elements[i].checked = true;
 			}
 		} else {
 			for(i = 1; i < (document.cartList.length); i++){
 				document.cartList.elements[i].checked = false;
 			}
 		}
 	}
 	
 	// 체크된 상품삭제
	function deleteChecked() { // 선택된 상품 삭제
		var checkboxes = document.getElementsByName("ap_check");
		var checkList = []; // 체크박스 전체 수

		for (var i = 0; i < checkboxes.length; i++) {
			if (checkboxes[i].checked) {
				checkList.push(checkboxes[i].value);
			}
		}

		if (checkboxes.length === 0) { // 선택된 행이 없을 때
			alert("선택된 상품이 없습니다.");
			return;
		}

		// 확인 창 
		var confirmMsg = "삭제하시겠습니까?";
		if (confirm(confirmMsg)) {
			document.getElementById("checkList").value = checkList.join(",");
			document.getElementById("cartList").action = "/mypage/cartDelete";
			document.getElementById("cartList").submit();
		}

	}

	// 선택된 상품의 총 가격 계산
	function calculateTotalPrice() {
		var totalPrice = 0;
		var checkboxes = document.getElementsByName("ap_check");
		
		for (var i = 0; i < checkboxes.length; i++) {
			if (checkboxes[i].checked) {
				var row = checkboxes[i].parentNode.parentNode; // 체크된 체크박스의 부모 행
				var price = parseFloat(row.cells[5].innerText); // 행의 6번째 셀(가격)에서 가격 정보를 가져옴
				totalPrice += price; // 총 가격에 가격을 더함
			}
		}

		// 총 가격을 페이지에 표시
		document.getElementById("totalPrice").innerText = totalPrice.toLocaleString(); // 원화 형식으로 변환하여 표시
	}
	
	// 상품 주문하기
	function orderProduct(button){
		
	}
</script>
 