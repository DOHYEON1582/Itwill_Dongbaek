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
			}else{
				var user = {
						"user_name" : value,
						"startDate" : $('#start-date').val(),
						"endDate" : $('#end-date').val()
				};
			}
			
 			$.ajax({
				url : "/admin/customerreview",
				type : "POST",
				data : JSON.stringify(user),
				contentType : "application/json; charset=UTF-8",
				success : function(data){
					if(data == ''){
						alert("리뷰정보가 없습니다!");
					}else{
						$(data).each(function(idx,item){
						$('#list-tbody').append("<tr><td>"+item.review_code
										 +"</td><td>"+item.product_code
										 +"</td><td>"+item.user_id
										 +"</td><td>"+item.title
										 +"</td><td>"+item.regdate
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
			
			var review_code = $(this).find('td:eq(0)').text();
			
			$.ajax({
				url : "/admin/customerreview/"+review_code,
				type : "GET",
				success : function(data){
					//console.log(data);
					//console.log(data.cartList);
					var review = data;
					var product = data.AdminProductVO[0];
					console.log(review);
					console.log(product);
					
					  	$('#modal-table1 thead').append(
								`<tr>
									<td style="background-color: rgb(245,247,250);"><h6>아이디</h6></td>
									<td>`+review.user_id+ `</td>
									<td style="background-color: rgb(245,247,250);"><h6>별점</h6></td>
									<td>`+review.star+`</td>
									<td style="background-color: rgb(245,247,250);"><h6>작성일</h6></td>
									<td colspan="2">`+review.regdate+`</td>
								</tr>
								<tr>
									<td style="background-color: rgb(245,247,250);"><h6>제목</h6></td>
									<td colspan="6">`+review.title+`</td>
								</tr>
								<tr>
									<td colspan="7" style="background-color: rgb(245,247,250);"><h6>내용</h6></td>
								</tr>
								<tr>
									<td colspan="7">`+review.content+`</td>
								</tr>`
							);
					  	
					  	$('#modal-table2 tbody').append(
					  			`<tr>
									<th colspan="7" style="background-color: white; text-align: center;"><h6>구매상품내역</h6></th>
									<tr style="background-color: rgb(245,247,250); text-align: center;">
									<td><h6>상품코드</h6></td>
									<td><h6>상품명</h6></td>
									<td><h6>이미지</h6></td>
									<td><h6>원산지</h6></td>
									<td><h6>판매자아이디</h6></td>
									<td><h6>가격</h6></td>
									<td><h6>뷰카운트</h6></td>
								</tr>`		
					  	);
					  
						$('#modal-table2 tfoot').append(
								`<tr>
									<td>`+product.product_code+ `</td>
									<td>`+product.product_name+ `</td>
									<td>`+product.img1+ `</td>
									<td>`+product.country+`</td>											
									<td>`+product.seller_id+`</td>
									<td>`+product.price+`</td>
									<td>`+product.viewcnt+`</td>
								</tr>`
							);
						
					
					
					$('#chocolat-content-0').attr('class','chocolat-wrapper chocolat-visible');
					$('#second').attr('class','chocolat-overlay chocolat-visible');
				}
			});
			
		});// 상품 상세 정보 모달 오픈
		
		$('#addProduct').click(function(){
			
			$('#modal-table1 tbody').append(
				   `<tr>
						<td style="background-color: rgb(245,247,250);"><h6>상품명</h6></td>
						<td><input style="border: none" type="text" name="product_name"></td>
						<td style="background-color: rgb(245,247,250);"><h6>판매가격(원)</h6></td>
						<td><input style="border: none" type="text" name="price"></td>
						<td style="background-color: rgb(245,247,250);"><h6>원산지</h6></td>
						<td colspan="2"><input style="border: none" id="" type="text" name="country"></td>
					</tr>
					<tr>
						<td style="background-color: rgb(245,247,250);"><h6>최대판매수량</h6></td>
						<td><input style="border: none" type="text" name="max_account"></td>
						<td style="background-color: rgb(245,247,250);"><h6>단위</h6></td>
						<td><input style="border: none" type="text" name="unit"></td>
						<td style="background-color: rgb(245,247,250);"><h6>카테고리</h6></td>
						<td colspan="2"><input style="border: none" type="text" name="category"></td>
					</tr>
					<tr>
						<td colspan="6">
							<img id="imgFile"  src="">
	     					<button class="btnFile" id="btnFile" type="button">사진업로드</button>
	     					<input id="imgFileInput" type="file" name="img1">
	     				</td>
					</tr>
					<tr>
						<td colspan="6">상품 설명</td>
					</tr>
					<tr>
						<td colspan="6"><textarea id="content_textarea" rows="" cols="" placeholder="설명를 입력하세요" name="product_explain"></textarea><label id="txt_length">0</label>/2000</td>
					</tr>
					<tr>
						<td colspan="6"><button class="btnFile" id="btnUpload" type="button">등록하기</button></td>
					</tr>`
			);
	
			$('#chocolat-content-0').attr('class','chocolat-wrapper chocolat-visible');
			$('#second').attr('class','chocolat-overlay chocolat-visible');
			
		});// 상품 추가 모달 오픈
		
		
		$('.chocolat-close').click(function(){
			$('#modal-table1 thead').empty();
			$('#modal-table1 tbody').empty();
			$('#modal-table2 tbody').empty();
			$('#modal-table2 tfoot').empty();
			$('#chocolat-content-0').attr('class','chocolat-wrapper');
			$('#second').attr('class','chocolat-overlay');
		});// 모달 클로즈
		
	
		$('.col-1').on('mouseover',function() {
	        $(this).css('cursor','pointer');
	    });
		
		// 사진 업로드 버튼
		 $(document).on('click', '#btnFile', function(){
        	$('#imgFileInput').click();
        	
    	});
		
		// 사진 미리보기 스크립트
		$(document).on('change', '#imgFileInput', function(){
			
			const fileDOM = document.querySelector('#imgFileInput');
			// 업로드 사진의 URL을 가져와서 img src 위치에 삽입
			const imageSrc = URL.createObjectURL(fileDOM.files[0]);
			console.log(imageSrc);
			$('#imgFile').attr('src',imageSrc);
			$('#btnFile').css('display','none');
			$('#imgFile').css('display','block');
			
		});
		
		// 텍스트 길이 체크
		$(document).on('keyup', '#content_textarea', function(){
			var txt = $('#content_textarea').val();
			
			$('#txt_length').text(0+txt.length);
		});
		
		// 제품 등록하기 submit
		$(document).on('click','#btnUpload',function(){
			$("#uploadForm").attr("action","/admin/sublist").submit();
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
					<h1>구독상품</h1>
					<div style="width: 100%; height: 100%; height: 750px; overflow: scroll; overflow-x:hidden; ">
						<form id="uploadForm" action=""  method="post" enctype="multipart/form-data">
							<table id="modal-table1" class="modal-table">
								<thead>
								</thead>
							
								<tbody>
								</tbody>
								
								<tfoot>
								</tfoot>
							</table>
						</form>
						<table id="modal-table2" class="modal-table" style="margin-top: 30px;">
							<thead>
							</thead>
							
							<tbody>
							</tbody>
							
							<tfoot>
							</tfoot>
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
		<h2>구독물품<button id="addProduct" type="button" class="btn btn-dark rounded-pill" style="margin-left: 30px;">상품추가</button></h2>
			<div class="col-sm-6 offset-sm-2 offset-md-0 col-lg-5 d-none d-lg-block">
            <div class="search-bar row bg-light p-2 my-2 rounded-4">
              <div class="col-md-4 d-none d-md-block">
                <select id="selectBox" class="form-select border-0 bg-transparent">
                  <option value="product_name">상품이름</option>
                  <option value="product_code">상품코드</option>
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
            </div>
          </div>
      </div>
</div>

<table class="listTable" style="width: 99%; border-collapse: collapse; margin-left: 19px; background-color: white;">
    <thead>
        <tr>
            <th>상품코드</th>
            <th>상품이름</th>
            <th>이미지</th>
            <th>가격</th>
            <th>단위</th>
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