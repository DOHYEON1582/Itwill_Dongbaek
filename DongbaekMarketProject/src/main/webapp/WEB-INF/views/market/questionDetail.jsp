<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${detail } 
	    <h1>Question Detail</h1>
    <div>
        <h2>${detail.title}</h2>
        <p>작성자: ${detail.user_id}</p>
        <p>문의 유형: ${detail.q_type}</p>
        <p>작성일: <fmt:formatDate value="${detail.regdate}"/></p>
        <p>내용: ${detail.content}</p>
    </div>
</body>
</html>

