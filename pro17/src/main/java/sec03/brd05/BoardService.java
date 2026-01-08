package sec03.brd05;

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
		//리턴 값 : articleNO
	}
	
	public void modArticle(ArticleDTO article) {
		boardDAO.updateArticle(article);
	}
	
	public ArticleDTO viewArticle(int articleNO) {
		ArticleDTO articleDTO;
		articleDTO = boardDAO.selectArticle(articleNO);		
		return articleDTO;
	}
}
