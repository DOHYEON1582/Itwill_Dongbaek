<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/adminHeader.jsp"%>

<div id="main">

<div id="main-first">
<!-- 내용 -->
<div class="notice">
	<div style="text-align: left; padding: 10px;">
		<h2>업체정산</h2>
			<div class="col-sm-6 offset-sm-2 offset-md-0 col-lg-5 d-none d-lg-block" >
            <div class="search-bar row bg-light p-2 my-2 rounded-4">
              <div class="col-md-4 d-none d-md-block">
                <select class="form-select border-0 bg-transparent">
                  <option>가게코드</option>
                  <option>가게명</option>
                  <option>상품명</option>
                </select>
              </div>
              <div class="col-11 col-md-7">
                <form id="search-form" class="text-center" action="index.html" method="post">
                  <input type="text" class="form-control border-0 bg-transparent" placeholder="Search for">
                </form>
              </div>
	          <div class="col-1">
              	<a id="search" href="">
	                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="currentColor" d="M21.71 20.29L18 16.61A9 9 0 1 0 16.61 18l3.68 3.68a1 1 0 0 0 1.42 0a1 1 0 0 0 0-1.39ZM11 18a7 7 0 1 1 7-7a7 7 0 0 1-7 7Z"></path></svg>
	            </a>
	          </div>
	          <div style="margin-left: 10px;"> <!-- 날짜div -->
          		시작일:<input type="date" id="inputDate" placeholder="시작일">종료일:<input type="date" id="inputDate" placeholder="종료일">
              </div>
            </div>
          </div>
      </div>
</div>

<table style="width: 99%; border-collapse: collapse; margin-left: 19px; background-color: white;">
    <thead>
        <tr>
            <th>가게코드</th>
            <th>가게명</th>
            <th>주문코드</th>
            <th>판매일자</th>
            <th>입금일자</th>
            <th>정산</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>124572</td>
            <td>아이티윌</td>
            <td>23333423</td>
            <td>2024-04-16</td>
            <td>2024-04-17</td>
            <td>정산완료</td>
        </tr>
    </tbody>
</table>    




<!-- 내용 -->

</div>
<ul class="pagination d-flex justify-content-center">
	<li class="page-item disabled">
		<a class="page-link bg-none border-0" href="#" aria-label="Previous">
		<span aria-hidden="true">«</span>
		</a>
	</li>
	<li class="page-item active" aria-current="page"><a class="page-link border-0" href="#">1</a></li>
	<li class="page-item"><a class="page-link border-0" href="#">2</a></li>
	<li class="page-item"><a class="page-link border-0" href="#">3</a></li>
	<li class="page-item">
		<a class="page-link border-0" href="#" aria-label="Next">
		<span aria-hidden="true">»</span>
		</a>
	</li>
</ul>   
    
</div>

	






<%@ include file="../include/adminFooter.jsp"%>