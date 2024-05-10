<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../include/header.jsp" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<title>구독 정보 목록</title>
<style>
	.container {
        max-width: 1200px;
        margin: 20px auto;
        padding: 20px;
        background-color: #fff;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }


</style>

<script type="text/javascript">

</script>


<div class="container">
    <h1>구독정보 목록</h1>
    ${sub }
<c:forEach var="item" items="${sub}">
    상품 이름: ${item.productVO.product_name}<br/>
</c:forEach>  
<%--             <c:forEach items="${sub}" var="sub"> --%>
<!--                 <div class="col" style="width: 19%;"> -->
<!--                     <div class="product-item"> -->
<!--                         <button id="wishProduct" class="btn-wishlist"><svg width="24" height="24"><use xlink:href="#heart"></use></svg></button> -->
<%--                          <input type="hidden" id="product_code" value="${sub.product_code }"> --%>
<!--                         <figure> -->
<%--                             <a href="productMain?product_code=${sub.product_code }" title="Product Title"> --%>
<%--                                 <img src="${pageContext.request.contextPath}/resources/images/product/${sub.img1}" alt="Product Thumbnail" class="tab-image" style="width : 180px"> --%>
<!--                             </a> -->
<!--                         </figure> -->
<%--                         <h4>${sub.product_name}</h4> --%>
<%--                         <span class="price"><fmt:formatNumber value="${sub.price}" pattern="#,##0" />원</span> --%>
<!--                         <div class="input-group product-qty d-flex align-items-center justify-content-between"> -->
<!--                             <div class="input-group product-qty"> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<%--             </c:forEach> --%>
</div>+

<%@include file="../include/footer.jsp" %>