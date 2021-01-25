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
@WebServlet("/Address")
//HttpServletを継承して、HelloWorldクラスを定義
public class Address extends HttpServlet {
private static final long serialVersionUID = 1L;
private static final String JSP_BASE = "/WEB-INF/jsp/";

//doGet()メソッドをオーバーライドして定義
public void doGet(HttpServletRequest request, HttpServletResponse response)
throws IOException, ServletException
{
	String forward = null;
	String target = request.getRequestURI();

	HttpSession session = request.getSession(false);

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
				forward = JSP_BASE+"address.jsp";
				// getRequestDispatcherの引数に forwardedを指定して、サーブレット処理をフォワードする。
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
				dispatcher.forward(request, response);
			}
		}
	}
}