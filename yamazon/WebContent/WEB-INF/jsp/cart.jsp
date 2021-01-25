<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="yamazon.ItemBean"%>
<%
request.setCharacterEncoding("UTF-8");
//インスタンスのクラスをインポートし、セッションスコープから取得
List<ItemBean> item = (List) session.getAttribute("ITEM");

int total = 0;
int count = 0;
int Total = 0;
%>

<html>
<head>
<link rel="stylesheet" href="css/style_ItemSearch.css">
<title>カート</title>
</head>
<jsp:include page="header.jsp" flush="true" />
<br>
<br>
		<div class="contents-wrap">
				<div class="contents-box">
					<% for (int i=0;i<item.size();i++) {%>
						<img src="img/<%=item.get(i).getImage()%>" alt="flexbox" name="ItemID">
							<br>
							商品名 	:<%=item.get(i).getitemName()%>
							<br>
							メーカー:<%=item.get(i).getitemMaker() %>
							<br>
							値段	:<%=item.get(i).getPrice() %>円
							<br>
							数量	:<%=item.get(i).getSelectCnt() %>
							<br>
							<%
							total = Integer.parseInt(item.get(i).getPrice());
							count = Integer.parseInt(item.get(i).getSelectCnt());
							Total += total * count;
							%>
							<button type="button" onclick="DeleteCart()">カートから削除</button>
							<br>
							<%} %>
							<br>
							現在の合計金額 :<%=Total %>円
				</div>
			</div>
		<button type="button" onclick="Click()">レジに進む</button>
		<button type="button" onclick="history.back()">戻る</button>
</html>

<script>
function Click() {
	form.action = "/yamazon/Cart";//カートページに遷移
	form.method = 'Post';//Postとしてセット
	form.submit();
}

function DeleteCart() {
	<%session.removeAttribute("ITEM");%>

}
</script>