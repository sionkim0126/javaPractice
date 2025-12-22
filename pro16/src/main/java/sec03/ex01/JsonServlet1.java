package sec03.ex01;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Servlet implementation class JsonServlet1
 */
@WebServlet("/json")
public class JsonServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
	
	// 실제 공통 처리 메서드
		private void doHandle(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			// 한글 깨짐 방지 (요청 데이터 인코딩)
			request.setCharacterEncoding("UTF-8");

			// 응답 데이터 타입 설정
			response.setContentType("text/html; charset=UTF-8");

			// Ajax에서 전달한 JSON 문자열 받기
			// data : { jsonInfo : _jsonInfo }
			String jsonInfo = request.getParameter("jsonInfo");

			try {
				// JSON 문자열을 파싱하기 위한 객체 생성
				JSONParser jsonParser = new JSONParser();

				// JSON 문자열 → JSONObject 변환
				JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonInfo);

				// 파싱된 데이터 확인 (서버 콘솔 출력)
				System.out.println("회원정보");
				System.out.println("이름 : " + jsonObject.get("name"));
				System.out.println("나이 : " + jsonObject.get("age"));
				System.out.println("성별 : " + jsonObject.get("gender"));
				System.out.println("닉네임 : " + jsonObject.get("nicName"));

			} catch (Exception e) {
				// JSON 파싱 오류 발생 시
				e.printStackTrace();
			}
		}
	}