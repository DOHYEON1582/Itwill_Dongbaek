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

	<table id="productList" class="productList" border=1>
		<thead>
			<tr>
				<th><input type="checkbox" id="allCheck" name="allCheck" class="allCheck"></td>
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
				<td id="cart_code${list.cart_code }"><input type="checkbox" id="ap_check" name="ap_check" class="ap_check" value="${list.cart_code }"></td>
				<td><img alt="" src="">경로확인 후 값 넣기</td>
				<td>${list.store_name}<br>${list.product_name}</td><!-- 가게명, 상품명 -->
				<td>${list.price }</td>
				<td>
					<input type="number" id="count" name="count" value="${list.count }">
					<button type="button" id="countUpdate">변경</button>
				</td>
				<c:set var="total" value="${list.price * list.count }"/>
				<td>${total}</td>
				<td>
					<button type="button" id="orderProduct">주문하기(주문하기 페이지로)</button><br> 
					<button type="button" id="addWish">관심상품등록(위시리스트로)</button><br> 
					<button type="button" id="deleteProduct">삭제(삭제)</button><br> 
				</td>
			</tr>
			<c:set var="num" value="${number -1 }"/>
			</c:forEach>
		</tbody>
	</table>
	
	<table>
		<tr>
			<td><button type="button" id="deleteAll">장바구니 비우기</button></td>
			<td><button type="button" id="deleteChecked">선택삭제</button></td>
		</tr>
	</table>
	
	<!-- 금액 보여주기 시작 -->
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
	<!-- 금액 보여주기 끝 -->
	
	<button type="button">전체주문하기</button>
	<button type="button">선택주문하기</button>

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
	$(document).ready(function(){
		
		// 전체 선택, 해제
		$('#allCheck').change(function(){
			$('.ap_check').prop('checked',$(this).prop('checked'));
		});
		
		// 선택된 상품 삭제
		$('#deleteChecked').click(function(){
			var checkList = $('.ap_check:checked').map(function(){
				return $(this).val();
			}).get();
			
			if(checkList.length === 0){
				alert("선택된 상품이 없습니다.");
				return;
			}
			
			if(confirm("삭제하시겠습니까?")){
				$.ajax({
					url: '/mypage/cart/deleteChecked',
					type: 'POST',
					data: {"checkList":checkList},
					success: function(){
						alert("삭제되었습니다.");
					},
					error: function(){
						
					}
				});
			}
			
		});
		
		// 전체 상품 삭제
		$('#deleteAll').click(function(){
			if(confirm("장바구니를 비우시겠습니까?")){
				$.ajax({
					url: '/mypage/cart/deleteAll',
					type: 'POST',
					success: function(){
						alert("모든 상품이 삭제되었습니다.");
					},
					error: function(){
						
					}
				});
			}
		});
		
		// 개별 상품 삭제
		$('#productList').on('click','#deleteProduct',function(){
			var row = $(this).closest('tr');
			var cartCode = row.find('.ap_check').val();
			
			if(confirm("해당 상품을 삭제하시겠습니까?")){
				$.ajax({
					url: '/mypage/cart/delete',
					type: 'POST',
					data: {"cartCode":cartCode},
					success: function(){
						row.remove();
						alert("모든 상품이 삭제되었습니다.");
					},
					error: function(){
						
					}
				});
			}
		});
		
		// 상품 수량 변경
	  $('#productList').on('click', '#countUpdate', function() {
	        var cartCode = $(this).closest('tr').find('.ap_check').val(); // 상품 코드 가져오기
	        var newCount = $(this).closest('tr').find('#count').val(); // 변경된 수량 가져오기

	        $.ajax({
	            url: '/mypage/cart/updateCount', 
	            type: 'POST',
	            data: {"cartCode": cartCode, "newCount": newCount}, 
	            success: function() {
	                alert("상품 수량이 변경되었습니다.");
	            },
	            error: function() {
	                
	            }
	        });
	    });
		
	});//끝 
</script>
 