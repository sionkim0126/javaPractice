package sec03.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class JsonServlet3
 */
@WebServlet("/json3")
public class JsonServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		JSONObject totalObject = new JSONObject();
		JSONArray membersArray = new JSONArray();
		
		JSONObject memberInfo = new JSONObject();
		
		memberInfo.put("name", "Lee_jung_ho");
		memberInfo.put("age", "27");
		memberInfo.put("gender", "men");
		memberInfo.put("nicName", "Son of windy");
		membersArray.add(memberInfo);
		
		memberInfo = new JSONObject();
		
		memberInfo.put("name", "Song_sung_mon");
		memberInfo.put("age", "29");
		memberInfo.put("gender", "men");
		memberInfo.put("nicName", "yellow mon");
		membersArray.add(memberInfo);
		
		totalObject.put("members", membersArray);
		
		JSONArray bookArray = new JSONArray();
		
		JSONObject bookInfo = new JSONObject();
		
		bookInfo.put("title", "자바 웹을 다루는 기술");
		bookInfo.put("writer", "이병승");
		bookInfo.put("price", "30,000");
		bookInfo.put("genre", "IT");
		bookInfo.put("image", "http://localhost:8090/pro16/image/book.png");
		bookArray.add(bookInfo);
		
		bookInfo = new JSONObject();
		
		bookInfo.put("title", "점프 투 자바");
		bookInfo.put("writer", "위키독스");
		bookInfo.put("price", "15,000");
		bookInfo.put("genre", "IT");
		bookInfo.put("image", "http://localhost:8090/pro16/image/book.png");
		bookArray.add(bookInfo);
		
		totalObject.put("books", bookArray);
		
		String jsonInfo = totalObject.toJSONString();
		System.out.println(jsonInfo);
		out.print(jsonInfo);
	}
}
