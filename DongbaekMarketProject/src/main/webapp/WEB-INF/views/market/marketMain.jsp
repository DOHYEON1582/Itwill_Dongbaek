<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include/header.jsp"%>
	<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
	
<!-- <link rel="stylesheet" href="marketMain.css"> -->
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
    .marketTable {
        border-collapse: separate;
        border-spacing: 0 20px; /* 상하로 30px 간격 지정 */
        margin-top: 10px; /* 테이블 위쪽에 20px 간격 추가 */
    }
    .marketTable th, td {
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
	  color: #9D9D9D;S
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
	.product-item .btn-number1 {
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
	.wished {
	    background: red; /* 텍스트 색상을 빨간색으로 변경 */
	    /* 또는 필요에 따라 다른 스타일을 적용할 수 있습니다. */
	}
	.cart {
    width: 120px;
    padding: 5px;
    margin: 5px;
    border: none;
    background-color: lightyellow;
    cursor: pointer;
  	}
</style>
<script type="text/javascript">
function topFunction() {
    document.body.scrollTop = 0; // Safari 지원
    document.documentElement.scrollTop = 0; // Chrome, Firefox, IE 및 Opera 지원
}

    $(document).ready(function(){
        $('.bxslider').bxSlider({
            infiniteLoop: false,
            hideControlOnEnd: true,
            slideWidth: 500
        });
        
	    // 스크롤 위치가 일정 위치 이상인 경우 버튼을 표시
	    window.onscroll = function() {
	        if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
	            document.getElementById("scrollToTopBtn").style.display = "block";
	        } else {
	            document.getElementById("scrollToTopBtn").style.display = "none";
	        }
	    };
        
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
        // 수량을 변경할 때마다 총 가격을 계산하여 보여주는 함수
        function updateTotalPrice(element) {
            var quantity = parseInt(element.val()); // 수량을 가져옴
            var price = parseInt(element.closest('.product-item').find(".price").text().replace(/[^\d]/g, '')); // 상품의 가격을 가져와서 숫자로 변환
            var totalPrice = quantity * price; // 총 가격 계산
            element.closest('.product-item').find(".total-price").text(totalPrice.toLocaleString() + "원"); // 총 가격을 화면에 표시
        }

        // 수량을 감소하는 버튼에 대한 이벤트 처리
        $(".quantity-left-minus").click(function() {
            var input = $(this).closest('.product-item').find(".quantity");
            var currentValue = parseInt(input.val());
            if (currentValue > 1) {
                input.val(currentValue - 1);
                updateTotalPrice(input);
            }
        });

        // 수량을 증가하는 버튼에 대한 이벤트 처리
        $(".quantity-right-plus").click(function() {
            var input = $(this).closest('.product-item').find(".quantity");
            var currentValue = parseInt(input.val());
            input.val(currentValue + 1);
            updateTotalPrice(input);
        });
        
        $(".cart").click(function(){
        	alert("장바구니에 담으시겠습니까 ?");
            var productCode = $(this).closest('.product-item').find("#product_code").val();
            var userId = $(this).closest('.product-item').find("#user_id").val();
            var quantity = $(this).closest('.product-item').find(".quantity").val();
            var price = parseInt($(this).closest('.product-item').find(".price").text().replace(/[^\d]/g, ''));

            var totalPrice = quantity * price; // 총 가격 계산

            var cart = {
                "user_id": userId,
                "product_code": productCode,
                "count": quantity,
                "price": totalPrice
            };        	
            
            $.ajax({
            	type: "POST",
            	url: "/market/addCart",
            	data: JSON.stringify(cart),
            	contentType: "application/json; charset=UTF-8",
            	success: function(data){
            		alert("제품이 장바구니에 추가되었습니다");
            		location.reload();
            	},
                error: function(xhr, status, error) {
                    var errorMessage = xhr.status + ': ' + xhr.statusText;
                    alert('에러가 발생했습니다.\n' + errorMessage);
                    console.log(" error "+ error);
                }            	
            });
            
        });
        
//         // 찜 상품 
//         $(".btn-wishlist").click(function(){
//         	alert("찜에 등록됩니다 !" + $("#product_code").val() + $("#user_id").val());
//             var btn = $(this); // 클릭된 버튼을 저장
//             var productCode = btn.siblings("#product_code").val();
//             var userId = btn.siblings("#user_id").val();
            
//             var wish = {
//                 "product_code": productCode,
//                 "user_id": userId
//             };
//         	$.ajax({
//         		type: "POST",
//         		url: "/market/addWish",
//         		data: JSON.stringify(wish),
//         		contentType: "application/json; charset=UTF-8",
//         	    success: function(data){
//         	            // 찜 상태 업데이트 후 UI 업데이트
//         	            var isWished = data.isWished; // 서버에서 반환한 찜 상태 정보
//         	            if (isWished) {
//         	                // 찜 상태인 경우
//         	                btn.addClass("wished"); // 찜 상태를 나타내는 클래스 추가
//         	                console.log(isWished);
//         	            } else {
//         	                // 찜 상태가 아닌 경우
//         	                btn.removeClass("wished"); // 찜 상태를 나타내는 클래스 제거
//         	            }
//         	        },
//         	        error: function(xhr, status, error) {
//         	            var errorMessage = xhr.status + ': ' + xhr.statusText;
//         	            console.log(" error "+ error);
//         	        }
//         	});
//         });
     // JavaScript

        $(".btn-wishlist").click(function(){
            var btn = $(this);
            var productCode = btn.siblings("#product_code").val();
            var userId = btn.siblings("#user_id").val();
            
            // 중복 체크를 위한 AJAX 요청
            $.ajax({
                type: "POST",
                url: "/checkDuplicateWish",
                data: { product_code: productCode },
                success: function(data) {
                    if (data === "true") {
                        alert("이미 찜한 상품입니다.");
                    } else {
                        // 중복이 아닌 경우에만 찜 상품 추가 요청
                        var wish = {
                            "product_code": productCode,
                            "user_id": userId
                        };
                        $.ajax({
                            type: "POST",
                            url: "/market/addWish",
                            data: JSON.stringify(wish),
                            contentType: "application/json; charset=UTF-8",
                            success: function(data) {
                                // 성공적으로 추가되었을 때의 처리
                                alert("찜에 추가되었습니다.");
                            },
                            error: function(xhr, status, error) {
                                // 오류 발생시의 처리
                                console.error(error);
                                alert("찜 추가에 실패했습니다.");
                            }
                        });
                    }
                },
                error: function(xhr, status, error) {
                    // 오류 발생시의 처리
                    console.error(error);
                    alert("중복 체크에 실패했습니다.");
                }
            });
        });


    });
</script>


<body>
<!-- 시장정보 -->
<section class="py-2 mb-1" style="background: url(${pageContext.request.contextPath}/resources/images/background-pattern.jpg);">

<div id="sijamg_top">
	<div class="bxslider" style="display: inline-block;">
		<div>
			<img src="${pageContext.request.contextPath }/resources/images/market/${marketList.img1}" />
		</div>
		<div>
			<img src="${pageContext.request.contextPath }/resources/images/market/${marketList.img2}" />
		</div>
		<div>
			<img src="${pageContext.request.contextPath }/resources/images/market/${marketList.img3}" />
		</div>
	</div>
	<div id="sijang_text" style="display: inline-block; vertical-align: top;">
		<div class="tit">
			<div class="sij_name" style="font-size: 30px; font-weight: bold;">▶ ${marketList.name }</div>
			<div class="sij_sub_name" style="font-size: 25px;">${marketList.explain }</div>
		</div>
		<table class="marketTable">
			<tbody>
				<tr>
					<th>주소</th>
					<td style="padding-left: 10px;">${marketList.market_addr1 }</td>
				</tr>
				
				<tr>
					<th>전화</th>
					<td style="padding-left: 10px;">${marketList.phone }</td>
				</tr>

				<tr>
					<th>개설주기(장날)</th>
					<td style="padding-left: 10px;">${marketList.build }</td>
				</tr>

				<tr>
					<th>교통</th>
					<td style="padding-left: 10px;">${marketList.traffic }</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
</section>
	<h3>인기 상점</h3>
	<div class="slider-container">
		<div class="slider5">
		<c:forEach var="store" items="${storeList }" >
	    <div class="slide">
	    <img src="${pageContext.request.contextPath}/resources/images/product/${store.img1}">
	    <a href="storeMain?store_code=${store.store_code}" class="store-link">
	    <p style="margin: 0;">${store.store_name } 바로가기</p>
	    </a>
	    </div>
	  	<</c:forEach>
	  </div>
	</div>
<div class="bootstrap-tabs product-tabs">
    <h3>인기 상품</h3>
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-6 justify-content-center gap-3">
            <c:forEach items="${productList}" var="product">
                <div class="col" style="width: 19%;">
                    <div class="product-item">
                        <button id="wishProduct" class="btn-wishlist"><svg width="24" height="24"><use xlink:href="#heart"></use></svg></button>
                        <input type="hidden" id="product_code" value="${product.product_code }">
                        <input type="hidden" id="user_id" value="${user_id }">                        
                        <figure>
                            <a href="productMain?product_code=${product.product_code }" title="Product Title">
                                <img src="${pageContext.request.contextPath}/resources/images/carrot.jpg" alt="Product Thumbnail" class="tab-image" style="width : 180px">
                            </a>
                        </figure>
                        
                        <h4>${product.product_name}</h4>
                        <span class="price"><fmt:formatNumber value="${product.price}" pattern="#,##0" />원</span>
                        <span class="total-price">총 가격</span>
                        <div class="input-group product-qty d-flex align-items-center justify-content-between">
                            <div class="input-group product-qty"> 
                                <!-- - 버튼 -->
                                <span class="input-group-btn">
                                    <button type="button" class="quantity-left-minus btn btn-danger btn-number" data-type="minus">
                                        <svg width="16" height="16"><use xlink:href="#minus"></use></svg>
                                    </button>
                                </span>
                                <!-- 수량 입력 -->
                                <input type="text" name="quantity" class="form-control input-number quantity" value="1" style="width: 60px;">
                                <!-- + 버튼 -->
                                <span class="input-group-btn">
                                    <button type="button" class="quantity-right-plus btn btn-success btn-number" data-type="plus">
                                        <svg width="16" height="16"><use xlink:href="#plus"></use></svg>
                                    </button>
                                </span>
                                <!-- 장바구니 버튼 -->
                                <button class="cart">장바구니<svg width="18" height="18"><use xlink:href="#cart"></use></svg></button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>



<%@ include file="../include/footer.jsp"%>
