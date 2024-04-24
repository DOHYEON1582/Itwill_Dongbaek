<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title></title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/styles.css" rel="stylesheet" />
    
<style>
        .nav-tabs {
            border-bottom: 1px solid #dee2e6;
        }

        .nav-tabs li {
            padding: 20px 30px;
            margin-right: 20px;
            cursor: pointer;
            list-style: none;
            display: inline-block;
            font-size: 18px;
        }

        .nav-tabs li:hover {
            background-color: #e9ecef;
        }

        .nav-tabs li.active {
            font-weight: bold;
            color: #007bff;
        }

        #middle_nav {
            text-align: center;
        }
        .product-image {
            width: 500px;
            height: 500px;
            object-fit: cover; 
            margin-bottom: 20px;
        }     
</style>
</head>

<body>
        <section class="py-0">
            <div class="container px-4 px-lg-5 my-5">
                <div class="row gx-4 gx-lg-5 align-items-center">
                    <div class="col-md-6"><img class="product-image" src="${pageContext.request.contextPath}/resources/images/${product.img1}" alt="..." /></div>
                    <div class="col-md-6">
                        <div class="small mb-1">카테고리 : ${product.category }</div>
                        <h1 class="display-5 fw-bolder">${product.product_name }</h1>
                        <div class="fs-5 mb-5">
                            <span>${product.price }원</span>
                        </div>
                        <p class="lead">${product.product_explain } 대충 상품 설명</p>
                        <div class="d-flex">
                            <input class="form-control text-center me-3" id="inputQuantity" type="num" value="1" style="max-width: 3rem" />
                            <button class="btn btn-outline-dark flex-shrink-0" type="button">
                                <i class="bi-cart-fill me-1"></i>
                                장바구니에 담기
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </section>

		<div class="container">
			<div class="row nav">
				<nav id="middle_nav">
					<ul class="nav nav-tabs nav-justified">
						<li id = "about"><a href="#about1">상품 상세</a></li>
						<li id = "review"><a href="#review1">리뷰</a></li>
						<li id = "qna"><a href="#qna1">상품 문의</a></li>
					</ul>
				</nav>
			</div>
		</div>
		<div class="row" style="margin: 50px 0;">
			<h1 class="jumbotron">
				<div class="container1">
					<small>This is product page.</small>
				</div>
			</h1>
		</div>
		
		<div class="row about_product" style="text-align: center;">
			<h1 class="page-header" id="about1">상품 상세</h1>

		</div>
		<div class="row reviews" style="text-align: center; margin: 80px 0;">
			<h1 class="page-header" style="margin-bottom: 50px;" id="review1">Review</h1>
			<c:forEach begin="1" end="5">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Panel title</h3>
				</div>
				<div class="panel-body">Panel content</div>
			</div>
			</c:forEach>
		</div>

		<div class="row qnas" style="text-align: center; height: 700px;">
			<h1 class="page-header" id="qna1">상품 Q&A</h1>
            /* Q&A 테이블 */
		</div>




</body>
<%@ include file="../include/footer.jsp"%>