<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
		<link rel="stylesheet" href="css/style_Resist.css">
			<head>
				<title>ログイン</title>
			</head>
		<jsp:include page="header.jsp" flush="true" />
	<br><br><br>
<body>
	<form method="POST">
		<div class="box">
			<p>ログイン</p>
			<br>
					<p>メールアドレス<br><input id="box2" id="b2" type="text" name="text1" required></p>
					<p>パスワード<br><input id="box4" id="b4" type="password" name="text2" required></p>
			<br>
			<input type="submit" style="position: absolute; left: 570px; top: 345px" value="ログイン" class="btnResult">
			<br>
			<br>
		</div>
	</form>
</body>
</html>