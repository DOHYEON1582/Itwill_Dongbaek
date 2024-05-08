<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/adminHeader.jsp"%>

<script>
	$(document).ready(function(){
		
		$('.col-1').click(function(){
			$('#list-tbody').empty();
			var title = $('#selectBox option:selected').val();
			var value = $('#searchText').val();
			
			if(title == "store_name"){
				var user = {
						"store_name" : value
				};
			}else if(title == "store_number"){
				var user = {
						"store_number" : value
				};
			}else{
				var user = {
						"seller_name" : value
				};
			}
			
			$.ajax({
				url : "/admin/shop",
				type : "POST",
				data : JSON.stringify(user),
				contentType : "application/json; charset=UTF-8",
				success : function(data){
					if(data == ''){
						alert("업체정보가 없습니다!");
					}else{ 
						$(data).each(function(i,data){
							var store = data;
							var seller = data.adminSellerVO;
							
							$('#list-tbody').append("<tr><td>"+seller.store_number
											 +"</td><td>"+store.store_code
											 +"</td><td>"+store.store_name
											 +"</td><td>"+seller.seller_name
											 +"</td><td>"+store.phone
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
			    	
				$('#chocolat-content-0').attr('class','chocolat-wrapper chocolat-visible');
				$('#second').attr('class','chocolat-overlay chocolat-visible');
					
				var store_number = $(this).find('td:eq(0)').text();
				
			$.ajax({
				url : "/admin/shop/"+store_number,
				type : "GET",
				success : function(data){
						
						var store = data;
						var seller = data.adminSellerVO;
						console.log(store);
						console.log(seller);
						$('#h1tag').append("업체 정보 조회");
					
						$('#modal-table1').append(`
						<tr>
							<th colspan="6" style="text-align: center;"><h5>가게정보</h5></th>
						</tr>
						<tr>
							<td style="background-color: rgb(245,247,250);"><h6>가게코드</h6></td>
							<td>`+store.store_code+ `</td>
							<td style="background-color: rgb(245,247,250);"><h6>가게명</h6></td>
							<td>`+store.store_name+`</td>
							<td style="background-color: rgb(245,247,250);"><h6>시장</h6></td>
							<td>`+store.market_code+`</td>
						</tr>
						<tr>
							<td style="background-color: rgb(245,247,250);"><h6>주소</h6></td>
							<td colspan="5">`+store.store_addr1+store.store_addr2+`</td>
						</tr>
						<tr>
							<td style="background-color: rgb(245,247,250);"><h6>가게종류</h6></td>
							<td>`+store.store_value+`</td>
							<td style="background-color: rgb(245,247,250);"><h6>전화번호</h6></td>
							<td>`+store.phone+`</td>
							<td style="background-color: rgb(245,247,250);"><h6>가게상태</h6></td>
							<td>`+store.status+`</td>
						</tr>`);					
						
						
						$('#modal-table2').append(`
						<tr>
							<th colspan="6" style="text-align: center;"><h5>사장님정보</h5></th>
						</tr>
						<tr>
							<td style="background-color: rgb(245,247,250);"><h6>아이디</h6></td>
							<td>`+seller.seller_id+ `</td>
							<td style="background-color: rgb(245,247,250);"><h6>사장님</h6></td>
							<td>`+seller.seller_name+`</td>
							<td style="background-color: rgb(245,247,250);"><h6>개인전화번호</h6></td>
							<td>`+seller.seller_phone+`</td>
						</tr>
						<tr>
							<td style="background-color: rgb(245,247,250);"><h6>주소</h6></td>
							<td colspan="5">`+seller.store_addr1+seller.store_addr2+`</td>
						</tr>
						<tr>
							<td style="background-color: rgb(245,247,250);"><h6>은행</h6></td>
							<td>`+seller.bank+`</td>
							<td style="background-color: rgb(245,247,250);"><h6>계좌번호</h6></td>
							<td>`+seller.account+`</td>
							<td style="background-color: rgb(245,247,250);"><h6>사업자번호</h6></td>
							<td>`+seller.store_number+`</td>
						</tr>`);
						
				}
			});
			
		});// 모달 오픈
		
		$('#addProduct').click(function(){
			$('#h1tag').append("업체 정보 등록");
			
			$('#modal-table1 tbody').append(
				   `<tr>
						<td style="background-color: rgb(245,247,250);"><h6>아이디</h6></td>
						<td><input id="seller_id" style="border: none" type="text" name="seller_id"><label id="label1" style="font-size: 15px;"></label><button id="idCheck" type="button">중복검사</button></td>
						<td style="background-color: rgb(245,247,250);"><h6>비밀번호</h6></td>
						<td><input style="border: none" type="password" name="seller_pw"></td>
						<td style="background-color: rgb(245,247,250);"><h6>휴대폰번호</h6></td>
						<td colspan="2"><input style="border: none" id="" type="text" name="seller_phone"></td>
					</tr>
					<tr>
						<td style="background-color: rgb(245,247,250);"><h6>성함</h6></td>
						<td><input style="border: none" type="text" name="seller_name"></td>
						<td style="background-color: rgb(245,247,250);"><h6>가게주소</h6></td>
						<td><input style="border: none" type="text" name="store_addr1"></td>
						<td style="background-color: rgb(245,247,250);"><h6>상세주소</h6></td>
						<td colspan="2"><input style="border: none" type="text" name="store_addr2"></td>
					</tr>
					<tr>
						<td style="background-color: rgb(245,247,250);"><h6>은행</h6></td>
						<td><input style="border: none" type="text" name="bank"></td>
						<td style="background-color: rgb(245,247,250);"><h6>계좌번호</h6></td>
						<td><input style="border: none" type="text" name="account"></td>
						<td style="background-color: rgb(245,247,250);"><h6>사업자번호</h6></td>
						<td colspan="2"><input style="border: none" type="text" name="store_number"></td>
					</tr>
					<tr>
						<td style="background-color: rgb(245,247,250);"><h6>마켓코드</h6></td>
						<td><input style="border: none" type="text" name="market_code"></td>
						<td style="background-color: rgb(245,247,250);"><h6>가게이름</h6></td>
						<td><input style="border: none" type="text" name="store_name"></td>
						<td style="background-color: rgb(245,247,250);"><h6>가게종류</h6></td>
						<td colspan="2"><input style="border: none" type="text" name="store_value"></td>
					</tr>
					<tr>
						<td style="background-color: rgb(245,247,250);"><h6>가게설명</h6></td>
						<td><input style="border: none" type="text" name="store_explain"></td>
						<td style="background-color: rgb(245,247,250);"><h6>가게전화번호</h6></td>
						<td><input style="border: none" type="text" name="phone"></td>
						<td style="background-color: rgb(245,247,250);"><h6>최소주문금액</h6></td>
						<td colspan="2"><input style="border: none" type="text" name="min_price"></td>
					</tr>
					<tr>
						<td colspan="6"><button class="btnFile" id="btnUpload" type="button">등록하기</button></td>
					</tr>`
			);
	
			$('#chocolat-content-0').attr('class','chocolat-wrapper chocolat-visible');
			$('#second').attr('class','chocolat-overlay chocolat-visible');
			
		});// 상품 추가 모달 오픈
		
		
		$('.chocolat-close').click(function(){
			$('#modal-table1').empty();
			$('#modal-table2').empty();
			$('#h1tag').empty();
			
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
			$("#uploadForm").attr("action","/admin/insertupload").submit();
		});
		
		$('.col-1').on('mouseover',function() {
	        $(this).css('cursor','pointer');
	    });
		
		
		// 아이디 중복검사
     	$(document).on('click','#idCheck',function(){
     		
     		var seller_id = $("#seller_id").val();
     		console.log(seller_id);
     		var user = {
     				"seller_id" : seller_id
     		};
 
         	$.ajax({
         		url : '/admin/idcheck',
         		type : 'POST',
         		data : JSON.stringify(user),
				contentType : "application/json; charset=UTF-8",
         		success : function(result) {
         			if (result == 0) {
         				$("#label1").css("color", "black").text("사용 가능한 ID 입니다.");
         			} else{
         				$("#label1").css("color", "red").text("사용 불가능한 ID 입니다.");
         				$("#seller_id").val('');
         			}
         		}
         	}); //End Ajax
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
					<h1 id="h1tag"></h1>
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
		<h2>업체정보조회<button id="addProduct" type="button" class="btn btn-dark rounded-pill" style="margin-left: 30px;">업체등록</button></h2>
			<div class="col-sm-6 offset-sm-2 offset-md-0 col-lg-5 d-none d-lg-block">
            <div class="search-bar row bg-light p-2 my-2 rounded-4">
              <div class="col-md-4 d-none d-md-block">
                <select id="selectBox" class="form-select border-0 bg-transparent">
                  <option value="store_name">가게명</option>
                  <option value="store_number">사업자번호</option>
                  <option value="seller_name">사업자이름</option>
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

<table style="width: 99%; border-collapse: collapse; margin-left: 19px; background-color: white;">
    <thead>
        <tr>
            <th>사업자번호</th>
            <th>가게코드</th>
            <th>가게명</th>
            <th>사업자이름</th>
            <th>가게전화번호</th>
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