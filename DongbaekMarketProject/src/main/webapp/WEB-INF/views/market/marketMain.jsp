<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet"><script>
	$(document).ready(function(){
		$('.bxslider').bxSlider({
			  infiniteLoop: false,
			  hideControlOnEnd: true,
			  slideWidth: 500
			});
		
	  $(document).ready(function(){
		    $('.slider5').bxSlider({
		        slideWidth: 200,
		        minSlides: 5,
		        maxSlides: 5,
		        moveSlides: 5,
		        slideMargin: 10,
		        adaptiveHeight: true, // 높이를 자동 조정하여 일관된 높이 유지
		        pager: false
		    });
		  });
	});
</script>
<style>
	.bxslider{
 		display: inline-block;
        margin-right: 20px; /* 이미지 슬라이더 오른쪽에 공간 추가 */
	}
    #sijang_text {
        display: inline-block;
        vertical-align: top;
        margin-left: 50px; /* 시장 정보 왼쪽에 공간 추가 */
        font-family: 'Gowun Dodum', sans-serif;
        font-size: 20px;
    }
    #sijamg_top {
        display: flex;
        justify-content: center; /* 페이지 가운데 정렬 */
        align-items: flex-start; /* 시장 정보 컨테이너를 상단에 정렬 */
    }
    table {
        border-collapse: separate;
        border-spacing: 0 50px; /* 상하로 30px 간격 지정 */
        margin-top: 20px; /* 테이블 위쪽에 20px 간격 추가 */
    }
    th, td {
        padding-left: 10px; /* 셀 내부 왼쪽 간격 지정 */
    }
	.slider-container {
	    display: flex;
	    justify-content: center;
	}
</style>
<!-- 시장정보 -->
<div id="sijamg_top">
	<div class="bxslider" style="display: inline-block;">
		<div>
			<img src="${pageContext.request.contextPath }/resources/images/gupo.png" />
		</div>
		<div>
			<img src="${pageContext.request.contextPath }/resources/images/gupo2.png" />
		</div>
		<div>
			<img src="${pageContext.request.contextPath }/resources/images/gupo3.png" />
		</div>
	</div>
	<div id="sijang_text" style="display: inline-block; vertical-align: top;">
		<div class="tit">
			<div class="sij_name" style="font-size: 30px; font-weight: bold;">▶ ${marketList[0].name }</div>
			<div class="sij_sub_name" style="font-size: 25px;">${marketList[0].explain }</div>
		</div>
		<table>
			<tbody>
				<tr>
					<th>주소</th>
					<td style="padding-left: 10px;">${marketList[0].market_addr1 }</td>
				</tr>
				
				<tr>
					<th>전화</th>
					<td style="padding-left: 10px;">${marketList[0].phone }</td>
				</tr>

				<tr>
					<th>개설주기(장날)</th>
					<td style="padding-left: 10px;">${marketList[0].build }</td>
				</tr>

				<tr>
					<th>교통</th>
					<td style="padding-left: 10px;">${marketList[0].traffic }</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
	
	${storeList }
	<div class="slider-container">
		<div class="slider5">
		<%-- <c:forEach var="storeList" items="${storeList }" > --%>
	    <div class="slide"><img src="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMSEhUTExIWFhUXGBcYGBgYFxsYFxcXGhgXGhcYFxoYHSggGBolHRcYITEhJSkrLi4uFx8zODMtNygtLisBCgoKDg0OGxAQGy0lHyUtLTArLS0tLS8tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLf/AABEIAKgBKwMBIgACEQEDEQH/xAAcAAABBQEBAQAAAAAAAAAAAAAGAgMEBQcAAQj/xABWEAABAgMEBAYMCAoJAwUAAAABAhEAAyEEBRIxBkFRYRMicYGR0QcUFTJSU1STobHB0iMzQmKCkuHwFhdDRGNylKKywiRVc4OEo9Pi8TSzwyVkpMTU/8QAGgEAAwEBAQEAAAAAAAAAAAAAAAECAwQFBv/EADMRAAIBAgMGAwgCAgMAAAAAAAABAgMREiFRBBMUMUFhUpGhBRUiU4Gx0fAycWPBM0Ji/9oADAMBAAIRAxEAPwB5OlVkP51IHKpW7agRY2O9JMx8Fqsy9wmpDcocmKlXYgn7Eejrj2b2IJyi4RKApTFuD6qVc88d3GvQ437Ko9Jl+m0S2rPkedB2RHt17yJTYrRIfYJjk66BIOz0RWI7DcwjvpY5jHkrsQTcR40pw1a1pnluPRBxj0JXsmlfOfoS7LfuMjDKUR4RUhAyNfhCktzPuizE350rzo3bt0Uy+xJOaqpLc/Pqjj2K0SgFTS4JASJMjhFGhNQ4pTfBxj0Lfsmh8z0YQypqfDR9dO/Yd8LSKd8nJu+Gz/mBwdjOznVPHLY/98e/ixs/6b9jPvw+L7Ee6KPzfRhGE706/lDaOqHEoO0dI2k9UC/4sbN4U0ctiV7FR4exhZ/GK/YZvsg4vsHuel830YVJkq3dI2AQsyVv3vp3iBE9jGz+Gv8AYJ3VHv4srP4Sv2Cf7sHFdg9zUvm+jC5VnmENh9MJMlYIcCu8bH27BAj+LOR4R/YZ/uwj8Wcl++/+HP8AchPail7Go9avowxEpez0j52/fCwlWz70gSR2P5YDCYB/hJw/kj1GgyU5TzzWaePUiDiuwvctL53owsc7PvWEqKtSSejdtIgaGieFv6Uocsm0D+SEzdFSfzsc6J4/kg4paB7lp3/5l5MKU4nfAelPhE+FHYVZFJGWzZuJECcrRcklrZLV51/4IV+Cax+dpHnh/JC4nsHuaHzV5MLa7Pu4jwA7PuyoEvwbmeXI+tO9yG16Nr8vl+dnJ/lh8StBe5Y/OXkwuwKfI5n2w2sHZq9sCsrR1Yf/ANRk5H86mjVTVthf4PT/AOs5X7bNh8StB+5F82PqEwSdkKwFsjq9R+yBTuFOet6yt/8ATpvXCEXBOJpe0s8lvmn2w+KWhPuX/LH1CspOw9EeIQXDg5bN0DAua0jK85R/xh9phXce1f1jJ/axBxUdCfcr+bH1/ARkHYejkjwg+Ceg7/sgcN1Wr+sZP7WmG1XXa9V4ST/i09cHFLQPcsvmx9fwEa38E9B3RXz7xRLbhCEPQY+K9C4GJniuRdlu8ulH/FIPthidcl4rBBtaCk5g2mWRzgmB7UtCl7GtzqLzCixzSuqQTvApUJ5qMYmTEtmCBq38m2Mjt+jtpsqwSubKxVCkTGQsUqkoOFQrq2xYXfeVrswxSZ6ljNUucozZa6A5KJKTvSQcneIe0X6Gy9iVMN4u5oyobIijujTKzT1BE0GyzjQJmF5KjsRN1HcpqnXBBNlqTRQb1HkORjohUjLkeVWoVKTtNEZQ3R4ZYhxUKG+NbmFzRY6OhOMO2uPFPpRUNqTUEch5P+WhyOgA6GlDjJpk59nthaYbCnUGOo+sdRgAejo6OgA6PCY4mBfSHSVIBlyS5NFLGSdoSdZ36o0pUpVJYYoic1BXZPu/SSVNUEMpBOWJqnZQljuiFpBfM+zzQAEGWoOlwXJHfA1pmK74DZExIPCOrEhlSwO8KgX4zGtMhk+cSLZes60EGalQYcV0YQcTOR4WQj01sUY1kunf/RxvaG6ff95mkXfaeFlImM2NKVNscO0SYg3IGs8r+zR6hE6PKkrSaO6LujoH7+0lTZypIDqSHJPepoDqqSxeO0ovuZZcCkysaC+JTE4TRgcOT1rugAvjSAWicCxQssCAhakq1AkkU5Y6tl2fHJOXL+0YVquFWXMLbr03SvvkUGZAUkjlQupgvlrBAIyNRyRjVrtRBTiUqupKFFwMwSHbPcc2gx0f0onzp0uXwPENPipiMKQM8SqU9OUbbXsihnG3fP7GdCu3z+gbx40ex0eadglKAMhEW8bbLkJxrycCgcknIQ5bbYiUnGtQSnfrOwDWd0A2kWknChg6ZYOTOpZ1UD8yQ5jehQlVl26syq1VBd+gV92UKlrXKHCKQl+DyUekRHuG/wDthWAy8JYqoXFGoQRTMQIaPaQokrUyQpSykEKxJmAZABKuV2YcseyL0MicpSElRxrSAEqWS5PyU1OUdfCRtK2l07mG/fwvzRpXBjYOiPOCGwdEMXdOUuWlS04VEOU7NnJTVqiVHmvI7CFeFtlSUhUwhIJYUdzU0AD5AmG7Le0iYrChaSo5BiCWqWcQDaT2CciaUzZgmSlkrS5U4Y0BT3tHoRsyiklLU6mQZZailYVAj6KidWRbOPRo7FGpDFi5nJPaHGVrB/d+lImzRLXJKHVhDlyFbFJanp6IIO1keAn6ojObBKVJtEsKUVHHLLkYSysNG1ZkRpgjDaaUYYXHqjWjNyvcZFlR4CfqiETrGgguhJ5okNHpjlNjNOylZwJFlIAASqYimQoKfuQJ6LSkqnykrDpVMSkjaFHDBx2UUPY0GnFn+sTIz+5J2CYhWTLSr6qni0exsmez2/sONJexlLmgmSR+orI8hgFQLxus4EuqSD8ROBVLb9GrOXTJi2tjH0AIZtdjRNThmIChsIeJUmjzHUxrDUV0ZNc2lVmtJEsk2ef4maaKP6KbkuuosTsi8VJUCxCgRsB6oTpT2LZM8EyThPgKqnmOqAhWjl8SPgpc+1BCKJCVFQA2A7I6obS0szz6vsynN3pyt2ZvsJKA7tURR92VeKHnT7sei+1eKH1/9scd0VxdHxF7HRQK0hanBh/1vsj1OkB8V++OqGVxFPUuyGL83VDcpsRA2D0lRiqVe5I+IJ5FpjpV6LBJMlZoB3yNXPBdBxNLUvI6KkXwfEL6Ue9Hvdg+JmdKPehXQcRS1JN62ITpS5RUU4gzjMHMHfyQLWPQdz8PNxp8FIKQrcquW6CDuz+iX+770d3bTrQodHXGsK04pxi8mS6lGbvdXKm8NCZSviTwJ2AOn6uo8kVWlNmFnRZ5WLFhQrjHM1S5gp7uDVKmfu9cRbfaZM4DhbKuYAXAKUljzmNaO1ShJNu6XQzqOjJNJq7La6fiJX9mj+ERLiql3whvipo3YOqF92EeDM+oY5m7u5uq9NL+SLFSXjJdJbFwdomhRmISVEpOSTirReHaTR3EaSb5l+DM+oqKy+7amcgJQVp4wJeW+JIzTUUByeNaO0ug3JK/YzqypVFbEkZsgKSGCkJHzlKmHpURGqaIJAskpnyJ42ZdRLhvknMbiIqrouyyy0uuUhayoseCSCNbUGrJ4m3dexdfC8V+8CQSlIDsDSpyMXtO271RjhaClGFNuTkgijoGbovRY4ThlkhyUOlvojCnvX1mtd1V2++FKkcRXBzCagDEQK5YhhJyz31jj3mV7G+9p+JeZa3vdcu0y+DmgkOCGJSQoOxBFQanpio7gyrJLmTZSSualBKSvjEUow6s4Ta70WZKME5KJo750FT9FPTDd6Xurg5YlTTicYyJbk1DjjJAZnDiuUNV5JWs7c7CcqbzugLtExU9RUZ6UnwlIUt9wCSMPqix0FBFqDJSSUqxkDIM7g5jjMOeLC+Lts07AtIQlRbH8Gt1HXkwHLXkgjuk2WQnDKATtZKgSdpcPHpS9oKcJQwPNdrL+jmhSSaeNF0I5URDeUrwx6Y7unJ8YnpjgOreQ1QBXtd1vmuibKVNZRKVJUkDlSqik8kNJ0atim+CCWyxTNmXegvGgd1pHjUdMd3XkeOR0x1R2ypFWjZfQxdKm3dv1M0+GlTSLQ2NKklwoqxBgQXUAdo5o1gRTTUWOZMTNUZalpyUTUbIsk2yWflp+sImvX3qjdZoqlCMG7PmPtHsMi1I8NPSI97YR4SekRzm10BvZIkf0Cb82Yg9Kke8YymyLf0+yNf03CV2S0pChiooJBBKyAmjZnLVGTWCxTVH4lY+grqi0ersNWKg031N/u+djlS1+EhKukAxIil0YtSe1ZCVKAWJSAUqooFIALg8kWvDo8JPSIg8yVk2Ox0Niel2xJflEOQCMvTeRh1NvMD6e3PJ09KocCrb5OjpXGGKGqPmt1G/NeZYotpUSr7/AHYQ8i3xSyVWxLgSEf5nt1Qha7XUizIJ1stY9kab2GptKzeTQQpvkpLQ8b4VqeM+nX9ORMSiZZUoWo8VKpqnVyNLLxcIt1q1WZDD9JNB/wCwYmTihSotZ5eYWS72Xvh3uorYYGkWy2D81T5yb/8AnhRt9t8jHnF+2REOa7GLjPt5hEq9FbDEVd5Er5opxeds8j/zF/6ENi32nEV9ol/7RX+lFRkloVTg1du3LUI0XiQdcPi9TvgYVe9p8hV5zrlx4L5tHkZ86PaiFdfrI3c+3mgpF7GPO68DKb5tHkR88j3Y9VfU0Z2Qj++R7RB+8xbup280E/daG1XrA53bmeSHz0uGF3+sZ2RfnZHtmCDL9Y1Snp6oJ5l5in0fXDxvFoArdpOkKTikTgXAABkqrqfDNpymJn4SA/m87/J/1otvI6alOThH8hkm9hCu68BytIkNWROHNK9k6KTR/TRc6fOQuR8GkEoKA6qKAGJyAXBJcbN8TYxjs9VptdO5ppvaEm+IFhf0vXLmj6Kffjl39JGaJo/uyfU8FiVSqfrCdV7Qyu90gjf1/bAz+ENn18IP7mb7Ewzab6sym4yw22RO9kuNIo3pU5p5hqL1TqMe91hAkm/rL4w+anD/AMceHSKy65oHKlY9aYjCZuhU0CzusI43uIE1aR2TyiV0kesQ0rSKyeUyeeYkeswYQVCrowtVeYOyETrxDOMxAsL+s3lEjz0v3obnXzJKSEzpJLH8vJ5vlxaTuawp1U1kwwTeiWBIHRHvdVHgp6BAlZ71lYWVNlZ+NlmnMqFd0ZPjJfnEe9ClF3FOlUUnZMKDeyPBT0CO7sJ2J6BAiu2IJpMR5xHvRwtKfGI84j3opRKjTqdwuXerg5QqTe7gF4D020A9+jziPeh2xW5Id1oA1ErR73JFNZGrhUwhXOvFxQ11GHbLpQcCeMDTW788DCLwSQ4Wg/TT1xSrtISSCoZnI6iSRlughfqVQdRXTBwdkS27UfUjz8Ytt1KT9QdUDVoC0pBc1LehzlDHDL+79cY7qGh2cPQ8KCi0dkC2qDYktrGBJ9kRpWmVsS6hOAcZMltjYWoOiKDGvZCCsPU1/USfSTWHu46CdCjbKKL2fpNNmlK5hCloqhXE4pLOQMIpuxRJTpxbAGEyWeVA9ioG0rSxrs/Jp3790eBQ+8tPXBu46CdGm8mkEo7JFuSWxoIpmgRIHZNtp1o5kj2iAedmY9E0p1wbqGhfC0fCvIOk9km2bUnmT7kLPZKtmxHQOqAZNtOz0/ZDgvA+D+99kG6joHDUfCg0HZKtv6P6sOjsmWvwUejqgKl3gSQMOZAzHuw9arQqWQDVw/yRrI8GDdR0Fw1DwIM09k+0+Aj780L/ABn2rxaIBBeJ2HpHuwrukdn8PuwbqOg+Fo+FBx+NC1eLR6YSrsn2nxcuApFuKiEtmQPk6/ox4u2sSC9CRQJ1GFu46C4Wh4UEF86ZrtJlmZKT8EtMxLEjjJyfaIsJXZPtCfyUvneA03gNYP1Uxxt6dh+qiHu1oU9npNWcQztHZOtC0lBlS2UCk0JoaRRaM6TTLDjMtKVGYEg4qsE4suXF6IqQozBxcsqpG7YN8LUrggMT1dmA1M/fcohqCSsCoUknFLJhknsqWjXJldELHZSneJl9EBHdAbFdCYSbeNh+qmFu46C4Wj4Q6HZQm+JlQsdlCb4iV6euAYTzhCsLhSikUSP5TthtVtYkYMtyfdg3cdBcNR8KD78aE3yeV0Hrj38Z07yeV0HrgBTbSfk+hPux7223yR6OqFu46BwtHwh5+M6d4iV0H3o4dk1euRKf9Un+eAW1zFILKGb+ht2+I/bnzfV1Q93HQOFov/qaF+M9Xkso9I9phCuyafI5PODAAbYfB9XVDZtBNMuiFuo6BwlHw/cPF9k7/wBlI6PshhfZIUoH+h2Yc1fUYA15wuRnmeYOYe6joHC0l0+5bXxfotCsRkoQfmFumkVyLS2QY7dcLIUda/q/bHYFfO5kP7YtWWRpG0VZF1clokKKeGeqqqSBjSnImiSS2bU3RollsymKrPaLNaUBzgmJTiAbXQrJ5SBGQ9rk8uxTJPQTlCxjQxqGy2c0ZTp4uplV2feZp/g2KVMSms67lIBrilS0zEt4TS3UmI86Vc6iSqcEqOYVMWgjcUkgiAC69M7XI72aojYo4h0LcDmaCFPZTnNWRKJ2lJr/AJkYujLo/U4ZbFVTyfk7fe5TaQXev4EIlrV8aThSVMSpADtl3pitF1T/ACed5pfVGiWefZ0AAWm0N+ok6yc+A3mJCbykD85tHNKH+gY2xNHRjmlaxmvci0eTzvNL6or1OCQ0wFyCMTMeTDSNa7qWcfnNq8w//wBaMkPGJKiHNT31STXKkXBtmtKUne6FS1Fj8Znnjbbuj1AOtS/ODqrHstCGqR+999kKXMQAQGdjti7Gtitm1P3ePZslRwskmgyBPqhctDkcsX92yQ4dZDUyHVE3sOU8KKCXYZviph+grqhfc6d4mb5tfVGoXclDf9QofRT7sWSZiH/6hWeeFDZO/e80Q6j0OZ7U10/fIyOxXdO4SW8mYBjQ5KFANiDuSNkWmlN3TOFTglrUMAqlKlB8SqOB93jTFCWsFPbKjQ5IDs1fkxBlXzY0hhb5fSjqhbxvoRxEm7qP3/Bk/c6d4mb5tfVHvc+d4mb5tfVGsG/rJ5ejpT1R4m/LJ5ejpRD3j0L4mfh+/wCDMbru+aZ0oGVMAxpclCgAHGbiIVupNmDYtY/eMa2u+rIR/wBfL6URDTbbEKC3IZzrRvPrgxvQFtEr3cX6/gylxtjxxGryrxsZ/O0jY5TWPRarJ5cjpTDxvQriH4X+/QCtFpGJKzsUPXL64e04khPBHaZme5Mnrg0ky7PNfg7UJjMCwCsLkEHiih4uuLGTZkeO/c10GzdE487mb2hqV7GI4htEcFDaI3USEePP1B1R7wKPH/ujqh7zsPi//L/foZOizE2OQpKSp5s3IE0BGyIVqu6bjU0qYQ5yQo+oRsVnkywThtBqSe8epZ8huiQJY8cfN/ZC3nYniWun3/BiSLvnD8jM82rqhMyxTWPwMzX+TV1RtqkDx5+oPdhiYE+UHnR/tg3j0HxT0+/4M70qu1ToKEKVuSknNKTUDkih7nzfEzPNq6o2CTZ0JJPbCg4SO8YUFG4uwx7MweUn6o6oSm9CY7S4q1vv+DGFWGa/xSxyoV1QhdmUlQ4qucHbGuWgI8oPQn3YGL8kob40nmHVFqTZtCu5PkBVqQyiPaw+2OlJcDXnmWTD94oZQO0bK5nmhiRnVhXM79g1xZ03uh5Mncjzg96FLkgfJTkPljYN8KSoeMl+aHtlw5NUGHHl1HixqUfmckFiCGuWHSzByzYgrZsyjxM9ad3JT1Q+myrm0ljGocYhCC4GRJZIo5HTBEdHQqqVyySHwqcKD1ZmNYMkPGo8wYTaSSxSC+72ho8M9Pgnp6xFtP0enomBRlunEDxSDR3yzitvOwrTNWAhRGIsQkkMaio5YMilNMlJvWczcNM11xqeoyzjzunP8fN84vriGmFpTBYyJQvOdrnzW1/CL64YC0+EfqA+l49TL5zsBrzQvgleDM5z9kUkVE5MxIAzJ/UG3lhmdOoRlvwBulzEw48sMzId6sDU/gxHtKVbJw5V4vQAHhstITdSHmp5R15aoiXgxmL/AFleuLS4UvOGT11VHFV0wwLMnhFKVXjE4c3eoc7KxF7CX8iDKQD8kfflh6RZMTsKpDsx21yDDbXZFwbGlRCpSk1ZweKxyo4AaPbZdq0KJMsswrRnZznU55DX0wJtuxpcm9jqSO21FspE1X8A9sBqA0aToEmak2grSvD2tMUFKSRUMzPu9QjOVauQQ8NiV/N/QbMeEUj0iOTrhlsSDHCPIUIBHoEOyRVtv/MIaFZVG2JXM6JQ+ELdDLKJlhtqSUjjSC6mYVVWtIGrSEhSwnIEhNE1DkOW5qiL/RgkXfeJchlWOozrMmRSJkI2nnEWlc4+UmMgkdFN2w8sEF33OmYnESXcUBAGQ3GKrtZKjRYHNBfc0jBLfMU9AY+qCwmy9N2ImXdLSUghISzh2KVLT7COeKU6Pyw3waPqiC2ygdznoGC8906bFB20ijrT9YP64mK5mNO+f9kFV0y3olI+ix9cJtVl+DV3uR+Qn1s8TJ9tlg/GJPO59ERl22WQRjT0xaNLD2lEkm6bGsAkjtcnzK0l+d4BChZIODUacrj2xpdvUnuJKU7BPB8ZnymrR7YAE2iU9Zg5hEJ2KpRyK5UuYCDhFOTLph++LEUiUohgUgbnqQ27qiVMmST+VGzI5QQ3l2uq70qUrFhCcLUOLIF+X2wSnkVL4bAxeocSjtQDSmzXzxXpJejjLIYosbzSOBkKp3pFX1MNXJFcjWzmnyaa9sJCjyLWUhe20crH14oemSVsKz81DI7Enwt5iOUJzUgFzrmpTTnyh9MiXhPEAAIpwqTUg6+YRdhHSrVaLO8yWqak0DrQ6WJFDicahBX+Ekxk4pSVhSJaq079CFFmDazAROkpDskD+8SfRri8mFZlSVpIA4FIII8BcyW7/QERNZicYt5ljadJrMFYVyFIJDuhgK/qlxkYl9rSlgLGJlBKhU5EAjPdAFfClEpUWyIBG4v01jUdFUCZZJKnA4uHV8glH8sCRE4KPIy5hsPTC0SASzGGMW+FJWRURRRKVJAGojeQl+c0ibctxrtc0SZEpKlkE/GhkpGalM7AU1axFaLQo5lP0nb0QU6A6TIsFp4WZwZQpBQoIxYw5SQQ4YsU5OM4aGr2I2keh0+xBKp8mXhWcKViaSlwMi+EgsC1KgGB2dLTRkpz1L61H0Qe9kXTlFtSiTJwJloViVwoU6lgFIACUkABzr17qhNusi5fBlaZY4RAmIZKhiQXZSXA2HohFxv1JmjSfhCa0STXIZCmzOHdHbv4aYrJiHrurqhGjiG4UhqIORO7byRZXJLSlMvj9+FFYZ9gR0Vy6oi2ZjUeTzJg0alcIgKUgBRIDEmrUfdQ87RWKumelSwm0kBKyEhiaamrFjZrSCiapi8pYPKAznpiqt1vQq0FQCmoaZuw2HfGlOPxhC6jzL7RBM9Mq3zJ0zGBZ1pSTuJctqBjO5yKJO4RpdzkixXgurcA4f5yVn2Rm85Xejki5xs2jWGbuMTpeEtDMTbQHAVt9hMRkDimM+ppcahYEeBMKTmIBrNnqRHLh3BHFEZrmehKm8ISaLqHc68yoFnsWX9rMHtiimzU6gYv9E0Pd95jfYvRPXFZNsUbRv0PKdrsgpnbBEqzzCrMdMeCQ0cFMcobbBpWyNPuWWFXIoP8id6JkzrgKRYQDm/KYMdGZgNzTP1bQOcLJ9ogMFqH3EKCKo2V7ntpsic2EQJ9mTsETZ85JQd1fuIrTaUbYJZF1GsGQeSUYrgUM8OLmCbTi9REZzgEaNcawq5bQBkBaPRhXALJlSzIVMJOIOMwA9GpzxmxUnZfUYkSxXk2QQ2uzDuW+GvFUDuxu42U9sD9mngJUWyr0V9kaNekhHaDAcUS0ht1AfQ8Zt2ZVaSVkAU9D2SWa0WoUD+F1iKnBt/eLDV0ReWNIVZFAlgF58w64plpSEhiHq+sANGplEmS1JDE8FkKHGdWdIlS5iWPxQFCwEzUWGY+dECXaAyfhQKapQ28nNzRY2SYFOOFDlKvyYzHG2bm54tCZHtcxJJbgss1BbvuLNBBdpBsQLPh4ZHRhmN/m+mB61TMLgzAC/iwW9EXlwEKsq0uG4RuabLI5n4H92E3Z3FLoBtoUciXYmC+4r94KQhGwH0kn2xSXtdBlpK8aSAztvLDlzER7JMGAONvrMRe/IcldDkxaSkCrtUvrhpCuLz9H3eGwvk6IXImkGgHRFmdhYmtrSP1khQ6CDE+xWgFQBVLU4LJ4FIq1KhI2Qmxypk1TSseLPDLluVM2+jbWLPzEhsVzWvhULXJtC0DNBkpSCGLCq+TMQXRaaSBftgHOckf3I6obnTAfyj5/Iam46oubfctrlJVMmJny0Zl0JYOcgOGc57IplEnXMVTWlqv+sawrlrMt7iI4GeoEHi6khOpeyCC5EOEAJSDxKgV7wuemKCxE9qTySpyw4xrm3ti1uqTLkrLjjKlOFPVJTWm9ynoiW7MxnRc02mFU6zgpMtK0YikagVVo5CS5FICrRYwkYmTsyq9RTn27IurBdaEKSsgMnCxywkMfblFRbrKjGkBVQtSSxqxJqaagc40psmNNUfhbuWdzki7rwJyCJaRQZOrZnnGc2hTmlY0aWnBdd4cqU5v4I/mjM0Kind5nRBq2RMEzEhIbJ3iNIyP32w5IzaGhNqaZu8QxnhyjpCawgmHZEwDOEzSlbGrknDHNDuGOKYzPblFKLbCDRRWGxXnu7UPROVEUTApL5RO0VlvZbzByKbN/wBwmIPBMG9kbxPnZcyNMNYjYxib77YkzpRZ2dq5iISVcYndTlaGNGl6FgG6Zw32j1J6oCEy3EEmgNsUJM+UTxDLnLApm6A+3IHdFRY1jCpiIIGcsmVdtSoJDOxcFoq29sEVsmDAA4cLetDUEa+aKvgkqMTPmbxzgg50GGO7LWj+2HTKTGapTGn9jlA4G0ywc1D0oI9nojNrRJKFAGlIhxui6VRQvceu9Lkjd63HtjTLu+Eu6Xi1ywDzUPqgAm2TFIBALgVAFSQVOTzH0RoWiaAbtQ+xf8SozmrIzrNSdwFsUjDItMs5oUx+iWc/V9EUhPzqg6kgN1wa3xYyifawMpktK+cAux2u/TAcokAuF85OHni07oiPMkWVaiE4VWlwT3iS+qtFb/RFlJnLfv7VkQ0wnWCNatT02RUypanICZ+fySXOqnFqKemJUmUsKSeDtTBQxPiIFdfFjRDYuauYfl2jLVif+LOH7umlNnn1IUDJXxxxqTChyNvw3piGZCgT8FaTUs6vU6In3LZvjpa0qGKSqiy6jhUiaKgB/i35oUlcRWW20LUkpcFJS9AMhX2RTiYRrggthwgAIJ4qwA2bguNpZxFBgJhYbcirkoAQuWnXqfpOwb4LJej1mR8YZxp8pGFP7rn0xTW2wDhjwZTwaSMIWojUCrUVNieucW4tK5kjT9GtD55kSrQLXMTwsqWWSoghOHEEUTkHV9zF0nRGeS3b059vCL9FI8uPSeziXZ5YtEtKUykJXx1JwqSgAhL0zET597KKSRaJKvBwzl1GskIZW3L7IztqaWQD6d2BcmyzkrmzJqk8GAr5QBWgkAgOc/TGYgGjiZl8ssDU0c6+eNB7I19SJkpcpE0zVzDLOFGJRZKgSSogkEEMxL0jPkSMOaWoHxqcZE5AA0fphJDirFuhLWCYQAHUmgL5KQc3OyJ6LaF2mUcLpEsgsQWxYXc5UYQyCO1EJJHHmAUDB+M2r5oi90KsHCOpIYoTgc5Yia+gCKwYmOnUUFK4uzXkrBOwyFKYKUrEWDamAGwDXFZPkmbMUoIqpywrnUgazr6IN5FiShS0rI46C++heKjRqUTaUAJKyQsABZQ/FJ75IfVGqikcVSeJlLeKCi6LSkgpJnJoQQWxyyM9wjOJeca/2T5RRY5oUjApUyUcOIqaoAqoA5DZGQJzgXU6qfIflFliI6i5fbDstTrTygemGtcZss9VnCTClZxyANeUIZaIcpCmzjxa0gVNWegfk9kIkGWoDjGW2oPWu8kF9jBvXKFjQCSlQUk5YhkeaHJYcy5bRUkrNl5oYo9qXi/gWbp4Ujqhm0Bsy7xZaJr4ORbVYUFk2ZTM6ThnUBD1ESu7CQnGi7bOU4sJVhoFcWjNTvh0iIlOS/ir/U4qlSSfwxv9UCFpqWB9uowwqThbXBdfs5VpwrVZkSiluOg0IOQOqh9sN3NYSk4lygpGHMgKJLhiBmNdd8EXJ5vLsawqpxvJWZZ9jdCTZ57prjWkHW3BpccmdIEdHbJiQslnCRnGjaNIScWGgdiGYEkLLs2wNzQFWa7VBLJSsA0PGABIo3zjTW0axByKmzWbHiYEswSxDO4Jd8xhfLWRvjy0yDLZ2rF33HKQWBq3ymq+v0RDtN2TCyUtR66uR2gsG8Vwg7GM0kzwf0R/jEBkxAMxSXZlEAs5oWLQZ9j9OCZNGFjhS9Xqlf2wM22RwFrWpyUmZNGWrGoNWBIL8x+1LMuSvgwcWRUUl2PfboONDEvd0sDZ61GAG+rw4SiEqGpWIu+12zg47HSv6EgHUtY6FqMRtCVsgztmRdJklNsw6lSC362Jjy0aM4MsVYJ15Ky5lFzGoaYp/pFmmbRMR0gKH8JjNbSMKynigOWdLdBArEQ/iOJ4myYsXwKi5BpMSHFa1SWzh6XYVpIKZMxJDZTUuGII+SKuAeaI0qWHI4OWolIzmYXyNeOMJpuhZsqdUmSOW0JHrmxoiiROsZE0r4FzixOVpFXd2eLDR2SUzwnAEYhNHfhblUpaXcEtUiKq12RyDwcrvUGs9OZSP0kWOj0rBaJRwoAK5ROFeI9+ynGItraBifIhC2ElKy5Y7MhFQS1BqJgjnzCFEFIBBILZU/4igtjY1VGZ1iCw0Xsm+MHezFDcwb+KGu2AovwiXJOZCS5LuSfXHsdDxCwImWZSghShKlrqr4QrKikJoSEyl1Dg8ZQbXURBnWoE7BShUOnIAvnQR0dDbHhEm276ag8SkWuUlPwgSonJLA9LeqOjoVwwlhOkGfJSnFLRxwpIKsgHzZ68YsMt+qL24ZU2TVE6zuogrdagFMeQtRxlrPN0dBexLgmEIllUxE0zpAUE4VMss7lsORblhF2XWmTNK+GkrThUnDwmEjEQXdjsbnMdHQObZO6iDfZPnp7XwhSCTNl0QrEGCVV9A1Rl+LU9I6OgTyaNYqxyVNUao4GsdHRIxbOfvshSktHsdBcLCpclRBIBwggFXyQTqJyBizsbAAY0nMZ56zzVz3R7HQPMTVwu0IkSp3bEnGmXwiEAkkMDiJycPlBZJ0RkiUJSrbLKQvHscu7FpmWf1o6OiUicCJyrkQO8tclIyqkGjMR34fVm4plWGLr0fRIJKbbKUSGOMODlVkzEsesx0dDuwwK1h1FnlyDSdKUCRkSK1SScUxTvi3RXfghLLHugjIBsKGYEkUxVzzjo6HcHBHK0NlVa8EBwQQEpbl+Mz3x5+BkhmVeAI2AAeqZHkdDTYt2j2VcMmyqVNRakKdOEpZIckjjPjL976c4q7z0PRaXV2/LlpWcWHgwo1qxPCh+gR0dDuCgkN3foOiTiAvCUoKDEGSmo55tCNUW9x2CVY5fBi0JmAKKqsnN/nHbHR0J2azHhIGkl4ImlFUjAXHGB1Ec2cZ/ezCapiWfvgpxU7Oc646OhWS5Ao2IaQDqlHiqHGJSrW2Iggahry2Q4gJ8XZhyzlH/zGOjodyrEm0KThQSLODh1KmEUUoN3xyAT6YtLqn2XgkkBPDh8RBISKqUlsbcVmyeoO2OjodxOIbXnf1gkJMyXLkKUpyFJTLKll6lxUjeT1QE2jTG0KUSJoSDkkAEDcCQ8ex0Ju3IWE//Z"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar2"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar3"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar4"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar5"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar6"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar7"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar8"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar9"></div>
	    <div class="slide"><img src="http://placehold.it/300x150&text=FooBar10"></div>
	  	<%-- </c:forEach> --%>
	  </div>
	</div>
<h2>marketMain.jsp</h2>

${marketList }
<%@ include file="../include/footer.jsp"%>
