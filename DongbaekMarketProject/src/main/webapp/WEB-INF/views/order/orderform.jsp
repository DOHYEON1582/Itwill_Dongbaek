<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../include/header.jsp"%>

<style>
	.oderFormTbl {
		width: 1300px;
	}
	
	.oderFormTbl td {
		border: 1px solid black;
	}
</style>

<div class="container">
	<table id="" class="">
		
	</table>
	
	<table class="oderFormTbl">
		<tr>
			<td>받으시는분</td>
			<td><input type="text" id="" name=""></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td>
				<select>
				    <option value="010">010</option>
		            <option value="011">011</option>
		            <option value="016">016</option>
		            <option value="017">017</option>
		            <option value="018">018</option>
		            <option value="019">019</option>
				</select>
					-
				<input type="text" id="" name="" size="4">-
				<input type="text" id="" name="" size="4">
			</td>
		</tr>
		<tr>
			<td>주소</td>
			<td>
				<input type="text" id="sample6_postcode" name="zipcode" placeholder="우편번호"><br>
				<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" id="sample6_address" name="addr1" placeholder="주소"><br>
				<input type="text" id="sample6_detailAddress" name="addr2" placeholder="상세주소"><br>
				<input type="text" id="sample6_extraAddress" name="addr3" placeholder="참고항목"><br>
			</td>
		</tr>
		<tr>
			<td>배송메세지</td>
			<td><textarea rows="20" cols="3"></textarea></td>
		</tr>
		<tr>
			<td>적립금</td>
			<td>
				<input type="number" id="" nmae="reduce_point" value="">
				&nbsp;&nbsp;&nbsp;사용가능 적립금:<span id="point"></span>
			</td>
		</tr>
		<tr>
			<td>결제방법</td>
			<td>
				<input type="radio" id="" name="pay_method" value="card">카드결제
				<input type="radio" id="" name="pay_method" value="pay">
			</td>
		</tr>
	</table>
	
	<!-- 아이디, 묶음번호, 배달비, 총 결제금액, 적립 포인트, 예약여부..?  -->
	<input type="hidden" id="" name="" value="${sessionScope.(아이디세션) }"> 
	<input type="hidden" id="" name="" value="">
	<input type="hidden" id="" name="" value="">
	<input type="hidden" id="" name="" value="">
	<input type="hidden" id="" name="" value="">
	<input type="hidden" id="" name="" value="">
	
	
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
	
	$(document).ready(function(){
		
		$('#payBtn').click(function(){
			$.ajax({
				url: '/order/pay',
				type: 'POST',
				data: {"checkList":checkList},
				success: function(data){
					alert("삭제되었습니다.");
					// 새로고침 해야하나... 
				},
				error: function(){
					alert("오류가 발생했습니다.");
				}
			});
		});
		
	}); // 제이쿼리 끝 
</script>