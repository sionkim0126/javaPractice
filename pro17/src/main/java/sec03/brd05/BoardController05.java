package sec03.brd05;

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
 * Servlet implementation class BoardController05
 */
@WebServlet("/board5/*")
public class BoardController05 extends HttpServlet {
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
		System.out.println("action : " + action);
		
		try {
			List<ArticleDTO>articlesList = new ArrayList<ArticleDTO>();
			if(action == null || action.equals("/listArticles.do")) {
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board05/listArticles.jsp";
				
			}else if(action.equals("/articleForm.do")) {
				nextPage = "/board05/articleForm.jsp";
				
			}else if(action.equals("/addArticle.do")) {
				Map<String, String> articleMap = upload(request, response);
				/*웹 브라우저에서 전송된 multipart/form-data 요청을 처리하고,
				 *일반 텍스트 데이터(제목, 내용)와 파일 데이터(파일 이름)를 Map 형태로 받아옵니다.*/
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				
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
						+ "/board5/listArticles.do';" + "</script>");
				/* nextPage = "/board3_1/listArticles.do"; */
				return;//why 그냥 return? 그냥 alert만 진행하고 nextPage로 하면 안돼나?
				
			}else if(action.equals("/modArticle.do")) {
				Map<String, String> articleMap = upload(request, response);
				//요청이 들어오는 Parameter는 번호, 제목, 내용, 이미지등
				int articleNO = Integer.parseInt(articleMap.get("articleNO"));
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String id = articleMap.get("writer");
				String imageFileName = articleMap.get("imageFileName");
				
				ArticleDTO dto = new ArticleDTO(articleNO, title, content, id, imageFileName);
				
				boardService.modArticle(dto);
				
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
						+ "/board5/viewArticle.do?articleNO="
						+ articleNO + "';" +" </script>");
				return;
				
			}else if(action.equals("/viewArticle.do")) {
				String _articleNO = request.getParameter("articleNO");
				int articleNO = Integer.parseInt(_articleNO);
				
				ArticleDTO articleDTO = boardService.viewArticle(articleNO);
				
				request.setAttribute("article", articleDTO);
				nextPage = "/board05/viewArticle.jsp";
				
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
		
		ServletFileUpload upload = new ServletFileUpload(factory);//실제 업로드 처리 객체: Factory의 설정을 바탕으로 실제 요청을 파싱(분석)하는 핵심 도구입니다.
		
		try {
			List<FileItem> items = upload.parseRequest(request);
			
			for(int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem)items.get(i);
				if(fileItem.isFormField()) {
					System.out.println("name : "+ fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
					//값을 Map에 저장하여 나중에 게시글 DTO에 담을 수 있도록 준비합니다.
				}else {
					System.out.println("파라미터 name :" + fileItem.getFieldName());
					System.out.println("파일 이름 =" + fileItem.getName());
					System.out.println("파일 크기 =" + fileItem.getSize() + "bytes");
					
					if(fileItem.getSize() > 0) {
						//파일 내용이 실제로 존재할 때만 처리를 진행합니다.
						int idx = fileItem.getName().lastIndexOf("\\");//뒤에서 부터 문자열 찾는 함수
						if(idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
							 /* 운영체제에 따라 파일 이름 앞에 붙은 경로 구분자(\ 또는 /)가 다를 수 있습니다. 이 코드는 앞의 경로를 모두 제거하고 순수한 파일
							 이름만 추출하기 위한 안전 장치입니다.*/
						}
						String fileName = fileItem.getName().substring(idx + 1);
						articleMap.put("imageFileName", fileName);
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
						fileItem.write(uploadFile);
						fileItem.write(uploadFile);
						// multipart 요청으로 전송된 파일 데이터를 서버의 지정된 경로(temp)에 물리적으로 저장한다.
						// DB에는 파일명이 저장되고, 실제 파일은 이후 조회/다운로드 시 디스크에서 읽어 사용된다.
					}
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return articleMap;
	}
}
