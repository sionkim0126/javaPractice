package sec03.ex04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionTest4
 */
@WebServlet("/session4")
public class SessionTest4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
//		if(session.isNew()) {
//			if(user_id != null && user_id.length() != 0) {
//				session.setAttribute("user_id", user_id);
//				out.print("<a href='session4'>로그인 상태 확인</a>");
//			}else {
//				out.print("<h3><a href='login2.html'>다시 로그인 하세요</a></h3>");
//				session.invalidate();
//			}
//		}else {
//			user_id = (String)session.getAttribute("user_id");
//			if(user_id != null && user_id.length() != 0) {
//				out.print("안녕하세요 " + user_id + "님! <br>");
//			}else {
//				out.print("<h3><a href='login2.html'>다시 로그인 하세요</a></h3>");
//				session.invalidate();
//			}
//		}
		// 🚩 로그인 시도 시 처리
	    if (user_id != null) { // 폼에서 값이 들어온 경우만 체크
	        if (user_id.length() == 0) {  // 빈칸 로그인
	            out.print("<h3><a href='login2.html'>다시 로그인 하세요</a></h3>");
	            session.invalidate();
	            return; // 세션 무효화 후 더 이상 진행 안 함
	        } else {
	            session.setAttribute("user_id", user_id); // 정상 아이디면 세션 저장
	        }
	    }

	    // 🚩 항상 세션에서 값 꺼내기
	    user_id = (String) session.getAttribute("user_id");

	    // 🚩 세션 신규 여부 판단
	    if (session.isNew()) {
	        // 처음 로그인 직후
	        out.print("<a href='session4'>로그인 상태 확인</a>");
	    } else {
	        if (user_id != null && user_id.length() != 0) {
	            // 세션 유지 상태
	            out.print("안녕하세요 " + user_id + "님! <br>");
	            out.print("<a href='login2.html'>로그인창 이동</a>");
	        } else {
	            // 세션은 있는데 아이디 없음 → 강제 로그아웃
	            out.print("<h3><a href='login2.html'>다시 로그인 하세요</a></h3>");
	            session.invalidate();
	        }
	    }
	}
}
