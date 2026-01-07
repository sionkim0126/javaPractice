package sec03.brd04;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardController4
 */
@WebServlet("/board4/*")
public class BoardController4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BoardService boardService;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		boardService = new BoardService();//생각해보니까 DAO는 Service에서만 관리하니까 여기서 생성하면 큰일 아님?
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
		
		List<ArticleDTO> articlesList = new ArrayList<ArticleDTO>();
		try {
			if(action == null || action.equals("/listArticles.do")) {
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board04/listArticles.jsp";
				
			}else if(action.equals("/viewArticle.do")) {
				String _articleNO = request.getParameter("articleNO");
				int articleNO = Integer.parseInt(_articleNO);
				
				ArticleDTO articleDTO = boardService.viewArticle(articleNO);
				
				request.setAttribute("article", articleDTO);
				nextPage = "/board04/viewArticle.jsp";
				
			}
			RequestDispatcher dispath = request.getRequestDispatcher(nextPage);
			dispath.forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
