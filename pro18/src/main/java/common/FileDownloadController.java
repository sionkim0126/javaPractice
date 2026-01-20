package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDownloadController
 */
@WebServlet("/download.do")
public class FileDownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String ARTICLE_IMAGE_REPO = "E:\\Projects\\필요모음\\upload_repo";
	
	
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
		response.setContentType("text/html; chatset=UTF-8");
		
		String imageFileName = (String)request.getParameter("imageFileName");
		String articleNO = request.getParameter("articleNO");
		System.out.println("imageFileName : " + imageFileName);
		
		OutputStream out = response.getOutputStream();
		
		String path = ARTICLE_IMAGE_REPO + "\\" + articleNO + "\\" + imageFileName;
		//글 번호에 대한 파일 경로를 설정합니다.
		
		File imageFile = new File(path);
		
		response.setHeader("Cache-Control", "no-coche");//이 응답(파일)을 캐시에 저장하지 말고 매번 서버에 다시 요청
		/*
		 * 배경: 파일 다운로드와 같은 경우, 브라우저가 예전(오래된) 버전을 캐시에 저장해 두고 서버에 요청하지 않고 캐시된 파일을 보여주면 문제가
		 * 생길 수 있습니다. 효과: 이 코드를 사용하면 브라우저는 항상 최신 이미지 파일을 서버로부터 다운로드하게 됩니다.
		 */
		response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(imageFileName, "UTF-8"));
		/*
		 * 다운로드 지시 : 이 파일은 브라우저 창에 표시하지 말고, 사용자의 로컬 컴퓨터에 지정된 파일 이름으로 저장하도록 한다. 이미지 파일을
		 * 내려 받는데 필요한 response에 해더 정보를 설정합니다. 응답 본문이 브라우저에 표시되는(inline) 것이 아니라, **파일로
		 * 저장되어야 함(attachment)**을 나타냅니다.
		 * 실무에서는 한글 깨짐 문제 때문에 URLEncoder.encode(imageFileName, "UTF-8") 사용!!!!
		 */
		FileInputStream in = new FileInputStream(imageFile);//디스크에 있는 파일 → 자바 프로그램으로 읽어오는 통로
		byte[] buffer = new byte[1024 * 8];
		while(true) {
			//버퍼를 이용해 한번에 8Kb씩 전송합니다.
			int count = in.read(buffer);//파일에서 최대 8KB만큼 읽어서 buffer 배열에 채운다 실제로 읽은 바이트 수를 count에 반환
			if(count == -1) {
				//count == -1 → 파일 끝(EOF) 더 이상 읽을 게 없으면 반복 종료
				break;
			}
			out.write(buffer, 0, count);//버퍼에 담긴 만큼만 전송 : buffer의 0번부터 count 만큼 전송
		}
		in.close();
		out.close();
	}

}
