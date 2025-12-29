package sec02.ex02;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberController3
 */
@WebServlet("/member3/*")
public class MemberController3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO memberDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController3() {
    	
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		memberDAO = new MemberDAO();
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
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String nextPage = null;
		String action = request.getPathInfo();
		System.out.println("action : " + action);
		
		if(action == null || action.equals("/listMembers.do")) {
			List<MemberDTO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test03/listMembers.jsp";
			
		}else if(action.equals("/addMember.do")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberDTO memberDTO = new MemberDTO(id, pwd, name, email);
			memberDAO.addMember(memberDTO);
			request.setAttribute("msg", "addMember");
			nextPage = "member3/listMembers.do";
			
		}else if(action.equals("/modMemberForm.do")) {
			String id = request.getParameter("id");
			MemberDTO memInfo = memberDAO.findMember(id);
			request.setAttribute("memInfo", memInfo);
			nextPage = "/test03/modMemberForm.jsp";
			
		}else if(action.equals("/modMember.do")) {
			String id = request.getParameter("id");
			String pwd =request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			MemberDTO memberDTO = new MemberDTO(id, pwd, name, email);
			
			memberDAO.modMember(memberDTO);
			request.setAttribute("msg", "modified");
			nextPage = "member3/listMembers.do";
			
		}else if(action.equals("/delMember.do")) {
			String id = request.getParameter("id");
			memberDAO.delMember(id);
			request.setAttribute("msg", "deleted");
			nextPage = "member3/listMembers.do";
			
		}else {
			List<MemberDTO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test03/listMembers.jsp";
			
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}
