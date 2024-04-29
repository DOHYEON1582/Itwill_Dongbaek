<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
        font-size: 80px;
    }
</style>
</head>
<body>

<div class="container">
    <h1>찜 목록</h1>
    <form action="deleteWish" method="post">
	    <div class="wishlist-items">
	        <c:forEach var="wishList" items="${wishList}">
	            <div class="wishlist-item">
	                <input type="checkbox" name="product_code" value="${wishList.product_code }" > 선택
	                <img alt="Product Image" src="../resources/images/product/${wishList.img1}">
	                <p>${wishList.product_name}</p>
	                <p><fmt:formatNumber value="${wishList.price}" pattern="#,##0"/>원</p>
	                <span class="heart-icon">&#x2665;</span> <!-- 하트 아이콘 추가 -->
	            </div>
	        </c:forEach>
	    </div>
	    <button type="submit">선택한 상품 삭제</button>
    </form>
</div>

</body>
</html>