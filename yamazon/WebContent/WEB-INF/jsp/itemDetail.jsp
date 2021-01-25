<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
request.setCharacterEncoding("UTF-8");
List<String> itemid = (List)request.getAttribute("ITEMID");
List<String> itemName = (List)request.getAttribute("ITEMNAME");
List<String> itemMaker = (List)request.getAttribute("ITEMMAKER");
List<String> price = (List)request.getAttribute("PRICE");
List<String> stock = (List)request.getAttribute("STOCK");
List<String> image = (List)request.getAttribute("IMAGE");
%>

<html>
<head>
<link rel="stylesheet" href="css/style_ItemSearch.css">
<title>商品詳細</title>
</head>
<jsp:include page="header.jsp" flush="true" />
<br>
<br>
		<div class="contents-wrap">
				<div class="contents-box">
						<img src="img/<%=image.get(0)%>" alt="flexbox" name="ItemID"><br>
							商品名 	:<%=itemName.get(0) %><br>
							メーカー:<%=itemMaker.get(0) %><br>
							値段	:<%=price.get(0) %>円<br>
							数量:<select name="stock" id="stock1">
							<option value="">選択してください</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							</select>本<br>
				</div>
			</div>
		<button type="button" onclick="Click(<%=itemid.get(0)%>,'stock1');">カートに入れる</button>
		<br>
		<br>
		<button type="button" onclick="history.back()">戻る</button>
</html>

<script>
function Click(itemid,idname) {
	// 数量(プルダウン)のオブジェクトを取得
	var obj = document.getElementById(idname);
	// 選択したインデックス番号を取得
	var index = obj.selectedIndex;
	// インデックス番号からvalue値を取得
	var val = obj.options[index].value;

	var form = document.createElement('form');
	var request1 = document.createElement('input');
	var request2 = document.createElement('input');

	form.action = "/yamazon/Cart";//カートページに遷移
	form.method = 'Get';//GETとしてセット
	//itemidをセット
	request1.type = 'hidden'; //入力フォームが表示されないように
	request1.name = 'itemid'; //itemidとしてネームを付ける
	request1.value = itemid; //itemidに選択した画像からitemidを取得
	//数量をセット
	request2.type = 'hidden'; //入力フォームが表示されないように
	request2.name = 'selectcnt'; //selectcntとしてネームを付ける
	request2.value = val;

	form.appendChild(request1); // itemidをrequestにセット
	form.appendChild(request2); // selectcnt(数量)をrequestにセット

	document.body.appendChild(form);
	form.submit();
}
</script>