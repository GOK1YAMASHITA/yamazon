<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
		<link rel="stylesheet" href="css/style_Resist.css">
			<head>
				<title>クレジットカードを追加</title>
			</head>
		<jsp:include page="header.jsp" flush="true" />
	<br><br><br>
<body>
		<div class="box">
			<p>登録するクレジットカードの情報を入力してください。</p>
			<br>
					<p>カード名義人(半角ローマ字)<br><input id="box1" type="text" name="text1" ></p>
					<p>カード番号(ハイフンなし)<br><input id="box2" type="text" name="text2" ></p>
					<p>有効期限(月)<br><select name="stock" id="stock3">
							<option value="">選択してください</option>
							<option value="1">01</option>
							<option value="2">02</option>
							<option value="3">03</option>
							<option value="4">04</option>
							<option value="5">05</option>
							<option value="6">06</option>
							<option value="7">07</option>
							<option value="8">08</option>
							<option value="9">09</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							</select>月</p>
					<p>有効期限(年)<br><select name="stockyear" id="stock4" >
							<option value="">選択してください</option>
							<option value="2019">2019</option>
							<option value="2020">2020</option>
							<option value="2021">2021</option>
							<option value="2022">2022</option>
							<option value="2023">2023</option>
							<option value="2024">2024</option>
							<option value="2025">2025</option>
							<option value="2026">2026</option>
							<option value="2027">2027</option>
							<option value="2028">2028</option>
							<option value="2029">2029</option>
							<option value="2030">2030</option>
							</select>年</p>
			<br>
			<button type="button" onclick="Click('stock1', 'stock2', 'stock3', 'stock4');" style="position: absolute; left: 565px; top: 510px" class="btnResult">クレジットカードを追加</button>
			<br>
			<br>
			<button type="button" onclick="history.back()">戻る</button>
		</div>
</body>
</html>

<script>
function Click(cardname,creditnum,month,year) {

	// 月(プルダウン)のオブジェクトを取得
	var objMonth = document.getElementById(month);
	// 選択した月を取得
	var indexMonth = objMonth.selectedIndex;
	// 月からvalue値を取得
	var valMonth = objMonth.options[indexMonth].value;
	// 年(プルダウン)のオブジェクトを取得
	var objYear = document.getElementById(year);
	// 選択した年を取得
	var indexYear = objYear.selectedIndex;
	// 年からvalue値を取得
	var valYear = objYear.options[indexYear].value;
	//カード名義人に入力された値を取得
	var objCreditName = document.getElementById("box1");
	var valCreditName = objCreditName.value
	//カード番号に入力された値を取得
	var objCreditNum = document.getElementById("box2");
	var valCreditNum = objCreditNum.value

	var form = document.createElement('form');
	var request1 = document.createElement('input');
	var request2 = document.createElement('input');
	var request3 = document.createElement('input');
	var request4 = document.createElement('input');
	var request5 = document.createElement('input');

	form.action = "/yamazon/PaymentRegist";//PaymentRegist.javaに遷移
	form.method = 'Post';//Postとしてセット
	//カード名義をセット
	request1.type = 'hidden'; //入力フォームが表示されないように
	request1.name = 'creditname'; //ネームを付ける
	request1.value = valCreditName; //選択した値を取得
	//カード番号をセット
	request2.type = 'hidden'; //入力フォームが表示されないように
	request2.name = 'creditnum'; //ネームを付ける
	request2.value = valCreditNum; //選択した値を取得
	//有効期限(月)をセット
	request3.type = 'hidden'; //入力フォームが表示されないように
	request3.name = 'month'; //ネームを付ける
	request3.value = valMonth; //選択した値を取得
	//有効期限(年)をセット
	request4.type = 'hidden'; //入力フォームが表示されないように
	request4.name = 'year'; //ネームを付ける
	request4.value = valYear; //選択した値を取得

	form.appendChild(request1); // カード名義をrequestにセット
	form.appendChild(request2); // カード番号をrequestにセット
	form.appendChild(request3); // 有効期限(月)をrequestにセット
	form.appendChild(request4); // 有効期限(年)をrequestにセット

	document.body.appendChild(form);
	form.submit();
}
</script>