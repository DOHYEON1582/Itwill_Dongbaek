<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
    $(document).ready(function(){
        $("a[href='/member/update']").click(function(event) {
            event.preventDefault();
            
            var user_pw = prompt("현재 비밀번호를 입력하세요.: ", "");
            if(!user_pw){
                return;
            }
            
            $.ajax({
                url : "/checkPassword",
                type : "POST",
                data : {user_pw: user_pw},
                success : function(data){ // 서버 응답을 처리할 함수에 매개변수를 추가합니다.
                	alert("갔다옴");
                    if(data === "success"){
                        // 비밀번호가 일치할 때 수정 페이지로 이동합니다.
                        window.location.href = "/member/update";
                    } else {
                        // 비밀번호가 일치하지 않을 때 알림을 표시합니다.
                        alert("비밀번호가 일치하지 않습니다. 다시 시도해주세요.");
                    }
                },
                error : function(xhr, status, error){
                    // 서버 오류가 발생할 경우 알림을 표시합니다.
                    alert("서버오류 : " + error);
                }
            });
        });
    });
</script>

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
