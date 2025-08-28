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
 * Servlet implementation class SessionServlet
 */
@WebServlet("/session6")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(user_id);
		memberDTO.setPw(user_pw);
		
		MemberDAO memberDAO = new MemberDAO();
		boolean result = memberDAO.isExisted(memberDTO);
		
		if(result) {
			HttpSession session = request.getSession();
			session.setAttribute("isLogon", true);
			session.setAttribute("logon.id", user_id);
			session.setAttribute("logon.pw", user_pw);
			out.print("<html><body");
			out.print("안녕하세요 " + user_id + "님!<br>");
			out.print("<a href='show'>회원정보 보기</a>");
			out.print("</body></html>");
			}else {
				out.print("<html><body>");
				out.print("<center>회원 아이디가 틀립니다.");
				out.print("<a href='login3.html'>다시 로그인하기</a>");
				out.print("</body></html>");
			}
	}

}
