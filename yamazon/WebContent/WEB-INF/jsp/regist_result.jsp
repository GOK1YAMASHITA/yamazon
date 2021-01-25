<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	int regist_result = (int) request.getAttribute("regist_result");
%>

<html>
	<link rel="stylesheet" href="css/style_Resist.css">
		<head>
			<title>登録完了</title>
		</head>
	<jsp:include page="header.jsp" flush="true" />
<body>
	<form method="GET">
		<div class="boxResult">
			<c:choose>
				<c:when test="${regist_result == 1}">
						<p>アカウントの作成完了</p><br>
						<p>ご登録ありがとうございます。</p><br>
						<p>ご登録頂きましたメールアドレスに登録完了メールをお送りしますのでご確認をお願い致します。</p><br><br>
				</c:when>
				<c:when test="${regist_result == -1}">
						<h2>作成未完了</h2>
						<p>登録に失敗しました。</p>
						<p>内容をお確かめの上再度ご登録をお願いします。</p><br><br>
				</c:when>
			</c:choose>
					<input type="button"  style="position: absolute; left: 565px; top: 445px" onclick="location.href='./top'" value="Topに戻る" class="btnResult">
			</div>
	</form>
</body>
</html>