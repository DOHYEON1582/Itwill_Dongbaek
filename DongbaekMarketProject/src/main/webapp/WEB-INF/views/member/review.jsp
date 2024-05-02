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

    // 페이지 로드 후 별점 표시
    showStarRating();
</script>
</head>

<div class="container">
    <h1>상품</h1>
    <div class="wishlist-items">
        <c:forEach var="wishList" items="${product}">
            <div class="wishlist-item">
                <a href="#" class="btn-wishlist"><svg width="24" height="24"><use xlink:href="#heart"></use></svg></a>
                <a href="review?product_code=${wishList.product_code }"><img alt="Product Image" src="../resources/images/product/${wishList.img1}"> </a>
                <p>${wishList.product_name}</p>
                <p><fmt:formatNumber value="${wishList.price}" pattern="#,##0"/>원</p>
            </div>
        </c:forEach>
    </div>
    
    <h1>리뷰</h1>
    <div class="wishlist-items">
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
		            <span class="star-rating"  data-rating = ${review.star }>
		                <c:forEach begin="1" end="${review.star}">
		                    	&#9733; <!-- 별 모양의 아이콘 -->
		                </c:forEach>
		            </span>
		        </td>
				<td><img src="${pageContext.request.contextPath}/resources/images/product/${review.img1}" style="width: 100px; height: 100px;"></td>
				<td><fmt:formatDate value="${review.regdate }"/></td>
			</tr>
		</c:forEach>    		
    	</tbody>
    </table>

    </div>
    
</div>

<%@ include file="../include/footer.jsp"%>