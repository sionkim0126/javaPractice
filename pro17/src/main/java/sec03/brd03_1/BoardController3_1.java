package sec03.brd03_1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

/**
 * Servlet implementation class BoardController2_1
 */
@WebServlet("/board3_1/*")
public class BoardController3_1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BoardService boardService;
	/* ArticleDTO articleDTO; */ //DTO는 내용을 담는 그릇이기 때문에 매번 새롭게 생성해서 사용해야 함
	BoardDAO boardDAO;//DTO 싱글톤은 아닌 거 같은데 일단 DAO로 함 → 싱글톤이 맞음 서블릿은 싱글톤으로 사용자의 요청을 처리
	//글에 첨부한 이미지 저장위치는 상수로 선언
	private static String ARTICLE_IMAGE_REPO = "E:\\Projects\\필요모음\\upload_repo";
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		boardService = new BoardService();
		boardDAO = new BoardDAO();
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
	
	public void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String nextPage = null;
		String action = request.getPathInfo();
		System.out.println("action :" + action);
		
		try {
			List<ArticleDTO>articlesList = new ArrayList<ArticleDTO>();
			if(action == null || action.equals("/listArticles.do")) {
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board03_1/listArticles.jsp";
				
			}else if(action.equals("/articleForm.do")) {
				nextPage = "/board03_1/articleForm.jsp";
				
			}else if(action.equals("/addArticle.do")) {
				Map<String, String> articleMap = upload(request, response);
				/*웹 브라우저에서 전송된 multipart/form-data 요청을 처리하고,
				 *일반 텍스트 데이터(제목, 내용)와 파일 데이터(파일 이름)를 Map 형태로 받아옵니다.*/
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");//만약 첨부파일이 없음 null?
				
				ArticleDTO dto = new ArticleDTO();
				dto.setParentNO(0);
				dto.setId("test");
				dto.setTitle(title);
				dto.setContent(content);
				dto.setImageFileName(imageFileName);
								
				/* boardService.addArticle(dto); */
				//기존은 단순 파일명 업로드 그래서 articleNO 필요 없었음.
				int articleNO = boardService.addArticle(dto);
				
				if(imageFileName != null && imageFileName.length() != 0) {
					//파일을 첨부한 경우에만 실행
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" +"temp" + "\\" + imageFileName);
					//temp 폴더에 임시로 업로드 된 파일 객체를 생성 : 왜 하는거지?
					File desDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
					//글 번호를 이름으로 하는 폴더 생성
					desDir.mkdirs();//디렉토리 생성 함수
					FileUtils.moveFileToDirectory(srcFile, desDir, true);//temp폴더의 파일을 글 번호를 이름으로 하는 폴더로 이동					
				}
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + " alert('새글을 추가했습니다.');" 
						+ "location.href='" 
						+ request.getContextPath()
						+ "/board3_1/listArticles.do';" + "</script>");
				/* nextPage = "/board3_1/listArticles.do"; */
				return;//why 그냥 return? 그냥 alert만 진행하고 nextPage로 하면 안돼나?
				
				
			}
			RequestDispatcher dispath = request.getRequestDispatcher(nextPage);
			dispath.forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException{
		Map<String, String> articleMap = new HashMap<>();
		
		String encoding = "UTF-8";
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		
		DiskFileItemFactory factory = new DiskFileItemFactory();//파일 업로드 요청을 처리하는 설정 객체입니다. 파일 처리 방식을 설정합니다.
		factory.setRepository(currentDirPath);//임시 저장소 설정: 파일이 너무 커서 메모리에 한 번에 올리기 어려울 때, 파일을 임시로 저장할 디스크 경로를 지정합니다.
		factory.setSizeThreshold(1024 * 1024);
		//임계 크기 설정: 1MB (1024 * 1024)를 기준으로, 이 크기보다 작은 파일은 메모리에서 처리하고, 큰 파일은 위에서 지정한 임시 저장소(setRepository)에 저장합니다.
		
		ServletFileUpload upload = new ServletFileUpload(factory);//실제 업로드 처리 객체: Factory의 설정을 바탕으로 실제 요청을 파싱(분석)하는 핵심 도구입니다.
		
		try {
			List<FileItem> items = upload.parseRequest(request);
			//Request 객체를 분석해서 multipart로 전송된 모든 항목(파일, 텍스트)을 FileItem 객체 리스트(items)로 만들어 반환합니다.
			for(int i = 0; i < items.size(); i++) {
				//items 리스트를 돌면서, 각 항목이 일반 텍스트인지 파일인지 구분하여 처리합니다. 하나씩!
				
				FileItem fileItem = (FileItem)items.get(i);//일반 텍스트이든, 파일이든 하나의 항목을 나타냅니다.
				if(fileItem.isFormField()) {
					//메서드로 해당 항목이 파일인지, 텍스트인지 구분합니다. 이 항목은 <input type="text"> 등으로 전송된 제목, 내용, 작성자 ID와 같은 일반 데이터입니다.
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					//                 <input> 태그의 name 속성값          전송된 실제 값
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
					//값을 Map에 저장하여 나중에 게시글 DTO에 담을 수 있도록 준비합니다.
				}else {
					//이 항목은 <input type="file"> 등으로 전송된 실제 파일입니다.
					System.out.println("파라미터 name값 : " + fileItem.getFieldName());
					System.out.println("파일 이름 : " + fileItem.getName());
					System.out.println("파일 크기 : " + fileItem.getSize() + "bytes");
					
					if(fileItem.getSize() > 0) {
						//파일 내용이 실제로 존재할 때만 처리를 진행합니다.
						int idx = fileItem.getName().lastIndexOf("\\");//뒤에서 부터 문자열 찾는 함수
						if(idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
							 /* 운영체제에 따라 파일 이름 앞에 붙은 경로 구분자(\ 또는 /)가 다를 수 있습니다. 이 코드는 앞의 경로를 모두 제거하고 순수한 파일
							 이름만 추출하기 위한 안전 장치입니다.*/
						}
						String fileName = fileItem.getName().substring(idx + 1);
						//문자열 자르기함수(substring) : 순수한 파일명만 추출
						articleMap.put(fileItem.getFieldName(), fileName);//Map에 파일의 필드명과 추출된 순수 파일 이름을 저장
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);// 저장 경로 + 파일 이름으로 저장할 File 객체 생성
						fileItem.write(uploadFile);// **실제로 파일을 서버 디스크에 기록(저장)하는 핵심 메서드!!!!!!!!!!**
						
					}
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return articleMap;
	}
	
}
