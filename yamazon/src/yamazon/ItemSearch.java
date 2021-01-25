package yamazon;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//アノテーション宣言
@WebServlet("/ItemSearch")
public class ItemSearch extends HttpServlet {
private static final long serialVersionUID = 1L;
private static final String JSP_BASE = "/WEB-INF/jsp/";

//doGet()メソッドをオーバーライドして定義
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
	  response.setContentType("text/html; charset=UTF-8");
	  	// DB.userDBsearchメッソドを使って、ユーザ検索を行い、その結果をdbresultに返す
	  	int dbresult = DBitem.ItemDBsearch(request);
  			System.out.println("dbresult = " + dbresult);

  			String forward = null;

  			forward = JSP_BASE+"ItemSearch.jsp";
  		// dbresultをJSP側に渡す
  			 request.setAttribute("ItemSearch", dbresult);

  		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
  		dispatcher.forward(request, response);
		}
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
	  response.setContentType("text/html; charset=UTF-8");
	  	// ItemSearch.jspからItemIDを取得
		String ItemID = request.getParameter("itemid");
		  System.out.println("itemID = " + ItemID);
		  	request.setAttribute("itemid", ItemID);
		  	// ItemDBdetailメソッドを使って、ItemIDをもとに表示する商品を選定
		  	DBitem.ItemDBdetail(request);

			String forward = null;

			forward = JSP_BASE+"itemDetail.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		dispatcher.forward(request, response);
}
}
