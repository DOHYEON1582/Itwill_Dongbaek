<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../include/header.jsp" %>

<title>전체 제품 목록</title>
<style>
    .container {
        margin: 20px auto;
        padding: 20px;
        background-color: #fff;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    h1 {
        text-align: center;
        margin-bottom: 30px;
    }
    .product-items {
        display: grid;
        grid-template-columns: repeat(4, 1fr);
        grid-gap: 20px; /* 이미지 간격 조정 */
    }
    .product-item {
        text-align: center;
    }
    .product-item img {
        width: 250px;
        height: 250px;
        display: block;
        margin: 0 auto;
        margin-bottom: 10px; /* 이미지 아래 간격 조정 */
    }
    .heart-icon {
        position: absolute;
        bottom: 100px;
        right: 30px;
        color: pink;
        font-size: 60px;
    }
	.product-item .btn-wishlist {
	  position: absolute;
	  top: 20px;
	  right: 20px;
	  width: 40px;
	  height: 40px;
	  border-radius: 50px;
	  display: flex;
	  align-items: center;
	  justify-content: center;
	  background: #fff;
	  border: 1px solid #d8d8d8;
	  transition: all 0.3s ease-out;
	}
	.product-item .btn-wishlist:hover {
	  background: rgb(240, 56, 56);
	  color: #fff;
	}
</style>

<script type="text/javascript">
$(document).ready(function(){
/* 	$(".btn-prowish").click(function(){
		alert("찜!");
		var btn = $(this);
		var product_code = btn.siblings("#product_code").val();
		var userId = btn.siblings("#user_id").val();
		
		var wish = {
				"product_code" : product_code,
				"user_id" : userId
		};
		$.ajax({
			url : "/market/addWish",
			type : "POST",
			data : JSON.stringify(wish),
			contentType : "application/json; charset=UTF-8",
			success : function(data){
				var isWished = data.isWished;
				if (isWished) {
					btn.addClass("wished");
					console.log(isWished);
				} else {
					btn.removeClass("wished");
				}
			},
			error: function(xhr, status, error) {
				var errorMessage = xhr.status + ': ' + xhr.statusText;
				console.log(" error "+ error);
			}
		});
	}); */
	
	
	// 제품 찜 추가, 삭제 
	$('.btn-wishlist').on('click',function(){
		var product_code = $(this).val();
		var heartIcon = $(this).find('svg');
		console.log(heartIcon);
		 
		$.ajax({
			url : "/product/insertWish1/"+product_code,
			type : "GET",
			success : function(data){
				//console.log(data);
				if(data == 1){
					console.log('1!!!!!');
					 heartIcon.css("color", "red");
				}else{
					console.log('0!!!!!');
					 heartIcon.css("color", "");
				}
			}
		});
		
	});
});
</script>

<body>
    <div class="container">
        <h1>전체 제품 목록</h1>
         <!-- 정렬 옵션 드롭다운 메뉴 -->
        <form action="" method="get">
            <select name="orderBy">
                <option value="popularity" ${param.orderBy == 'popularity' ? 'selected' : ''}>인기순</option>
                <option value="lowPrice" ${param.orderBy == 'lowPrice' ? 'selected' : ''}>낮은 가격순</option>
                <option value="highPrice" ${param.orderBy == 'highPrice' ? 'selected' : ''}>높은 가격순</option>
            </select>
            <input type="submit" value="정렬">
        </form>
        
        <div class="product-items">
            <c:forEach var="product" items="${productList}">
                <div class="product-item">
                        <button class="btn-wishlist" value="${product.product_code }">
                        <!-- 위시리스트 색변화 -->
                        	<c:set var="found" value="false" />
	                        	<c:forEach items="${wishList }" var="wishList">
		                            	<c:if test="${wishList.product_code eq product.product_code }">
	                        				<svg id="check" style="color: red;" width="24" height="24"><use xlink:href="#heart"></use></svg>
	                        				<c:set var="found" value="true" />
	                        			</c:if>
		                        </c:forEach>
			                    <c:if test="${not found}">
			                        <svg id="check" width="24" height="24"><use xlink:href="#heart" ></use></svg>
								</c:if>
						<!-- 위시리스트 색변화 -->	
                        </button>
	                <input type="hidden" id="product_code" value="${product.product_code }">
                    <img alt="Product Image" src="../resources/images/product/${product.img1}">
                    <p> 상품명 : ${product.product_name}</p>
                    <p> 가격 : <fmt:formatNumber value="${product.price}" pattern="#,##0"/>원</p>
                </div>
            </c:forEach>
        </div>
    </div>
    
</body>

<%@include file="../include/footer.jsp" %>