package sec03.brd06;

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
	
	public int addArticle(ArticleDTO article) {
		return boardDAO.insertNewArticle(article);
	}
	
	public ArticleDTO viewArticle(int articleNO) {
		ArticleDTO articleDTO = boardDAO.selectArticle(articleNO);
		return articleDTO;
	}
	
	public void modArticle(ArticleDTO article) {
		boardDAO.updateArticle(article);
	}
}
