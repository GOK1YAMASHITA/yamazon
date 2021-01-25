<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<html>
<link rel="stylesheet" href="css/style_Resist.css">
<head>
<title>アカウント情報</title>
</head>
<form method="Get">
<jsp:include page="header.jsp" flush="true" />
<div class="box">
<br>
<br>
	<p>アカウント情報</p>
		<br>
			<a id="box1" id="b1" href = "/yamazon/Address">住所</a><br>
			<a id="box2" id="b2" href = "/yamazon/Payment">お支払い方法</a><br><br>
		<button type="button" onclick="location.href='/yamazon/Top'">トップへ戻る</button>
</div>
</form>
</html>