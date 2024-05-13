<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../include/header.jsp" %>

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> -->

<title>찜 목록</title>
<style>
	.container {
        max-width: 1200px;
        margin: 20px auto;
        padding: 20px;
        background-color: #fff;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    h1 {
        text-align: center;
        margin-bottom: 30px;
    }
    .wishlist-items {
        display: grid;
        grid-template-columns: repeat(4, 1fr);
        grid-gap: 5px; /* 이미지 간격 조정 */
    }
    .wishlist-item {
        text-align: center;
        position: relative;
    }
    .wishlist-item img {
        width: 250px;
        height: 250px;
        display: block;
        margin: 0 auto;
        margin-bottom: 5px; /* 이미지 아래 간격 조정 */
    }
    .heart-icon {
        position: absolute;
        bottom: 100px;
        right: 30px;
        color: pink;
        font-size: 60px;
    }
    .btn-wishlist {
     position: absolute;
     top: 0;
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
   .btn-wishlist:hover {
     background: rgb(240, 56, 56);
     color: #fff;
   }
   #deleteAllButton {
   	margin-left: 20px;
   	margin-bottom: 10px;
   }
</style>

<script type="text/javascript">
    $(document).ready(function(){
    	$("#deleteAllButton").click(function(){
            var confirmation = confirm("전체 찜 목록을 삭제하시겠습니까?");
            if (confirmation) {
                $.ajax({
                    url : "deleteWishAll",
                    type : "post",
                    success : function(data){
                    	console.log(" 삭제 완료 ");
                    },
                    error: function(xhr, status, error) {
                        console.error("삭제 실패:", error);
                    },
                    complete: function() {
                        location.reload();
                    }
                });
            }
        });
    	
        $(".btn-wishlist").click(function(){
            var product_code = $(this).closest('.wishlist-item').find('a').data('product-code');
            var confirmation = confirm("해당 상품을 찜 목록에서 삭제하시겠습니까?");
            if (confirmation) {
                $.ajax({
                    url : "deleteWish",
                    type : "post",
                    data: { product_code: product_code },
                    success : function(data){
                    	console.log(" 삭제 완료 ");
                    },
                    error: function(xhr, status, error) {
                        console.error("삭제 실패:", error);
                    },
                    complete: function() {
                        location.reload();
                    }
                });
            }
            
        });
    });
</script>


<div class="container">
    <h1>찜 목록</h1>
    
     <!-- 정렬 옵션 드롭다운 메뉴 -->
    <form action="" method="get">
        <select name="orderBy">
            <option value="popularity" ${param.orderBy == 'popularity' ? 'selected' : ''}>인기순</option>
            <option value="lowPrice" ${param.orderBy == 'lowPrice' ? 'selected' : ''}>낮은 가격순</option>
            <option value="highPrice" ${param.orderBy == 'highPrice' ? 'selected' : ''}>높은 가격순</option>
        </select>
        <input type="hidden" name="user_id" value="${sessionScope.userVO.user_id}">
        <input type="submit" value="정렬">
    </form>
    
    <button id="deleteAllButton" class="btn btn-danger">전체 삭제</button>
    <div class="wishlist-items">
        <c:forEach var="wishList" items="${wishList}">
            <div class="wishlist-item">
                <div class="btn-wishlist">
                    <svg width="24" height="24"><use xlink:href="#heart"></use></svg>
                </div>
                <a href="review?product_code=${wishList.product_code}" data-product-code="${wishList.product_code}">
                    <img alt="Product Image" src="../resources/images/product/${wishList.img1}">
                </a>
                <p> 상품명 : ${wishList.product_name}</p>
                <p> 가격 : <fmt:formatNumber value="${wishList.price}" pattern="#,##0"/>원</p>
                <p> 가게명 : 
                <c:forEach var="store" items="${wishList.storeVO}">
                    ${store.store_name}<c:if test="${not loop.last}"></c:if>
	                </c:forEach>
	            </p>
            </div>
        </c:forEach>
    </div>
</div>

<%@include file="../include/footer.jsp" %>