<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="include/header.jsp"%>

<style>
.Ccontainer {
	max-width: 1500px;
	margin: 0 auto;
	padding: 20px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
	text-align: center;
	color: #333;
}

h3 {
	color: #666;
}

p {
	line-height: 1.6;
	color: #666;
}

hr {
	border: 0;
	border-top: 1px solid #ccc;
	margin: 20px 0;
}
</style>

<title>동백마켓 소개</title>
<div class="Ccontainer">
	<h1>동백장터(전통시장 온라인 주문, 배송 및 구독 프로젝트)</h1>

	<h3>전통시장 이란?</h3>
	<p>
		대한민국의 전통시장은 주로 길 양쪽에 가게들이 있는 형태에 노점이 중간중간 혼재된 구조로 이뤄져 있습니다. <br> 5일장 등 정기시장의 경우에도 많은 경우 장터에 어느 정도의 상설 매장이 있고, 장이 서는 날에는 노점이 추가되는 형태가 됩니다.<br> 고정된 지역 수요가 있기에 정기시장에서도 최소한의 상설 매장이 없다면 매우 불편하여 이런 식으로 운영하는 경우가 대부분입니다.
	</p>
	<hr>

	<h3>개발 목적</h3>
	<p>
		전반적으로 시장규모가 영세하여 상권 매력이 떨어지고, 공동 사업 수준이 낮으며 시장간 네트워크가 부족하다고 느꼈습니다. <br> 지방 정부 사업으로 시설 현대화를 진행하고 있지만, 단순한 시설 현대화만으로는 온라인 장보기의 편리함이 익숙해진 젊은 세대에게 어필할 경쟁력이 낮다고 생각합니다. <br> 전통시장에서 상인들이 직접 판매할 물품을 업로드하면 주문 및 라이더 배달이 가능하며 정해진 기간마다 원하는 물품을 주문하지 않더라도 알아서 배달해 주는 구독 시스템을 통해 젊은 세대와<br> 신혼부부를 타겟으로 구현하였습니다. <br> 각 시장마다 CAMP(물류 창고)를 만들어 각 시장에서 물류를 사입하여 업체에서 배달하는 시스템으로 직접 유통을 하며 판매하는 이익이 발생합니다. <br> 그리고 각 CAMP(물류창고)에 근무중인 직원은 전통시장 특성상 애플리케이션 사용이 어려운 사장님들의 동백마켓 사용에 도움을 주어 보수적인 시장 상인의 애플리케이션 사용 원활 및 긍정적인<br> 이미지를 줄 수 있습니다.
	</p>
</div>

<%@include file="include/footer.jsp"%>