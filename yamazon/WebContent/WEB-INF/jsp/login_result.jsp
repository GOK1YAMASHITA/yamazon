<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.RequestDispatcher"%>
<%@ page import="javax.servlet.ServletException"%>
<%@ page import="javax.servlet.annotation.WebServlet"%>
<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
request.setCharacterEncoding("UTF-8");

int login_result = (int) request.getAttribute("login_result");
%>

<html>
<head>
	<link rel="stylesheet" href="css/style_Resist.css">
		<title>ログイン結果</title>
			</head>
				<jsp:include page="header.jsp" flush="true" />
				<br><br><br>
				<body>
					<div class="boxResult">
						<form method="GET">
										<p>ログイン失敗</p>
 										<p>入力内容をお確かめの上再度ログインを実行してください。</p>
 									<br>
 									<br>
 							 	<input type="button"  style="position: absolute; left: 530px; top: 350px" onclick="location.href='./Login'" value="ログイン画面に戻る" class="btnResult">
					</form>
				</div>
			</body>
</html>