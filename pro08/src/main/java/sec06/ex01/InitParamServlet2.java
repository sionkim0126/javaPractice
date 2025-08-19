package sec06.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InitParamServlet2
 */
/* @WebServlet("/InitParamServlet2") */
public class InitParamServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String email = getInitParameter("email2");
		String tel = getInitParameter("tel2");
		
		out.print("<html><body>");
		out.print("<table><tr>");
		out.print("<td> email : </td><td>" + email +"</td></tr>");
		out.print("<tr><td> 전화번호 : </td><td>" + tel +"</td></tr>");
		out.print("</table></body></html>");
	}

}
