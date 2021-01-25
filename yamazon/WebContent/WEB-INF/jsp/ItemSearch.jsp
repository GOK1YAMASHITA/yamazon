
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
request.setCharacterEncoding("UTF-8");
//String userID = (String) request.getAttribute("USERID");
//String Name = (String) request.getAttribute("NAME");
//String mailaddress = (String) request.getAttribute("MAILADDRESS");
//String tel = (String) request.getAttribute("TEL");
List<String> itemid = (List)request.getAttribute("ITEMID");
List<String> itemName = (List)request.getAttribute("ITEMNAME");
List<String> itemMaker = (List)request.getAttribute("ITEMMAKER");
List<String> price = (List) request.getAttribute("PRICE");
List<String> stock = (List) request.getAttribute("STOCK");
List<String> image = (List) request.getAttribute("IMAGE");

int Post_ItemSearch = (int) request.getAttribute("ItemSearch");
%>

<html>
<head>
<link rel="stylesheet" href="css/style_ItemSearch.css">
<title>検索結果</title>
</head>
<jsp:include page="header.jsp" flush="true" />
<br><br><br>
		<body>
	<!--  <form method="Post"> -->
		<c:choose>
		<c:when test = "${ItemSearch >= 1}">
			<% for (int i=0;i<itemName.size();i++) {%>
		<div class="contents-wrap">
				<div class="contents-box">
						<input type="image" src="img/<%=image.get(i) %>" alt="flexbox" name="ItemID" onclick="Click(<%=itemid.get(i) %>)">
							<br>
							商品名 	:<%=itemName.get(i) %>
							<br>
							メーカー:<%=itemMaker.get(i) %>
							<br>
							値段	:<%=price.get(i) %>円
							<br>
							在庫数	:<%=stock.get(i) %>本
					<br>
				</div>
			</div>
																			<%} %>
					<br>
			</c:when>
			<c:when test="${ItemSearch  == -1}">
							<h2>検索結果</h2>
 							<p>入力された商品は存在しません。</p>
 							<br>
 						<br>
			</c:when>
			</c:choose>
	<!-- </form> -->
</body>
</html>

<script>
function Click(num) {
	var form = document.createElement('form');
	var request = document.createElement('input');

	form.method = 'Post';//POSTとしてセット

	request.type = 'hidden'; //入力フォームが表示されないように
	request.name = 'itemid'; //itemidとしてネームを付ける
	request.value = num; //numに選択した画像からitemidを取得

	form.appendChild(request); // requestにセット
	document.body.appendChild(form);

    form.submit();
}
</script>