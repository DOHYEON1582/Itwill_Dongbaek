<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../include/header.jsp" %>

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> -->

<title>구독 정보 목록</title>
<style>
h1 {
	text-align: center;
	margin-bottom: 30px;
}

.sub-items {
	display: grid;
	grid-template-columns: repeat(4, 1fr);
	grid-gap: 5px; /* 이미지 간격 조정 */
}

.sub-item {
	text-align: center;
	position: relative;
}

.sub-item img {
	width: 250px;
	height: 250px;
	display: block;
	margin: 0 auto;
	margin-bottom: 5px; /* 이미지 아래 간격 조정 */
}

.container {
	max-width: 1200px;
	margin: 20px auto;
	padding: 20px;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
.heart-icon {
    position: absolute;
    bottom: 100px;
    right: 30px;
    color: pink;
    font-size: 60px;
}
.btn-sub {
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
.btn-sub:hover {
  background: rgb(240, 56, 56);
  color: #fff;
}
</style>

<script type="text/javascript">
	$(document).ready(function(){
		$("#deleteAllButton").click(function(){
			var confirmation = confirm("전체 찜 목록을 삭제하시겠습니까?");
			if (confirmation) {
				$.ajax({
					url : "deleteSubAll",
					type : "POST",
					success : function(data){
						console.log(" 삭제 완료 ");
					},
					error : function(xhr, status, error){
						console.error("삭제 실패:", error);
					},
					complete: function() {
						location.reload();
					}
				});
			}
		});
		$(".btn-sub").click(function(){
			var product_code = $(this).closest('.sub-item').find('input').data('productCode');
			var confirmation = confirm("해당 상품을 찜 목록에서 삭제하시겠습니까?");
			if(confirmation){
				$.ajax({
					url : "deleteSub",
					type : "POST",
					data : {product_code : product_code},
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
	<h1> ${sessionScope.userVO.user_name }님의 구독정보 목록</h1>
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
	<div class="sub-items">
		<c:forEach items="${subSort}" var="subscribe">
			<div class="sub-item">
				<c:forEach items="${subscribe.productVO}" var="product">
					<c:if test="${not empty product}">
						<div class="btn-sub">
		                    <svg width="24" height="24"><use xlink:href="#heart"></use></svg>
		                </div>
		                <input type="hidden" name="product_code" data-product-code="${product.product_code}">
						<img alt="Product Image" src="../resources/images/product/${product.img1}">
						<p>상품명: ${product.product_name}</p>
						<p>가격 :<fmt:formatNumber value="${product.price}" pattern="#,##0" />원</p>
						<p>원산지: ${product.country}</p>
					</c:if>
				</c:forEach>
			</div>
		</c:forEach>
	</div>
</div>

<%@include file="../include/footer.jsp" %>