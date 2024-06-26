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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
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


    table {
        width: 100%;
        border-collapse: collapse;
    }

    th, td {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
    }

    th {
        background-color: #f2f2f2;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    tr:hover {
        background-color: #dddddd;
    }
    .pagination {
        display: inline-block;
    }

    .pagination li {
        display: inline;
        padding: 0 5px;
    }

    .pagination li.active {
        font-weight: bold;
    }

    .pagination li.disabled {
        pointer-events: none;
        cursor: default;
    }
</style>
<script type="text/javascript">
// 맨 위로 스크롤되도록 설정
function topFunction() {
  document.body.scrollTop = 0; // For Safari
  document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
}

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

//문의하기 버튼 클릭 시 폼 제출 및 모달 닫기 함수
function submitFormAndCloseModal() {
    var form = document.getElementById('questionForm');
    $.ajax({
        type: "POST",
        url: "/market/productMain",
        data: $(form).serialize(),
        success: function(data) {
            closeModal(); // 모달 닫기
            location.reload(); // 페이지 리로드
        },
        error: function(xhr, status, error) {
            var errorMessage = xhr.status + ': ' + xhr.statusText;
            alert('에러가 발생했습니다.\n' + errorMessage);
        }
    });
}

// 모달 열기 함수
function openModal() {
    document.getElementById('myModal').style.display = 'block';
}

// 모달 닫기 함수
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
//문의하기 버튼 클릭 이벤트 핸들러
$(document).ready(function() {
    $("#writeQuestion").click(function() {
        alert("문의를 작성합니다.");
        var currentDate = new Date();
        var formattedDate = currentDate.toISOString();
        var question = {
            "title": $("#title").val(),
            "q_type": $("#q_type").val(),
            "user_id": $("#user_id").val(),
            "user_name": $("#user_name").val(),
            "content": $("#content").val(),
            "product_code": $("#product_code").val(),
            "regdate": formattedDate
        };
        $.ajax({
            type: "POST",
            url: "/market/productMain",
            data: JSON.stringify(question),
            contentType: "application/json; charset=UTF-8",
            success: function(data) {
                closeModal(); // 모달 닫기
                location.reload(); // 페이지 리로드
            },
            error: function(xhr, status, error) {
                var errorMessage = xhr.status + ': ' + xhr.statusText;
                alert('에러가 발생했습니다.\n' + errorMessage);
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
            <div class="col-md-6"><img class="product-image" src="${pageContext.request.contextPath}/resources/images/product/${product.img1}" alt="..." /></div>
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
                <li id="qna"><a href="#qna1">상품 문의</a></li>
            </ul>
        </nav>
    </div>
</div>

<div class="row reviews" style="text-align: center;"></div>


<div class="container" style="text-align: center; height: 700px;">
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
<%-- 				<td>${question.q_type }</td> --%>
				<td>
				<c:choose>
					<c:when test="${question.q_type eq 1}">
						배송문의
	                </c:when>
	                <c:when test="${question.q_type eq 2}">
	                    상품문의
	                </c:when>
	            </c:choose>
				</td>
				<td>${question.user_id }</td>
				<td>${question.title }</td>
				<td><fmt:formatDate value="${question.regdate }"/></td>
			</tr>
		</c:forEach>    		
    	</tbody>
    </table>
		<div class="dataTables_paginate paging_simple_numbers" id="example2_paginate">
			<ul class="pagination">
				<c:if test="${pageVO.prev }">
					<li class="paginate_button previous disabled" id="example2_previous">
						<a href="/market/questionMain?product_code=${product.product_code }&page=${pageVO.startPage - 1 }&pageSize=${cri.pageSize}"
						aria-controls="example2" data-dt-idx="0"
                            tabindex="0">«</a></li>
				</c:if>
				<c:forEach var="idx" begin="${pageVO.startPage }" end="${pageVO.endPage }" step="1">
					<li ${pageVO.cri.page == idx? "class='active'":""}>
					<a href="/market/questionMain?product_code=${product.product_code }&page=${idx }&pageSize=${cri.pageSize}"
					aria-controls="example2" data-dt-idx="1" tabindex="0">${idx }</a></li>
				</c:forEach>
				<c:if test="${pageVO.next }">
					<li class="paginate_button next" id="example2_next">
						<a href="/market/questionMain?product_code=${product.product_code }&page=${pageVO.endPage + 1 }&pageSize=${cri.pageSize}"
						aria-controls="example2" data-dt-idx="7" tabindex="0">»</a>
					</li>
				</c:if>
			</ul>
		</div>

		<a href="/market/productMain?product_code=${product.product_code }"> 상품 보러가기</a>
 		
    <button type="button" class="ask-button" onclick="openModal()">문의하기</button>
    <!-- 모달 -->
    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <!-- 문의할 수 있는 양식 등을 추가합니다. -->
            <h2>문의하기</h2>
            <div method="post" id="questionForm">
            <input type="hidden" id="product_code" name="product_code" value="${product.product_code }">
            <input type="hidden" id="user_id" name="user_id" value="${sessionScope.userVO.user_id }">
            <input type="hidden" id="user_name" name="user_name" value="${sessionScope.userVO.user_name }">
				<div class="box-body">
					<div class="form-group" style="margin-bottom: 20px;">
						<label for="exampleInputPassword1">문의 유형</label> 
							<select class="form-control" id="q_type" name="q_type">
								<option value="">문의 유형을 선택하세요</option>
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
			</div>
        </div>
    </div>



<!-- Scroll to Top button -->
<button onclick="topFunction()" id="scrollToTopBtn" title="맨 위로 이동">
  <i class="bi bi-arrow-up-circle-fill" style="font-size: 24px;"></i>
</button>

<%@ include file="../include/footer.jsp"%>
