package sec01.ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecondServlet extends HttpServlet{
	//매핑을 하지 않고 요청 : http://IP주소:포트주소/프로젝트이름/패키지이름이 포함된 클래스이름 ex) //127.0.0.1:8090/pro05/sec01.ex01.FirstServlet

	@Override
	public void init() throws ServletException{
		System.out.println("init 메서드 호출 >>>>");
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy 메서드 호출 >>>>");
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.out.println("doGet 메서드 호출 >>>>");
	}

}