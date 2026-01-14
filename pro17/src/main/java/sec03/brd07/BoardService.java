package sec03.brd07;

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
	
	public List<Integer> moveArticle(int articleNO){
		List<Integer> articleNOList = boardDAO.selectRemovedArticles(articleNO);
		boardDAO.deleteArticle(articleNO);
		return articleNOList;
	}
	
	public int addReply(ArticleDTO article) {
		int articleNO = boardDAO.insertNewArticle(article);
		return articleNO;
	}
}
