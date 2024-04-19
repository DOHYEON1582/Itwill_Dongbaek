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
	        pager: false
	    });
	
	/* $('.market_codeOption').change(function(){
			var market_code = $(this).val();
			
			$.ajax({
				type: 'POST',
				url: '/marketMain',
				data: {market_code : 0},
				success: function(response){
					updateMarketInfo(response);
				},
				error: function(xhr, status, error){
					console.error("오류");
				}
			});
		});
	
		function updateMarketInfo(data){
			var market_code = $('.market_codeOption').val();
			var marketList = null;
			
			for (var i=0; i< data.length; i++){
				if(data[i].market_code === parseInt(market_code)){
					market = data[i];
					break;
				}
			}
			if (marketList != null){
		        $('.sij_name').text('▶ ' + marketList.name);
		        $('.sij_sub_name').text(marketList.explain);
		        $('#market_addr').text(marketList.market_addr1);
		        $('#phone').text(marketList.phone);
		        $('#build').text(marketList.build);
		        $('#traffic').text(marketList.traffic);
			} else {
				console.error("해당하는 시장 정보를 찾을 수 없습니다.")
			}
		} */
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

</style>
<!-- 시장정보 -->
<section class="py-2 mb-1" style="background: url(${pageContext.request.contextPath}/resources/images/background-pattern.jpg);">

<div id="sijamg_top">
	<div class="bxslider" style="display: inline-block;">
		<div>
			<img src="${pageContext.request.contextPath }/resources/images/gupo.png" />
		</div>
		<div>
			<img src="${pageContext.request.contextPath }/resources/images/gupo2.png" />
		</div>
		<div>
			<img src="${pageContext.request.contextPath }/resources/images/gupo3.png" />
		</div>
	</div>
	<div id="sijang_text" style="display: inline-block; vertical-align: top;">
		<div class="tit">
			<div class="sij_name" style="font-size: 30px; font-weight: bold;">▶ ${marketList[0].name }</div>
			<div class="sij_sub_name" style="font-size: 25px;">${marketList[i].explain }</div>
		</div>
		<table>
			<tbody>
				<tr>
					<th>주소</th>
					<td style="padding-left: 10px;">${market.market_addr1 }</td>
				</tr>
				
				<tr>
					<th>전화</th>
					<td style="padding-left: 10px;">${market.phone }</td>
				</tr>

				<tr>
					<th>개설주기(장날)</th>
					<td style="padding-left: 10px;">${market.build }</td>
				</tr>

				<tr>
					<th>교통</th>
					<td style="padding-left: 10px;">${market.traffic }</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
</section>
	<h3>인기 상점</h3>
	<div class="slider-container">
		<div class="slider5">
		<%-- <c:forEach var="storeList" items="${storeList }" > --%>
	    <div class="slide"><img src=""></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar2"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar3"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar4"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar5"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar6"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar7"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar8"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar9"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar10"></div>
	  	<%-- </c:forEach> --%>
	  </div>
	</div>
<div class="bootstrap-tabs product-tabs">
    <h3>인기 상품</h3>
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-6 justify-content-center gap-3">
            <c:forEach items="${productList}" var="product">
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

              
  
${marketList }
<%@ include file="../include/footer.jsp"%>
