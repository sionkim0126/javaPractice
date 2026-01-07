package sec03.brd04;

import java.util.List;

public class BoardService {
	BoardDAO boardDAO;
	public BoardService() {
		boardDAO = new BoardDAO();
	}
	
	public List<ArticleDTO> listArticles(){
		List<ArticleDTO> articlesList = boardDAO.selectAllArticles();
		return articlesList;
	}
	
	public ArticleDTO viewArticle(int articleNO) {
		ArticleDTO articleDTO;
		articleDTO = boardDAO.selectArticle(articleNO);		
		return articleDTO;
	}
	
}
