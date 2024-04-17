<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet"><script>
	$(document).ready(function(){
		$('.bxslider').bxSlider({
			  infiniteLoop: false,
			  hideControlOnEnd: true,
			  slideWidth: 500
			});
		
	  $(document).ready(function(){
		    $('.slider5').bxSlider({
		        slideWidth: 200,
		        minSlides: 5,
		        maxSlides: 5,
		        moveSlides: 5,
		        slideMargin: 10,
		        adaptiveHeight: true, // 높이를 자동 조정하여 일관된 높이 유지
		        pager: false
		    });
		  });
	});
</script>
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
        font-size: 20px;
    }
    #sijamg_top {
        display: flex;
        justify-content: center; /* 페이지 가운데 정렬 */
        align-items: flex-start; /* 시장 정보 컨테이너를 상단에 정렬 */
    }
    table {
        border-collapse: separate;
        border-spacing: 0 50px; /* 상하로 30px 간격 지정 */
        margin-top: 20px; /* 테이블 위쪽에 20px 간격 추가 */
    }
    th, td {
        padding-left: 10px; /* 셀 내부 왼쪽 간격 지정 */
    }
	.slider-container {
	    display: flex;
	    justify-content: center;
	}
</style>
<!-- 시장정보 -->
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
		<div class="tit">
			<div class="sij_name" style="font-size: 30px; font-weight: bold;">▶ ${marketList[0].name }</div>
			<div class="sij_sub_name" style="font-size: 25px;">${marketList[0].explain }</div>
		</div>
		<table>
			<tbody>
				<tr>
					<th>주소</th>
					<td style="padding-left: 10px;">${marketList[0].market_addr1 }</td>
				</tr>
				
				<tr>
					<th>전화</th>
					<td style="padding-left: 10px;">${marketList[0].phone }</td>
				</tr>

				<tr>
					<th>개설주기(장날)</th>
					<td style="padding-left: 10px;">${marketList[0].build }</td>
				</tr>

				<tr>
					<th>교통</th>
					<td style="padding-left: 10px;">${marketList[0].traffic }</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

	<div class="slider-container">
		<div class="slider5">
		<c:forEach var="storeList" items="${storeList.name} }">
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar1"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar2"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar3"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar4"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar5"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar6"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar7"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar8"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar9"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar10"></div>
	  	</c:forEach>
	  </div>
	</div>
<h2>marketMain.jsp</h2>

${marketList }
<%@ include file="../include/footer.jsp"%>
