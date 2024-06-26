<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<title>동백마켓</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="author" content="">
<meta name="keywords" content="">
<meta name="description" content="">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="resources/css/vendor.css">
<link rel="stylesheet" type="text/css" href="style.css">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;700&family=Open+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Hahmlet&display=swap" rel="stylesheet">

</head>
<style>
/* 노멀라이즈 시작 */
body, ul, li {
  margin: 0;
  padding: 0;
  list-style: none;   /* 해당 태그의 list-style을 none으로 하는 것으로 ●을 제거한다 */
}

a{
	text-decoration: none;
}
/* 2차 이상의 메뉴를 숨기기 */
.side-bar > ul ul {
  display: none;
}

/* 사이트의 높이를 5000px로 만들어 스크롤 생성 */
body {
  height: 5000px;
}

/* 사이드바의 너비와 높이를 변수를 통해 통제 */
:root {
  --side-bar-width: 270px;
  --side-bar-height: 90vh;
}

.side-bar {
  position: fixed;    /* 스크롤을 따라오도록 지정 */
  background-color: #E7E7E7;
  width: var(--side-bar-width);
  min-height: var(-side-bar-height);   /* 사이드바의 높이를 전체 화면 높이의 90%로 지정 */
  margin-top: calc((100vh - var(-side-bar-height)) / 2);    /* 사이드바 위와 아래의 마진을 동일하게 지정 */
}

/* 모든 메뉴의 a에 속성값 부여 */
.side-bar ul > li > a {
  display: block;
  color: #000;
  font-size: 1.4rem;
  font-weight: bold;
  padding-top: 20px;
  padding-bottom: 20px;
  padding-left: 50px;
}
/* 자식의 position이 absolute일 때 자식을 영역 안에 가두어 준다 */
.side-bar > ul > li {
  position: relative;
}

/* 모든 메뉴가 마우스 인식 시 반응 */
.side-bar ul > li:hover > a {
  background-color: #BBBBBB;

}

/* 1차 메뉴의 항목이 마우스 인식 시에 2차 메뉴 등장 */
.side-bar > ul > li:hover > ul {
  display: block;
  position: absolute;
  background-color: #ccc;
  top: 0;         /* 2차 메뉴의 상단을 1차 메뉴의 상단에 고정 */
  left: 100%;     /* 2차 메뉴를 1차 메뉴의 너비만큼 이동 */
  width: 100%;    /* 1차 메뉴의 너비를 상속 */
}
/* 사이드바 너비의 80%만큼 왼쪽으로 이동 */
.side-bar {
  border-radius: 20px;
  transform: translate(calc(var(--side-bar-width) * -0.8), 0);  /* X축 이동, Y축 고정 */
  transition: .5s;
}

/* 마우스 인식 시 원래의 위치로 이동 */
.side-bar:hover {
  transform: translate(-20px, 0);   /* 둥근 모서리의 너비만큼 X축 이동, Y축 고정 */
}
/* 출처: https://me-in-journey.com/entry/HTMLCSS-사이드바-메뉴-만들기-나타나는-2차-메뉴-만들기-feat-transform-translate [내 코딩 여정:티스토리] */


.mylogo {
	width: 200px;
	height: 70px;
}

.footer-menu {
	width: 200px;
	height: 110px;
	font-family: 'Hahmlet', sans-serif; /* 햄릿(Hahmlet) 글꼴 적용 */
	font-size: 15px;
	padding: 15px;
}

#r {
	height: 100px;
	margin: 0 auto;
}

.c {
	margin: 10px 100px;
}
.container-fluid2 {
	padding-left: 100px;
	padding-right: auto;
}
.search1{
	margin-left: 10px;
}
.search-bar input[type="text"] {
	border: none;
	border-radius: 20px;
	padding: 10px auto;
}
.search-bar{
	padding: 10px auto;
}
.main-menu .menu-list .nav-item {
	margin-right: 15px;
}

@media ( max-width : 767px) {
	.main-menu .menu-list .nav-item:last-child {
		margin-right: 0;
	}
	.main-menu .navbar-toggler {
		margin-right: 15px;
	}
}

.offcanvas-body, .text-center {
	font-family: 'Hahmlet', sans-serif; /* 햄릿(Hahmlet) 글꼴 적용 */
	font-size: 20px;
}

.main-logo{
	width: 300px;
	height: 100px;
	font-size: 20px;
	padding-left: 70px; /* 왼쪽 여백 추가 */
}
.mylogo2 {
	width: 300px;
	height: 100px;
}

.login, .join, .service {
	text-decoration-line: none;
	font-family: 'Hahmlet', sans-serif; /* 햄릿(Hahmlet) 글꼴 적용 */
	color: #000;
}
#ct{
	padding: 0 auto;
	margin: 0 auto;
}

</style>

<body>

	<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
      <defs>
        <symbol xmlns="http://www.w3.org/2000/svg" id="link" viewBox="0 0 24 24">
          <path fill="currentColor" d="M12 19a1 1 0 1 0-1-1a1 1 0 0 0 1 1Zm5 0a1 1 0 1 0-1-1a1 1 0 0 0 1 1Zm0-4a1 1 0 1 0-1-1a1 1 0 0 0 1 1Zm-5 0a1 1 0 1 0-1-1a1 1 0 0 0 1 1Zm7-12h-1V2a1 1 0 0 0-2 0v1H8V2a1 1 0 0 0-2 0v1H5a3 3 0 0 0-3 3v14a3 3 0 0 0 3 3h14a3 3 0 0 0 3-3V6a3 3 0 0 0-3-3Zm1 17a1 1 0 0 1-1 1H5a1 1 0 0 1-1-1v-9h16Zm0-11H4V6a1 1 0 0 1 1-1h1v1a1 1 0 0 0 2 0V5h8v1a1 1 0 0 0 2 0V5h1a1 1 0 0 1 1 1ZM7 15a1 1 0 1 0-1-1a1 1 0 0 0 1 1Zm0 4a1 1 0 1 0-1-1a1 1 0 0 0 1 1Z" />
        </symbol>
        <symbol xmlns="http://www.w3.org/2000/svg" id="arrow-right" viewBox="0 0 24 24">
          <path fill="currentColor" d="M17.92 11.62a1 1 0 0 0-.21-.33l-5-5a1 1 0 0 0-1.42 1.42l3.3 3.29H7a1 1 0 0 0 0 2h7.59l-3.3 3.29a1 1 0 0 0 0 1.42a1 1 0 0 0 1.42 0l5-5a1 1 0 0 0 .21-.33a1 1 0 0 0 0-.76Z" />
        </symbol>
        <symbol xmlns="http://www.w3.org/2000/svg" id="category" viewBox="0 0 24 24">
          <path fill="currentColor" d="M19 5.5h-6.28l-.32-1a3 3 0 0 0-2.84-2H5a3 3 0 0 0-3 3v13a3 3 0 0 0 3 3h14a3 3 0 0 0 3-3v-10a3 3 0 0 0-3-3Zm1 13a1 1 0 0 1-1 1H5a1 1 0 0 1-1-1v-13a1 1 0 0 1 1-1h4.56a1 1 0 0 1 .95.68l.54 1.64a1 1 0 0 0 .95.68h7a1 1 0 0 1 1 1Z" />
        </symbol>
        <symbol xmlns="http://www.w3.org/2000/svg" id="calendar" viewBox="0 0 24 24">
          <path fill="currentColor" d="M19 4h-2V3a1 1 0 0 0-2 0v1H9V3a1 1 0 0 0-2 0v1H5a3 3 0 0 0-3 3v12a3 3 0 0 0 3 3h14a3 3 0 0 0 3-3V7a3 3 0 0 0-3-3Zm1 15a1 1 0 0 1-1 1H5a1 1 0 0 1-1-1v-7h16Zm0-9H4V7a1 1 0 0 1 1-1h2v1a1 1 0 0 0 2 0V6h6v1a1 1 0 0 0 2 0V6h2a1 1 0 0 1 1 1Z" />
        </symbol>
        <symbol xmlns="http://www.w3.org/2000/svg" id="heart" viewBox="0 0 24 24">
          <path fill="currentColor" d="M20.16 4.61A6.27 6.27 0 0 0 12 4a6.27 6.27 0 0 0-8.16 9.48l7.45 7.45a1 1 0 0 0 1.42 0l7.45-7.45a6.27 6.27 0 0 0 0-8.87Zm-1.41 7.46L12 18.81l-6.75-6.74a4.28 4.28 0 0 1 3-7.3a4.25 4.25 0 0 1 3 1.25a1 1 0 0 0 1.42 0a4.27 4.27 0 0 1 6 6.05Z" />
        </symbol>
        <symbol xmlns="http://www.w3.org/2000/svg" id="plus" viewBox="0 0 24 24">
          <path fill="currentColor" d="M19 11h-6V5a1 1 0 0 0-2 0v6H5a1 1 0 0 0 0 2h6v6a1 1 0 0 0 2 0v-6h6a1 1 0 0 0 0-2Z" />
        </symbol>
        <symbol xmlns="http://www.w3.org/2000/svg" id="minus" viewBox="0 0 24 24">
          <path fill="currentColor" d="M19 11H5a1 1 0 0 0 0 2h14a1 1 0 0 0 0-2Z" />
        </symbol>
        <symbol xmlns="http://www.w3.org/2000/svg" id="cart" viewBox="0 0 24 24">
          <path fill="currentColor" d="M8.5 19a1.5 1.5 0 1 0 1.5 1.5A1.5 1.5 0 0 0 8.5 19ZM19 16H7a1 1 0 0 1 0-2h8.491a3.013 3.013 0 0 0 2.885-2.176l1.585-5.55A1 1 0 0 0 19 5H6.74a3.007 3.007 0 0 0-2.82-2H3a1 1 0 0 0 0 2h.921a1.005 1.005 0 0 1 .962.725l.155.545v.005l1.641 5.742A3 3 0 0 0 7 18h12a1 1 0 0 0 0-2Zm-1.326-9l-1.22 4.274a1.005 1.005 0 0 1-.963.726H8.754l-.255-.892L7.326 7ZM16.5 19a1.5 1.5 0 1 0 1.5 1.5a1.5 1.5 0 0 0-1.5-1.5Z" />
        </symbol>
        <symbol xmlns="http://www.w3.org/2000/svg" id="check" viewBox="0 0 24 24">
          <path fill="currentColor" d="M18.71 7.21a1 1 0 0 0-1.42 0l-7.45 7.46l-3.13-3.14A1 1 0 1 0 5.29 13l3.84 3.84a1 1 0 0 0 1.42 0l8.16-8.16a1 1 0 0 0 0-1.47Z" />
        </symbol>
        <symbol xmlns="http://www.w3.org/2000/svg" id="trash" viewBox="0 0 24 24">
          <path fill="currentColor" d="M10 18a1 1 0 0 0 1-1v-6a1 1 0 0 0-2 0v6a1 1 0 0 0 1 1ZM20 6h-4V5a3 3 0 0 0-3-3h-2a3 3 0 0 0-3 3v1H4a1 1 0 0 0 0 2h1v11a3 3 0 0 0 3 3h8a3 3 0 0 0 3-3V8h1a1 1 0 0 0 0-2ZM10 5a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v1h-4Zm7 14a1 1 0 0 1-1 1H8a1 1 0 0 1-1-1V8h10Zm-3-1a1 1 0 0 0 1-1v-6a1 1 0 0 0-2 0v6a1 1 0 0 0 1 1Z" />
        </symbol>
        <symbol xmlns="http://www.w3.org/2000/svg" id="star-outline" viewBox="0 0 15 15">
          <path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" d="M7.5 9.804L5.337 11l.413-2.533L4 6.674l2.418-.37L7.5 4l1.082 2.304l2.418.37l-1.75 1.793L9.663 11L7.5 9.804Z" />
        </symbol>
        <symbol xmlns="http://www.w3.org/2000/svg" id="star-solid" viewBox="0 0 15 15">
          <path fill="currentColor" d="M7.953 3.788a.5.5 0 0 0-.906 0L6.08 5.85l-2.154.33a.5.5 0 0 0-.283.843l1.574 1.613l-.373 2.284a.5.5 0 0 0 .736.518l1.92-1.063l1.921 1.063a.5.5 0 0 0 .736-.519l-.373-2.283l1.574-1.613a.5.5 0 0 0-.283-.844L8.921 5.85l-.968-2.062Z" />
        </symbol>
        <symbol xmlns="http://www.w3.org/2000/svg" id="search" viewBox="0 0 24 24">
          <path fill="currentColor" d="M21.71 20.29L18 16.61A9 9 0 1 0 16.61 18l3.68 3.68a1 1 0 0 0 1.42 0a1 1 0 0 0 0-1.39ZM11 18a7 7 0 1 1 7-7a7 7 0 0 1-7 7Z" />
        </symbol>
        <symbol xmlns="http://www.w3.org/2000/svg" id="user" viewBox="0 0 24 24">
          <path fill="currentColor" d="M15.71 12.71a6 6 0 1 0-7.42 0a10 10 0 0 0-6.22 8.18a1 1 0 0 0 2 .22a8 8 0 0 1 15.9 0a1 1 0 0 0 1 .89h.11a1 1 0 0 0 .88-1.1a10 10 0 0 0-6.25-8.19ZM12 12a4 4 0 1 1 4-4a4 4 0 0 1-4 4Z" />
        </symbol>
        <symbol xmlns="http://www.w3.org/2000/svg" id="close" viewBox="0 0 15 15">
          <path fill="currentColor" d="M7.953 3.788a.5.5 0 0 0-.906 0L6.08 5.85l-2.154.33a.5.5 0 0 0-.283.843l1.574 1.613l-.373 2.284a.5.5 0 0 0 .736.518l1.92-1.063l1.921 1.063a.5.5 0 0 0 .736-.519l-.373-2.283l1.574-1.613a.5.5 0 0 0-.283-.844L8.921 5.85l-.968-2.062Z" />
        </symbol>
      </defs>
    </svg>

	<div class="preloader-wrapper">
		<div class="preloader"></div>
	</div>

	

	<div class="offcanvas offcanvas-end" data-bs-scroll="true" tabindex="-1" id="offcanvasSearch" aria-labelledby="Search">
		<div class="offcanvas-header justify-content-center">
			<button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
		</div>
		<div class="offcanvas-body">
			<div class="order-md-last">
				<h4 class="d-flex justify-content-between align-items-center mb-3">
					<span class="text-primary">Search</span>
				</h4>
				<form role="search" action="index.html" method="get" class="d-flex mt-3 gap-0">
					<input class="form-control rounded-start rounded-0 bg-light" type="email" placeholder="What are you looking for?" aria-label="What are you looking for?">
					<button class="btn btn-dark rounded-end rounded-0" type="submit">Search</button>
				</form>
			</div>
		</div>
	</div>

	<div class="c">
	<header>
		<div class="container-fluid">
			<div class="row py-3 border-bottom">
				<div class="col-sm-4 col-lg-3 text-center text-sm-start">
					<div class="main-logo">
						<a href="/seller/sellermain"> <img src="${pageContext.request.contextPath }/resources/images/logo2.png" alt="logo" class="mylogo2">
						</a>
					</div>
				</div>

				<div class="col-sm-4 offset-sm-2 offset-md-0 col-lg-5 d-none d-lg-block" class="search1">
					<div>
<!-- 					<div class="search-bar row bg-light p-2 my-4 rounded-4" > -->
						<div class="col-9 col-md-8 align-self-center">
<!-- 							<form id="search-form" class="text-center" action="search.html" method="post"> -->
<!-- 								<input type="text" class="form-control border-0 bg-transparent" placeholder="찾고 싶은 음식, 시장을 검색해보세요!" /> -->
<!-- 							</form> -->
						</div>
						<div class="col-3 col-md-4 align-self-center text-end">
<!-- 							<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"> -->
<!--                					<path fill="currentColor" d="M21.71 20.29L18 16.61A9 9 0 1 0 16.61 18l3.68 3.68a1 1 0 0 0 1.42 0a1 1 0 0 0 0-1.39ZM11 18a7 7 0 1 1 7-7a7 7 0 0 1-7 7Z" /> -->
<!-- 							</svg> -->
						</div>
					</div>
				</div>
				<div class="col-sm-8 col-lg-3 d-flex justify-content-end gap-5 align-items-center mt-4 mt-sm-0 justify-content-center justify-content-sm-end">
				    <div>
				    	<c:if test="${sessionScope.seller_id == null }">
					        <div class="align-items-center">
					            <a href="/seller/login" class="login">로그인</a>

					   
					        </div>
				    	</c:if>
				    	<c:if test="${sessionScope.seller_id != null }">
					        <div class="align-items-center">
					            로그인 id : ${sessionScope.seller_id.seller_id }

					            <input type="button" value="로그아웃" onclick="location.href='/seller/logout';">
					        </div>
				    	</c:if>
				    </div>
				</div>
			</div>
		</div>
	<div class="container" id="ct">
    	<div class="row py-3">
        	<div>
	            <nav class="main-menu d-flex navbar navbar-expand-lg">
	                <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar">
	                    <span class="navbar-toggler-icon"></span>
	                </button>
	                <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
	                    <div class="offcanvas-header justify-content-center">
	                        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
	                    </div>
	                   <div class="offcanvas-body">
	                        <ul class="navbar-nav justify-content-end menu-list list-unstyled d-flex gap-md-3 mb-0">
	                            <li class="nav-item active"><a href="/seller/product" class="nav-link">상품관리</a></li>
	                            <li class="nav-item dropdown"><a href="/seller/orderlist" class="nav-link">주문관리</a></li>
	                            <li class="nav-item"><a href="/seller/review" class="nav-link">리뷰관리</a></li>

	                            <li class="nav-item"><a href="/seller/productregist" class="nav-link">상품 등록</a></li>
	                        </ul>
	                    </div>
	                </div>
	            </nav>
	        </div>
	    </div>
	</div>

	</header>
	</div>
	
<!-- 사이드바 -->	
	<aside class="side-bar">
  <section class="side-bar__icon-box">
    <section class="side-bar__icon-1">
      <div></div>
      <div></div>
      <div></div>
    </section>
  </section>
  <ul>
    <li>
      <a href="/seller/product">상품 관리 </a>
      <ul>
        <li><a href="/seller/product">상품 목록</a></li>
        <li><a href="/seller/productregist">상품 등록</a></li>
      </ul>
    </li>
    <li>
      <a href="/seller/orderlist">주문 관리</a>
    </li>
    <li>
      <a href="/seller/review">리뷰 관리</a>
    </li>
  </ul>
</aside>

	
	
	



<div class="container" style="
                             
                              margin: 50px auto; /* 페이지 상단과의 간격 조절 및 페이지 가운데 정렬 */
                              max-width: 800px;"> <!-- 페이지 너비 제한 -->
    <h2 style="text-align: center;">판매자 주문 목록</h2>
    <table style="width: 100%;
                  border-collapse: collapse;">
        <thead>
            <tr>
                <th style="padding: 10px;
                           text-align: left;
                           border-bottom: 1px solid #ddd;
                           background-color: #f2f2f2;">주문코드</th>
                <th style="padding: 10px;
                           text-align: left;
                           border-bottom: 1px solid #ddd;
                           background-color: #f2f2f2;">주문자ID</th>
                <th style="padding: 10px;
                           text-align: left;
                           border-bottom: 1px solid #ddd;
                           background-color: #f2f2f2;">주문날짜</th>
            
                
            </tr>
        </thead>
        <tbody>
            <!-- 주문 목록 데이터를 반복해서 표시하는 부분 -->
           <c:forEach items="${orderList}" var="order" varStatus="loop">
    <tr>
        <td style="padding: 10px;
                   text-align: left;
                   border-bottom: 1px solid #ddd;">${order.order_code}</td>
        <td style="padding: 10px;
                   text-align: left;
                   border-bottom: 1px solid #ddd;">${order.user_id}</td>
        <td style="padding: 10px;
                   text-align: left;
                   border-bottom: 1px solid #ddd;">${order.order_date}</td>
    </tr>
</c:forEach>

        </tbody>
    </table>
</div>

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<footer style="position: fixed; bottom: 0; width: 100%; font-family: 'Roboto', sans-serif; padding-bottom: 0;">

    <div class="row justify-content-center" id="r">
        <div class="col-lg-2 col-md-4 col-sm-4" style="padding: 0;">
            <div class="footer-menu">
                <img class="mylogo" src="/resources/images/logo2.png" alt="logo">
            </div>
        </div>

        <div class="col-md-1 col-sm-4" style="padding: 0;">
            <div class="footer-menu">
                <h5 class="widget-title">소개</h5>
                <ul class="menu-list list-unstyled">
                    <li class="menu-item"><a href="#" class="nav-link">▶ 동백장터 소개</a></li>
                    <li class="menu-item"><a href="#" class="nav-link">▶ 동백팀 소개</a></li>
                </ul>
            </div>
        </div>

        <div class="col-md-1 col-sm-4" style="padding: 0;">
            <div class="footer-menu">
                <h5 class="widget-title">고객 문의</h5>
                <ul class="menu-list list-unstyled">
                    <li class="menu-item"><a href="#" class="nav-link">▶ FAQ</a></li>
                </ul>
            </div>
        </div>

        <div class="col-md-1 col-sm-4" style="padding: 0;">
            <div class="footer-menu">
                <h5 class="widget-title">개인정보</h5>
                <ul class="menu-list list-unstyled">
                    <li class="menu-item"><a href="#" class="nav-link">▶ 약관확인</a></li>
                </ul>
            </div>
        </div>

        <div class="col-lg-2 col-md-6 col-sm-4" style="padding: 0;">
            <div class="footer-menu">
                <h5 class="widget-title">Global Site</h5>
                <ul class="menu-list list-unstyled">
                    <li class="menu-item"><a href="#" class="nav-link">▶ English</a></li>
                </ul>
            </div>
        </div>
    </div>
</footer>


<script src="js/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
<script src="js/plugins.js"></script>
<script src="js/script.js"></script>
</html>