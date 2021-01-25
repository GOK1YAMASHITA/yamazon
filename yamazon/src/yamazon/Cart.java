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
@WebServlet("/Cart")
//HttpServletを継承して、クラスを定義
public class Cart extends HttpServlet {
private static final long serialVersionUID = 1L;
private static final String JSP_BASE = "/WEB-INF/jsp/";

//doGet()メソッドをオーバーライドして定義
public void doGet(HttpServletRequest request, HttpServletResponse response)
throws IOException, ServletException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//商品詳細ページからItemIDと数量を取得
		String ItemID = request.getParameter("itemid");
		String SELECTCNT = request.getParameter("selectcnt");
		 	System.out.println("ITEMID = " + ItemID);
		 	System.out.println("SELECTCNT = " + SELECTCNT);
		request.setAttribute("itemid", ItemID);
		request.setAttribute("selectcnt", SELECTCNT );
		//他のページから直接カートに接続した場合DB処理を行わない
		if (ItemID != null) {
		// ItemDBcartメソッドを使って、ItemIDをもとに表示する商品を選定
		 DBitem.ItemDBcart(request);
		 }
	//forward初期化
	String forward = null;
	forward = JSP_BASE+"cart.jsp";
	// getRequestDispatcherの引数に forwardを指定して、サーブレット処理をフォワードする(カート画面へ)。
	RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
	dispatcher.forward(request, response);
	}
// doPost()メソッドをオーバーライドして定義
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//現在のページを取得
		String target = request.getRequestURI();
		HttpSession session = request.getSession(false);
		//ログインしていなければログインページへ遷移
		if (session == null) {
			session = request.getSession(true);
			session.setAttribute("target", target);
			response.sendRedirect("/yamazon/Login");
			return;
		}else {
			Object logincheck = session.getAttribute("login");
			if (logincheck == null) {
				session.setAttribute("target", target);
				response.sendRedirect("/yamazon/Login");
				}else {
						//UserBean user = new UserBean();
						//セッションよりログインパスワードを取得
						String password = (String) session.getAttribute("password");
						System.out.println("PASSWORD = " + password);
						//UserBeanに値を渡す
						request.setAttribute("password", password);
					 	// UserDBaddressSelectメソッドを使って、パスワードをもとに表示する住所を選定
					 	DBuser.UserDBaddressSelect(request);
					 	//UserDBcreditSelectメソッドを使って、パスワードをもとに表示するクレジット情報を選定
					 	DBuser.UserDBcreditSelect(request);
						//forwardをnullで初期化
						String forward = null;
						forward = JSP_BASE+"register.jsp";
						// getRequestDispatcherの引数に forwardedを指定して、サーブレット処理をフォワードする。
						RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
						dispatcher.forward(request, response);
				}
			}
		}
	}