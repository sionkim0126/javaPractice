package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SetSessionCookieServlet
 */
@WebServlet("/setC2")
public class SetSessionCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Date d = new Date();
		Cookie c = new Cookie("cookieTest2", URLEncoder.encode(d.toString(), "UTF-8"));
		
		c.setMaxAge(-1); //유효시간 설정 - 음수로 설정시 SessionCookie생성 브라우저 종료시 쿠키 자동 소멸
		
		response.addCookie(c); //생성한 쿠키를 브라우져로 전송
		
		out.print("현재시간 : " + d + "<br>");
		out.print("현재시간을 쿠키로 저장합니다.");
	}

}
