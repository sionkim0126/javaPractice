package sec01.ex02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 파일 다운로드를 처리하는 서블릿
 * /download.do 요청이 오면 이 클래스가 실행된다
 */
@WebServlet("/download.do")
public class fileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// GET 방식 요청 처리
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response); // 실제 로직을 공통 메서드로 위임
	}

	// POST 방식 요청 처리
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response); // GET, POST 동일한 처리
	}

	/*
	 * 실제 파일 다운로드 처리 메서드
	 */
	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 요청 파라미터 한글 처리
		request.setCharacterEncoding("UTF-8");

		// 다운로드할 파일들이 저장된 서버의 실제 폴더 경로
		String file_repo = "C:\\file_repo";

		// JSP에서 전달된 파일 이름 파라미터 받기
		String fileName = request.getParameter("fileName");

		// 콘솔 확인용 (파일명 제대로 넘어오는지)
		System.out.println("fileName : " + fileName);

		// 다운로드할 실제 파일 객체 생성
		File file = new File(file_repo, fileName);

		/*
		 * 응답 타입 설정
		 * 브라우저에게 "이건 파일 데이터다"라고 알려줌
		 */
		response.setContentType("application/octet-stream");

		// 캐시 방지 (항상 새로 다운로드하도록)
		response.setHeader("Cache-Control", "no-cache");

		/*
		 * 한글 파일명 깨짐 방지를 위한 인코딩 처리
		 * 브라우저가 파일명을 정상 인식하도록 변환
		 */
		String encodedFileName =
				new String(fileName.getBytes("UTF-8"), "ISO-8859-1");

		/*
		 * Content-Disposition
		 * attachment → 브라우저에게 "다운로드 해라"
		 */
		response.setHeader(
				"Content-Disposition",
				"attachment; filename=" + encodedFileName
		);

		// 파일을 읽기 위한 입력 스트림
		FileInputStream in = new FileInputStream(file);

		// 브라우저로 데이터를 보내기 위한 출력 스트림
		OutputStream out = response.getOutputStream();

		// 파일 데이터를 담을 임시 버퍼
		byte[] buffer = new byte[1024 * 8];

		/*
		 * 파일 내용을 읽어서
		 * → 버퍼에 담고
		 * → 브라우저로 그대로 전송
		 */
		while (true) {
			int count = in.read(buffer); // 파일에서 데이터 읽기
			if (count == -1) {           // 더 이상 읽을 데이터가 없으면 종료
				break;
			}
			out.write(buffer, 0, count); // 읽은 만큼 브라우저로 출력
		}

		// 자원 정리
		in.close();
		out.close();
	}
}
