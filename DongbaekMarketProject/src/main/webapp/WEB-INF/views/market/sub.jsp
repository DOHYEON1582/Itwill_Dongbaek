<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../include/header.jsp" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<title>구독 제품 목록</title>
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
    
    	.bxslider{
 		display: inline-block;
        margin-right: 20px; /* 이미지 슬라이더 오른쪽에 공간 추가 */
	}
    #sijang_text {
        display: inline-block;
        vertical-align: top;
        margin-left: 50px; /* 시장 정보 왼쪽에 공간 추가 */
        font-family: 'Gowun Dodum', sans-serif;
        font-size: 25px;
    }
    #sijamg_top {
        display: flex;
        justify-content: center; /* 페이지 가운데 정렬 */
        align-items: flex-start; /* 시장 정보 컨테이너를 상단에 정렬 */
    }
    table {
        border-collapse: separate;
        border-spacing: 0 20px; /* 상하로 30px 간격 지정 */
        margin-top: 10px; /* 테이블 위쪽에 20px 간격 추가 */
    }
    th, td {
        padding-left: 8px; /* 셀 내부 왼쪽 간격 지정 */
    }
	.slider-container {
	    display: flex;
	    justify-content: center;
	}
	.product-item {
	  position: relative;
	  padding: 16px;
	  background: #FFFFFF;
	  border: 1px solid #FBFBFB;
	  box-shadow: 0px 5px 22px rgba(0, 0, 0, 0.04);
	  border-radius: 16px;
	  margin-bottom: 30px;
	  transition: box-shadow 0.3s ease-out;
	}
	.product-item:hover {
	  box-shadow: 0px 21px 44px rgba(0, 0, 0, 0.08);
	}
	.product-item h3 {
	  display: block;
	  width: 100%;
	  font-weight: 600;
	  font-size: 18px;
	  line-height: 25px;
	  text-transform: capitalize;
	  color: #333333;
	  margin: 0;
	}
	.product-item figure {
	  background: #F9F9F9;
	  border-radius: 12px;
	  text-align: center;
	}
	.product-item figure img {
	  max-height: 210px;
	  height: auto;
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
	.product-item .qty {
	  font-weight: 400;
	  font-size: 13px;
	  line-height: 18px;
	  letter-spacing: 0.02em;
	  text-transform: uppercase;
	  color: #9D9D9D;
	}
	.product-item .rating {
	  font-weight: 600;
	  font-size: 13px;
	  line-height: 18px;
	  text-transform: capitalize;
	  color: #222222;
	}
	.product-item .rating iconify-icon {
	  color: #FFC43F;
	}
	.product-item .price {
	  display: block;
	  width: 100%;
	  font-weight: 600;
	  font-size: 22px;
	  line-height: 30px;
	  text-transform: capitalize;
	  color: #222222;
	}
	.product-item .product-qty {
	  width: 85px;
	}
	.product-item .btn-link {
	  text-decoration: none;
	}
	.product-item #quantity {
	  height: auto;
	  width: 28px;
	  text-align: center;
	  border: none;
	  margin: 0;
	  padding: 0;
	}
	.product-item .btn-number {
	  width: 26px;
	  height: 26px;
	  line-height: 1;
	  text-align: center;
	  background: #FFFFFF;
	  border: 1px solid #E2E2E2;
	  border-radius: 6px;
	  color: #222;
	  padding: 0;
	}
	
	h3 {
    padding-left: 320px; /* 왼쪽 여백을 20px로 설정 */
    font-family: 'Gowun Dodum', sans-serif;
    font-weight: bold;
	}
    .store-link {
        text-decoration: none; /* 밑줄 제거 */
        color: #333; /* 링크 색상 */
        font-size: 16px; /* 글자 크기 */
        font-weight: bold; /* 글자 굵기 */
        font-family: 'Gowun Dodum', sans-serif;
    }

    .store-link:hover {
        color: purple; /* 마우스 오버시 텍스트 색상 */
    }
</style>
<body>
<%-- ${wishList }
${productList } --%>
    <div class="container">
        <h1>구독 제품 목록</h1>
         <!-- 정렬 옵션 드롭다운 메뉴 -->
        <form action="" method="get">
            <select name="orderBy">
                <option value="popularity" ${param.orderBy == 'popularity' ? 'selected' : ''}>인기순</option>
                <option value="lowPrice" ${param.orderBy == 'lowPrice' ? 'selected' : ''}>낮은 가격순</option>
                <option value="highPrice" ${param.orderBy == 'highPrice' ? 'selected' : ''}>높은 가격순</option>
            </select>
            <input type="submit" value="정렬">
        </form>
        
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-6 justify-content-center gap-3">
            <c:forEach items="${productList}" var="product">
                <div class="col" style="width: 19%;">
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
                        <figure>
                            <a href="productMain?product_code=${product.product_code }" title="Product Title">
                                <img src="${pageContext.request.contextPath}/resources/images/carrot.jpg" alt="Product Thumbnail" class="tab-image" style="width : 130px">
                            </a>
                        </figure>
                        <h4>${product.product_name}</h4>
                        <span class="qty">${product.unit}</span><span class="rating"><svg width="24" height="24" class="text-primary"><use xlink:href="#star-solid"></use></svg> 4.5</span>
                        <span class="price"><fmt:formatNumber value="${product.price}" pattern="#,##0" />원</span>
                        <div class="d-flex align-items-center justify-content-between">
                            <div class="input-group product-qty">
                                <span class="input-group-btn">
		                        <button type="button" class="quantity-left-minus btn btn-danger btn-number" data-type="minus">
		                            <svg width="16" height="16"><use xlink:href="#minus"></use></svg>
		                         </button>
                                </span>
                                <input type="text" name="quantity" class="form-control input-number quantity" value="1">
                                <span class="input-group-btn">
                                    <button type="button" class="quantity-right-plus btn btn-success btn-number" data-type="plus">
                                        <svg width="16" height="16"><use xlink:href="#plus"></use></svg>
                                    </button>
                                </span>
                            </div>
                            <button href="#" class="nav-link" style="font-size: 10px;" value="${product.product_code }">구독추가<svg width="18" height="18"><use xlink:href="#cart"></use></svg></button>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>


<script>
$(document).ready(function(){
	// count 증가
	 $(".quantity-right-plus").click(function(e){
	    	var quantity = parseInt($(this).parent().prev().val());
	    	$(this).parent().prev().val(quantity + 1);
	    });
	    $(".quantity-left-minus").click(function(e){
	    	var quantity = parseInt($(this).parent().next().val());
	        if(quantity > 1){
	            $(this).parent().next().val(quantity - 1);
	        }
	    });
	
		
	
	
	// 제품 찜 추가, 삭제 
	$('.btn-wishlist').on('click',function(){
		var product_code = $(this).val();
		var heartIcon = $(this).find('svg');
		console.log(heartIcon);
		 
		$.ajax({
			url : "/product/insertWish/"+product_code,
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
	
	// 제품 구독 추가
	$('.nav-link').on('click',function(){
		var product_code = $(this).val();
		var count = $(this).parent().find('input').val();
		console.log(count);
		
		 $.ajax({
			url : "/product/addsub/"+product_code+"/"+count,
			type : "GET",
			success : function(data){
				if(data == 1){
					alert("구독리스트 추가 완료!")
				}else{
					alert("구독리스트 추가 실패!")
				}
			} 
		}); 
	});
	
	
	
});




</script>

</body>

<%@include file="../include/footer.jsp" %>