<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="yamazon.UserBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	HttpSession session1 = request.getSession(true);
	String str = (String)session1.getAttribute("login");
	System.out.println("login="+str);
%>
<html>
<link rel="stylesheet" href="css/style_Top.css">
<form id="form" action="http://localhost:8080/yamazon/top" method="GET">
<header id="head">
	<h1> <a href="/yamazon/top" style="color:#000000; text-decoration:none; top:10px; left:15px">yamazon</a>	</h1>
		<input id="sbox" id="s" name="text1" type="text" placeholder="商品名を入力" />
			<input id="sbtn" type="image" formaction="/yamazon/itemSearch" value="検索" src="img/search.png"/>
				<ul class="pull">
					<li><a href="#">アカウントサービス</a>
						<ul>
							<li><a href="/yamazon/Resist">新規登録</a></li>
							<li><a href="/yamazon/Login">ログイン</a></li>
							<li><a href="/yamazon/Auth">アカウント情報</a></li>
							<li><a href="#">注文履歴</a></li>
							<li><a href="#">閲覧履歴</a></li>
							<li><a href="/yamazon/Logout">ログアウト</a>
							</li>
						</ul>
					</ul>
</header>
</form>
		<input id="cbtn" type="image" value="カート" src="img/cart.png" onclick = "location.href='/yamazon/Cart'"/>
				<% if(str == "OK") {%>
						<p id="login">ログイン中です</p>
				<% }%>
</html>
