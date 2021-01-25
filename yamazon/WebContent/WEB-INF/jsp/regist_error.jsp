<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	int regist_error = (int) request.getAttribute("regist_error");
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
				<c:when test="${regist_error == 1}">
					<p>名前が入力されていません。　　　</p>
						<br>
					</c:when>
						<c:when test="${regist_error == 2}">
							<p>メールアドレスが入力されていません。</p>
						<br>
					</c:when>
						<c:when test="${regist_error == 3}">
							<p>電話番号が入力されていません。　</p>
						<br>
					</c:when>
						<c:when test="${regist_error == 4}">
							<p>パスワードが入力されていません。</p>
						<br>
				</c:when>
			</c:choose>
							<p>内容をお確かめの上再度ご登録をお願いします。</p>
			<input type="button"  style="position: absolute; left: 517px; top: 295px" onclick="location.href='./Resist'" value="アカウント作成画面に戻る" class="btnResult">
				<br>
			<br>
		</div>
	</form>
</body>
</html>