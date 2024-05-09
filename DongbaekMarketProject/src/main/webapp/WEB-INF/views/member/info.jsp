<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <style>
        .infoBody {
        text-align: center;
        margin: 50px auto; /* Adjust as needed */
        padding: 10px;
        border-radius: 10px;
        width: 50%; /* Adjust width as needed */
        }

        .info-table {
            margin: 0 auto;
            font-family: 'Gowun Dodum', sans-serif;
            font-weight: bold;
            width: 69%;
            margin: 20px auto;
            border-collapse: collapse;
            border: 1px solid #ddd;
        }
        .info-table th {
            background-color: #f2f2f2;
            border: 1px solid #ddd; /* 수정된 부분 */
            font-size: 25px;
        }
        .info-table td {
            border: 1px solid #ddd; /* 수정된 부분 */
            font-size: 22px;
        }

        .info-row{
        	padding: 10px;
        }
	.info-link {
	    margin-left: 20px; /* Adjust as needed */
	    margin-right: 10px; /* Add margin between links */
	    padding: 10px; /* Add padding for better clickability */
	    font-size: 18px;
	    text-decoration: none;
	    color: black;
	    background-color: lightyellow; /* Add background color */
	    border-radius: 5px; /* Add border radius for rounded corners */
	}
    </style>
</head>
<body>
<div class="infoBody">
    <h1 style="">My Page</h1>
    <table class="info-table">
        <tr class="info-row"> 
            <td>아이디</td>
            <td>${userinfo.user_id}</td>
        </tr>
        <tr class="info-row">
            <td>이름</td>
            <td>${userinfo.user_name}</td>
        </tr>
        <tr class="info-row"> 
            <td>전화번호</td>
            <td>${userinfo.phone}</td>
        </tr>
        <tr class="info-row"> 
            <td>주소</td>
            <td>${userinfo.addr1}</td>
        </tr>
        <tr class="info-row"> 
            <td>상세주소</td>
            <td>${userinfo.addr2}</td>
        </tr>
        <tr class="info-row"> 
            <td>회원가입일</td>
            <td>${userinfo.regdate}</td>
        </tr>
        <tr class="info-row"> 
            <td>회원 수정일</td>
            <td>${userinfo.update_date}</td>
        </tr>
    </table>
	    <a href="/member/update" class="info-link">회원정보 수정</a>
	    <a href="/member/delete" class="info-link">회원정보 삭제</a>
	    <a href="/member/wish" class="info-link">찜 목록</a>
	    <a href="/member/mark" class="info-link">즐겨찾기</a>
	    <a href="/member/subscribe" class="info-link">구독 정보 조회</a>
</div>

<%@include file="../include/footer.jsp" %>
