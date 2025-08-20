package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("LoginServlet의 destroy메서드 호출");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String user_address = request.getParameter("user_address");
		String user_email = request.getParameter("user_email");
		String user_hp = request.getParameter("user_hp");
		
		if (user_id != null) {			
			try {			
				String data = "안녕하세요 !<br> 로그인 하셨습니다.<br><br>";
				data += "<html><body>";
				data += "아이디 : " + user_id + "<br>";
				data += "비 번 : " + user_pw + "<br>";
				data += "주 소 : " + user_address + "<br>";
				data += "이메일 : " + user_email + "<br>";
				data += "번 호 : " + user_hp + "<br>";
				data += "</body></html>";
				
				out.print(data);
			}catch (Exception e) {
				e.printStackTrace();
		}
		}else {
			out.print("데이터 전송 오류");
		}
	}

}
