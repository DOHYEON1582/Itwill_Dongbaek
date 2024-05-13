<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="include/header.jsp" %>

    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=37fae0919828ca56ee2095e651f697ce"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    
    <script src="./resources/js/jquery-2.1.1.js"></script>
	<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
    
	<style>
	.bx-wrapper {
		max-width: 1000px;
		height: 360px;
		margin: 0 auto;
	}
	.bxslider img {
		max-width: 100%;
		max-height: 100%;
	}
	#map-container{
		display: flex;
		align-items: flex-start; /* 지도와 정보를 위로 정렬 */
		justify-content: center; /* 가로 중앙 정렬 */
		margin: 100px auto;
	}
	#map {
		width: 800px;
		height: 500px;
	}
	#marketInfo {
		margin-left: 20px; /* 지도와 정보 사이에 여백 추가 */
	}
	</style>
	
    <script>
    $(document).ready(function(){
        // 이미지 슬라이더 설정
        $('.bxslider').bxSlider({
          auto: true,
          autoControls: true,
          stopAutoOnClick: true,
          pager: true,
          slideWidth: 1000
        });
 
        var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
        var options = { //지도를 생성할 때 필요한 기본 옵션
            center: new kakao.maps.LatLng(35.137922, 129.055628), //지도의 중심좌표.
            level: 9 //지도의 레벨(확대, 축소 정도)
        };

        var map = new kakao.maps.Map(container, options);

        var infowindow = new kakao.maps.InfoWindow({ removable: true }); // 정보창 생성

        var positions = [
            {
                title: '구포시장', 
                latlng: new kakao.maps.LatLng(35.20924299865605, 129.0035674291987) // 구포시장의 실제 위치로 수정
            },
            {
                title: '자갈치시장',
                latlng: new kakao.maps.LatLng(35.09664, 129.03031)
            }
        ];

        // 마커 이미지의 이미지 주소입니다
        var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png'; 
        for (var i = 0; i < positions.length; i ++) {
            // 마커 이미지의 이미지 크기 입니다
            var imageSize = new kakao.maps.Size(24, 35); 

            // 마커 이미지를 생성합니다    
            var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 

            // 마커를 생성합니다
            var marker = new kakao.maps.Marker({
                map: map, // 마커를 표시할 지도
                position: positions[i].latlng, // 마커를 표시할 위치
                title : positions[i].title, // 마커의 타이틀
                image : markerImage // 마커 이미지 
            });

            // 클로저를 사용하여 클릭 이벤트에 정보 전달
            (function(marker, i) {
                // 마커에 클릭 이벤트를 등록합니다
                kakao.maps.event.addListener(marker, 'click', function() {
                    // 마커 클릭 시 정보창에 새로운 내용을 표시하기 전에 이전 내용을 초기화합니다
                    infowindow.close();
                    
                    // AJAX를 통해 서버로 데이터 요청을 보냅니다
                    $.ajax({
                        url: '/get', // 서버 측에서 데이터를 가져올 URL을 지정합니다.
                        type: 'post', // HTTP 요청 방식을 지정합니다.
                        data: { market_code: i + 1 }, // 클릭한 마커의 정보를 서버에 전달합니다.
                        dataType: 'json', // 서버가 반환하는 데이터의 타입을 지정합니다.
                        success: function(data) { // AJAX 요청이 성공한 경우 실행될 콜백 함수입니다.
                            // 반환된 데이터를 사용하여 정보 창을 열어서 표시합니다.
                            var content = '<div style="padding:5px;font-size:12px;">' + data.name + '</div>';
                            infowindow.setContent(content);
                            infowindow.open(map, marker);
                            console.log(data);
                            $('#marketName').html('<a href="http://localhost:8088/market/marketMain?market_code=' + (i + 1) + '">'+data.name+ '</a>');
                            $('#marketExplain').html("시장 설명 : "+data.explain);
                            $('#marketPhone').html("전화번호 : "+data.phone);
                            $('#marketTraffic').html("교통편 : " +data.traffic);
                            $('#marketAddr').html("주소 : " +data.market_addr1 + "(" + data.market_addr2 + ")");
                        },
                        error: function(xhr, status, error) { // AJAX 요청이 실패한 경우 실행될 콜백 함수입니다.
                            alert('데이터를 불러오는 데 실패했습니다: ' + error);
                        }
                    });
                });
            })(marker, i);
        }
     	// 드롭다운 메뉴 항목을 클릭했을 때의 이벤트 핸들러
        $('.dropdown-menu a').click(function(){
            var selectedType = $(this).data('value');
            $(this).closest('.input-group-prepend').find('.btn').text($(this).text());
            $(this).closest('form').find('input[name="searchType"]').val(selectedType);
            
            if(selectedType === 'name') {
                $('input[name="query"]').attr("placeholder", "찾고 싶은 시장을 검색해보세요!");
            } else if(selectedType === 'product_name') {
                $('input[name="query"]').attr("placeholder", "찾고 싶은 음식을 검색해보세요!");
            }
        });
     	
        $('#search-form').submit(function(event) {
            event.preventDefault();
            // 검색어 가져오기
            var query = $(this).find('input[name="query"]').val();
            // 검색어가 비어있지 않은 경우에만 서버로 전송
            if (query.trim() !== '') {
                // 서버로 검색어를 전송하는 AJAX 요청
                $.ajax({
                    url: '/search', // 서버 측에서 검색을 처리할 URL
                    type: 'GET', // HTTP 요청 방식
                    data: { query: query }, // 검색어를 서버에 전송
                    dataType: 'json', // 서버가 반환하는 데이터의 타입
                    success: function(data) {
                        // 검색 결과 처리
                        // 여기서는 간단히 예시로 콘솔에 결과를 출력하는 것으로 대체
                        console.log(data);
                    },
                    error: function(xhr, status, error) {
                        // 에러 처리
                        console.error(error);
                    }
                });
            }
        });
    });
    </script>
    
   	<div class="bxslider">
		<div><img src="resources/images/product/seasonfood.png" /></div>
		<div><img src="resources/images/product/dohyun2.png" /></div>
		<div><img src="resources/images/product/hyunjin2.png" /></div>
	</div>
	
	<div id="map-container">
		<div id="map"></div>
		
		<div id="marketInfo">
			<h2 id="marketName"></h2>
			<p id="marketExplain"/>
			<p id="marketPhone"/>
			<p id="marketTraffic"/>
			<p id="marketAddr"/>
		</div>
	</div>
	
<%@include file="include/footer.jsp" %>