<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
		<link rel="stylesheet" href="css/style_Resist.css">
			<head>
				<title>住所追加</title>
			</head>
		<jsp:include page="header.jsp" flush="true" />
	<br><br><br>
<body>
		<div class="box">
			<p>お届け先を入力してください。</p>
			<br>
					<p>宛名<br><input id="box1" type="text" name="text1" ></p>
					<p>郵便番号(ハイフンなし)<br><input id="box2" type="text" name="text2" ></p>
					<p>都道府県<br><select name="stock" id="stock3">
							<option value="">選択してください</option>
							<option value="北海道">北海道</option>
							<option value="青森県">青森県</option>
							<option value="岩手県">岩手県</option>
							<option value="宮城県">宮城県</option>
							<option value="秋田県">秋田県</option>
							<option value="山形県">山形県</option>
							<option value="福島県">福島県</option>
							<option value="茨城県">茨城県</option>
							<option value="栃木県">栃木県</option>
							<option value="群馬県">群馬県</option>
							<option value="埼玉県">埼玉県</option>
							<option value="千葉県">千葉県</option>
							<option value="東京都">東京都</option>
							<option value="神奈川県">神奈川県</option>
							<option value="新潟県">新潟県</option>
							<option value="富山県">富山県</option>
							<option value="石川県">石川県</option>
							<option value="福井県">福井県</option>
							<option value="山梨県">山梨県</option>
							<option value="長野県">長野県</option>
							<option value="岐阜県">岐阜県</option>
							<option value="静岡県">静岡県</option>
							<option value="愛知県">愛知県</option>
							<option value="三重県">三重県</option>
							<option value="滋賀県">滋賀県</option>
							<option value="京都府">京都府</option>
							<option value="大阪府">大阪府</option>
							<option value="兵庫県">兵庫県</option>
							<option value="奈良県">奈良県</option>
							<option value="和歌山県">和歌山県</option>
							<option value="鳥取県">鳥取県</option>
							<option value="島根県">島根県</option>
							<option value="岡山県">岡山県</option>
							<option value="広島県">広島県</option>
							<option value="山口県">山口県</option>
							<option value="徳島県">徳島県</option>
							<option value="香川県">香川県</option>
							<option value="愛媛県">愛媛県</option>
							<option value="高知県">高知県</option>
							<option value="福岡県">福岡県</option>
							<option value="佐賀県">佐賀県</option>
							<option value="長崎県">長崎県</option>
							<option value="熊本県">熊本県</option>
							<option value="大分県">大分県</option>
							<option value="宮崎県">宮崎県</option>
							<option value="鹿児島県">鹿児島県</option>
							<option value="沖縄県">沖縄県</option>
							</select></p>
					<p>市区町村<br><input id="box3" type="text" name="text3" ></p>
					<p>番地・マンション名<br><input id="box4" type="text" name="text4" ></p>
			<br>
			<button type="button" onclick="Click('stock1', 'stock2', 'stock3', 'stock4', 'stock5');" style="position: absolute; left: 565px; top: 510px" class="btnResult">住所を追加</button>
			<br>
			<br>
			<button type="button" onclick="history.back()">戻る</button>
		</div>
</body>
</html>

<script>
function Click(destination,zip,state,city,street) {
	//destination(宛先)に入力された値を取得
	var obj2 = document.getElementById("box1");
	var val2 = obj2.value
	//zip(郵便番号)に入力された値を取得
	var obj3 = document.getElementById("box2");
	var val3 = obj3.value
	//city(市区町村)に入力された値を取得
	var obj4 = document.getElementById("box3");
	var val4 = obj4.value
	//street(番地、マンンション名)に入力された値を取得
	var obj5 = document.getElementById("box4");
	var val5 = obj5.value
	// 都道府県(プルダウン)のオブジェクトを取得
	var obj = document.getElementById(state);
	// 選択した都道府県名を取得
	var index = obj.selectedIndex;
	// 都道府県名からvalue値を取得
	var val = obj.options[index].value;

	var form = document.createElement('form');
	var request1 = document.createElement('input');
	var request2 = document.createElement('input');
	var request3 = document.createElement('input');
	var request4 = document.createElement('input');
	var request5 = document.createElement('input');

	form.action = "/yamazon/AddressRegist";//AddressRegist.javaに遷移
	form.method = 'Post';//Postとしてセット
	//宛先をセット
	request1.type = 'hidden'; //入力フォームが表示されないように
	request1.name = 'destination'; //ネームを付ける
	request1.value = val2; //選択した値を取得
	//郵便番号をセット
	request2.type = 'hidden'; //入力フォームが表示されないように
	request2.name = 'zip'; //ネームを付ける
	request2.value = val3; //選択した値を取得
	//都道府県をセット
	request3.type = 'hidden'; //入力フォームが表示されないように
	request3.name = 'state'; //ネームを付ける
	request3.value = val; //選択した値を取得
	//市区町村をセット
	request4.type = 'hidden'; //入力フォームが表示されないように
	request4.name = 'city'; //ネームを付ける
	request4.value = val4; //選択した値を取得
	//番地・マンション名をセット
	request5.type = 'hidden'; //入力フォームが表示されないように
	request5.name = 'street'; //ネームを付ける
	request5.value = val5; //選択した値を取得

	form.appendChild(request1); // 宛先をrequestにセット
	form.appendChild(request2); // 郵便番号をrequestにセット
	form.appendChild(request3); // 都道府県をrequestにセット
	form.appendChild(request4); // 市区町村をrequestにセット
	form.appendChild(request5); // 番地・マンションをrequestにセット

	document.body.appendChild(form);
	form.submit();
}
</script>