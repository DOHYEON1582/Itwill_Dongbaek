<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>

<h1> 회원정보 페이지 </h1>

<h2>아이디 : ${userinfo.user_id } </h2>
<h2>이름 : ${userinfo.user_name }</h2>
<h2>전화번호 : ${userinfo.phone }</h2>
<h2>주소 : ${userinfo.addr1 }</h2>
<h2>상세주소 : ${userinfo.addr2 }</h2>
<h2>회원가입일 : ${userinfo.regdate }</h2>
<h2>회원 수정일 : ${userinfo.update_date }</h2>

<a href="/member/update">회원정보 수정</a>

<a href="/member/delete">회원정보 삭제</a>

<a href="cart">장바구니</a>







<%@include file="../include/footer.jsp" %>