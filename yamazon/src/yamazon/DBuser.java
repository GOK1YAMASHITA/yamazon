package yamazon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class DBuser {
	  public static Connection connect()
			    throws ClassNotFoundException, SQLException {
			//JDBCドライバの読み込み
				Class.forName("com.mysql.cj.jdbc.Driver");
				return DriverManager.getConnection ("jdbc:mysql://localhost:3306/Userdb", "root",  "rootroot");
	  }
	  //新規登録
	  public static int UserDBinsert (UserBean request) {
		  	String sql = "insert into user (Name, mailaddress, tel, password) values(?,?,?,?)";
		  	int result = -1;
		  		try {
		  			try (Connection con = DBuser.connect()) {
		  				try (PreparedStatement ps = con.prepareStatement(sql)) {
		  						con.setAutoCommit(false);
		  				//入力値を取得
		  				String Name = request.getName();
		  				String mailaddress = request.getMailaddress();
		  				String tel = request.getTel();
		  				String password = request.getPassword();

		  				//sqlにセット
		  				ps.setString(1,Name);
		  				ps.setString(2,mailaddress);
		  				ps.setString(3,tel);
		  				ps.setString(4,password);

		  		//更新行数を返す
		  				result = ps.executeUpdate();
		  				System.out.println("DbUserResult" + result);

		  				con.commit();

		  				ps.close();
		  				con.close();
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
	  //削除
	  public static int UserDBdelete (HttpServletRequest request) {
		  String sql = "delete from user where userID = ?";
		  int result = -1;
			try {
  				try (Connection con = DBuser.connect()) {
  					try (PreparedStatement ps = con.prepareStatement(sql)) {
  						con.setAutoCommit(false);
  						//入力値を取得
  						String userID = request.getParameter("text1");
  						request.setAttribute("USERID", userID);
  						//sqlにセット
  						ps.setString(1,userID);
  						//反映して確定
  						result = ps.executeUpdate();

  						con.commit();

  						ps.close();
  						con.close();

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
	  //ログイン
	  public static int UserDBlogin (UserBean request) {
		  String sql = "select mailaddress, password from user where mailaddress = ? and password = ?";

		  int result = -1;
	  			try {
	  				try (Connection con = DBuser.connect()) {
	  					try (PreparedStatement ps = con.prepareStatement(sql)) {
	  						con.setAutoCommit(false);
		  		//暗号化の秘密鍵、暗号化アルゴリズムを設定
		  		String key = "abcdefghijklmnop";
		  		String algorithm = "AES";
	  			//入力値を取得
		  		String mailaddress = request.getMailaddress();
		  		String password = request.getPassword();
	  					try {
	  							//入力されたパスワードを暗号化
	  							SecretKeySpec sksSpec = new SecretKeySpec(key.getBytes(), algorithm);
	  							Cipher cipher = Cipher.getInstance(algorithm);
	  							cipher.init(Cipher.ENCRYPT_MODE, sksSpec);
	  							byte[] encrypted = cipher.doFinal(password.getBytes());
	  							// Base64エンコード
	  							password = Base64.encode(encrypted);
	  								System.out.println("password = " + password);
	  					//sqlにセット
	  					ps.setString(1, mailaddress);
	  					ps.setString(2, password);
	  					//ResultSetに保存
	  					ResultSet rs = ps.executeQuery();
	  					//DBに登録されているIDであれば表示
	  					while (rs.next()) {
	  							result = 1;
	  							rs.getString("mailaddress");
	  							rs.getString("password");
	  					}
	  					//ResultSetを確定
	  					con.commit();
	  					rs.close();
	  					ps.close();
  				}catch(Exception e){
					System.out.println("SQLException:" + e.getMessage());
				}
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
	  //住所設定
	  public static int UserDBaddressinsert (UserBean request) {
		  	String sql = "update user set destination = ?, zip = ?, state = ?, city = ?, street = ? where password = ?";
		  	int result = -1;
		  		try {
		  			try (Connection con = DBuser.connect()) {
		  				try (PreparedStatement ps = con.prepareStatement(sql)) {
		  						con.setAutoCommit(false);
		  				//入力値を取得
		  				String Destination = request.getDestination();
		  				String Zip = request.getZip();
		  				String State = request.getState();
		  				String City = request.getCity();
		  				String Street = request.getStreet();
		  				String Password = request.getPassword();

		  				//sqlにセット
		  				ps.setString(1, Destination);
		  				ps.setString(2, Zip);
		  				ps.setString(3, State);
		  				ps.setString(4, City);
		  				ps.setString(5, Street);
		  				ps.setString(6, Password);

		  				//更新行数を返す
		  				result = ps.executeUpdate();
		  				System.out.println("dbresult" + result);

		  				con.commit();

		  				ps.close();
		  				con.close();
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
	  //レジ住所表示
	  public static int UserDBaddressSelect (HttpServletRequest request) {
		  	String sql = "select password, destination, zip, state, city, street from user where password = ?";
		  	ArrayList<UserBean> User = new ArrayList<UserBean>();
		  	int result = -1;
		  		try {
		  			try (Connection con = DBuser.connect()) {
		  				try (PreparedStatement ps = con.prepareStatement(sql)) {
		  						con.setAutoCommit(false);
		  				UserBean user = new UserBean();
 	 					HttpSession session = request.getSession(true);
		  				//ログインパスワードを取得
		  				String Password = (String) request.getAttribute("password");
		  						System.out.println("PassWord = " + Password);

		  				//selectの為パスワードを暗号化
		  			  	String passkey = "abcdefghijklmnop";
		  			  	String algorithm = "AES";
		  				//入力された値とパスワードを暗号化して取得
		  				try {
		  					SecretKeySpec passsksSpec = new SecretKeySpec(passkey.getBytes(),algorithm);
		  					Cipher passcipher = Cipher.getInstance(algorithm);
		  					passcipher.init(Cipher.ENCRYPT_MODE, passsksSpec);
		  					byte[] encryptedpassword = passcipher.doFinal(Password.getBytes());
		  					// Base64エンコード
		  					String password = Base64.encode(encryptedpassword);
		  					System.out.println("PASSWORD = " +password);
		  				//sqlにセット
		  				ps.setString(1, password);
	  					//ResultSetに保存
	  					ResultSet rs = ps.executeQuery();
	  					//DBに登録されているパスワードであれば表示
	  					if (rs.next()) {
			  		      user.setDestination(rs.getString("destination"));
			  		      user.setZip(rs.getString("zip"));
			  		      user.setState(rs.getString("state"));
			  		      user.setCity(rs.getString("city"));
			  		      user.setStreet(rs.getString("street"));
	  					}
  						System.out.println("getdestination = " + user.getDestination());
	  					//ArrayListに格納
		  				User.add(user);
		  				//セッションスコープにインスタンスを保存
		  				session.setAttribute("USER",User);

		  				con.commit();
		  				ps.close();
		  				con.close();
		  		}catch (Exception e){
		  			System.out.println("SQLException:" + e.getMessage());
		  		}
				} catch (Exception e) {
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
	 //カード設定
	  public static int UserDBcreditinsert (UserBean request) {
		  	String sql = "update user set creditname = ?, creditnum = ?, creditmonth = ?, credityear = ? where password = ?";
		  	int result = -1;
		  		try {
		  			try (Connection con = DBuser.connect()) {
		  				try (PreparedStatement ps = con.prepareStatement(sql)) {
		  						con.setAutoCommit(false);
		  				//入力値を取得
		  				String creditname = request.getCreditName();
		  				String creditnum = request.getCreditNum();
		  				String month = request.getMonth();
		  				String year = request.getYear();
		  				String Password = request.getPassword();

		  				//sqlにセット
		  				ps.setString(1, creditname);
		  				ps.setString(2, creditnum);
		  				ps.setString(3, month);
		  				ps.setString(4, year);
		  				ps.setString(5, Password);

		  				//更新行数を返す
		  				result = ps.executeUpdate();
		  				System.out.println("dbresult" + result);

		  				con.commit();

		  				ps.close();
		  				con.close();
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
	  //レジカード表示
	  public static int UserDBcreditSelect (HttpServletRequest request) {
		  	String sql = "select password, creditname, creditnum, creditmonth, credityear from user where password = ?";
		  	ArrayList<UserBean> UserCredit = new ArrayList<UserBean>();
		  	int result = -1;
		  		try {
		  			try (Connection con = DBuser.connect()) {
		  				try (PreparedStatement ps = con.prepareStatement(sql)) {
		  						con.setAutoCommit(false);
		  				UserBean user = new UserBean();
 	 					HttpSession session = request.getSession(true);
		  				//ログインパスワードを取得
		  				String Password = (String) request.getAttribute("password");
		  						System.out.println("PassWord = " + Password);
		  				//selectの為パスワードを暗号化
		  			  	String passkey = "abcdefghijklmnop";
		  			  	String algorithm = "AES";
		  				//入力された値とパスワードを暗号化して取得
		  				try {
		  					SecretKeySpec passsksSpec = new SecretKeySpec(passkey.getBytes(),algorithm);
		  					Cipher passcipher = Cipher.getInstance(algorithm);
		  					passcipher.init(Cipher.ENCRYPT_MODE, passsksSpec);
		  					byte[] encryptedpassword = passcipher.doFinal(Password.getBytes());
		  					// Base64エンコード
		  					String password = Base64.encode(encryptedpassword);
		  					System.out.println("PASSwORD = " +password);
		  				//sqlにセット
		  				ps.setString(1, password );
	  					//ResultSetに保存
	  					ResultSet rs = ps.executeQuery();
	  					//DBに登録されているパスワードであれば表示
	  					if (rs.next()) {
			  		      user.setCreditName(rs.getString("creditname"));
			  		      user.setCreditNum(rs.getString("creditnum"));
			  		      user.setMonth(rs.getString("creditmonth"));
			  		      user.setYear(rs.getString("credityear"));
	  					}
	  					//ArrayListに格納
		  				UserCredit.add(user);
		  				//セッションスコープにインスタンスを保存
		  				session.setAttribute("USERCREDIT",UserCredit);

		  				con.commit();
		  				ps.close();
		  				con.close();
		  		}catch (Exception e){
		  			System.out.println("SQLException:" + e.getMessage());
		  		}
				} catch (Exception e) {
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
}
