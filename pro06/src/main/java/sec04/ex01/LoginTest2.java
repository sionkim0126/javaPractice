package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginTest2
 */
@WebServlet("/LoginTest2")
public class LoginTest2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("LoginTestServlet2 init 메서드 호출");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("LoginTestServlet destroy2 메서드 호출");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		
		System.out.println("LginTest2 Post 방식 요청");
		System.out.println("ID : " + id);
		System.out.println("PW : " + pw);
		
		if(id != null &&(id.length() != 0)) {
			if(id.equals("admin")) {
				out.print("<html>");
				out.print("<body>");
				out.print("<font size= '12'>관리자로 로그인 하셨습니다.</font><br>");
				out.print("<input type='button' value='회원정보 수정하기' />");
				out.print("<input type='button' value='회원정보 삭제하기' />");
				out.print("</body>");
				out.print("</html>");
			}else {
				out.print("<html>");
				out.print("<body>");
				out.print("<font size= '5'>"+ id +"님 로그인 하셨습니다.</font><br>");
				out.print("</body>");
				out.print("</html>");
			}
		}else {
			out.print("<html>");
			out.print("<body>");
			out.print("<font size= '5' color='red'>ID와 PW를 입력해주세요.</font><br>");
			out.print("<a href='http://localhost:8090/pro06/test01/login.html'>로그인 창으로 이동 </a>");
			out.print("</body>");
			out.print("</html>");
		}
	}

}
