package controller;

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
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import dto.ArticleDTO;
import dto.MemberDTO;
import service.BoardService;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/Board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ARTICLE_IMAGE_REPO = "E:\\Projects\\필요모음\\upload_repo";
	BoardService boardService;
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
		System.out.println("action : " + action);
		try {
			if(action.equals("/listArticles.do")) {
				List<ArticleDTO> articlesList = new ArrayList<ArticleDTO>();
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board/listArticles.jsp";
				
			}else if(action.equals("/articleForm.do")) {
				nextPage ="/board/articleForm.jsp";
				
			}else if(action.equals("/addArticle.do")) {
				HttpSession session = request.getSession(false);
				MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");
				String id = loginMember.getId();
				Map<String, String> articleMap = upload(request,response);
				
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				
				ArticleDTO article = new ArticleDTO();
				article.setTitle(title);
				article.setContent(content);
				article.setId(id);
				article.setParentNO(0);
				
				int articleNO = boardService.addArticle(article);
				
				if(imageFileName != null && imageFileName.length() != 0) {
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\"+ "temp" + "\\" + imageFileName);
					File desDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
					desDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, desDir, true);
					PrintWriter pw = response.getWriter();
					pw.print("<script>" + " alert('새글을 추가했습니다.');" 
							+ "location.href='" 
							+ request.getContextPath()
							+ "/Board/listArticles.do';" + "</script>");
					return;
				}else {
					PrintWriter pw = response.getWriter();
					pw.print("<script>" + " alert('이미지파일 없이 추가했습니다.');" 
							+ "location.href='" 
							+ request.getContextPath()
							+ "/Board/listArticles.do';" + "</script>");
					return;
				}
			}else if(action.equals("/viewArticle.do")) {
				int articleNO = Integer.parseInt(request.getParameter("articleNO"));
				ArticleDTO article = boardService.viewArticle(articleNO);
				request.setAttribute("article", article);
				nextPage = "/board/viewArticle.jsp";
				
			}
			
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
							
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> articleMap = new HashMap<>();
		
		String encoding = "UTF-8";
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List<FileItem> items = upload.parseRequest(request);
			for(int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem)items.get(i);
				if(fileItem.isFormField()) {
					System.out.println("name : " + fileItem.getFieldName());
					System.out.println("value : " + fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
					
				}else {
					System.out.println("paraName : " + fileItem.getFieldName());
					System.out.println("valueName : " + fileItem.getName());
					System.out.println("valueSize : " + fileItem.getSize() + "bytes");
					
					if(fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						// ex: C:\Users\sion\Desktop\test.jpg 마지막부터 찾음
						if(idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
							//혹시나 브라우저가 다를 경우
						}
						String fileName = fileItem.getName().substring(idx + 1);
						articleMap.put(fileItem.getFieldName(), fileName);
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
						
						fileItem.write(uploadFile);
						//일단 임시파일에 이미지이름으로 저장
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
