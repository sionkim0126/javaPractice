package sec03.brd06;

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
 * Servlet implementation class BoardController6
 */
@WebServlet("/board6/*")
public class BoardController6 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BoardService boardService;
	
	private static String ARTICLE_IMAGE_REPO = "E:\\Projects\\필요모음\\upload_repo";
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
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
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String nextPage = null;
		String action = request.getPathInfo();
		System.out.println("action :" + action);
		
		List<ArticleDTO> articlesList = new ArrayList<ArticleDTO>();
		try {
			if(action == null || action.equals("/listArticles.do")) {
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board06/listArticles.jsp";
				
			}else if(action.equals("/addArticle.do")) {
				Map<String, String> articleMap = upload(request, response);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				
				ArticleDTO dto = new ArticleDTO();
				
				dto.setParentNO(0);
				dto.setId("test");
				dto.setTitle(title);
				dto.setContent(content);
				dto.setImageFileName(imageFileName);
				
				int articleNO = boardService.addArticle(dto);
				
				if(imageFileName != null && imageFileName.length() != 0) {
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\temp\\" + imageFileName);
					File desDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
					desDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, desDir, true);
				}
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + " alert('새글을 추가했습니다.');" 
						+ "location.href='" 
						+ request.getContextPath()
						+ "/board6/listArticles.do';" + "</script>");
				return;
				
			}else if(action.equals("/viewArticle.do")) {
				int articleNO = Integer.parseInt(request.getParameter("articleNO"));
				
				ArticleDTO articleDTO = boardService.viewArticle(articleNO);
				request.setAttribute("article", articleDTO);
				nextPage = "/board06/viewArticle.jsp";
				
			}else if(action.equals("/modArticle.do")) {
				Map<String, String> articleMap = upload(request, response);
				
				int articleNO = Integer.parseInt(articleMap.get("articleNO"));
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String id = articleMap.get("writer");
				String imageFileName = articleMap.get("imageFileName");
				
				ArticleDTO articleDTO = new ArticleDTO(articleNO, title, content, id, imageFileName);
				
				boardService.modArticle(articleDTO);
				
				if(imageFileName != null && imageFileName.length() != 0) {
					String originalFileName = articleMap.get("originalFileName");
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\temp\\" + imageFileName);
					
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO + "\\" + originalFileName);
					oldFile.delete();
				
				}
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + " alert('글을 수정했습니다.');" + " location.href='"
						+ request.getContextPath()
						+ "/board6/viewArticle.do?articleNO="
						+ articleNO + "';" +" </script>");
				return;
				
			}else if(action.equals("/removeArticle.do")) {
				int articleNO = Integer.parseInt(request.getParameter("articleNO"));
				List<Integer> articleNOList = boardService.moveArticle(articleNO);
				for(int _articleNO : articleNOList) {
					File imgDir = new File(ARTICLE_IMAGE_REPO + "\\" + _articleNO);
					if(imgDir.exists()) {
						FileUtils.deleteDirectory(imgDir);
					}
				}
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + " alert('새글을 삭제했습니다.');" 
						+ "location.href='" 
						+ request.getContextPath()
						+ "/board6/listArticles.do';" + "</script>");
				return;
			}
			RequestDispatcher dispath = request.getRequestDispatcher(nextPage);
			dispath.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Map<String, String> articleMap = new HashMap<>();
		
		String encoding = "UTF-8";
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List<FileItem> items = upload.parseRequest(request);
			
			for(int i = 0; i < items.size(); i++ ) {
				FileItem fileItem = (FileItem)items.get(i);
				
				if(fileItem.isFormField()) {
					System.out.println("name : " + fileItem.getFieldName());
					System.out.println("value : " + fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
													
				}else {
					System.out.println("name : " + fileItem.getFieldName());
					System.out.println("valueName : " +fileItem.getName());
					System.out.println("valueSize : " + fileItem.getSize() + "bytes");
					
					if(fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if(idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						String fileName = fileItem.getName().substring(idx + 1);
						articleMap.put(fileItem.getName(), fileName);
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName );
						fileItem.write(uploadFile);
						// multipart 요청으로 전송된 파일 데이터를 서버의 지정된 경로(temp)에 물리적으로 저장한다.
						// DB에는 파일명이 저장되고, 실제 파일은 이후 조회/다운로드 시 디스크에서 읽어 사용된다.
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return articleMap;
	}
}
