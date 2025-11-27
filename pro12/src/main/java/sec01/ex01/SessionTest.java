package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionTest1
 */
@WebServlet("/sess")
public class SessionTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter pw = response.getWriter();
		
		HttpSession session = request.getSession();
		
		session.setAttribute("name", "KSO");
		
		pw.println("<html><body>");
		pw.print("<h1>세션에 이름을 바인딩 합니다.</h1>");
		pw.print("<a href='/pro12/test01/session01.jsp'>첫 번째 페이지로 이동하기</a>");
		pw.print("</body></html>");
	}

}