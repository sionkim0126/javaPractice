package sec05.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ShowMember
 */
@WebServlet("/show")
public class ShowMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String id = "";
		String pw = "";
		Boolean isLogon = false;
		
		//이미 세션이 존재하면 세션을 반환하고, 없으면 null을 반환합니다.
		HttpSession session = request.getSession(false);
		if(session != null) {
			isLogon = (Boolean)session.getAttribute("isLogon");
			if(isLogon == true) {
				id = (String)session.getAttribute("logon.id");
				pw = (String)session.getAttribute("logon.pw");
				out.print("<html><body>");
				out.print("아이디 :" + id + "<br>");
				out.print("비 번 :" + pw + "<br>");
				out.print("</body></html>");
			}else {
				response.sendRedirect("login3.html");
			}
		}else {
			response.sendRedirect("login3.html");
		}
		
	}

}
