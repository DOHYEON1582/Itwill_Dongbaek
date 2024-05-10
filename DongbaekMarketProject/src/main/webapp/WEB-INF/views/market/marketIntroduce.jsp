<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include/header.jsp"%>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<style>
	#sijamg_top {
	    display: flex;
	    justify-content: center; /* Horizontally center align */
	    align-items: flex-start; /* Vertically align to the top */
	    margin-top: 20px; /* Add some top margin */
	}
	.bxslider{
/*  		display: inline-block; */
        margin-right: 20px; /* 이미지 슬라이더 오른쪽에 공간 추가 */
	}
	.bxslider img {
		max-width: 100%;
		height: auto;
	}
    #sijang_text {
        display: inline-block;
        vertical-align: top;
        margin-left: 50px; /* 시장 정보 왼쪽에 공간 추가 */
        font-family: 'Gowun Dodum', sans-serif;
        font-size: 25px;
        line-height: 1.5;
    }
    .marketTable {
        border-collapse: separate;
        border-spacing: 0 20px; /* 상하로 30px 간격 지정 */
        margin-top: 10px; /* 테이블 위쪽에 20px 간격 추가 */
    }
    .market-item {
	    display: flex;
	    align-items: flex-start; /* 설명을 이미지 상단에 정렬합니다 */
	}
	.description {
	    font-family: 'Gowun Dodum', sans-serif;
	    font-size: 25px;
	    margin-left: 20px;
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
	<div id="sijang_text" style="display: inline-block; vertical-align: top;">
		<c:forEach var="marketList" items="${marketList }">
		<div class="market-item">
			<div class="bxslider" >
				<div>
					<img src="${pageContext.request.contextPath }/resources/images/market/${marketList.img1}" />
				</div>
				<div>
					<img src="${pageContext.request.contextPath }/resources/images/market/${marketList.img2}" />
				</div>
				<div>
					<img src="${pageContext.request.contextPath }/resources/images/market/${marketList.img3}" />
				</div>
			</div>
			<div class="description">
				<div class="tit">
					<div class="sij_name" style="font-size: 30px; font-weight: bold;">▶ ${marketList.name }
						<button style="font-size: 15px" onclick="location.href='http://localhost:8088/market/marketMain?market_code=${marketList.market_code}'">시장가기</button>
					</div>
					<div class="sij_sub_name" style="font-size: 25px; white-space: pre-wrap;">${marketList.explain }</div>
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
			</div>
		</div>
		</c:forEach>
	</div>
</div>
</section>


<%@ include file="../include/footer.jsp"%>