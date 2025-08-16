package sec05.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SetServletContext
 */
@WebServlet("/cset")
public class SetServletContext extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ServletContext context = getServletContext(); //servletContext 객체를 가져옵니다.
		
		List<Object>member = new ArrayList<>(); // 리스트 생성
		
		member.add("이순신");
		member.add(30);
		
		context.setAttribute("member", member); // ServletContext 객체에 데이터를 바인딩
		
		out.print("<html><body>");
		out.print("이순신과 30 설정");
		out.print("</body></html>");
		
	}

}
