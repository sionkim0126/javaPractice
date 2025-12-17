package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ajaxTest3")
public class AjaxTest3Json extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");

		PrintWriter out = response.getWriter();

		String json = "{"
				+ "\"books\": ["
				+ "  {"
				+ "    \"title\": \"초보자를 위한 자바 프로그래밍\","
				+ "    \"writer\": \"인포북스 저 | 이병승\","
				+ "    \"image\": \"http://localhost:8090/pro16/image/재즈클럽.png\""
				+ "  },"
				+ "  {"
				+ "    \"title\": \"모두의 파이썬\","
				+ "    \"writer\": \"길벗 저 | 이승찬\","
				+ "    \"image\": \"http://localhost:8090/pro16/image/yellowHi.png\""
				+ "  }"
				+ "]"
				+ "}";

		out.print(json);
	}
}
