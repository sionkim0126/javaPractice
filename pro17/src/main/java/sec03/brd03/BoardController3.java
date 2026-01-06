package sec03.brd03;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
 * Servlet implementation class BoardController3
 */
@WebServlet("/board3/*")
public class BoardController3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    BoardService boardService;
    ArticleDTO articleDTO;
    
    private static String ARTICLE_IMAGE_REPO = "E:\\Projects\\필요모음\\upload_repo";
    
	public void init(ServletConfig config) throws ServletException {
		boardService = new BoardService();
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
		System.out.println("action :" + action);
		
		try {
			List<ArticleDTO> articlesList = new ArrayList<ArticleDTO>();
			if(action == null || action.equals("/listArticles.do")) {
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board03/listArticles.jsp";
				
			}else if(action.equals("/articleForm.do")) {
				nextPage = "/board03/articleForm.jsp";
				
			}else if(action.equals("/addArticle.do")) {
				Map<String, String>articleMap = upload(request, response);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				
				ArticleDTO articleDTO = new ArticleDTO(); // <--- 수정된 부분!
				
				articleDTO.setParentNO(0);
				articleDTO.setId("hong");
				articleDTO.setTitle(title);
				articleDTO.setContent(content);
				articleDTO.setImageFileName(imageFileName);
				int articleNO = boardService.addarticle(articleDTO);
				
				if(imageFileName != null && imageFileName.length() != 0) {
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + " alert('새글을 추가했습니다.');" + "location.href='" + request.getContextPath()
						+ "/board3/listArticles.do';" + "</script>");
				return;
			}
			RequestDispatcher dispath = request.getRequestDispatcher(nextPage);
			dispath.forward(request, response);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> articleMap = new HashMap<>();
		String encoding = "UTF-8";
		File currentDirPath = new File(ARTICLE_IMAGE_REPO); // 실제 파일이 저장될 서버 디스크 경로
		DiskFileItemFactory factory = new DiskFileItemFactory();// 파일 업로드 처리를 위한 팩토리 객체 생성
		factory.setRepository(currentDirPath);// 업로드 중 임시 파일이 저장될 위치
		factory.setSizeThreshold(1024 * 1024);// 메모리에 저장할 최대 크기 (1MB 초과 시 디스크 사용)
		
		// multipart 요청을 파싱(분해)해주는 객체
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
	        // multipart/form-data 요청을 FileItem 리스트로 변환
	        // 텍스트(title, content) + 파일(imageFileName) 모두 포함됨
			List<FileItem> items = upload.parseRequest(request);
			for(int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem)items.get(i);
				
				 // ===== 텍스트 필드인 경우 (title, content) =====
				if(fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
					
				// ===== 파일 필드인 경우 (imageFileName) =====
				}else {					
					System.out.println("파라미터 이름 :" + fileItem.getFieldName());
					System.out.println("파일 이름 :" + fileItem.getName());
					System.out.println("파일 크기 :" + fileItem.getSize() + "bytes");
					
					// 파일이 실제로 선택된 경우만 처리
					if(fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if(idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						
						String fileName = fileItem.getName().substring(idx + 1);
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);//첨부한 파일을 먼저 temp폴더에 업로드합니다.
						fileItem.write(uploadFile);
						
					}
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return articleMap;
	}

}
