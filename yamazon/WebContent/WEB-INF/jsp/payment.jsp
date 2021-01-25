<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<html>
<link rel="stylesheet" href="css/style_Resist.css">
<head>
<title>お支払い方法設定</title>
</head>
<form method="Get">
<jsp:include page="header.jsp" flush="true" />
<div class="box">
<br>
<br>
	<p>お支払い方法設定</p>
			<br>
				<a id="box1" id="b1" href = "/yamazon/PaymentRegist">クレジットカードを追加</a><br>
				<a id="box2" id="b2" href = "/yamazon/PaymentView">登録中のクレジットカード</a><br>
				<br>
				<br>
			<button type="button" onclick="history.back()">戻る</button>
		<button type="button" onclick="location.href='/yamazon/Top'">トップへ戻る</button>
</div>
</form>
</html>