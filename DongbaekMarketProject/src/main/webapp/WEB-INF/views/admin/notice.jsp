<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/adminHeader.jsp"%>
<div id="main">
<!-- 내용 -->

<div id="main-first">
<!-- 내용 -->
<div class="notice">
	<div style="text-align: left; padding: 10px;">
		<h2>공지사항</h2>
			<div class="search-bar row bg-light p-2 my-2 rounded-4" style="width: 300px; border: 1px solid #dddddd;">
		              <div class="col-11 col-md-7">
		                <form id="search-form" class="text-center" action="index.html" method="post" style="width: 200px;">
		                  <input type="text" class="form-control border-0 bg-transparent" placeholder="Search for ">
		                </form>
		              </div>
		              <div class="col-1">
		                <a href="">
		                	<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" style="margin-left: 50px;"><path fill="currentColor" d="M21.71 20.29L18 16.61A9 9 0 1 0 16.61 18l3.68 3.68a1 1 0 0 0 1.42 0a1 1 0 0 0 0-1.39ZM11 18a7 7 0 1 1 7-7a7 7 0 0 1-7 7Z"></path></svg>
						</a>              
		              </div>
		      </div>
      </div>
</div>
</div>

<table style="width: 99%; border-collapse: collapse; margin-left: 19px;">
    <thead>
        <tr>
            <th>제목</th>
            <th>내용</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>공지사항 제목 1</td>
            <td>이것은 첫 번째 공지사항의 내용입니다. 내용 내용 내용 내용 내용.</td>
            <td>이도현</td>
            <td>2024.04.17</td>
        </tr>
    </tbody>
</table>    

</div>	






<%@ include file="../include/adminFooter.jsp"%>