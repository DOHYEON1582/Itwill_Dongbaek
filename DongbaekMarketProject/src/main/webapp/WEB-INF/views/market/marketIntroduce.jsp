<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include/header.jsp"%>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<style>
	.bxslider{
 		display: inline-block;
        margin-right: 20px; /* 이미지 슬라이더 오른쪽에 공간 추가 */
	}
    #sijang_text {
        display: inline-block;
        vertical-align: top;
        margin-left: 50px; /* 시장 정보 왼쪽에 공간 추가 */
        font-family: 'Gowun Dodum', sans-serif;
        font-size: 25px;
    }
    #sijamg_top {
        display: flex;
        justify-content: center; /* 페이지 가운데 정렬 */
        align-items: flex-start; /* 시장 정보 컨테이너를 상단에 정렬 */
    }
    .marketTable {
        border-collapse: separate;
        border-spacing: 0 20px; /* 상하로 30px 간격 지정 */
        margin-top: 10px; /* 테이블 위쪽에 20px 간격 추가 */
    }
    .marketTable th, td {
        padding-left: 8px; /* 셀 내부 왼쪽 간격 지정 */
    }
	.slider-container {
	    display: flex;
	    justify-content: center;
	}
</style>
<script type="text/javascript">
function topFunction() {
    document.body.scrollTop = 0; // Safari 지원
    document.documentElement.scrollTop = 0; // Chrome, Firefox, IE 및 Opera 지원
}

    $(document).ready(function(){
        $('.bxslider').bxSlider({
            infiniteLoop: false,
            hideControlOnEnd: true,
            slideWidth: 500
        });
        
    // 스크롤 위치가 일정 위치 이상인 경우 버튼을 표시
    window.onscroll = function() {
        if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
            document.getElementById("scrollToTopBtn").style.display = "block";
        } else {
            document.getElementById("scrollToTopBtn").style.display = "none";
        }
    };
        
});
</script>

<body>
<!-- 시장정보 -->
<section class="py-2 mb-1" style="background: url(${pageContext.request.contextPath}/resources/images/background-pattern.jpg);">
<div id="sijamg_top">
	<div class="bxslider" style="display: inline-block;">
		<div>
			<img src="${pageContext.request.contextPath }/resources/images/gupo.png" />
		</div>
		<div>
			<img src="${pageContext.request.contextPath }/resources/images/gupo2.png" />
		</div>
		<div>
			<img src="${pageContext.request.contextPath }/resources/images/gupo3.png" />
		</div>
	</div>
	<div id="sijang_text" style="display: inline-block; vertical-align: top;">
		<c:forEach var="marketList" items="${marketList }">
		<div class="tit">
			<div class="sij_name" style="font-size: 30px; font-weight: bold;">▶ ${marketList.name }</div>
			<div class="sij_sub_name" style="font-size: 25px;">${marketList.explain }</div>
		</div>
		<table class="marketTable">
			<tbody>
				<tr>
					<th>주소</th>
					<td style="padding-left: 10px;">${marketList.market_addr1 }</td>
				</tr>
				
				<tr>
					<th>전화</th>
					<td style="padding-left: 10px;">${marketList.phone }</td>
				</tr>

				<tr>
					<th>개설주기(장날)</th>
					<td style="padding-left: 10px;">${marketList.build }</td>
				</tr>

				<tr>
					<th>교통</th>
					<td style="padding-left: 10px;">${marketList.traffic }</td>
				</tr>
			</tbody>
		</table>
		</c:forEach>
	</div>
</div>
</section>


<%@ include file="../include/footer.jsp"%>