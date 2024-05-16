<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/adminHeader.jsp"%>
<%-- user_id = ${sessionScope.user_id} --%>
<%-- sellList = ${sellList} 
userCountList = ${userCountList} 
sellerCountList = ${sellerCountList} --%>
<div id="main">
<div id="main-first">
<label style="font-size: 30px; margin: 20px; font-weight: bold; color: black;">DongBaek Dashboard</label><br>
<h1 style="color: rgba(75, 192, 192, 0.5); margin-left: 60px;">*어제 매출 <label id="sellLabel" style="color: rgba(255, 99, 132, 0.5);"></label>원 달성!!</h1>

<div class="chart-container1">
	<canvas id="myChart" width="600px" height="400px"></canvas>
</div>

  <script>
    // 데이터

    var date = Object.keys(${sellList});
    var sell = Object.values(${sellList});

    
    const cn1 = sell[5].replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
	$('#sellLabel').text(cn1);

    
    var monthlyData = {
      labels: date,
      datasets: [{
        label: "누적 매출",
        borderColor: "rgb(75, 192, 192)",
        data: sell,
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
	  <canvas id="myChart1" style="height: 400px;width: 500px;"></canvas>
  </div>

  <script>
  var date = Object.keys(${userCountList});
  var user = Object.values(${userCountList});
  var seller = Object.values(${sellerCountList});
  
  
  var monthlyData1 = {
	      labels: date,
	      datasets: [{
	        label: "고객수(누적)",
	        borderColor: "rgb(255, 99, 132)",
	        data: user,
	        fill: false
	      },{
		        label: "사장님(누적)",
		        borderColor: "rgb(75, 192, 192)",
		        data: seller,
		        fill: false
		      }]
	    };

	    // 차트 설정
	    var ctx = document.getElementById('myChart1').getContext('2d');
	    var myChart1 = new Chart(ctx, {
	      type: 'line',
	      data: monthlyData1,
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
    
    
</div>

	
<div class="notice">

<div style="text-align: left; padding: 10px;">
	<h2 style="margin: 10px;">공지사항</h2>
</div>


	<table style="width: 100%; border-collapse: collapse;">
    <thead>
        <tr>
        	<th>글번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="list" items="${noticeList}">
        	 <tr>
	            <td>${list.q_code }</td>
	            <td>${list.title }</td>
	            <td>${list.user_name }</td>
	            <td>${list.regdate }</td>
	        </tr>
        
        </c:forEach>
        
    </tbody>
</table>


</div>

</div>






<%@ include file="../include/adminFooter.jsp"%>