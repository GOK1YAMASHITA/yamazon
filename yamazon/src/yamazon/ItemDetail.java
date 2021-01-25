package yamazon;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//アノテーション宣言
@WebServlet("/ItemDetail")
public class ItemDetail extends HttpServlet {
private static final long serialVersionUID = 1L;
private static final String JSP_BASE = "/WEB-INF/jsp/";

//doPost()メソッドをオーバーライドして定義
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
	  response.setContentType("text/html; charset=UTF-8");

	  String itemID = (String) request.getAttribute("itemid");
	  System.out.println("image = " + itemID);
	  	//
	  	int dbresult = DBitem.ItemDBsearch(request);

  			String forward = null;

  			forward = JSP_BASE+"auth.jsp";
  		// dbresultをJSP側に渡す
  			 request.setAttribute("ItemDetail", dbresult);

  		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
  		dispatcher.forward(request, response);
		}
}
