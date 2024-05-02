<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title></title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Bootstrap icons-->
    <link href="https://fastly.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/styles.css" rel="stylesheet" />
    
<style>
    .nav-tabs {
        border-bottom: 1px solid #dee2e6;
    }

    .nav-tabs li {
        padding: 20px 30px;
        margin-right: 20px;
        cursor: pointer;
        list-style: none;
        display: inline-block;
        font-size: 18px;
    }

    .nav-tabs li:hover {
        background-color: #e9ecef;
    }

    .nav-tabs li.active {
        font-weight: bold;
        color: #007bff;
    }

    #middle_nav {
        text-align: center;
    }
    
    .product-image {
        width: 500px;
        height: 500px;
        object-fit: cover; 
        margin-bottom: 20px;
    }
    
    .about_product img {
        width: 400px;
        height: 400px;
        margin: 10px;
    }
    
    .reviews .panel {
        margin-bottom: 20px;
    }
    
    .qnas {
        margin-top: 80px;
    }
  #scrollToTopBtn {
    display: none; /* 초기에는 숨김 */
    position: fixed;
    bottom: 30px;
    right: 30px;
    z-index: 99;
    width: 100px; /* 버튼 크기 조정 */
    height: 100px; /* 버튼 크기 조정 */
    border: none;
    outline: none;
    background-color: transparent;
    cursor: pointer;
    border-radius: 50%; /* 원형 버튼을 위한 테두리 반경 설정 */
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 그림자 추가 */
  }

  #scrollToTopBtn i {
    color: #007bff;
  }

  #scrollToTopBtn:hover i {
    color: #0056b3;
  }
  .product-qty input.form-control1 {
    width: 30px !important;
    
  }


.ask-button {
  /* 버튼의 스타일을 설정합니다. */
  background-color: #4caf50;
  color: white;
  border: none;
  padding: 8px 16px; /* 크기 조절 */
  font-size: 20px; /* 폰트 크기 조절 */
  cursor: pointer;
  border-radius: 5px;
}
.modal {
    display: none; /* 모달 초기에는 숨김 상태 */
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0,0,0,0.4); /* 배경에 흐린 효과 */
}

/* 모달 콘텐츠 스타일 */
.modal-content {
    background-color: #fefefe;
    margin: 15% auto; /* 모달이 수직으로 중앙에 오도록 설정 */
    padding: 20px;
    border: 1px solid #888;
    width: 80%;
    max-width: 600px;
}

/* 모달 닫기 버튼 스타일 */
.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
}
.form-group {
    text-align: left;
    font-size: 20px;
}
    .star-rating {
        unicode-bidi: bidi-override;
        color: #f8ce0b;
        font-size: 25px;
        display: inline-block;
    }

    .star-rating::before {
        content: '\2605';
        position: absolute;
        z-index: 0;
        top: 0;
        left: 0;
        overflow: hidden;
        width: 0;
    }

    .star-rating span {
        position: absolute;
        top: 0;
        left: 0;
        overflow: hidden;
        width: 0;
        color: #000;
    }
</style>
<script type="text/javascript">
// 맨 위로 스크롤되도록 설정
function topFunction() {
  document.body.scrollTop = 0; // For Safari
  document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
}

function showStarRating() {
    var starElements = document.getElementsByClassName('star-rating');
    Array.prototype.forEach.call(starElements, function(starElement) {
        var rating = parseInt(starElement.getAttribute('data-rating'), 10);
        var stars = '';
        for (var i = 0; i < 5; i++) {
            if (i < rating) {
                stars += '<span>&#x2605;</span>';
            } else {
                stars += '<span>&#x2606;</span>';
            }
        }
        starElement.innerHTML = stars;
    });
}
    showStarRating(); // 페이지가 로드될 때 별점 표시


$(document).on("click", ".quantity-right-plus", function(e){
    // + 버튼을 클릭하면 수량 증가
    var $quantityInput = $(this).parent().find(".input-number");
    var quantity = parseInt($quantityInput.val());
    if (quantity < 20) { // 최대 수량은 20
        $quantityInput.val(quantity + 1);
    }
});

$(document).on("click", ".quantity-left-minus", function(e){
    // - 버튼을 클릭하면 수량 감소
    var $quantityInput = $(this).parent().find(".input-number");
    var quantity = parseInt($quantityInput.val());
    if(quantity > 1){
        $quantityInput.val(quantity - 1);
    }
});
// 스크롤 위치를 감지하여 버튼을 표시하거나 숨김
window.onscroll = function() {scrollFunction()};

function scrollFunction() {
  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
    document.getElementById("scrollToTopBtn").style.display = "block";
  } else {
    document.getElementById("scrollToTopBtn").style.display = "none";
  }
}


//문의하기 버튼 클릭 시 폼 제출 및 페이지 리로드 함수
function submitFormAndReload() {
    // 폼 요소 참조
    var form = document.getElementById('questionForm');

    // 폼 제출
    form.submit();

    // 모달 닫기 및 폼 초기화
    closeModal();
}

//모달 열기 함수
function openModal() {
    document.getElementById('myModal').style.display = 'block';
}

//모달 닫기 함수
function closeModal() {
    document.getElementById('myModal').style.display = 'none';
    clearForm(); // 폼 요소 초기화 함수 호출
}

function clearForm() {
    document.getElementById('q_type').value = ''; // 문의 유형 선택
    document.getElementById('title').value = ''; // 문의 제목 초기화
    document.getElementById('content').value = ''; // 문의 내용 초기화
}

// 모달 창 외부를 클릭하여 모달 닫기
window.onclick = function(event) {
    var modal = document.getElementById('myModal');
    if (event.target == modal) {
        closeModal(); // 모달 닫기 함수 호출
    }
}
$(document).ready(function(){
	$("#writeQuestion").click(function(){
		alert("문의를 작성합니다.");
        var currentDate = new Date();
        var formattedDate = currentDate.toISOString();
		var question = {
			"title" : $("#title").val(),
			"q_type" : $("#q_type").val(),
			"content" : $("#content").val(),
			"product_code" : $("#product_code").val(),
			"regdate" : formattedDate
		};
	$.ajax({
		type : "POST",
		url : "/productMain",
		data : question,
		contentType : "application/json; charset=UTF-8",
		success : function(data){
			$("#title").val("");
			$("#q_type").val("");
			$("#content").val("");
			$("#product_code").val("");
			$("#regdate").val("");
		},
		error : function(){
			alert("실패");
		}
		});
	});
});
</script>
</head>

<body>
<section class="py-0">
    <div class="container px-4 px-lg-5 my-5">
        <div class="row gx-4 gx-lg-5 align-items-center">
            <div class="col-md-6"><img class="product-image" src="${pageContext.request.contextPath}/resources/images/${product.img1}" alt="..." /></div>
            <div class="col-md-6">
                <div class="small mb-1">카테고리 : ${product.category }</div>
                <h1 class="display-5 fw-bolder">${product.product_name }</h1>
                <div class="fs-5 mb-5">
                    <span>${product.price }원</span>
                </div>
                <p class="lead">${product.product_explain } 대충 상품 설명</p>
					<div class="d-flex align-items-center ">
						<div class="input-group product-qty" style="width: 200px">
							<span class="input-group-btn">
								<button type="button" class="quantity-left-minus btn btn-danger btn-number" data-type="minus">
									<svg width="16" height="16">
										<use xlink:href="#minus"></use></svg>
								</button>
							<input type="text" name="quantity" class="form-control1 input-number quantity" value="1" style="width: 20px;"> 
								<button type="button" class="quantity-right-plus btn btn-success btn-number" data-type="plus">
									<svg width="16" height="16">
										<use xlink:href="#plus"></use></svg>
								</button>
							</span> 
						</div>
						<a href="#" class="nav-link">장바구니<svg width="18" height="18">
						<use xlink:href="#cart"></use></svg></a>
					</div>
				</div>
        </div>
    </div>
</section>

<div class="container">
    <div class="row nav">
        <nav id="middle_nav">
            <ul class="nav nav-tabs nav-justified">
                <li id="about"><a href="#about1">상품 상세</a></li>
                <li id="review"><a href="#review1">리뷰</a></li>
                <li id="qna"><a href="#qna1">상품 문의</a></li>
            </ul>
        </nav>
    </div>
</div>

<div class="row" style="margin: 50px 0;">
    <h1 class="jumbotron">
        <div class="container1">
            <small>This is product page.</small>
        </div>
    </h1>
</div>

<div class="row about_product" style="text-align: center;">
    <h1 class="page-header" id="about1">상품 상세</h1>
    <div style="text-align: center; margin: 0 auto;">
    <img class="product-img2" src="${pageContext.request.contextPath}/resources/images/${product.img2}" style="width: 400px; height: 400px; margin: 10px; display: inline-block;"/>
    <img class="product-img3" src="${pageContext.request.contextPath}/resources/images/${product.img3}" style="width: 400px; height: 400px; margin: 10px; display: inline-block;"/>
	</div>
</div>
<div class="row reviews" style="text-align: center;">


<hr>

<div class="container"></div>
    <h1 class="page-header" style="margin-bottom: 50px;" id="review1">Review</h1>
    <table class="table table-bordered">
    	<tbody>
    		<tr>
				<th>Title</th>
				<th>Writer</th>
				<th>Content</th>
				<th>Star</th>
				<th>img1</th>
				<th>Regdate</th>
    		</tr>
		<c:forEach var="review" items="${review }">
			<tr>
				<td>${review.title }</td>
				<td>${review.user_id }</td>
				<td>${review.content }</td>
		        <td>
		            <span class="star-rating" data-rating = ${review.star }>
		                <c:forEach begin="1" end="${review.star}">
		                    &#9733; <!-- 별 모양의 아이콘 -->
		                </c:forEach>
		            </span>
		        </td>
				<td><img src="${pageContext.request.contextPath}/resources/images/${review.img1}" style="width: 100px; height: 100px;"></td>
				<td><fmt:formatDate value="${review.regdate }"/></td>
			</tr>
		</c:forEach>    		
    	</tbody>
    </table>
</div>

<hr>

<div style="text-align: center; height: 700px;">
    <h1 class="page-header" id="qna1">상품 Q&A</h1>
	 <table class="table table-bordered">
    	<tbody>
    		<tr>
				<th>문의유형</th>
				<th>작성자</th>
				<th>문의제목</th>
				<th>작성일</th>
    		</tr>
		<c:forEach var="question" items="${question }">
			<tr>
				<td>${question.q_type }</td>
				<td>${question.user_id }</td>
				<td>${question.title }</td>
				<td><fmt:formatDate value="${question.regdate }"/></td>
			</tr>
		</c:forEach>    		
    	</tbody>
    </table>
	<a href="/market/questionMain"> 전체 문의 보러가기</a>
 		
    <button type="button" class="ask-button" onclick="openModal()">문의하기</button>
    <!-- 모달 -->
    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <!-- 문의할 수 있는 양식 등을 추가합니다. -->
            <h2>문의하기</h2>
            <form action="" method="post" id="questionForm">
            <input type="hidden" id="product_code" name="product_code" value="${product.product_code }">
				<div class="box-body">
					<div class="form-group" style="margin-bottom: 20px;">
						<label for="exampleInputPassword1">문의 유형</label> 
							<select class="form-control" id="q_type" name="q_type">
								<option value="1">배송 문의</option>
								<option value="2">상품 문의</option>
							</select>
						</div>
					<div class="form-group" style="margin-bottom: 20px;">
						<label for="exampleInputEmail1">문의 제목</label> <input type="text" class="form-control" id="title" placeholder="문의 유형" name="title">
					</div>
					<div class="form-group" style="margin-bottom: 20px;">
						<label>내 용</label>
						<textarea class="form-control" rows="3" id="content" placeholder="문의 내용을 입력하세요" name="content"></textarea>
					</div>
				</div>
				<br>
				<div class="box-footer">
					<button type="submit" class="btn btn-primary" id="writeQuestion">글 쓰기</button>
				</div>
			</form>
        </div>
    </div>

</div>



<!-- Scroll to Top button -->
<button onclick="topFunction()" id="scrollToTopBtn" title="맨 위로 이동">
  <i class="bi bi-arrow-up-circle-fill" style="font-size: 24px;"></i>
</button>

<%@ include file="../include/footer.jsp"%>
