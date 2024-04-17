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
			<img id="mainImg" src="/resources/images/logo1.png">
		</div>
		<div id="sideBar">
			<ul class="side-menu">
			
				<li class="menu-item">
					<a class="sideBtn" href="/admin/main"> <img class="sideIcon" src="https://cdn-icons-png.flaticon.com/128/88/88282.png">
						<div class="sideBarList">동백마켓</div>
					</a>
					<ul class="dropdown">
                        <li style="line-height:300%;"><a href="/admin/main" class="dropdown-item">동백마켓현황</a></li>
                        <li style="line-height:300%;"><a href="/admin/notice" class="dropdown-item">공지사항</a></li>
                    </ul>
				</li>
				
				<li class="menu-item">
					<a class="sideBtn" href="/admin/customer"> <img class="sideIcon" src="https://cdn-icons-png.flaticon.com/128/684/684837.png">
						<div class="sideBarList">회원관리</div>
					</a>
					<ul class="dropdown">
                        <li style="line-height:300%;"><a href="/admin/customer" class="dropdown-item">회원정보조회</a></li>
                        <li style="line-height:300%;"><a href="/admin/customer/order" class="dropdown-item">회원주문내역</a></li>
                        <li style="line-height:300%;"><a href="/admin/customer/review" class="dropdown-item">회원리뷰조회</a></li>
                    </ul>
				</li>
				
				<li class="menu-item">
					<a class="sideBtn" href="/admin/shop"> <img class="sideIcon" src="https://cdn-icons-png.flaticon.com/128/88/88271.png">
						<div class="sideBarList">입점업체</div>
					</a>
					<ul class="dropdown">
                        <li style="line-height:300%;"><a href="/admin/shop" class="dropdown-item">업체정보조회</a></li>
                        <li style="line-height:300%;"><a href="/admin/shop/order" class="dropdown-item">업체주문내역</a></li>
                        <li style="line-height:300%;"><a href="/admin/shop/review" class="dropdown-item">업체리뷰조회</a></li>
                        <li style="line-height:300%;"><a href="/admin/shop/cal" class="dropdown-item">업체정산</a></li>
                    </ul>
				</li>
				
				<li class="menu-item">
					<a class="sideBtn" href="/admin/sub"> <img class="sideIcon" src="https://cdn-icons-png.flaticon.com/128/1077/1077033.png">
						<div class="sideBarList">구독관리</div>
					</a>
					<ul class="dropdown">
                        <li style="line-height:300%;"><a href="/admin/sub" class="dropdown-item">구독정보조회</a></li>
                        <li style="line-height:300%;"><a href="/admin/sub/order" class="dropdown-item">구독주문내역</a></li>
                        <li style="line-height:300%;"><a href="/admin/sub/list" class="dropdown-item">구독물품조회</a></li>
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

<div id="main">

<div id="main-first">
<label style="font-size: 30px; margin: 20px; font-weight: bold; color: black;">DongBaek Dashboard</label><br>
<h1 style="color: rgba(75, 192, 192, 0.5); margin-left: 60px;">*어제 매출 <label style="color: rgba(255, 99, 132, 0.5);">22,421,227</label>원 달성!!</h1>

<div class="chart-container1">
	<canvas id="myChart" width="700px" height="500"></canvas>
</div>

  <script>
    // 데이터
    var monthlyData = {
      labels: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
      datasets: [{
        label: "누적 매출",
        borderColor: "rgb(75, 192, 192)",
        data: [10000, 15000, 20000, 25000, 30000, 35000, 40000, 45000, 50000, 55000, 60000, 65000],
        fill: false
      }, {
        label: "고객수",
        borderColor: "rgb(255, 99, 132)",
        data: [50, 70, 90, 110, 130, 150, 170, 190, 210, 230, 250, 270],
        fill: false
      }]
    };

    // 차트 설정
    var ctx = document.getElementById('myChart').getContext('2d');
    var myChart = new Chart(ctx, {
      type: 'line',
      data: monthlyData,
      options: {
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true
            }
          }]
        }
      }
    });
  </script>
  
  <div class="chart-container2">
	  <canvas id="myChart1" width="300" height="300"></canvas>
  </div>

  <script>
    var ctx = document.getElementById('myChart1').getContext('2d');
    var myChart1 = new Chart(ctx, {
      type: 'pie',
      data: {
        labels: ['일반주문', '구독주문',],
        datasets: [{
          label: '# of Votes',
          data: [40,60],
          backgroundColor: [
            'rgba(255, 99, 132, 0.5)',
            'rgba(75, 192, 192, 0.5)'
          ],
          borderColor: [
            'rgba(255, 99, 132, 1)',
            'rgba(75, 192, 192, 1)'
          ],
          borderWidth: 1
        }]
      },
      options: {
    	  plugins: {
    		    legend: {
    		      display: true,
    		      position: 'bottom'
    		    },
    		    tooltip: {
    		      enabled: false
    		    },
    		    datalabels: {
    		      display: true,
    		      color: '#fff',
    		      formatter: (value, ctx) => {
    		        return value;
    		      },
    		      font: {
    		        weight: 'bold',
    		        size: 16
    		      }
    		    }
    		  }
      }
    });
    </script>
    
    
</div>

	
<div class="notice">

<div style="text-align: left; padding: 10px;">
	<h2>공지사항</h2>
</div>


	<table style="width: 100%; border-collapse: collapse;">
    <thead>
        <tr>
            <th>제목</th>
            <th>내용</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>공지사항 제목 1</td>
            <td>이것은 첫 번째 공지사항의 내용입니다. 내용 내용 내용 내용 내용.</td>
            <td>이도현</td>
            <td>2024.04.17</td>
        </tr>
    </tbody>
</table>


</div>

</div>






</body>
</html>