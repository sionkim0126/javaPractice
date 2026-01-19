package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.MemberDTO;
import service.MemberService;
/**
 * Servlet implementation class MemberController
 */
@WebServlet("/Member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/* HttpSession session; */
	//서블릿은 싱글톤이다.필드로 두면 동시 요청 시 세션 꼬인다. 반드시 메서드 내부에서만 사용해라.
	MemberService memberService;

    public MemberController() {
        memberService = new MemberService();
        		
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

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
		
		String nextPage = null;
		String action = request.getPathInfo();
		System.out.println("action :" + action);
		
		try {
			if(action.equals("/loginForm.do")) {
				nextPage = "/member/loginForm.jsp";
				
			}else if(action.equals("/memberForm.do")) {
				nextPage = "/member/memberForm.jsp";
				
			}else if(action.equals("/addMember.do")) {
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				
				MemberDTO member = new MemberDTO(id, pwd, name, email);
				
				memberService.addMember(member);
				
				request.setAttribute("msg", "addMember");
				//월래는 listMembers.jsp로 넘어가서 메세지 전송 이걸 listArticles.do로 이동
				nextPage = "/member/login.jsp";
				
			}else if(action.equals("/login.do")) {
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				
				MemberDTO member = memberService.login(id, pwd);
				
				if(member != null) {
					HttpSession session = request.getSession();
					session.setAttribute("loginMember", member);
					nextPage = "/Board/listArticles.do";
				}else {
					request.setAttribute("loginError", "아이디 또는 비밀번호가 틀립니다.");
					nextPage = "/member/loginForm.jsp";
				}
				
			}
			
			RequestDispatcher disPath = request.getRequestDispatcher(nextPage);
			disPath.forward(request, response);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
