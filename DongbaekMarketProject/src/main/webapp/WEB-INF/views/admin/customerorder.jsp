<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/adminHeader.jsp"%>

<script>
	$(document).ready(function(){
		
		$('.col-1').click(function(){
			$('#list-tbody').empty();
			var title = $('#selectBox option:selected').val();
			var value = $('#searchText').val();
			
 			if(title == "user_id"){
				var user = {
						"user_id" : value,
						"startDate" : $('#start-date').val(),
						"endDate" : $('#end-date').val()
				};
			}else if(title == "user_name"){
				var user = {
						"user_name" : value,
						"startDate" : $('#start-date').val(),
						"endDate" : $('#end-date').val()
				};
			}else{
				var user = {
						"order_code" : value,
						"startDate" : $('#start-date').val(),
						"endDate" : $('#end-date').val()
				};
			}
 			
 			
			console.log(user);
			
 			$.ajax({
				url : "/admin/customerorder",
				type : "POST",
				data : JSON.stringify(user),
				contentType : "application/json; charset=UTF-8",
				success : function(data){
					console.log(data);
					if(data == ''){
						alert("주문정보가 없습니다!");
					}else{
						$(data).each(function(idx,item){
						$('#list-tbody').append("<tr><td>"+item.order_code
										 +"</td><td>"+item.user_id
										 +"</td><td>"+item.rcv_name
										 +"</td><td>"+item.rcv_addr1
										 +"</td><td>"+item.total_price+"원"
										 +"</td><td>"+item.ordr_date+
										 +"</td></tr>");
						});
					}
					
				}
			}); 
			
		});// 검색 이벤트
	
		$('#list-tbody').on('mouseover', 'tr', function() {
	        $(this).css('background-color','rgb(250,250,250)');
	    });
		
	    $('#list-tbody').on('mouseout', 'tr', function() {
	        $(this).css('background-color','white');
	    });// 리스트 선택 이벤트
		
	    $('#list-tbody').on('click', 'tr', function() {
			
			var order_code = $(this).find('td:eq(0)').text();
			
			$.ajax({
				url : "/admin/orderlist/"+order_code,
				type : "GET",
				success : function(data){
					console.log(data);				
					$('#chocolat-content-0').attr('class','chocolat-wrapper chocolat-visible');
					$('#second').attr('class','chocolat-overlay chocolat-visible');
					
				}
			});
			
		});// 모달 오픈
		
		$('.chocolat-close').click(function(){
			$('.modal-table tbody').empty();
			$('#chocolat-content-0').attr('class','chocolat-wrapper');
			$('#second').attr('class','chocolat-overlay');
		});// 모달 클로즈
		
		$('#end-date').change(function(){
			var startDate = $('#start-date').val();
			var endDate = $('#end-date').val();
			if(startDate >= endDate){
				alert('시작일이 종료일보다 작아야 합니다!');
				$('#start-date').val("");
				$('#end-date').val("");
			}
		});
		
	
	});//document
</script>

<!-- 모달 -->
<div id="chocolat-content-0" class="chocolat-wrapper">
	<div id="second" class="chocolat-overlay"></div>
	<div class="chocolat-loader"></div>
	<div class="chocolat-layout">
		<div class="chocolat-top">
			<span class="chocolat-close"></span>
		</div>
		<div class="chocolat-center">
			<div class="chocolat-image-canvas chocolat-visible" >
				<div class="chocolat-image-wrapper" style="width: 868px; height: 868px; background-color: white; text-align: center; color: black; padding: 20px;">
					<h1>주문상세정보</h1>
					<div style="border: 1px solid black; width: 100%; height: 100%;">
						<table class="modal-table">
							<thead>
								<tr>
									<th colspan="6" style="background-color: rgb(245,247,250); text-align: center;">주문내역</th>
								</tr>
							</thead>
							<tbody>
							
							</tbody>

						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="chocolat-bottom">
			<span class="chocolat-description">Calm Before The Storm (One Shoe Photography Ltd.)</span><span class="chocolat-pagination">1/1</span><span class="chocolat-set-title"></span><span class="chocolat-fullscreen"></span>
		</div>
	</div>
</div>


<div id="main">

<div id="main-first">
<!-- 내용 -->
<div class="notice">
	<div style="text-align: left; padding: 10px;">
		<h2>회원주문조회</h2>
			<div class="col-sm-6 offset-sm-2 offset-md-0 col-lg-5 d-none d-lg-block">
            <div class="search-bar row bg-light p-2 my-2 rounded-4">
              <div class="col-md-4 d-none d-md-block">
                <select id="selectBox" class="form-select border-0 bg-transparent">
                  <option value="user_id">아이디</option>
                  <option value="user_name">이름</option>
                  <option value="order_code">주문번호</option>
                </select>
              </div>
              <div class="col-11 col-md-7">
                <form id="search-form" class="text-center" action="index.html" method="post">
                  <input id="searchText" type="text" class="form-control border-0 bg-transparent" placeholder="Search for">
                </form>
              </div>
	          <div class="col-1">
	                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="currentColor" d="M21.71 20.29L18 16.61A9 9 0 1 0 16.61 18l3.68 3.68a1 1 0 0 0 1.42 0a1 1 0 0 0 0-1.39ZM11 18a7 7 0 1 1 7-7a7 7 0 0 1-7 7Z"></path></svg>
	          </div>
	          <div style="margin-left: 10px;"> <!-- 날짜div -->
          		시작일:<input type="date" id="start-date" placeholder="시작일">종료일:<input type="date" id="end-date" placeholder="종료일">
              </div>
            </div>
          </div>
      </div>
</div>

<table class="listTable" style="width: 99%; border-collapse: collapse; margin-left: 19px; background-color: white;">
    <thead>
        <tr>
            <th>주문코드</th>
            <th>아이디</th>
            <th>이름</th>
            <th>주소</th>
            <th>주문금액</th>
            <th>주문날짜</th>
        </tr>
    </thead>
    <tbody id="list-tbody">
        
    </tbody>
</table>    



<!-- 내용 -->

</div>
<ul class="pagination d-flex justify-content-center">
	<li class="page-item disabled">
		<a class="page-link bg-none border-0" href="#" aria-label="Previous">
		<span aria-hidden="true">«</span>
		</a>
	</li>
	<li class="page-item active" aria-current="page"><a class="page-link border-0" href="#">1</a></li>
	<li class="page-item"><a class="page-link border-0" href="#">2</a></li>
	<li class="page-item"><a class="page-link border-0" href="#">3</a></li>
	<li class="page-item">
		<a class="page-link border-0" href="#" aria-label="Next">
		<span aria-hidden="true">»</span>
		</a>
	</li>
</ul>     
    
</div>

	







<%@ include file="../include/adminFooter.jsp"%>