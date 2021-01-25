<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="yamazon.ItemBean"%>
<%@ page import="yamazon.UserBean"%>
<%
request.setCharacterEncoding("UTF-8");
//インスタンスのクラスをインポートし、セッションスコープから取得
List<ItemBean> item = (List) session.getAttribute("ITEM");
List<UserBean> user = (List) session.getAttribute("USER");
List<UserBean> usercredit = (List) session.getAttribute("USERCREDIT");

int Total = 0;
int total = 0;
int count = 0;
%>

<html>
<head>
<link rel="stylesheet" href="css/style_ItemSearch.css">
<title>ご注文内容確認</title>
</head>
<form method="Get">
<jsp:include page="header.jsp" flush="true" />
<br>
<br>
		<div class="contents-wrap">
				<div class="contents-box">
					<p>■現在の商品</p>
					<% for (int i=0;i<item.size();i++) {%>
							<br>
							商品名 	:<%=item.get(i).getitemName() %>
							<br>
							メーカー:<%=item.get(i).getitemMaker() %>
							<br>
							値段	:<%=item.get(i).getPrice() %>円
							<br>
							数量	:<%=item.get(i).getSelectCnt()%>
							<br>
							<%
							total = Integer.parseInt(item.get(i).getPrice());
							count = Integer.parseInt(item.get(i).getSelectCnt());
							Total += total * count;%>
							<%} %>
							<br>
							合計金額:<%=Total%>円
				</div>
		</div>
							<br>
							<% for (int i=0;i<user.size();i++) {%>
							<p>■現在のお届け先</p>
							宛名:<%=user.get(i).getDestination() %>様
							<br>
							郵便番号:<%=user.get(i).getZip() %>
							<br>
							住所:<%=user.get(i).getState() %>
									<%=user.get(i).getCity() %>
									<%=user.get(i).getStreet() %>
												<%} %>
				<br>
				<br>
							<% for (int i=0;i<usercredit.size();i++) {%>
							<p>■現在のお支払い方法</p>
							カード名義:<%=usercredit.get(i).getCreditName() %>
							<br>
							カード番号:<%=usercredit.get(i).getCreditNum() %>
							<br>
							有効期限(月):<%=usercredit.get(i).getMonth() %>月/
							有効期限(年):<%=usercredit.get(i).getYear() %>年
												<%} %>
				<br>
		<br>
		<button type="button" onclick="location.href='/yamazon/Confirm'">注文確定</button>
		<button type="button" onclick="history.back()">戻る</button>
</form>
</html>