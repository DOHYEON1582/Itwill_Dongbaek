<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/adminHeader.jsp"%>
<%-- user_id = ${sessionScope.user_id} --%>
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
	<h2 style="margin: 10px;">공지사항</h2>
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






<%@ include file="../include/adminFooter.jsp"%>