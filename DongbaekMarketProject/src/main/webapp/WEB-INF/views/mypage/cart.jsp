<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../include/header.jsp"%>

<style>
	.productList {
		width: 1000px;
		border-collapse: collapse; 
   	 	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
   	 	margin-bottom: 20px;
	}
	
	.productList th {
		/* border: 1px solid black; */
		text-align: center;
	}
	
	.productList td {
		/* border: 1px solid black; */
	}
	
	.productList button {
		background: lightyellow;
		border-style: none;
		color: black;
		border-radius: 5px;
		font-style: bold;
	}
	
	#orderProduct{
		margin-bottom: 8px;
	}
	
	.deleteBtn {
		width: 1000px;
		margin-bottom: 40px;
	}
	
	.deleteBtn button {
		background: lightyellow;
		border-style: none;
		color: black;
		border-radius: 5px;
		font-style: bold;
	}
	
	.amountList {
		width: 1000px;
		/* border: 1px solid black; */
	    border-collapse: collapse;
	    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	    margin-bottom: 40px;
	}
	
	.amountList td{
		height: 70px;
		/* border: 1px solid black; */
		font-style: bold;
		text-align: center;
		border-right: 1px solid gray;
		
	}
	
	.amountTd1 {
		color:black;
		font-size:20px;
		text-align: center;
		border-bottom: 2px solid #BDBDBD;
		
	}
	
	.amountList td:nth-child(3) {
	    border-right: none;
	}
	
	.btnList{
		width:1000px;
		margin-bottom: 60px;
	}
	
	
	#orderChecked{
		background: lightyellow;
		border-style: none;
		color: black;
		border-radius: 5px;
		width:180px;
		height:50px;
		font-style: bold;
	}

</style>

<!-- 제이쿼리 -->
<!-- <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script> -->

<div class="container">

	<table id="productList" class="productList" align="center">
		<thead>
			<tr>
				<td height=25px></td>
			</tr>
			<tr>
				<td colspan=7 align=center><h4>장바구니</h4></td>
			</tr>
			<tr>
				<td height=25px></td>
			</tr>
			<tr>
				<th style="width:35px; text-align:center;" width="35px" ><input type="checkbox" id="allCheck" name="allCheck" class="allCheck"></th>
				<th colspan="2" width=220px>상품정보</th>
				<th width=135px>판매가</th>
				<th>수량</th>
				<th width=135px>합계</th>
				<th>선택</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="num" value="${pageMaker.totalCount - (pageMaker.cri.page - 1) * pageMaker.cri.perPageNum }" />
			
			<c:choose>
				<c:when test="${empty cartList}">
					<tr>
						<td align="center" colspan="7" height=200px;> 장바구니에 담긴 상품이 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="list" items="${cartList }">
						<tr>
							<td align="center" width=35px id="cart_code${list.cart_code }"><input type="checkbox" id="ap_check" name="ap_check" class="ap_check" value="${list.cart_code }"></td>
							<td align="center" width=130px><img src="${pageContext.request.contextPath}/resources/images/product/${list.img1}" alt="Product Thumbnail" class="tab-image" style="width:120px; height:120px;"></td>
							<td>${list.store_name}<br>${list.product_name}</td><!-- 가게명, 상품명 -->
							<td align="center"><fmt:formatNumber value="${list.price }" pattern="#,###"/>원</td>
							<td align=center>
								<input style="width:50px; height:25px;" type="number" id="count" name="count" value="${list.count }" max="${list.max_account}">
								<button type="button" id="countUpdate">변경</button>
							</td>
							<c:set var="total" value="${list.price * list.count }"/>
							<td align="center"><fmt:formatNumber value="${total}" pattern="#,###"/>원</td>
							<td align=center>
								<button type="button" id="orderProduct" >주문하기</button><br> 
								<button type="button" id="deleteProduct">삭제</button><br> 
							</td>
						</tr>
						<c:set var="num" value="${number -1 }"/>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<tr>
				<td height="30px"></td>
			</tr>
		</tbody>
	</table>
	
	<table class="deleteBtn" align="center">
		<tr>
			<td>
				<button type="button" id="deleteAll">장바구니 비우기</button>
				<button type="button" id="deleteChecked">선택삭제</button>
			</td>
		</tr>
	</table>
	
	<!-- 금액 보여주기 시작 -->
	<table class="amountList" align="center">
		<tr>
			<td width="400" class="amountTd1">총 상품금액</td>
			<td width="400" class="amountTd1">배송비</td>
			<td width="400" class="amountTd1">결제예정금액</td>
		</tr>
		<tr>
			<td id="totalPrice"></td>
			<td id="deliveryFee"></td>
			<td id="payAmount"></td>
		</tr>
	</table>
	
	<!-- 금액 보여주기 끝 -->
	<table class="btnList" align="center" >
		<tr>
			<td align=center>
				<!-- <button type="button" id="orderAll">전체주문하기</button> -->
				<button type="button" id="orderChecked">주문하기</button>
			</td>
		</tr>
	</table>
	
	<%-- <!-- 페이징 시작 -->
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
	<!-- 페이징 끝 --> --%>
	
</div>
	
 <%@ include file="../include/footer.jsp"%>
 
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
                input.name = 'checkList';
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
        	
        	location.href="/mypage/cart/deleteAll";
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
                input.name = 'ap_check';
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
    
 	// 전체 선택, 해제
    var allCheck = document.getElementById('allCheck');
    var checkboxes = document.querySelectorAll('.ap_check');
    
    allCheck.addEventListener('change', function() {
        checkboxes.forEach(function(checkbox) {
            checkbox.checked = allCheck.checked;
        });
        calculatePrices(); // 전체 선택 및 해제 시 가격 재계산
    });

    checkboxes.forEach(function(checkbox) {
        checkbox.addEventListener('change', function() {
            if (!checkbox.checked) {
                allCheck.checked = false;
            } else {
                var allChecked = true;
                checkboxes.forEach(function(cb) {
                    if (!cb.checked) {
                        allChecked = false;
                    }
                });
                allCheck.checked = allChecked;
            }
            calculatePrices(); // 체크박스 선택 변경 시 가격 재계산
        });
    });

    // 초기화 시 가격 계산
    calculatePrices();

 	// 가격 계산 함수
    function calculatePrices() {
        var totalPrice = 0;
        var checkboxes = document.querySelectorAll('.ap_check:checked');
        checkboxes.forEach(function(checkbox) {
            var price = parseInt(checkbox.closest('tr').querySelector('td:nth-child(4)').textContent.replace(',', '')); // 콤마 제거 후 정수형으로 변환
            var count = parseInt(checkbox.closest('tr').querySelector('input[type="number"]').value);
            totalPrice += price * count; // 가격 * 수량을 누적하여 총 상품 금액 계산
        });
        var deliveryFee = calculateDeliveryFee(totalPrice);
        var payAmount = totalPrice + deliveryFee;

        document.getElementById('totalPrice').textContent = addCommas(totalPrice);
        document.getElementById('deliveryFee').textContent = checkboxes.length > 0 ? addCommas(deliveryFee) : 0; // 선택된 상품이 없으면 배송비를 0으로 설정
        document.getElementById('payAmount').textContent = addCommas(payAmount);
    }

    // 배송비 계산 함수
    function calculateDeliveryFee(totalPrice) {
        var deliveryFee = 0;
        if (totalPrice > 50000) {
            deliveryFee = 0;
        } else if(totalPrice > 0 && totalPrice <= 50000){
            deliveryFee = 2500;
        }
        return deliveryFee;
    }

    // 콤마 추가 함수
    function addCommas(x) {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }

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
        form.action = '/order/ordersession';

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
        
    	location.href="/order/ordersession";
    	// form 요소 생성
        /* var form = document.createElement('form');
        form.method = 'POST';
        form.action = '/order/orderform';

        // form을 body에 추가하고 submit
        document.body.appendChild(form);
        form.submit(); */
    });

    // 개별 주문
    document.getElementById('productList').addEventListener('click', function(event) {
        if (event.target && event.target.id === 'orderProduct') {
            var cartCode = event.target.closest('tr').querySelector('.ap_check').value;

            // form 요소 생성
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = '/order/ordersession';

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
 