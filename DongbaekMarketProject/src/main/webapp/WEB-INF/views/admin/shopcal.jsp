<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/adminHeader.jsp"%>

<script>
	$(document).ready(function(){
		
		$('.col-1').click(function(){
			$('#list-tbody').empty();
			var con = $('#selectBox option:selected').val();
			var search = $('#searchText').val();
			console.log(search);
			if($('#start-date').val() == ""){
				var startDate = '0000-00-00';
			}else{
				var startDate = $('#start-date').val();
			}
			if($('#end-date').val() == ""){
				var endDate = '9999-99-99';
				
			}else{
				var endDate = $('#end-date').val();
			}
			console.log(startDate);
			console.log(endDate);
			
			
 			$.ajax({
				url : "/admin/shoporder/"+con+"/"+search+"/"+startDate+"/"+endDate,
				type : "GET",
				contentType : "charset=UTF-8",
				success : function(data){
					if(data == ''){
						alert("주문정보가 없습니다!");
					}else{
						$(data).each(function(idx,item){
						
						var currentDate = new Date(item.ordr_date);

						var year = currentDate.getFullYear();
						var month = ('0' + (currentDate.getMonth() + 1)).slice(-2);
						var day = ('0' + currentDate.getDate()).slice(-2);
						var formatDate = year + '-' + month + '-' + day;

						$('#list-tbody').append("<tr><td>"+item.order_code
										 +"</td><td>"+formatDate
										 +"</td><td>"+item.user_id
										 +"</td><td>"+item.pay_method
										 +"</td><td>"+item.total_price+"원"
										 +"</td><td>"+item.cal_check
										 +"</td></tr>");
						});
					}
					
				}
 			});
			
		});// 검색 이벤트
	
		$('#list-tbody').on('mouseover', 'tr', function() {
	        $(this).css('background-color','rgb(250,250,250)');
	        $(this).css('cursor','pointer');
	    });
		
	    $('#list-tbody').on('mouseout', 'tr', function() {
	        $(this).css('background-color','white');
	    });// 리스트 선택 이벤트
		
	    $('#list-tbody').on('click', 'tr', function() {
			
			var order_code = $(this).find('td:eq(0)').text();
			
			$.ajax({
				url : "/admin/shoporder/"+order_code, 
				type : "GET",
				success : function(data){
					//console.log(data);
					
					var orderList = data.orderList[0];
					var cartList = data.cartList;
					
					//console.log(orderList); 
					//console.log(cartList);
					
					var currentDate = new Date(orderList.ordr_date);
					var year = currentDate.getFullYear();
					var month = ('0' + (currentDate.getMonth() + 1)).slice(-2);
					var day = ('0' + currentDate.getDate()).slice(-2);
					var formatDate = year + '-' + month + '-' + day;
					
 					$('#modal-table1 tbody').append(
								`<tr>
									<td style="background-color: rgb(245,247,250);"><h6>아이디</h6></td>
									<td>`+orderList.user_id+ `</td>
									<td style="background-color: rgb(245,247,250);"><h6>이름</h6></td>
									<td>`+orderList.rcv_name+`</td>
									<td style="background-color: rgb(245,247,250);"><h6>주문일</h6></td>
									<td colspan="2">`+formatDate+`</td>
								</tr>
								<tr>
									<td style="background-color: rgb(245,247,250);"><h6>주소</h6></td>
									<td colspan="6">`+orderList.rcv_addr1+orderList.rcv_addr2+`</td>
								</tr>
								<tr>
									<td style="background-color: rgb(245,247,250);"><h6>휴대폰</h6></td>
									<td>`+orderList.rcv_phone+`</td>
									<td style="background-color: rgb(245,247,250);"><h6>결제수단</h6></td>
									<td>`+orderList.pay_method+`</td>
									<td style="background-color: rgb(245,247,250);"><h6>결제금액</h6></td>
									<td colspan="2">`+orderList.total_price+`원</td>
								</tr>
								<tr>
									<td style="background-color: rgb(245,247,250);"><h6>적립포인트</h6></td>
									<td>`+orderList.add_point+`</td>
									<td style="background-color: rgb(245,247,250);"><h6>사용포인트</h6></td>
									<td>`+orderList.reduce_point+`</td>
									<td style="background-color: rgb(245,247,250);"><h6>주문업체</h6></td>
									<td colspan="2">`+orderList.store_code+`</td>
								</tr>`
							
							);
					
					$('#modal-table2 thead').append(
							`<tr>
								<th colspan="7" style="background-color: white; text-align: center;"><h6>주문상품</h6></th>
							</tr>
							<tr style="background-color: rgb(245,247,250); text-align: center;">
								<td><h6>상품코드</h6></td>
								<td><h6>상품명</h6></td>
								<td><h6>이미지</h6></td>
								<td><h6>원산지</h6></td>
								<td><h6>판매자아이디</h6></td>
								<td><h6>가격</h6></td>
								<td><h6>수량</h6></td>
							</tr>`
							);
					
					$(cartList).each(function(i,cartList){
						
								var ProductVO = cartList.adminProductVO[0];
								console.log(ProductVO);
								
								$('#modal-table2 tbody').append(
										`<tr>
											<td>`+ProductVO.product_code+ `</td>
											<td>`+ProductVO.product_name+ `</td>
											<td>`+ProductVO.img1+ `</td>
											<td>`+ProductVO.country+`</td>
											<td>`+ProductVO.seller_id+`</td>
											<td>`+cartList.price+`</td>
											<td>`+cartList.count+`</td>
										</tr>`
									);
					}); 
							
					
					$('#chocolat-content-0').attr('class','chocolat-wrapper chocolat-visible');
					$('#second').attr('class','chocolat-overlay chocolat-visible');
				}
			});
			
		});// 모달 오픈
		
		$('.chocolat-close').click(function(){
			$('#modal-table1 tbody').empty();
			$('#modal-table1 tfoot').empty();
			$('#modal-table2 thead').empty();
			$('#modal-table2 tbody').empty();
			$('#chocolat-content-0').attr('class','chocolat-wrapper');
			$('#second').attr('class','chocolat-overlay');
		});// 모달 클로즈
		
		$('#end-date').change(function(){
			var startDate = $('#start-date').val();
			var endDate = $('#end-date').val();
			if(startDate > endDate){
				alert('시작일이 종료일보다 작아야 합니다!');
				$('#start-date').val("");
				$('#end-date').val("");
			}
		});
		
		$('.col-1').on('mouseover',function() {
	        $(this).css('cursor','pointer');
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
				<div class="chocolat-image-wrapper" style="width: 868px; height: 868px; background-image:url(/resources/images/modal_back.jpg); text-align: center; color: black; padding: 20px;">
					<h1>주문상세정보</h1>
					<div style="width: 100%; height: 100%; height: 750px; overflow: scroll; overflow-x:hidden; ">
						<table id="modal-table1" class="modal-table">
							<thead>
								<tr>
									<th colspan="7" style="background-color: white; text-align: center;">주문내역</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
							<tfoot>
							</tfoot>
						</table>
						
						<table id="modal-table2" class="modal-table" style="margin-top: 30px;">
							<thead>
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
		<h2>업체 정산</h2>
			<div class="col-sm-6 offset-sm-2 offset-md-0 col-lg-5 d-none d-lg-block">
            <div class="search-bar row bg-light p-2 my-2 rounded-4">
              <div class="col-md-4 d-none d-md-block">
                <select id="selectBox" class="form-select border-0 bg-transparent">
                  <option value="store_name">가게명</option>
                  <option value="store_code">가게코드</option>
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
            <th>가게코드</th>
            <th>주문날짜</th>
            <th>판매자ID</th>
            <th>결제</th>
            <th>주문금액</th>
            <th>정산내역</th>
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
	<li class="page-item">
		<a class="page-link border-0" href="#" aria-label="Next">
		<span aria-hidden="true">»</span>
		</a>
	</li>
</ul>     
    
</div>

	







<%@ include file="../include/adminFooter.jsp"%>