<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../include/header.jsp"%>

<style>
	.reviewWrite{
		width:1300px;
	}
	.reviewWrite th {
		border: 1px solid black;
	}
	
	.reviewWrite td {
		border: 1px solid black;
	}
</style>

<div class="container">
	
	<table border="1">
		<tr>
			<td rowspan="3">${cvo.img1 }</td>
			<td>${cvo.store_name }</td>
		</tr>
		<tr>
			<td>${cvo.product_name }</td>
		</tr>
		<tr>
			<td>${cvo.price }</td>
		</tr>
	</table>
	
	
	<form id="" method="POST" enctype="multipart/form-data">
	<input type="hidden" name="order_code" value="${param.order_code }">
	<input type="hidden" name="product_code" value="${param.product_code }">
	<input type="hidden" name="user_id" value="${sessionScope.user_id }"><!-- 수정 해야함 -->
	<input type="hidden" id="rating-input" name="star" value="0">
	
	<table border="1" class="reviewWrite"> 
		<tr>
			<td colspan="2"><input type="text" id="" name="title" placeholder="제목"></td>
		</tr>
		<tr>
			<td colspan="2"><textarea rows="3" cols="20" id="" name="content" placeholder="후기를 작성해주세요."></textarea></td>
		</tr>
		<tr>
			<td>이미지첨부</td>
			<td><input multiple type="file" id="" name="images"  accept="image/*"></td>
		</tr>
		<tr>
			<td><input type="submit" value="작성하기"></td>
			<td><input type="reset"></td>
		</tr>
	</table>
	
	<table>
		<tr>
			<td>
				<p id="rating-value">별점: 0</p>
				<div class="rating">
					  <span class="star" data-value="1">&#9733;</span>
					  <span class="star" data-value="2">&#9733;</span>
					  <span class="star" data-value="3">&#9733;</span>
					  <span class="star" data-value="4">&#9733;</span>
					  <span class="star" data-value="5">&#9733;</span>
				</div>
			</td>
		</tr>
	</table>
	</form>
	
</div>
<script>
document.addEventListener("DOMContentLoaded", function () {
	  const stars = document.querySelectorAll(".star");
	  const ratingValue = document.getElementById("rating-value");
	  const ratingInput = document.getElementById("rating-input");

	  let isMouseDown = false; // 마우스 클릭 여부
	  let currentRating = parseInt(ratingInput.value); // 현재 별점

	  stars.forEach(function (star, index) {
	    star.addEventListener("mouseenter", function () {
	      if (isMouseDown) {
	        currentRating = index + 1;
	        updateRating();
	      } else {
	        highlightStars(currentRating);
	      }
	    });

	    star.addEventListener("mousedown", function () {
	      isMouseDown = true;
	      currentRating = index + 1;
	      updateRating();
	    });

	    star.addEventListener("mouseup", function () {
	      isMouseDown = false;
	    });

	    star.addEventListener("mouseleave", function () {
	      if (!isMouseDown) {
	        highlightStars(currentRating);
	      }
	    });
	  });

	  function updateRating() {
	    ratingValue.textContent = "별점: " + currentRating;
	    ratingInput.value = currentRating;
	    highlightStars(currentRating);
	  }

	  function highlightStars(index) {
	    stars.forEach(function (star, i) {
	      if (i < index) {
	        star.classList.add("selected");
	      } else {
	        star.classList.remove("selected");
	      }
	    });
	  }
	  
	// 이미지 선택 input 태그의 변경 이벤트 리스너 추가
    const imgInput = document.querySelector("input[name='img1']");
    imgInput.addEventListener("change", function() {
        const files = this.files;
        const fileCount = files.length;
        
        // 최대 3개 이상 선택 시 경고 메시지 표시
        if (fileCount > 3) {
            alert("이미지는 최대 3장까지 선택할 수 있습니다.");
            this.value = ""; // 선택한 이미지 초기화
            return;
        }

        let selectedFileNames = "";
        for (let i = 0; i < fileCount; i++) {
            if (i > 0) {
                selectedFileNames += ", ";
            }
            selectedFileNames += files[i].name;
        }

        const fileNamesElement = document.getElementById("selected-file-names");
        fileNamesElement.textContent = selectedFileNames;
    });
	    
	});
</script>
<%@ include file="../include/footer.jsp"%>