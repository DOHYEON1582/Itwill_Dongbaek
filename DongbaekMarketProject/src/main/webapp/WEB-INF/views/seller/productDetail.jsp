<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 페이지</title>
<!-- 여기에 CSS 링크 또는 스타일 시트를 추가할 수 있습니다. -->
</head>
<body>
    <h1>상품 상세 페이지</h1>
    
    <div>
        <h2>상품 정보</h2>
        <p>상품명: ${product.product_name}</p>
        <p>가격: ${product.price}</p>
        <!-- 필요한 다른 상품 정보를 여기에 추가할 수 있습니다. -->
    </div>
    
    <div>
        <h2>상품 이미지</h2>
        <c:choose>
            <c:when test="${not empty product.img1}">
                <img src="${product.img1}" alt="상품 이미지">
            </c:when>
            <c:when test="${not empty product.img2}">
                <img src="${product.img2}" alt="상품 이미지">
            </c:when>
            <c:when test="${not empty product.img3}">
                <img src="${product.img3}" alt="상품 이미지">
            </c:when>
            <c:otherwise>
                <img src="/resources/images/noimg.png" alt="상품 이미지 없음">
            </c:otherwise>
        </c:choose>
    </div>
    
    <!-- 상품 상세 설명 등 다른 정보를 추가할 수 있습니다. -->
    
    <!-- 여기에 JavaScript 링크 또는 스크립트를 추가할 수 있습니다. -->
</body>
</html>
