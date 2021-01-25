<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<html>
<link rel="stylesheet" href="css/style_Resist.css">
<head>
<title>住所設定</title>
</head>
<form method="Get">
<jsp:include page="header.jsp" flush="true" />
<div class="box">
<br>
<br>
	<p>住所設定</p>
			<br>
				<a id="box1" id="b1" href = "/yamazon/AddressRegist">住所追加</a><br>
				<a id="box2" id="b2" href = "/yamazon/AddressChenge">住所変更</a><br>
				<a id="box2" id="b2" href = "/yamazon/AddressDelete">住所削除</a>
				<br>
				<br>
			<button type="button" onclick="history.back()">戻る</button>
		<button type="button" onclick="location.href='/yamazon/Top'">トップへ戻る</button>
</div>
</form>
</html>