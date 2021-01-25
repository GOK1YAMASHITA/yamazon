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

import com.sun.org.apache.xml.internal.security.utils.Base64;

//アノテーション宣言
@WebServlet("/Resist")
//HttpServletを継承して、HelloWorldクラスを定義
public class Resist extends HttpServlet {
private static final long serialVersionUID = 1L;
private static final String JSP_BASE = "/WEB-INF/jsp/";

//doGet()メソッドをオーバーライドして定義
public void doGet(HttpServletRequest request, HttpServletResponse response)
throws IOException, ServletException
{
	String forward = null;

	forward = JSP_BASE+"regist.jsp";

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
	  	String key = "abcdefghijklmnop";
	  	String algorithm = "AES";
	  	//forwardをnullで初期化
		String forward = null;
	  	//入力項目を取得
	  	String Name = request.getParameter("text1");
	  	String mailaddress = request.getParameter("text2");
	  	String tel = request.getParameter("text3");
		//パスワードを暗号化して取得
		try {
			SecretKeySpec sksSpec = new SecretKeySpec(key.getBytes(), algorithm);
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.ENCRYPT_MODE, sksSpec);
			byte[] encrypted = cipher.doFinal(request.getParameter("text4").getBytes());
			// Base64エンコード
		String password = Base64.encode(encrypted);
		String EmptyPassword = "jmTOhz8XTbskI/zYFFgOFQ==";
				System.out.println(password);
		  			//入力欄が空文字かどうかチェックを行う。空文字の場合trueが返却される。
		  			if (Name.isEmpty() == true) {
		  				forward = JSP_BASE+"regist_error.jsp";
		  				request.setAttribute("regist_error", 1);
		  			}else if (mailaddress.isEmpty() == true) {
		  				forward = JSP_BASE+"regist_error.jsp";
		  				request.setAttribute("regist_error", 2);
		  			}else if (tel.isEmpty() == true) {
		  				forward = JSP_BASE+"regist_error.jsp";
		  				request.setAttribute("regist_error", 3);
		  			}else if (password.equals(EmptyPassword)) {
	  				forward = JSP_BASE+"regist_error.jsp";
	  				request.setAttribute("regist_error", 4);
	  				}else {
						//UserBeanクラスの中に名前、メールアドレス、電話番号、パスワードを保持させ、それをDBuser.UserDBinsertに渡す
						UserBean user = new UserBean();
						user.setName(Name);
						user.setMailaddress(mailaddress);
						user.setTel(tel);
						user.setPassword(password);
	  		// DBuser.UserDBinsertメッソドを使って、ユーザ登録を行い、その結果をdbresultに渡す
	  		int dbresult = DBuser.UserDBinsert(user);
	  		forward = JSP_BASE+"regist_result.jsp";
	  		//dbresultをJSP側に渡す
	  		request.setAttribute("regist_result", dbresult);
	  			System.out.println("dbUserResult = " + dbresult);
	  	}
		}catch(Exception e){
				System.out.println("SQLException:" + e.getMessage());
		}
	  		// getRequestDispatcherの引数に forwardを指定して、サーブレット処理をフォワードする。
	  		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
	  		dispatcher.forward(request, response);
		}
}