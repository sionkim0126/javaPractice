package sec04.ex03;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewServlet
 */
@SuppressWarnings("unchecked")
@WebServlet("/view")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		List<MemberDTO> memberList = (List<MemberDTO>) request.getAttribute("memberList");
		
		out.print("<html><body>");
		out.print("<table border=1><tr align='center' bgcolor='lightgreen'>");
		out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일자</td><td>삭제</td></tr>");
		
		for(int i = 0; i < memberList.size(); i++) {
			MemberDTO dto = (MemberDTO)memberList.get(i);
			String id = dto.getId();
			String pwd = dto.getPwd();
			String name = dto.getName();
			String email = dto.getEmail();
			Date joinDate = dto.getJoinDate();
			
			out.print("<tr><td>" + id + "</td><td>" + pwd + "</td><td>" + name + "</td><td>" + email + "</td><td>" + joinDate + "</td><td>"
			+ "<a href='/pro08/member?command=delMember&id=" + id + "'>삭제</a></td></tr>");
		}
		out.print("</table>");
		out.print("<a href='/pro08/memberFrm.html'>새 회원 등록하기</a></body></html>");
	}

}
