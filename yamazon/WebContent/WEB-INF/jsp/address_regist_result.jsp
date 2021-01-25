<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	int regist_result = (int) request.getAttribute("address_regist_result");
%>

<html>
	<link rel="stylesheet" href="css/style_Resist.css">
		<head>
			<title>登録結果</title>
		</head>
	<jsp:include page="header.jsp" flush="true" />
<body>
	<form method="Post">
		<div class="boxResult">
			<c:choose>
				<c:when test="${address_regist_result == 1}">
						<p>お届け先の追加完了</p><br>
						<p>お届け先を追加しました。</p><br><br>
				</c:when>
				<c:when test="${address_regist_result == -1}">
						<h2>追加未完了</h2>
						<p>登録に失敗しました。</p>
						<p>内容をお確かめの上再度ご登録をお願いします。</p><br><br>
				</c:when>
			</c:choose>
					<input type="button"  style="position: absolute; left: 565px; top: 445px" onclick="location.href='./top'" value="Topに戻る" class="btnResult">
			</div>
	</form>
</body>
</html>