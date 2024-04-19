<%@page pageEncoding="UTF-8" %>

<%@include file="include/header.jsp" %>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>

    
	<style>
		.bx-wrapper {
			max-width: 1000px;
			heigth: 700px;
			margin: 0 auto; 
		}	
	  
		#map-container {
			display: flex;
			justify-content: center;
			align-items: center;
			height: 600px; /* 지도의 높이 설정 */
			margin-top: 100px; /* 아래쪽으로 간격 추가 */
		}
	  #map {
	    width: 800px; /* 지도의 너비 설정 */
	    height: 100%; /* 지도의 높이를 부모 요소의 높이에 맞춤 */
	  }
	</style>
	
	
    <script>
    $(document).ready(function(){
        // 부산 지도 클릭 이벤트 처리
        document.getElementById('map').addEventListener('click', function(event) {
          var x = event.offsetX;
          var y = event.offsetY;
          
          alert('Clicked at: ' + x + ', ' + y);
        });
        
        // 이미지 슬라이더 설정
        $('.bxslider').bxSlider({
          auto: true,
          autoControls: true,
          stopAutoOnClick: true,
          pager: true,
          slideWidth: 1000
        });
      });
    </script>
    
	<div class="bxslider">
	  <div><img src="resources/images/spring.png" /></div>
	  <div><img src="resources/images/mylogo2.png" /></div>
	  <div><img src="resources/images/mylogo2.png" /></div>
	</div>
	
	<div id="map-container">
		<div id="map">
			<img src="resources/images/Map_Busan-gwangyeoksi.svg.png" alt="부산 지도">
		</div>
	</div>
	

<%@include file="include/footer.jsp" %>
	