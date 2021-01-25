<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
		<link rel="stylesheet" href="css/style_Resist.css">
			<head>
				<title>新規登録</title>
			</head>
		<jsp:include page="header.jsp" flush="true" />
	<br><br><br>
<body>
	<form method="POST">
		<div class="box">
			<p>アカウントを作成</p>
			<br>
					<p>名前<br><input id="box1" id="b1" type="text" name="text1" ></p>
					<p>メールアドレス<br><input id="box2" id="b2" type="text" name="text2" ></p>
					<p>電話番号<br><input id="box3" id="b3" type="text" name="text3" ></p>
					<p>パスワード<br><input id="box4" id="b4" type="password" name="text4" ></p>
			<br>
			<input type="submit" style="position: absolute; left: 520px; top: 455px" value="yamazonのアカウント作成" class="btnResult">
			<br>
			<br>
		</div>
	</form>
</body>
</html>