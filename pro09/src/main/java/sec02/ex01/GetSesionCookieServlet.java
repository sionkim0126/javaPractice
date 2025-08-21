package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetSesionCookieServlet
 */
@WebServlet("/getC2")
public class GetSesionCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Cookie[] allValues = request.getCookies();
		
		for(int i = 0; i < allValues.length; i++) {
			if(allValues[i].getName().equals("cookieTest2")) {
				out.print("<h2>Cookie 값 가져오기 : " + URLDecoder.decode(allValues[i].getValue(), "UTF-8"));
				/* 지금 쿠키는 2개 살아 있다 근데 난 지금 이름이 CookieTest2로 만들었기에 배열로는 살아있다ㅋㅋㅋ. */
			}
		}
	}

}
