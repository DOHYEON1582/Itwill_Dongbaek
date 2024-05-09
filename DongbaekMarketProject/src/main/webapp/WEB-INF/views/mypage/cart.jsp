<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ include file="../include/header.jsp"%> --%>
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
					<!-- <button type="button" id="addWish">관심상품등록(위시리스트로)</button><br>  -->
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
			<td id="totalPrice"></td>
			<td id="deliveryFee"></td>
			<td id="payAmount"></td>
		</tr>
	</table>
	<!-- 금액 보여주기 끝 -->
	
	<button type="button" id="orderAll">전체주문하기</button>
	<button type="button" id="orderChecked">선택주문하기</button>

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
	
<%--  <%@ include file="../include/footer.jsp"%> --%>
 
<script>
document.addEventListener('DOMContentLoaded', function() {

    // 전체 선택, 해제
    document.getElementById('allCheck').addEventListener('change', function() {
        var checkboxes = document.querySelectorAll('.ap_check');
        checkboxes.forEach(function(checkbox) {
            checkbox.checked = this.checked;
        }.bind(this));
    });

    // 선택된 상품 삭제
    document.getElementById('deleteChecked').addEventListener('click', function() {
        var checkList = Array.from(document.querySelectorAll('.ap_check:checked')).map(function(checkbox) {
            return checkbox.value;
        });

        if (checkList.length === 0) {
            alert("선택된 상품이 없습니다.");
            return;
        }

        if (confirm("삭제하시겠습니까?")) {
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = '/mypage/cart/deleteChecked';

            checkList.forEach(function(value) {
                var input = document.createElement('input');
                input.type = 'hidden';
                input.name = 'checkList[]';
                input.value = value;
                form.appendChild(input);
            });

            document.body.appendChild(form);
            form.submit();
        }
    });

    // 전체 상품 삭제
    document.getElementById('deleteAll').addEventListener('click', function() {
        if (confirm("장바구니를 비우시겠습니까?")) {
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = '/mypage/cart/deleteAll';

            document.body.appendChild(form);
            form.submit();
        }
    });

    // 개별 상품 삭제
    document.getElementById('productList').addEventListener('click', function(event) {
        if (event.target && event.target.id === 'deleteProduct') {
            var row = event.target.closest('tr');
            var cartCode = row.querySelector('.ap_check').value;
            if (confirm("해당 상품을 삭제하시겠습니까?")) {
                var form = document.createElement('form');
                form.method = 'POST';
                form.action = '/mypage/cart/delete';

                var input = document.createElement('input');
                input.type = 'hidden';
                input.name = 'cartCode';
                input.value = cartCode;
                form.appendChild(input);

                document.body.appendChild(form);
                form.submit();
            }
        }
    });

    // 상품 수량 변경
    document.getElementById('productList').addEventListener('click', function(event) {
        if (event.target && event.target.id === 'countUpdate') {
            var cartCode = event.target.closest('tr').querySelector('.ap_check').value;
            var newCount = event.target.closest('tr').querySelector('#count').value;

            var form = document.createElement('form');
            form.method = 'POST';
            form.action = '/mypage/cart/updateCount';

            var cartCodeInput = document.createElement('input');
            cartCodeInput.type = 'hidden';
            cartCodeInput.name = 'cartCode';
            cartCodeInput.value = cartCode;
            form.appendChild(cartCodeInput);

            var newCountInput = document.createElement('input');
            newCountInput.type = 'hidden';
            newCountInput.name = 'newCount';
            newCountInput.value = newCount;
            form.appendChild(newCountInput);

            document.body.appendChild(form);
            form.submit();
        }
    });
    
 	// 선택된 상품 가격의 합
    document.getElementById('totalPrice').textContent = (function() {
        var totalPrice = 0;
        var checkboxes = document.querySelectorAll('.ap_check:checked');
        checkboxes.forEach(function(checkbox) {
            var price = parseInt(checkbox.closest('tr').querySelector('td:nth-child(4)').textContent);
            totalPrice += price;
        });
        return totalPrice;
    })();

    // 배송비 처리
    document.getElementById('deliveryFee').textContent = (function() {
        var totalPrice = parseFloat(document.getElementById('totalPrice').textContent);
        var deliveryFee = 0;
        if (totalPrice > 50000) {
            deliveryFee = 0;
        } else {
            deliveryFee = 2500;
        }
        return deliveryFee;
    })();

    // 결제예정금액
    document.getElementById('payAmount').textContent = (function() {
        var totalPrice = parseFloat(document.getElementById('totalPrice').textContent);
        var deliveryFee = parseFloat(document.getElementById('deliveryFee').textContent);
        var payAmount = totalPrice + deliveryFee;
        return payAmount;
    })();



 	// 선택 주문
    document.getElementById('orderChecked').addEventListener('click', function() {
        var checkList = [];
        var checkboxes = document.querySelectorAll('.ap_check:checked');
        checkboxes.forEach(function(checkbox) {
            checkList.push(checkbox.value);
        });
        
        if (checkList.length === 0) {
            alert("선택된 상품이 없습니다.");
            return;
        }

        // form 요소 생성
        var form = document.createElement('form');
        form.method = 'POST';
        form.action = '/order/orderChecked';

        // hidden input 요소 추가
        checkList.forEach(function(cartCode) {
            var input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'checkList';
            input.value = cartCode;
            form.appendChild(input);
        });

        // form을 body에 추가하고 submit
        document.body.appendChild(form);
        form.submit();
    });

    // 전체 주문
    document.getElementById('orderAll').addEventListener('click', function() {
        // form 요소 생성
        var form = document.createElement('form');
        form.method = 'POST';
        form.action = '/order/orderAll';

        // form을 body에 추가하고 submit
        document.body.appendChild(form);
        form.submit();
    });

    // 개별 주문
    document.getElementById('productList').addEventListener('click', function(event) {
        if (event.target && event.target.id === 'orderProduct') {
            var cartCode = event.target.closest('tr').querySelector('.ap_check').value;

            // form 요소 생성
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = '/order/orderProduct';

            // hidden input 요소 추가
            var input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'cartCode';
            input.value = cartCode;
            form.appendChild(input);

            // form을 body에 추가하고 submit
            document.body.appendChild(form);
            form.submit();
        }
    });


});

</script>
 