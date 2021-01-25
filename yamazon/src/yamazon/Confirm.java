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
@WebServlet("/Confirm")
//HttpServletを継承して、HelloWorldクラスを定義
public class Confirm extends HttpServlet {
private static final long serialVersionUID = 1L;
private static final String JSP_BASE = "/WEB-INF/jsp/";

//doGet()メソッドをオーバーライドして定義
public void doGet(HttpServletRequest request, HttpServletResponse response)
throws IOException, ServletException
{
		//HttpSessionインスタンスの取得
		HttpSession session = request.getSession(false);

		//在庫数を引くメソッドを呼び出す
		DBitem.ItemDBminus(request);

		//セッションスコープからインスタンスを削除
		session.removeAttribute("ITEM");
		session.removeAttribute("USER");
		session.removeAttribute("USERCREDIT");
				//注文完了画面に遷移
				String forward = null;
				forward = JSP_BASE+"confirm.jsp";
				// getRequestDispatcherの引数に forwardedを指定して、サーブレット処理をフォワードする。
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
				dispatcher.forward(request, response);
			}
	}