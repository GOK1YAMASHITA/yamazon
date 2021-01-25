<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	int address_regist_error = (int) request.getAttribute("address_regist_error");
%>
<html>
		<link rel="stylesheet" href="css/style_Resist.css">
			<head>
				<title>エラー</title>
			</head>
		<jsp:include page="header.jsp" flush="true" />
	<br><br><br>
<body>
	<form method="POST">
		<div class="box">
			<c:choose>
				<c:when test="${address_regist_error == 1}">
					<p>宛名が入力されていません。　　　</p>
						<br>
					</c:when>
						<c:when test="${address_regist_error == 2}">
							<p>郵便番号が入力されていません。</p>
						<br>
					</c:when>
						<c:when test="${address_regist_error == 3}">
							<p>都道府県が選択されていません。　</p>
						<br>
					</c:when>
						<c:when test="${address_regist_error == 4}">
							<p>市町村が入力されていません。</p>
						<br>
					</c:when>
						<c:when test="${address_regist_error == 5}">
							<p>番地・マンション名が入力されていません。</p>
						<br>
				</c:when>
			</c:choose>
							<p>内容をお確かめの上再度ご登録をお願いします。</p>
			<input type="button"  style="position: absolute; left: 517px; top: 295px" onclick="location.href='./AddressResist'" value="住所追加ページに戻る" class="btnResult">
				<br>
			<br>
		</div>
	</form>
</body>
</html>