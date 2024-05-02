<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 제품 목록</title>
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
</style>
</head>
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
                    <img alt="Product Image" src="../resources/images/product/${product.img1}">
                    <p> 상품명 : ${product.product_name}</p>
                    <p> 가격 : <fmt:formatNumber value="${product.price}" pattern="#,##0"/>원</p>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
