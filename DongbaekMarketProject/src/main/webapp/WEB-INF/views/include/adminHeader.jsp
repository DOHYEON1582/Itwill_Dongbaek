<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

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
    <link rel="stylesheet" type="text/css" href="/resources/css/vendor.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;700&family=Open+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">



<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<link href="/resources/css/projectCSS.css" rel="stylesheet" type="text/css" />
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>

<meta charset="UTF-8">
<title>DongbaekAdmin</title>


</head>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		
		// 사이드바 이벤트
		var mouseEvent = true;
		

		$('.sideBarList').mouseover(function(){
			if(mouseEvent){
				$(this).css('backgroundColor','#f7f7f7');
			}
		});
		
		$('.sideBarList').mouseout(function(){
		  		$('.sideBarList').css("backgroundColor","rgb(255,220,220)");
		});
		
		$('.dropdown').children('li').mouseover(function(){
			$(this).css('color','rgb(255,220,220)');
		});
		
		$('.dropdown').children('li').mouseout(function(){
			$(this).css('color','black');
		});

		
	});//document.ready


</script>


<!-- 사이드바 -->

	<aside class="side-bar">
		<div>
			<a href="/admin/main"><img id="mainImg" src="/resources/images/logo1.png"></a>
		</div>
		<div id="sideBar">
			<ul class="side-menu">
			
				<li class="menu-item">
					<a class="sideBtn" href="/admin/main"> <img class="sideIcon" src="https://cdn-icons-png.flaticon.com/128/88/88282.png">
						<div class="sideBarList">동백장터</div>
					</a>
					<ul class="dropdown">
                        <li style="line-height:300%;"><a href="/admin/main" class="dropdown-item">동백장터현황</a></li>
                        <li style="line-height:300%;"><a href="/admin/notice" class="dropdown-item">공지사항</a></li>
                    </ul>
				</li>
				
				<li class="menu-item">
					<a class="sideBtn" href="/admin/customer"> <img class="sideIcon" src="https://cdn-icons-png.flaticon.com/128/684/684837.png">
						<div class="sideBarList">회원관리</div>
					</a>
					<ul class="dropdown">
                        <li style="line-height:300%;"><a href="/admin/customer" class="dropdown-item">회원정보조회</a></li>
                        <li style="line-height:300%;"><a href="/admin/customerorder" class="dropdown-item">회원주문내역</a></li>
                        <li style="line-height:300%;"><a href="/admin/customerreview" class="dropdown-item">회원리뷰조회</a></li>
                    </ul>
				</li>
				
				<li class="menu-item">
					<a class="sideBtn" href="/admin/shop"> <img class="sideIcon" src="https://cdn-icons-png.flaticon.com/128/88/88271.png">
						<div class="sideBarList">입점업체</div>
					</a>
					<ul class="dropdown">
                        <li style="line-height:300%;"><a href="/admin/shop" class="dropdown-item">업체정보조회</a></li>
                        <li style="line-height:300%;"><a href="/admin/shoporder" class="dropdown-item">업체주문&리뷰</a></li>
                        <li style="line-height:300%;"><a href="/admin/shopcal" class="dropdown-item">업체정산</a></li>
                    </ul>
				</li>
				
				<li class="menu-item">
					<a class="sideBtn" href="/admin/sub"> <img class="sideIcon" src="https://cdn-icons-png.flaticon.com/128/1077/1077033.png">
						<div class="sideBarList">구독관리</div>
					</a>
					<ul class="dropdown">
                        <li style="line-height:300%;"><a href="/admin/sub" class="dropdown-item">구독정보조회</a></li>
                        <li style="line-height:300%;"><a href="/admin/suborder" class="dropdown-item">구독주문내역</a></li>
                        <li style="line-height:300%;"><a href="/admin/sublist" class="dropdown-item">구독물품조회</a></li>
                    </ul>
				</li>
				
				
				<li class="menu-item">
					<a class="sideBtn" href="/admin/qna"> <img class="sideIcon" src="https://cdn-icons-png.flaticon.com/128/88/88288.png">
						<div class="sideBarList">고객문의</div>
					</a>
					<ul class="dropdown">
                        <li style="line-height:300%;"><a href="/admin/qna" class="dropdown-item">컴플레인</a></li>
                    </ul>
				</li>
			</ul>
		</div>


	</aside>
	
	
