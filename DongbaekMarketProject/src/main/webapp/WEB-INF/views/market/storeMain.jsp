<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<script>
    $(document).ready(function(){
        $('.bxslider').bxSlider({
            infiniteLoop: false,
            hideControlOnEnd: true,
            slideWidth: 500
        });

        $('.slider5').bxSlider({
            slideWidth: 200,
            minSlides: 6,
            maxSlides: 6,
            moveSlides: 6,
            slideMargin: 10,
            adaptiveHeight: true, // 높이를 자동 조정하여 일관된 높이 유지
            pager: false,
            touchEnabled: false
        });
        
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
    });
</script>

<style>

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
    .button-follow {
       padding: 5px 10px; /* 내부 여백(padding)을 조정하여 버튼의 크기 조절 */
       font-size: 15px;
       background-color: #FF69B4; /* 분홍색 배경색 */
       color: #FFF; /* 텍스트 색상 */
       border: 1px solid #FF69B4; /* 테두리 선 색상 */
       padding: 8px 16px; /* 내부 여백(padding) 설정 */
       border-radius: 10px; /* 버튼 모서리 둥글게 */
       cursor: pointer; /* 포인터 모양으로 변경 */
    }
  .button-follow:hover {
        background-color: #FF1493; /* 마우스 오버시 배경색 변경 */
    }

    .button-follow img {
        vertical-align: middle; /* 아이콘 세로 가운데 정렬 */
        margin-right: 6px; /* 아이콘과 텍스트 사이 간격 */
    }

    .button-follow .text {
        font-weight: bold; /* 텍스트 굵게 설정 */
    }
</style>
<!-- 시장정보 -->
<section class="py-2 mb-1" style="background: url(${pageContext.request.contextPath}/resources/images/background-pattern.jpg);">

<div id="sijamg_top">
	<div class="bxslider" style="display: inline-block;">
		<div>
			<img src="${pageContext.request.contextPath }/resources/images/도현상회.jpg" />
		</div>
		<div>
			<img src="${pageContext.request.contextPath }/resources/images/현진상회.jpg" />
		</div>
		<div>
			<img src="${pageContext.request.contextPath }/resources/images/gupo3.png" />
		</div>
	</div>
	<div id="sijang_text" style="display: inline-block; vertical-align: top;">
		<div class="tit">
			<div class="sij_name" style="font-size: 30px; font-weight: bold;">▶ ${store.store_name }
		        <button class="clear-button button button-follow" style="margin-left: 10px;">
		            <span class="content">
		                <div class="inner-container">
		                    <img src="https://front.coupangcdn.com/coupang-store-display/20240415182517/img/ic_heart_dark_outline.ebe809a.svg" width="12" alt="">
		                    <span class="text">판매자샵 찜하기</span>
		                </div>
		            </span>
		        </button>
		    </div>
		</div>
			<div class="sij_sub_name" style="font-size: 25px;">${store.store_explain }</div>
		<table>
			<tbody>
				<tr>
					<th>주소</th>
					<td style="padding-left: 10px;">${store.store_addr1 }</td>
				</tr>
				
				<tr>
					<th>전화</th>
					<td style="padding-left: 10px;">${store.phone }</td>
				</tr>

				<tr>
					<th>판매자</th>
					<td style="padding-left: 10px;">${store.seller_id }</td>
				</tr>

				<tr>
					<th>운영 상태</th>
					<td style="padding-left: 10px;">${store.status }</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
</section>

${product }
<div class="bootstrap-tabs product-tabs">
    <h3>가게 인기 상품</h3>
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-6 justify-content-center gap-3">
            <c:forEach items="${product}" var="product">
                <div class="col" style="width: 19%;">
                    <div class="product-item">
                        <a href="#" class="btn-wishlist"><svg width="24" height="24"><use xlink:href="#heart"></use></svg></a>
                        <figure>
                            <a href="" title="Product Title">
                                <img src="${pageContext.request.contextPath}/resources/images/carrot.jpg" alt="Product Thumbnail" class="tab-image" style="width : 180px">
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
                            <a href="#" class="nav-link">장바구니<svg width="18" height="18"><use xlink:href="#cart"></use></svg></a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>


<%@ include file="../include/footer.jsp"%>