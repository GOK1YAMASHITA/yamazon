package yamazon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



public class DBitem {
	  public static Connection connect()
			    throws ClassNotFoundException, SQLException {
			//JDBCドライバの読み込み
				Class.forName("com.mysql.cj.jdbc.Driver");
				return DriverManager.getConnection ("jdbc:mysql://localhost:3306/itemdb", "root",  "rootroot");
	  }
	  //検索結果表示
	  public static int ItemDBsearch (HttpServletRequest request) {
		  String sql = "select a.itemID, a.itemName, a.itemMaker, a.price, a.stock, a.image from item a where a.itemName like ?";
		  ArrayList<String> itemid = new ArrayList<String>();
		  ArrayList<String> itemname = new ArrayList<String>();
		  ArrayList<String> itemmaker = new ArrayList<String>();
		  ArrayList<String> price = new ArrayList<String>();
		  ArrayList<String> stock = new ArrayList<String>();
		  ArrayList<String> image = new ArrayList<String>();

		  int result = -1;
	  			try {
	  				try (Connection con = DBitem.connect()) {
	  					try (PreparedStatement ps = con.prepareStatement(sql)) {
	  						con.setAutoCommit(false);

		  					request.setCharacterEncoding("UTF-8");
	  					//入力値を取得
	  					String itemName = request.getParameter("text1");
	  					//sqlにセット
	  					ps.setString(1,"%" + itemName + "%");
	  					//ResultSetに保存
	  					ResultSet rs = ps.executeQuery();

	  					//DBに登録されているIDであれば表示
	  					while (rs.next()) {
	  							result = 1;
	  							itemid.add(rs.getString("itemID"));
	  							itemname.add(rs.getString("itemName"));
	  							itemmaker.add(rs.getString("itemMaker"));
	  							price.add(rs.getString("price"));
	  							stock.add(rs.getString("stock"));
	  							image.add(rs.getString("image"));
	  					}
	  							request.setAttribute("ITEMID", itemid);
	  							request.setAttribute("ITEMNAME", itemname);
	  							request.setAttribute("ITEMMAKER", itemmaker);
	  							request.setAttribute("PRICE", price);
	  							request.setAttribute("STOCK", stock);
	  							request.setAttribute("IMAGE", image);
	  					//ResultSetを確定
	  					con.commit();

	  					rs.close();
	  					ps.close();

	  			}catch (Exception e){
	  				System.out.println("SQLException:" + e.getMessage());
	  			}
				} catch (Exception e) {
					System.out.println("SQLException:" + e.getMessage());
				}
				} catch (Exception e) {
					System.out.println("SQLException:" + e.getMessage());
				}
  				return (result);
	  			}
	//商品詳細
	public static void ItemDBdetail (HttpServletRequest request) {
	  String sql = "select itemID, itemName, itemMaker, price, stock, image from item where itemID = ?";
	  ArrayList<String> itemid = new ArrayList<String>();
	  ArrayList<String> itemname = new ArrayList<String>();
	  ArrayList<String> itemmaker = new ArrayList<String>();
	  ArrayList<String> price = new ArrayList<String>();
	  ArrayList<String> stock = new ArrayList<String>();
	  ArrayList<String> image = new ArrayList<String>();

		try {
				try (Connection con = DBitem.connect()) {
					try (PreparedStatement ps = con.prepareStatement(sql)) {
						con.setAutoCommit(false);

  					request.setCharacterEncoding("UTF-8");
  					// ItemSearch.javaのdoPostメソッドよりItemIDを取得
  					String itemID = request.getParameter("itemid");
  					//sqlにセット
  					ps.setString(1, itemID);
  					//ResultSetに保存
  					ResultSet rs = ps.executeQuery();
  					while (rs.next()) {
  						itemid.add(rs.getString("itemID"));
  						itemname.add(rs.getString("itemName"));
  						itemmaker.add(rs.getString("itemMaker"));
  						price.add(rs.getString("price"));
  						stock.add(rs.getString("stock"));
  						image.add(rs.getString("image"));
  					}
  						request.setAttribute("ITEMID", itemid);
  						request.setAttribute("ITEMNAME", itemname);
  						request.setAttribute("ITEMMAKER", itemmaker);
  						request.setAttribute("PRICE", price);
  						request.setAttribute("STOCK", stock);
  						request.setAttribute("IMAGE", image);
	  				//ResultSetを確定
	  				con.commit();

	  				rs.close();
	  				ps.close();

			}catch (Exception e){
  				System.out.println("psSQLException:" + e.getMessage());
}
	  			}catch (Exception e){
	  				System.out.println("conSQLException:" + e.getMessage());
}
			}catch (Exception e){
  				System.out.println("SQLException:" + e.getMessage());
		}
}
//カート
public static void ItemDBcart (HttpServletRequest request) {
	//結果表示用
	String sql = "select itemID, itemName, itemMaker, price, stock, image from item where itemID = ?";
  ArrayList<ItemBean> ITEM =null;
	try {
			try (Connection con = DBitem.connect()) {
				try (PreparedStatement ps = con.prepareStatement(sql)) {
					con.setAutoCommit(false);
					//文字コード
 					request.setCharacterEncoding("UTF-8");
 					//セッションスコープに保存するインスタンスの生成
					ItemBean item = new ItemBean();
					//HttpSessionインスタンスの取得
 					 HttpSession session = request.getSession(false);
 					 //sessionがnulllの場合新規のセッションを開始
 					 if(session == null) {
 						 session = request.getSession(true);
 						 	System.out.println("現在セッションは存在しません。");
 					  	}else {
 					  		System.out.println("現在セッションは存在します" );
 					  	}
 						// sessionにITEMが格納されているかどうか判断。なければ新規にArrayListを作成し、ある場合は、その内容をArrayListに格納する
 						if(session.getAttribute("ITEM")==null) {
 							System.out.println("なし");
 							ITEM = new ArrayList<ItemBean>();
 						}else {
 							System.out.println("あり");
 							ITEM = (ArrayList<ItemBean>)session.getAttribute("ITEM");
 						}
					// ItemSearch.javaのdoPostメソッドよりItemID,選択数量を取得
					item.setitemID(request.getParameter("itemid"));
						System.out.println("ITEMID = " + item.getitemID());
					item.setSelectCnt(request.getParameter("selectcnt"));
						System.out.println("SelectCnt = " + item.getSelectCnt());
					//sqlにセット
					ps.setString(1, item.getitemID());
					//ResultSetに保存
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						//ItemBeanに値を格納する
						item.setitemName(rs.getString("itemName"));
						item.setitemMaker(rs.getString("itemMaker"));
						item.setPrice(rs.getString("price"));
						item.setStock(rs.getString("stock"));
						item.setImage(rs.getString("image"));
						}
					//追加する時for文でサイズ確認
					ITEM.add(item);
					//セッションスコープにインスタンスを保存
					session.setAttribute("ITEM",ITEM);
  				//ResultSetを確定
  				con.commit();
  				rs.close();
  				ps.close();
		}catch (Exception e){
				System.out.println("psSQLException:" + e.getMessage());
		}
  			}catch (Exception e){
  				System.out.println("conSQLException:" + e.getMessage());
  		}
		}catch (Exception e){
				System.out.println("SQLException:" + e.getMessage());
		}
}
	//在庫数を減らす
	public static void ItemDBminus (HttpServletRequest request) {
		//結果表示用
		String sql = "update item set stock = stock - ? where itemName = ?";

		try {
				try (Connection con = DBitem.connect()) {
					try (PreparedStatement ps = con.prepareStatement(sql)) {
						con.setAutoCommit(false);
						//文字コード
	 					request.setCharacterEncoding("UTF-8");
						//HttpSessionインスタンスの取得
	 					 HttpSession session = request.getSession(false);
	 					 //セッションより注文情報を取得しリストに格納
	 					ArrayList<ItemBean> item = (ArrayList) session.getAttribute("ITEM");
	 					//リストの要素分取り出し、sqlの?に値を入れる
	 					for (int i = 0; i < item.size(); i++)
	 					{
	 						ps.setString(2, item.get(i).getitemName());
	 						ps.setString(1, item.get(i).getSelectCnt());
		 					int resultItemMinus = ps.executeUpdate();
		 					System.out.println("ResultItemMinus" + resultItemMinus);
	 					}
	  				con.commit();
	  				ps.close();
			}catch (Exception e){
					System.out.println("psSQLException:" + e.getMessage());
			}
	  			}catch (Exception e){
	  				System.out.println("conSQLException:" + e.getMessage());
	  		}
			}catch (Exception e){
					System.out.println("SQLException:" + e.getMessage());
			}
	}
}