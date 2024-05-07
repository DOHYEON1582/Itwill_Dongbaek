<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/adminHeader.jsp"%>
<script>
	$(document).ready(function(){
		
		$('.col-1').click(function(){
			$('#list-tbody').empty();

				var search = {
						"content" : $('#searchText').val(),
				};
			
 			$.ajax({
				url : "/admin/notice",
				type : "POST",
				data : JSON.stringify(search),
				contentType : "application/json; charset=UTF-8",
				success : function(data){
					if(data == ''){
						alert("공지사항 정보가 없습니다!");
					}else{
						console.log(data);
						$(data).each(function(idx,item){
							
							var currentDate = new Date(item.regdate);

							var year = currentDate.getFullYear();
							var month = ('0' + (currentDate.getMonth() + 1)).slice(-2);
							var day = ('0' + currentDate.getDate()).slice(-2);
							var formatDate = year + '-' + month + '-' + day;
							
							$('#list-tbody').append(
											 `<tr><td>`+item.q_code
											 +`</td><td>`+item.title
											 +`</td><td>`+item.content
											 +`</td><td>`+item.user_name
											 +`</td><td>`+formatDate
											 +`</td></tr>`);
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
			
			var q_code = $(this).find('td:eq(0)').text();
			
			$.ajax({
				url : "/admin/notice/"+q_code,
				type : "GET",
				success : function(data){
					console.log(data);
					var currentDate = new Date(data.regdate);

					var year = currentDate.getFullYear();
					var month = ('0' + (currentDate.getMonth() + 1)).slice(-2);
					var day = ('0' + currentDate.getDate()).slice(-2);
					var formatDate = year + '-' + month + '-' + day;
					
					  	$('#modal-table1 thead').append(
					  			`<tr>
									<td style="background-color: rgb(245,247,250);"><h6>글번호</h6></td>
									<td>`+data.q_code+ `</td>
									<td style="background-color: rgb(245,247,250);"><h6>작성자ID</h6></td>
									<td>`+data.user_id+`</td>
									<td style="background-color: rgb(245,247,250);"><h6>작성자이름</h6></td>
									<td>`+data.user_name+`</td>
								</tr>
								<tr>
									<td style="background-color: rgb(245,247,250);"><h6>제목</h6></td>
									<td colspan="3">`+data.title+`</td>
									<td style="background-color: rgb(245,247,250);"><h6>작성일</h6></td>
									<td>`+formatDate+`</td>
								</tr>
								<tr>
									<td colspan="6" style="text-align: left;">`+data.content+`</td>
								</tr>`
							); 
					  	
					$('#chocolat-content-0').attr('class','chocolat-wrapper chocolat-visible');
					$('#second').attr('class','chocolat-overlay chocolat-visible');
				}
			});
			
		});// 공지사항 상세 정보 모달 오픈
		
		$('#addProduct').click(function(){
			
			$('#modal-table1 tbody').append(
				   `<tr>
						<td style="background-color: rgb(245,247,250);"><h6>제목</h6></td>
						<td><input style="border: none; width:100%;" type="text" name="title"></td>
					
					</tr>
					<tr>
						<td style="background-color: rgb(245,247,250);" colspan="6">본문작성</td>
					</tr>
					<tr>
						<td colspan="6"><textarea id="content_textarea" rows="" cols="" placeholder="내용을 작성해주세요" name="content"></textarea><label id="txt_length">0</label>/2000</td>
					</tr>
					<tr>
						<td colspan="6"><button class="btnFile" id="btnUpload" type="button">등록하기</button></td>
					</tr>`
			);
	
			$('#chocolat-content-0').attr('class','chocolat-wrapper chocolat-visible');
			$('#second').attr('class','chocolat-overlay chocolat-visible');
			
		});// 공지사항 모달 오픈
		
		
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
		
		// 텍스트 길이 체크
		$(document).on('keyup', '#content_textarea', function(){
			var txt = $('#content_textarea').val();
			
			$('#txt_length').text(0+txt.length);
		});
		
		// 공지사항 등록하기 submit
		$(document).on('click','#btnUpload',function(){
			$("#uploadForm").attr("action","/admin/insertnotice").submit();
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
					<h1>공지사항</h1>
					<div style="width: 100%; height: 100%; height: 750px; overflow: scroll; overflow-x:hidden; ">
						<form id="uploadForm" action=""  method="post" >
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
<!-- 내용 -->

<div id="main-first">
<!-- 내용 -->
<div class="notice">
	<div style="text-align: left; padding: 10px;">
		<h2>공지사항<button id="addProduct" type="button" class="btn btn-dark rounded-pill" style="margin-left: 30px;">글 작성</button></h2>
			<div class="search-bar row bg-light p-2 my-2 rounded-4" style="width: 300px; border: 1px solid #dddddd;">
		              <div class="col-11 col-md-7">
		                <form id="search-form" class="text-center" action="index.html" method="post" style="width: 200px;">
		                  <input id="searchText" type="text" class="form-control border-0 bg-transparent" placeholder="Search for ">
		                </form>
		              </div>
		              <div class="col-1">
		                
		                	<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" style="margin-left: 50px;"><path fill="currentColor" d="M21.71 20.29L18 16.61A9 9 0 1 0 16.61 18l3.68 3.68a1 1 0 0 0 1.42 0a1 1 0 0 0 0-1.39ZM11 18a7 7 0 1 1 7-7a7 7 0 0 1-7 7Z"></path></svg>
						             
		              </div>
		      </div>
      </div>
</div>
</div>

<table style="width: 99%; border-collapse: collapse; margin-left: 19px;">
    <thead>
        <tr>
        	<th>글번호</th>
            <th>제목</th>
            <th>내용</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
    </thead>
    <tbody id="list-tbody">
        
    </tbody>
</table>    
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