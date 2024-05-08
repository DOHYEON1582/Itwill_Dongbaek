<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>

  h1 {
  	margin-bottom: 5px;
  }

  table {
    width: 100%;
    border-top: 1px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    border-bottom: 1px solid #444444;
    border-left: 1px solid #444444;
    padding: 10px;
  }
  th:first-child, td:first-child {
    border-left: none;
  }
</style>
<meta charset="UTF-8">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>

<title>${param.q_code }의 문의 상세보기</title>
</head>
<body>
	<h2> ${param.q_code }의 문의 상세보기 </h2>
	
	<table>
		<tr>
			<th>문의 제목</th>
			<c:forEach var="dList" items="${detailList }">
				<td>${dList.title }</td>
			</c:forEach>
		</tr>
		<tr>
			<th>문의 내용</th>
			<c:forEach var="dList" items="${detailList }">
				<td>${dList.content }</td>
			</c:forEach>
		</tr>
		<tr>
			<th>문의한 사람 (아이디)</th>
			<c:forEach var="dList" items="${detailList }">
				<td>${dList.user_name } (${dList.user_id })</td>
			</c:forEach>
		</tr>
	</table>
	<fieldset>
		<legend>답글</legend>
		<table>
			<tr>
				<th>답글 내용</th>
				<c:forEach var="aList" items="${answerList }">
					<td>${aList.answer_content }</td>
				</c:forEach>
			</tr>
			<tr>
				<th>작성자</th>
				<c:forEach var="aList" items="${answerList }">
					<td>${aList.writer }</td>
				</c:forEach>
			</tr>
			<tr>
				<th>작성일</th>
				<c:forEach var="aList" items="${answerList }">
					<td>${aList.regdate }</td>
				</c:forEach>
			</tr>
			
		</table>
	</fieldset>
	
	
	<c:if test="${sessionScope.userVO.user_id == 'admin' }">
		<form action="" method="post" id="answerForm">
			답글 : <input type="text" name="answer_content" id="answer_content"> <br>
			작성자 : <input type="text" name="writer" value="${sessionScope.userVO.user_id }" id="writer" readonly="readonly">
			<input type="hidden" name="q_code" id="q_code" value="${param.q_code }">
			<button type="submit" id="btnGet"> 답글달기 </button>
		</form>
	</c:if>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$("#answerForm").submit(function(event){
				event.preventDefault(); // 기본 이벤트 방지
				
				var answer = {
					"answer_content" : $("#answer_content").val(),
					"writer" : $("#writer").val(),
					"q_code" : $("#q_code").val()
				};
				// 중복 체크를 수행하기 위한 Ajax 요청
		        $.ajax({
		            url : "/market/checkDuplicateAnswer",
		            type : "POST",
		            data : { q_code: answer.q_code },
		            success : function(data){
		                if (data === "true") {
		                    alert("이미 답변된 질문입니다.");
		                    window.close();
		                } else {
		                    // 중복이 아닌 경우, 답글 제출
		                    alert("답글을 작성했습니다.");
		                    submitAnswer(answer);
		                }
		            },
		            error : function(){
		                alert("중복 체크 실패!");
		            }
		        });
		    });
		});

		function submitAnswer(answer) {
		    $.ajax({
		        url : "/market/productDetail",
		        type : "POST",
		        data : JSON.stringify(answer),
		        contentType : "application/json; charset=UTF-8",
		        success : function(data){
		            window.close();
		        },
		        error : function(){
		            alert("실패!");
		        }
		    });
		}
	</script>
</body>
</html>