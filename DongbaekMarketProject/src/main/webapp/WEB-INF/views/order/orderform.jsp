<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../include/header.jsp"%>

<style>
	.productList th{
		border: 1px solid black;
	}
	.productList td{
		border: 1px solid black;
	}
	.orderFormTbl {
		width: 1300px;
	}
	
	.orderFormTbl td {
		border: 1px solid black;
	}
	
	/* Chrome, Safari, Edge, Opera */
	input::-webkit-outer-spin-button,
	input::-webkit-inner-spin-button {
	  -webkit-appearance: none;
	  margin: 0;
	}
	
	/* Firefox  */
	input[type='number'] {
	  -moz-appearance: textfield;
	}
</style>

<div class="container">
	<table id="productList" class="productList">
		<thead>
			<tr>
				<th colspan="2">상품정보</th>
				<th>판매가</th>
				<th>수량</th>
				<th>합계</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${cartList }">
			<tr>
				<td><img alt="" src="">경로확인 후 값 넣기</td>
				<td>${list.store_name}<br>${list.product_name}</td><!-- 가게명, 상품명 -->
				<td>${list.price }</td>
				<td>
					<input type="number" id="count" name="count" value="${list.count }">
				</td>
				<c:set var="total" value="${list.price * list.count }"/>
				<td>${total}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<form id="orderFrm" name="orderFrm">
	<table class="orderFormTbl">
		<tr>
			<th>받으시는분</th>
			<td><input type="text" id="rcv_name" name="rcv_name"></td>
		</tr>
		<tr>
			<th>전화번호</th><!-- 제출 안되게 제이쿼리 수정 -->
			<td>
				<select id="rcv_phone1" name="rcv_phone1">
				    <option value="010">010</option>
		            <option value="011">011</option>
		            <option value="016">016</option>
		            <option value="017">017</option>
		            <option value="018">018</option>
		            <option value="019">019</option>
				</select>
					-
				<input type="text" id="rcv_phone2" name="rcv_phone2" size="4">-
				<input type="text" id="rcv_phone3" name="rcv_phone3" size="4">
			</td>
		</tr>
		<tr>
			<th>주소</th><!-- 제출 안되게 제이쿼리 수정 -->
			<td>
				<input type="text" id="rcv_zip" name="rcv_zip" placeholder="우편번호"><br>
				<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" id="rcv_addr1" name="rcv_addr1" placeholder="주소"><br>
				<input type="text" id="addr2" name="addr2" placeholder="상세주소"><br>
				<input type="text" id="addr3" name="addr3" placeholder="참고항목"><br>
			</td>
		</tr>
		<tr>
			<th>배송메세지</th>
			<td><textarea rows="3" cols="20" id="rcv_msg" name="rcv_msg"></textarea></td>
		</tr>
		<tr>
			<th>적립금</th>
			<td>
				<input type="number" id="reduce_point" name="reduce_point" max="${point}">
				&nbsp;&nbsp;&nbsp;사용가능 적립금:<span id="point">${point}</span>
			</td>
		</tr>
		<tr>
			<th>결제방법</th>
			<td>
				<input type="radio" id="pay_method" name="pay_method" value="card">카드결제
				<input type="radio" id="pay_method" name="pay_method" value="pay">ㅇㅅㅇ?
			</td>
		</tr>
	</table>
	
	<!-- 아이디, 묶음번호, 가게코드, 배달비, 총 결제금액, 적립 포인트, 예약여부..?  -->
	<%-- <input type="hidden" id="user_id" name="user_id" value="${sessionScope.user_id }">  --%>
	<input type="hidden" id="name" name="name" value="${sessionScope.user_name }">
	<input type="hidden" id="bundle_code" name="bundle_code" value="${sessionScope.cart }">
	<input type="hidden" id="store_code" name="store_code" value="${cartList.store_code }">
	<input type="hidden" id="delivery" name="deleivery_fee">
	<input type="hidden" id="amount" name="total_price">
	<input type="hidden" id="add_point" name="add_point" value="">
	
	<!-- 합치기 -->
	<input type="hidden" id="rcv_phone" name="rcv_phone">
	<input type="hidden" id="rcv_addr2" name="rcv_addr2"><!-- 상세주소, 참고항목 합치기 -->
	
	<!-- 주문 상품 수량 -->
	<input type="hidden" id="productNum" name="productNum" value="${productNum }">	
	</form>

	
<!-- 금액 보여주기 시작 -->
<table border=1>
	<tr>
		<td>총 상품금액</td>
		<td>적립금 사용</td>
		<td>배송비</td>
		<td>결제예정금액</td>
	</tr>
	<tr>
		<td id="totalPrice"></td>
		<td id="usePoint"></td>
		<td id="deliveryFee"></td>
		<td id="payAmount"></td>
	</tr>
</table>
<!-- 금액 보여주기 끝 -->

<button id="cancelBtn">취소하기</button>
<button id="payBtn">결제하기</button>

</div>

<%@ include file="../include/footer.jsp"%>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<!-- 포트원 -->
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>

<!-- 제이쿼리 -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script>
	function sample6_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 각 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var addr = ''; // 주소 변수
						var extraAddr = ''; // 참고항목 변수

						//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
						if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
							addr = data.roadAddress;
						} else { // 사용자가 지번 주소를 선택했을 경우(J)
							addr = data.jibunAddress;
						}

						// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
						if (data.userSelectedType === 'R') {
							// 법정동명이 있을 경우 추가한다. (법정리는 제외)
							// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
							if (data.bname !== ''
									&& /[동|로|가]$/g.test(data.bname)) {
								extraAddr += data.bname;
							}
							// 건물명이 있고, 공동주택일 경우 추가한다.
							if (data.buildingName !== ''
									&& data.apartment === 'Y') {
								extraAddr += (extraAddr !== '' ? ', '
										+ data.buildingName : data.buildingName);
							}
							// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
							if (extraAddr !== '') {
								extraAddr = ' (' + extraAddr + ')';
							}
							// 조합된 참고항목을 해당 필드에 넣는다.
							document.getElementById("sample6_extraAddress").value = extraAddr;

						} else {
							document.getElementById("sample6_extraAddress").value = '';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample6_postcode').value = data.zonecode;
						document.getElementById("sample6_address").value = addr;
						// 커서를 상세주소 필드로 이동한다.
						document.getElementById("sample6_detailAddress")
								.focus();
					}
				}).open();
	}
	
	// 제이쿼리 시작
	$(document).ready(function(){
		
		// 주문 할 상품 가격의 합
		$('#totalPrice').text(function(){		
		    var totalPrice = 0;
		    $('#productList tbody tr').each(function(){
		        var price = parseInt($(this).closest('tr').find('td:eq(2)').text());
		        totalPrice += price;
		    });
		    return totalPrice;
		});

		// 사용한 적립금
		$('#usePoint').text(function(){		
		    var reducePoint = $('#reduce_point').val();
		    return reducePoint;
		});
		
		// 배송비 처리
		$('#deliveryFee').text(function(){
		    var totalPrice = parseInt($('#totalPrice').text());
		    var deliveryFee = 0;
		    if(totalPrice > 50000){ // 5만원 이상시 배달비 무료
		        deliveryFee = 0;
		    } else if(totalPrice < 50000){ // 5만원 미만 배달비 2500원 배달비는 2500원으로 고정
		        deliveryFee = 2500; 
		    }
		    return deliveryFee;
		});
		
		// 결제예정금액
		$('#payAmount').text(function(){
		    var totalPrice = parseInt($('#totalPrice').text());
		    var usePoint = parseInt($('#usePoint').text());
		    var deliveryFee = parseInt($('#deliveryFee').text());
		    var payAmount = totalPrice - usePoint + deliveryFee;
		    return payAmount;
		});

		// input 값 넣기 (수정 필요)
		$('#delivery').val($('#deliveryFee').text());
		$('#amount').val($('#payAmount').text());
		$('#rcv_phone').val(function(){
		    var phone1 = $('#rcv_phone1').val();
		    var phone2 = $('#rcv_phone2').val();
		    var phone3 = $('#rcv_phone3').val();
		    return phone1 + phone2 + phone3;
		});
		
	  	// addr2, addr3, rcv_phone1, rcv_phone2, rcv_phone3, productNum 폼 제출 막기
        $('#addr2, #addr3, #rcv_phone1, #rcv_phone2, #rcv_phone3, #productNum').change(function(){
            $('#orderFrm').off('submit').submit(function(event){
                event.preventDefault();
            });
        });
	  	
		// 주문하기
		$('#payBtn').click(function(){
			var orderInfo = $('#orderFrm').serialize();
			
			$.ajax({
				url: '/order/pay',
				type: 'POST',
				dataType: "text",
				success: function(data){
					requestPay(data)
				},
				error: function(){
					alert("오류가 발생했습니다.");
				}
			});
			
			function requestPay(data){
				var IMP = window.IMP;
				IMP.init("imp68706306");
				
				IMP.request_pay({
					pg : "html5_inicis",
					pay_method : 'card',
					merchant_uid : data,
					name : '상품명',
					amount : $('#amount').val(),
					buyer_name : $('#name').val(),
					buyer_tel : $('#rcv_phone').val(), // 없으면 오류
					buyer_addr : '',
					buyer_postcode : '',
				}, function(rsp){
					if(rsp.success){
						$.ajax({
							type: 'POST',
							url: '/order/success',
							data: JSON.stringify(orderInfo),
							contentType: 'application/json; charset=utf-8',
						});
					}else{
						alert("오류가 발생했습니다.");
						history.bakc()
					} // if문 끝
				}
				});
			}
			
		});
		
		
		
	}); // 제이쿼리 끝 
</script>