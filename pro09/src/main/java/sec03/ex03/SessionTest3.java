package sec03.ex03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionTest3
 */
@WebServlet("/session3")
public class SessionTest3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		
		out.print("세션 아이디 : " + session.getId() + "<br>");
		out.print("최초 세션 생성 시간 : " + new Date(session.getCreationTime()) + "<br>");
		out.print("최근 세션 접근 시간 : " + new Date(session.getLastAccessedTime()) + "<br>");
		out.print("세션 기본 유효 시간 : " + session.getMaxInactiveInterval() + "초<br>");
		session.setMaxInactiveInterval(3600);
		out.print("재설정한 세션 유효 시간 : " + ((Integer)session.getMaxInactiveInterval() / 60 ) +"분<br>"); //이건 그냥 내가 형변환 한번 해봄
		if(session.isNew()) {
			out.print("새 세션이 새로 만들어졌습니다.");
		}
		session.invalidate(); // invalidate 메서드 호출하여 세션 강제 삭제
	}
}
