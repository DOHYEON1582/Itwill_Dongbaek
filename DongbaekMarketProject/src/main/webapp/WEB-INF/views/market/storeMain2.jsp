<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style>

</style>

<h3> 가게 메인 페이지 </h3>

${store }
	
<div class="row g-5">
          <div class="col-lg-7">
            <div class="row flex-column-reverse flex-lg-row">
              <div class="col-md-12 col-lg-2">
                <!-- product-thumbnail-slider -->
                <div class="swiper product-thumbnail-slider swiper-initialized swiper-backface-hidden swiper-thumbs swiper-vertical">
                  <div class="swiper-wrapper" id="swiper-wrapper-26e7f1cad8311e1d" aria-live="polite" style="transform: translate3d(0px, 0px, 0px); transition-duration: 0ms;">
                    <div class="swiper-slide swiper-slide-visible swiper-slide-active" role="group" aria-label="1 / 5" style="height: 132px; margin-bottom: 20px;">
                      <img src="images/product-thumbnail-1.jpg" alt="" class="thumb-image img-fluid">
                    </div>
                    <div class="swiper-slide swiper-slide-visible swiper-slide-next" role="group" aria-label="2 / 5" style="height: 132px; margin-bottom: 20px;">
                      <img src="images/product-thumbnail-2.jpg" alt="" class="thumb-image img-fluid">
                    </div>
                    <div class="swiper-slide swiper-slide-visible" role="group" aria-label="3 / 5" style="height: 132px; margin-bottom: 20px;">
                      <img src="images/product-thumbnail-3.jpg" alt="" class="thumb-image img-fluid">
                    </div>
                    <div class="swiper-slide swiper-slide-visible swiper-slide-thumb-active" role="group" aria-label="4 / 5" style="height: 132px; margin-bottom: 20px;">
                      <img src="images/product-thumbnail-4.jpg" alt="" class="thumb-image img-fluid">
                    </div>
                    <div class="swiper-slide swiper-slide-visible" role="group" aria-label="5 / 5" style="height: 132px; margin-bottom: 20px;">
                      <img src="images/product-thumbnail-5.jpg" alt="" class="thumb-image img-fluid">
                    </div>
					</div>
                <span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span></div>
                <!-- / product-thumbnail-slider -->
              </div>
              <div class="col-md-8 col-lg-8">
                <!-- product-large-slider -->
                <div class="swiper product-large-slider swiper-fade swiper-initialized swiper-horizontal swiper-watch-progress swiper-backface-hidden">
                  <div class="swiper-wrapper" id="swiper-wrapper-1ac9acfb01294819" aria-live="polite" style="transition-duration: 0ms;">
                    <div class="swiper-slide" role="group" aria-label="1 / 5" style="width: 699px; opacity: 1; transform: translate3d(0px, 0px, 0px); transition-duration: 0ms;">
                      <div class="image-zoom" data-scale="2.5" data-image="images/product-large-1.jpg"><img src="${pageContext.request.contextPath}/resources/images/도현상회.jpg" alt="product-large" class="img-fluid"></div>
                    </div>
                  </div>
                <span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span></div>
                <!-- / product-large-slider -->
              </div>
            </div>
          </div>
          <div class="col-lg-5">
            <div class="product-info">
              <div class="element-header">	
                <h2 itemprop="name" class="display-6">${store.store_name }</h2>
                <div class="rating-container d-flex gap-0 align-items-center">
                  <div class="rating" data-rating="1">
                    <svg width="32" height="32" class="text-primary"><use xlink:href="#star-solid"></use></svg>
                  </div>
                  <div class="rating" data-rating="2">
                    <svg width="32" height="32" class="text-primary"><use xlink:href="#star-solid"></use></svg>
                  </div>
                  <div class="rating" data-rating="3">
                    <svg width="32" height="32" class="text-primary"><use xlink:href="#star-solid"></use></svg>
                  </div>
                  <div class="rating" data-rating="4">
                    <svg width="32" height="32" class="text-secondary"><use xlink:href="#star-solid"></use></svg>
                  </div>
                  <div class="rating" data-rating="5">
                    <svg width="32" height="32" class="text-secondary"><use xlink:href="#star-solid"></use></svg>
                  </div>
                  <span class="rating-count">(3.5)</span>
                </div>
              </div>
              <div class="product-price pt-3 pb-3">
                <strong class="text-primary display-6 fw-bold">$870.00</strong>
              </div>
              <p>${store.store_explain }</p>
              <div class="meta-product py-2">
                <div class="meta-item d-flex align-items-baseline">
                  <h6 class="item-title no-margin pe-2">Category:</h6>
                  <ul class="select-list list-unstyled d-flex">
                    <li data-value="S" class="select-item">
                      <a>${store.store_value }</a>
                    </li>
                  </ul>
                </div>
              </div>
              <div class="meta-product py-2">
                <div class="meta-item d-flex align-items-baseline">
                  <h6 class="item-title no-margin pe-2">가게 조회수</h6>
                  <ul class="select-list list-unstyled d-flex">
                    <li data-value="S" class="select-item">
                      <a>${store.viewcnt }</a>
                    </li>
                  </ul>
                </div>
              </div>
              </div>
              <div class="meta-product py-2">
                <div class="meta-item d-flex align-items-baseline">
                  <h6 class="item-title no-margin pe-2">가게 상태 : ${store.status }</h6>

                </div>
              </div>
            </div>
          </div>







<%@ include file="../include/footer.jsp"%>