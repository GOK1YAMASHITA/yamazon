package yamazon;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//アノテーション宣言
@WebServlet("/Top")
//HttpServletを継承して、HelloWorldクラスを定義
public class Top extends HttpServlet {
private static final long serialVersionUID = 1L;
private static final String JSP_BASE = "/WEB-INF/jsp/";

//doGet()メソッドをオーバーライドして定義
public void doGet(HttpServletRequest request, HttpServletResponse response)
throws IOException, ServletException
{
String forward = null;

forward = JSP_BASE+"Get_Top.jsp";

// getRequestDispatcherの引数に forwardedを指定して、サーブレット処理をフォワードする。
RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
dispatcher.forward(request, response);
}
}