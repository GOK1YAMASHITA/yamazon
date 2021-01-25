package yamazon;

import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xml.internal.security.utils.Base64;

//アノテーション宣言
@WebServlet("/AddressRegist")
//HttpServletを継承して、HelloWorldクラスを定義
public class AddressRegist extends HttpServlet {
private static final long serialVersionUID = 1L;
private static final String JSP_BASE = "/WEB-INF/jsp/";

//doGet()メソッドをオーバーライドして定義
public void doGet(HttpServletRequest request, HttpServletResponse response)
throws IOException, ServletException
{
	String forward = null;

	forward = JSP_BASE+"addressregist.jsp";

	// getRequestDispatcherの引数に forwardedを指定して、サーブレット処理をフォワードする。
	RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
	dispatcher.forward(request, response);
}
// doPost()メソッドをオーバーライドして定義
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	  	//暗号化の秘密鍵、暗号化アルゴリズムを設定
	  	String passkey = "abcdefghijklmnop";
	  	String algorithm = "AES";
	  	//forwardをnullで初期化
		String forward = null;
		//パスワードを取得
		HttpSession session = request.getSession(true);
		String password = (String) session.getAttribute("password");
			System.out.println("password = " + password);
		//入力値を取得
		String destination = request.getParameter("destination");
		String zip = request.getParameter("zip");
		String state = request.getParameter("state");
		String city = request.getParameter("city");
		String street = request.getParameter("street");
			System.out.println("destination = " + destination);
			System.out.println("state = " + state);
		//入力されたパスワードを暗号化して取得
		try {
			SecretKeySpec passsksSpec = new SecretKeySpec(passkey.getBytes(),algorithm);
			Cipher passcipher = Cipher.getInstance(algorithm);
			passcipher.init(Cipher.ENCRYPT_MODE, passsksSpec);
			byte[] encryptedpassword = passcipher.doFinal(password.getBytes());
			// Base64エンコード
		String Password = Base64.encode(encryptedpassword);
		System.out.println("PASSWORD = " +Password);
		  			//入力欄が空文字かどうかチェックを行う。
		  			if (destination.isEmpty() == true) {
		  				forward = JSP_BASE+"address_regist_error.jsp";
		  				request.setAttribute("address_regist_error", 1);
		  			}else if (zip.isEmpty() == true) {
		  				forward = JSP_BASE+"address_regist_error.jsp";
		  				request.setAttribute("address_regist_error", 2);
		  			}else if (state.isEmpty() == true) {
		  				forward = JSP_BASE+"address_regist_error.jsp";
		  				request.setAttribute("address_regist_error", 3);
		  			}else if (city.isEmpty() == true) {
	  				forward = JSP_BASE+"address_regist_error.jsp";
	  				request.setAttribute("address_regist_error", 4);
		  			}else if (street.isEmpty() == true) {
	  				forward = JSP_BASE+"address_regist_error.jsp";
	  				request.setAttribute("address_regist_error", 5);
	  				}else {
						//UserBeanクラスの中に入力値を保持させ、それをDBuser.UserDBaddressinsertに渡す
						UserBean user = new UserBean();
						user.setDestination(destination);
						user.setZip(zip);
						user.setState(state);
						user.setCity(city);
						user.setStreet(street);
						user.setPassword(Password);
	  		// DBuser.UserDBinsertメッソドを使って、ユーザ登録を行い、その結果をdbresultに渡す
	  		int dbresult = DBuser.UserDBaddressinsert(user);
	  		forward = JSP_BASE+"address_regist_result.jsp";
	  		//dbresultをJSP側に渡す
	  		request.setAttribute("address_regist_result", dbresult);
	  			System.out.println("dbresult = " + dbresult);
	  	}
		}catch(Exception e){
				System.out.println("SQLException:" + e.getMessage());
		}
	  		// getRequestDispatcherの引数に forwardを指定して、サーブレット処理をフォワードする。
	  		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
	  		dispatcher.forward(request, response);
		}
}