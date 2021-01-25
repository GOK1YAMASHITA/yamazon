package yamazon;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//アノテーション宣言
@WebServlet("/Login")
//HttpServletを継承して、クラスを定義
public class Login extends HttpServlet {
private static final long serialVersionUID = 1L;
private static final String JSP_BASE = "/WEB-INF/jsp/";

//doGet()メソッドをオーバーライドして定義
public void doGet(HttpServletRequest request, HttpServletResponse response)
throws IOException, ServletException
{
	String forward = null;

	forward = JSP_BASE+"login.jsp";

	HttpSession session = request.getSession(true);

	Object status = session.getAttribute("status");
	if(status != null) {
		session.setAttribute("status", "null");
		return;
	}
	// getRequestDispatcherの引数に forwardを指定して、サーブレット処理をフォワードする。
	RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
	dispatcher.forward(request, response);
	return;
}
// doPost()メソッドをオーバーライドして定義
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//forwardをnullで初期化
		String forward = null;
	  	//入力項目を取得
	  	String mailaddress = request.getParameter("text1");
	  	String password = request.getParameter("text2");
			//UserBeanクラスの中に名前、メールアドレス、電話番号、パスワードを保持させ、それをDBuser.UserDBinsertに渡す
			UserBean user = new UserBean();
			user.setMailaddress(mailaddress);
			user.setPassword(password);
				// DBuser.DBloginメッソドを使って、ユーザ検索を行い、その結果をdbresultに返す
				int dbresult = DBuser.UserDBlogin(user);
					//ログイン認証が失敗した場合、login_result.jspのログイン失敗画面を表示させる。
					if(dbresult == -1) {
						System.out.println("dbresult = " + dbresult);
						forward = JSP_BASE+"login_result.jsp";
						//dbresultをJSP側に渡す
						request.setAttribute("login_result", dbresult);
						// getRequestDispatcherの引数に forwardedを指定して、サーブレット処理をフォワードする。
						RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
						dispatcher.forward(request, response);
					//login認証が成功した場合の処理
					}else {
						//Session情報を取得
						HttpSession session = request.getSession(true);
						//Session情報のtarget情報を取得
						String target = (String)session.getAttribute("target");
							if(target == null) {
								target = request.getRequestURI();
							}
							//targetがアカウント情報の場合(アカウント情報画面から遷移した場合) auth.jspを表示させる
							if(target.equals("/yamazon/Auth")) {
								forward = JSP_BASE+"auth.jsp";
								session.setAttribute("login", "OK");
								session.setAttribute("password", user.getPassword());
								RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
								dispatcher.forward(request, response);
							//targetがログインの場合
							}else if(target.equals("/yamazon/Login")) {
								forward = JSP_BASE+"Get_Top.jsp";
								session.setAttribute("login", "OK");
								session.setAttribute("password", user.getPassword());
								RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
								dispatcher.forward(request, response);
							//targetがカートの場合
							}else if(target.equals("/yamazon/Cart")) {
								forward = JSP_BASE+"register.jsp";
								session.setAttribute("login", "OK");
								session.setAttribute("password", user.getPassword());
								RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
								dispatcher.forward(request, response);
							//targetが住所設定の場合
							}else if(target.equals("/yamazon/Address")) {
								forward = JSP_BASE+"address.jsp";
								session.setAttribute("login", "OK");
								session.setAttribute("password", user.getPassword());
								RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
								dispatcher.forward(request, response);
							//targetが支払い方法設定の場合
							}else if(target.equals("/yamazon/Payment")) {
								forward = JSP_BASE+"payment.jsp";
								session.setAttribute("login", "OK");
								RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
								dispatcher.forward(request, response);
							}
					}
			}
	}