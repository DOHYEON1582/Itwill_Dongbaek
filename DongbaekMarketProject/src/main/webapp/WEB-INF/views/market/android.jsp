<%@ page import="java.util.*"  %>
<%@ page import="com.itwillbs.domain.ProductVO2"  %>
<%@ page import="com.google.gson.Gson"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    List<ProductVO2> list = new ArrayList<>();
%>
	<c:forEach var="product" items="${productList }">
<%	
	ProductVO2 p1 = new ProductVO2();
	p1.setPrice((Integer)pageContext.getAttribute("product.price"));
	p1.setProduct_name((String)pageContext.getAttribute("product.product_name"));	
	p1.setSeller_id((String)pageContext.getAttribute("product.seller_id"));
	p1.setImg1((String)pageContext.getAttribute("product.img1"));
	
	list.add(p1);
%>
	</c:forEach>
<%
	Gson gson = new Gson();
	String jsonData = gson.toJson(list);
%>

<%= jsonData %>