<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../include/header.jsp"%>

<style>
h1 {
	text-align: center;
	margin-bottom: 30px;
}

#mtable-container {
	background-color: #fff;
	border-radius: 8px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	overflow: hidden;
	width: 600px;
	margin: auto;
}

.mtable {
	width: 100%;
	border-collapse: collapse;
}

.mtable th, td {
	text-align: center;
}

.data div {
	display: none;
	padding: 10px;
}

.data div p {
	margin: 0;
	padding: 5px 0;
}

.hidden {
	display: none;
}
</style>
<body>
	<h1>제철 음식</h1>
	<div id="mtable-container">
		<table class="mtable">
			<tr>
				<th>1월</th>
				<th>2월</th>
				<th>3월</th>
				<th>4월</th>
				<th>5월</th>
				<th>6월</th>
				<th>7월</th>
				<th>8월</th>
				<th>9월</th>
				<th>10월</th>
				<th>11월</th>
				<th>12월</th>
			</tr>
			<tr>
				<td class="data">
					<div class="jan">
						<p>우엉(1~3月)</p>
						<p>딸기(1~5月)</p>
						<p>꼬막(11~3月)</p>
						<p>더덕(1~4月)</p>
						<p>삼치10~2月)</p>
						<p>명태(12~1月)</p>
						<p>아귀(12~2月)</p>
						<p>도미(11~3月)</p>
						<p>과메기(11~1月)</p>
						<p>한라봉(12~3月)</p>
					</div>
				</td>
				<td class="data">
					<div class="feb hidden">
						<p>우엉(1~3月)</p>
						<p>바지락(2~4月)</p>
						<p>딸기(1~5月)</p>
						<p>꼬막(11~3月)</p>
						<p>더덕(1~4月)</p>
						<p>삼치10~2月)</p>
						<p>아귀(12~2月)</p>
						<p>도미(11~3月)</p>
						<p>한라봉(12~3月)</p>
					</div>
				</td>
				<td class="data">
					<div class="mar hidden">
						<p>달래(3~4月)</p>
						<p>우엉(1~3月)</p>
						<p>냉이(3~4月)</p>
						<p>바지락(2~4月)</p>
						<p>딸기(1~5月)</p>
						<p>꼬막(11~3月)</p>
						<p>더덕(1~4月)</p>
						<p>쑥(3月)</p>
						<p>도미(11~3月)</p>
					</div>
				</td>
				<td class="data">
					<div class="apr hidden">
						<p>달래(3~4月)</p>
						<p>냉이(3~4月)</p>
						<p>바지락(2~4月)</p>
						<p>딸기(1~5月)</p>
						<p>두릅(4~5月)</p>
						<p>주꾸미(4月)</p>
						<p>취나물(3~5月)</p>
						<p>키조개(4~5月)</p>
						<p>참다랑어(4~6月)</p>
					</div>
				</td>
				<td class="data">
					<div class="may hidden">
						<p>딸기(1~5月)</p>
						<p>두릅(4~5月)</p>
						<p>취나물(3~5月)</p>
						<p>장어(5~6月)</p>
						<p>매실(5~6月)</p>
						<p>키조개(4~5月)</p>
						<p>멍개(5月)</p>
						<p>다슬기(5~6月)</p>
						<p>참다랑어(4~6月)</p>
						<p>소라(3~6月)</p>
					</div>
				</td>
				<td class="data">
					<div class="jun hidden">
						<p>감자(6~9月)</p>
						<p>장어(5~6月)</p>
						<p>매실(5~6月)</p>
						<p>참외(6~8月)</p>
						<p>다슬기(5~6月)</p>
						<p>참다랑어(4~6月)</p>
						<p>복분자(6~8月)</p>
						<p>소라(3~6月)</p>
					</div>
				</td>
				<td class="data">
					<div class="jul hidden">
						<p>옥수수(7~9月)</p>
						<p>토마토(7~9月)</p>
						<p>감자(6~9月)</p>
						<p>블루베리(7~9月)</p>
						<p>도라지(7~8月)</p>
						<p>수박(7~8月)</p>
						<p>복숭아(7~8月)</p>
						<p>갈치(7~10月)</p>
						<p>참외(6~8月)</p>
						<p>복분자(6~8月)</p>
					</div>
				</td>
				<td class="data">
					<div class="aug hidden">
						<p>옥수수(7~9月)</p>
						<p>토마토(7~9月)</p>
						<p>전복(8~10月)</p>
						<p>감자(6~9月)</p>
						<p>고구마(8~10月)</p>
						<p>블루베리(7~9月)</p>
						<p>포도(8月)</p>
						<p>도라지(7~8月)</p>
						<p>수박(7~8月)</p>
						<p>복숭아(7~8月)</p>
					</div>
				</td>
				<td class="data">
					<div class="sep hidden">
						<p>옥수수(7~9月)</p>
						<p>굴(9~12月)</p>
						<p>게(9~10月)</p>
						<p>토마토(7~9月)</p>
						<p>고등어(9~11月)</p>
						<p>전복(8~10月)</p>
						<p>감자(6~9月)</p>
						<p>고구마(8~10月)</p>
						<p>대하(9~12月)</p>
						<p>블루베리(7~9月)</p>
					</div>
				</td>
				<td class="data">
					<div class="oct hidden">
						<p>굴(9~12月)</p>
						<p>사과(10~12月)</p>
						<p>게(9~10月)</p>
						<p>홍합(10~12月)</p>
						<p>고등어(9~11月)</p>
						<p>전복(8~10月)</p>
						<p>고구마(8~10月)</p>
						<p>무(10~12月)</p>
						<p>꽁치(10~11月)</p>
						<p>늙은호박(10~12月)</p>
					</div>
				</td>
				<td class="data">
					<div class="nov hidden">
						<p>굴(9~12月)</p>
						<p>사과(10~12月)</p>
						<p>홍합(10~12月)</p>
						<p>꼬막(11~3月)</p>
						<p>배추(11~12月)</p>
						<p>고구마(8~10月)</p>
						<p>무(10~12月)</p>
						<p>꽁치(10~11月)</p>
						<p>늙은호박(10~12月)</p>
						<p>대하(9~12月)</p>
					</div>
				</td>
				<td class="data">
					<div class="dec hidden">
						<p>굴(9~12月)</p>
						<p>사과(10~12月)</p>
						<p>홍합(10~12月)</p>
						<p>꼬막(11~3月)</p>
						<p>배추(11~12月)</p>
						<p>무(10~12月)</p>
						<p>늙은호박(10~12月)</p>
						<p>대하(9~12月)</p>
						<p>삼치(10~2月)</p>
						<p>유자(11~12月)</p>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<script>
	document.addEventListener('DOMContentLoaded', function() {
		var months = document.querySelectorAll('.mtable th');
		var dataCells = document.querySelectorAll('.mtable .data div');
		dataCells[0].style.display = 'block';
		months.forEach(function(month, index) {
			month.addEventListener('click', function() {
				// 모든 데이터를 숨김
				dataCells.forEach(function(cell) {
					cell.style.display = 'none';
				});
				// 클릭한 월에 해당하는 데이터를 보여줌
				dataCells[index].style.display = 'block';
			});
		});
	});
	</script>

</body>

<%@include file="../include/footer.jsp"%>