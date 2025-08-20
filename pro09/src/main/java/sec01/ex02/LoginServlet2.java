package sec01.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet2
 */
@WebServlet("/login2")
public class LoginServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("LoginServlet2의 init메서드 호출");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("LoginServlet2의 destroy메서드 호출");
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
				
				out.print(data);
				
				user_address = URLEncoder.encode(user_address, "UTF-8"); //GET 방식으로 한글을 전송하기 위해 인코딩합니다.
				out.print("<a href='/pro09/second?user_id=" + user_id + "&user_pw=" + user_pw + "&user_address=" + user_address +"'>두번째 서블릿이로 보내기</a>");
				data = "</body></html>";
				out.print(data);
				
						
			}catch (Exception e) {
				e.printStackTrace();
		}
		}else {
			out.print("데이터 전송 오류");
		}
	}

}
