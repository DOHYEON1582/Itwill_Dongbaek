<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../include/header.jsp"%>

<script src="../../resources/js/jquery-2.1.1.js"></script>
<!-- <script src="https://code.jquery.com/jquery-latest.min.js">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->

<style>
	caption{
		color : black;
		font-size: 20px;
	}
	.productList {
		width: 1200px;
		border-collapse: collapse; /* 테이블 셀 경계를 병합하여 테두리를 단순화합니다. */
   	 	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 그림자 효과를 추가합니다. */
   	 	margin-bottom: 40px;
   	 	/* text-align: center; */
	}
	
	/* .productList td{
		border-bottom: 1px solid gray;
	} */

	.orderFormTbl {
		width: 1200px;
	    border-collapse: collapse;
	    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	    margin-bottom: 40px;
	    /* text-align: center; */
	}
	.orderFormTbl td {
		/* border: 1px solid black; */
		vertical-align: middle;
	}
	/* .orderFormTbl th {
		border: 1px solid black;
	}
	.orderFormTbl td {
		border: 1px solid black;
		height: 50px; td의 높이를 50px로 지정합니다.
	} */
	
	/* .orderFormTbl th {
		border: 1px solid black;
	} */
	
	/* .orderFormTbl td {
		border: 1px solid black;
		height: 50px; 
	}  */
	
	.amountList {
		width: 1200px;
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
	
	.amountList td:nth-child(4) {
	    border-right: none;
	}
	
	.bbbtn {
		width: 1200px;
		/* border: 1px solid black; */
		text-align: center;
		vertical-align: middle;
		margin-bottom: 80px;
	}
	
	.bbbtn button {
		background: lightyellow;
		border-style: none;
	}
	
	#payBtn {
		background: lightyellow;
		border-style: none;
	}
	
	#addrBtn {
		background-color: lightyellow;
		border-style: none;
		color: black;
		border-radius: 5px;
		
	}
	
	#payButton{
		
	}
	
	input[type="text"] {
		height:30px;
        width: 200px; /* 원하는 너비로 조정하세요 */
        border: 1px solid gray;
        border-radius: 2px;
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
	
	.img{
		width: 100%; /* 이미지를 부모 요소(.image-container)의 너비에 맞추어 줄입니다. */
        height: auto; /* 이미지의 높이를 비율에 맞게 자동으로 조절합니다. */
	}
</style>

<div class="container">
	<h5>주문상품</h5>
	<table id="productList" class="productList">
		<tbody>
			<c:forEach var="list" items="${cartList }">
			<tr>
				<td rowspan="3" width="120" height="100"><img src="${pageContext.request.contextPath}/resources/images/product/${list.img1}" alt="Product Thumbnail" class="tab-image" style="width : 100px; height : 100pxx;"></td>
				<td>${list.store_name}<br><span style="color : black; font-size:20px">${list.product_name}</span></td><!-- 가게명, 상품명 -->
			</tr>
			<%-- <tr>
				<td><fmt:formatNumber value="${list.price }" pattern="#,###"/>${list.price }</td>
			</tr> --%>
			<tr>
				<td>
					수량 : ${list.count }<!-- <input type="number" id="count" name="count" value=""> -->
				</td>
			</tr>
				 <c:set var="total" value="${list.price * list.count}"/>
        		<td class="price"><fmt:formatNumber value="${total}" pattern="#,###"/>원</td> <!-- 각 상품의 총 가격 표시 -->
			</tr>
			<tr>
				<td colspan=2><hr></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<form  id="orderFrm" name="orderFrm" action="/order/pay" method="POST">
	<!-- <form name="OrderInfoVO" action="/order/payTest" method="POST"> -->
	<h5>배송지</h5>
	<table class="orderFormTbl">
		<tr>
			<th>받으시는분</th>
			<td height=50><input type="text" id="rcv_name" name="rcv_name"></td>
		</tr>
		<tr>
			<th>전화번호</th><!-- 제출 안되게 제이쿼리 수정 -->
			<td height=50>
				<select id="rcv_phone1">
				    <option value="010">010</option>
		            <option value="011">011</option>
		            <option value="016">016</option>
		            <option value="017">017</option>
		            <option value="018">018</option>
		            <option value="019">019</option>
				</select>
					-
				<input type="text" id="rcv_phone2" size="4">-
				<input type="text" id="rcv_phone3" size="4">
			</td>
		</tr>
		<tr>
			<th>주소</th><!-- 제출 안되게 제이쿼리 수정 -->
			<td height=150>
				<input type="text" id="rcv_zip" name="rcv_zip" placeholder="우편번호">
				<input type="button" onclick="sample6_execDaumPostcode()" id="addrBtn" value="우편번호 찾기"><br>
				<input type="text" id="rcv_addr1" name="rcv_addr1" placeholder="주소"><br>
				<input type="text" id="addr2" placeholder="상세주소"><br>
				<input type="text" id="addr3" placeholder="참고항목"><br>
			</td>
		</tr>
		<tr>
			<th>배송메세지</th>
			<td height=90><textarea rows="2" cols="30" id="rcv_msg" name="rcv_msg"></textarea></td>
		</tr>
		<%-- <tr>
			<th height=50>적립금</th>
			<td>
				<input type="number" id="reduce_point" name="reduce_point" max="${point}">
				&nbsp;&nbsp;&nbsp;사용가능 적립금:<span id="point"><fmt:formatNumber value="${point}" pattern="#,###"/></span>
			</td>
		</tr> --%>
		<tr>
			<th>결제방법</th>
			<td height=50>
				<input type="radio" name="pay_method" value="card">카카오페이
				<!-- <input type="radio" name="pay_method" value="pay">ㅇㅅㅇ? -->
			</td>
		</tr>
	</table>
	<!-- 아이디, 묶음번호, 가게코드, 배달비, 총 결제금액, 적립 포인트, 예약여부..?  -->
	<input type="hidden" id="user_id" name="user_id" value="${sessionScope.userVO.user_id }"> 
	<input type="hidden" id="name" value="${sessionScope.userVO.user_name }">
	<input type="hidden" id="bundle_code" name="bundle_code" value="${sessionScope.cart }">
	<%-- <input type="text" id="store_code" name="store_code" value="${cartList.store_code }"> --%>
	<input type="hidden" id="delivery" name="deleivery_fee">
	<input type="hidden" id="amount" name="total_price">
	<input type="hidden" id="add_point" name="add_point" value="">
	
	<!-- 합치기 -->
	<input type="hidden" id="rcv_phone" name="rcv_phone">
	<input type="hidden" id="rcv_addr2" name="rcv_addr2"><!-- 상세주소, 참고항목 합치기 -->
	
	<!-- 주문 상품 수량 -->
	<input type="hidden" id="productNum" name="productNum" value="${productNum }">	
	

<!-- 0510 금액 수정 해야함 -->	
<!-- 금액 보여주기 시작 -->
<table class="amountList">
	<tr>
		<td width="300" class="amountTd1">총 상품금액</td>
		<td width="300" class="amountTd1">적립금 사용</td>
		<td width="300" class="amountTd1">배송비</td>
		<td width="300" class="amountTd1">결제예정금액</td>
	</tr>
	<tr>
		<td id="totalPrice"></td>
		<td id="usePoint"></td>
		<td id="deliveryFee"></td>
		<td id="payAmount"></td>
	</tr>
</table>
<!-- 금액 보여주기 끝 -->

<table class="bbbtn">
	<tr>
		<td height="70px">
			<button type="button" onclick="cartBack();">취소하기</button>
			<!-- <button>결제</button> -->
			<!-- <input type="submit" id="payButton" value="결제하기"> -->
			<input type="button" id="payBtn" value="결제하기">
		</td>
	</tr>
</table>

</form>



</div>

<%@ include file="../include/footer.jsp"%>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<!-- 포트원 -->
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>

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
							document.getElementById("addr3").value = extraAddr;

						} else {
							document.getElementById("addr3").value = '';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('rcv_zip').value = data.zonecode;
						document.getElementById("rcv_addr1").value = addr;
						// 커서를 상세주소 필드로 이동한다.
						document.getElementById("addr2")
								.focus();
					}
				}).open();
	}
	
	// 주문취소
	function cartBack(){
		
		location.href="/mypage/cart";
		
	}
	
	

	// 시작
	$(document).ready(function(){
		
		// 배송 주소 처리
		$('#addr2, #addr3').on('input', function() {
	        var addr2 = $('#addr2').val();
	        var addr3 = $('#addr3').val();
	        var fullAddress = addr2 + ' ' + addr3;
	        $('#rcv_addr2').val(fullAddress);
	    });
		
		// 전화번호 합치기
		$('#rcv_phone1, #rcv_phone2, #rcv_phone3').on('input', function() {
	        // Concatenate the phone numbers
	        var phone1 = $('#rcv_phone1').val();
	        var phone2 = $('#rcv_phone2').val();
	        var phone3 = $('#rcv_phone3').val();
	        var fullPhoneNumber = phone1 + phone2 + phone3;
	        // Update the rcv_phone input field
	        $('#rcv_phone').val(fullPhoneNumber);
	    });
		
		// 주문 할 상품 가격의 합
		var totalAmount = 0;

		// productList 테이블의 각 행을 순회하며 각 상품의 총 가격을 합산합니다.
		$('#productList .price').each(function() {
		    var totalText = $(this).text().trim(); // 각 상품의 총 가격 텍스트 추출
		    var totalValue = parseInt(totalText.replace(/[^0-9]/g, ''), 10); // 각 상품의 총 가격
		    if (!isNaN(totalValue)) { // 값이 숫자인지 확인
		        totalAmount += totalValue; // 총 상품 금액에 더합니다.
		    }
		});

		// 총 상품금액 칸에 합산된 값을 보여줍니다.
		$('#totalPrice').text(numberWithCommas(totalAmount) + '원');
	 
		// 사용한 적립금
		var reducePoint = parseInt($('#reduce_point').val() || 0); // 입력한 값이 없으면 0으로 처리
		$('#usePoint').text(numberWithCommas(reducePoint) + '원');

		// 배송비 처리
		var deliveryFee = totalAmount > 50000 ? 0 : 2500; // 5만원 이상일 때 배송비 무료, 그 외에는 2500원
		$('#deliveryFee').text(numberWithCommas(deliveryFee) + '원');

		// 결제예정금액 계산
		var payAmount = totalAmount - reducePoint + deliveryFee;
		$('#payAmount').text(numberWithCommas(payAmount) + '원');
		
		// 쉼표 추가 함수
	    function numberWithCommas(x) {
	        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	    }
		
	 	// payAmount의 5% 값을 add_point로 설정합니다.
		var addPoint = payAmount * 0.03; // payAmount의 5%를 계산합니다.
		$('#add_point').val(numberWithCommas(addPoint) + '원'); // 쉼표로 값을 포맷하여 설정합니다.

	 	// 대표 상품명과 추가 상품의 수 계산
	    var productNames = [];
	    $('#productList tbody tr').each(function(){
	        var productName = $(this).find('td:eq(1)').text().trim();
	        productNames.push(productName);
	    });

	    var uniqueProductNames = Array.from(new Set(productNames));
	    var representativeProductName = uniqueProductNames[0];
	    var additionalProductCount = uniqueProductNames.length - 1;

        
	 	// 상품명 표시
	    var productName = "";
	    if (additionalProductCount > 0) {
	        productName = representativeProductName + " 외 " + additionalProductCount + "개";
	    } else {
	        productName = representativeProductName;
	    }

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

		var orderCode = "";
        $('#payBtn').click(function(){
			$.ajax({
				url: '/order/orderCode',
				type: 'POST',
				/* dataType: data, */
				success: function(response){
					orderCode = response;
					requestPay(response);
				},
				error: function(){
					alert("orderCode 못 받아옴");
					alert("오류가 발생했습니다.");
				}
			});
			
        });
        
        var IMP = window.IMP;
		IMP.init("imp68706306");
		
		var orderInfo = $('#orderFrm').serialize();
		/* orderInfo.order_code = orderCode; */
		
		// 난수 생성 
		function generateRandomInteger() {
		    return Math.floor(Math.random() * 90000) + 10000;
		}
		// 5자리 난수
		var randomNumber = generateRandomInteger();
		console.log(randomNumber);

		
        function requestPay(response){
        	/* alert("requestPay1"); */
        	// IMP.request_pay(param, callback) 결제창 호출
		    IMP.request_pay({ // param
		    	pg: "kakaopay.TC0ONETIME",
		        pay_method: "card",
		        merchant_uid: randomNumber, // 한번 결제 했을 때 썼던 번호는 사용 X 항상 다르게 
		        name: '1111', // 대표 상품명 및 추가 상품 개수 표시
		        amount: payAmount,
		        buyer_name : '11111',
				buyer_tel : '01011111111', // 없으면 오류
				buyer_addr : '1111', // 주소
	            buyer_postcode : '1111' // 우편번호
	            
		        /* pg: "kakaopay.TC0ONETIME",
		        pay_method: "card",
		        merchant_uid: response,
		        name: productName, // 대표 상품명 및 추가 상품 개수 표시
		        amount: (payAmount.replace(/[^0-9]/g, '')),
		        buyer_name : $('#name').val(),
				buyer_tel : $('#rcv_phone').val(), // 없으면 오류
				buyer_addr : ($('#rcv_addr1').val() + ' ' + $('#rcv_addr2').val()), // 주소
	            buyer_postcode : $('#rcv_zip').val() // 우편번호 */
	            
		    }, function (rsp) { // callback
		        if (rsp.success) {
		        	
		        	location.href="/order/success?orderCode="+orderCode;
		           
		        	
		        } else {
		        	/* alert("requestPay6"); */
		            // 결제 실패 시 로직
		        	alert("결제가 취소되었습니다.");
		        }
		    });
        };
        	
	}); // 제이쿼리 끝 
	
</script>