package service;

import java.util.List;

import dao.BoardDAO;
import dto.ArticleDTO;

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
		ArticleDTO article = boardDAO.selectArticle(articleNO);
		return article; 
	}
	
	public String getArticleWriter(int articleNO) {
	    return boardDAO.selectArticleWriter(articleNO);
	}
	
	public void modArticle(ArticleDTO article) {
		boardDAO.updateArticle(article);
	}
	
	public List<Integer>moveArticle(int articleNO){
		List<Integer> articleNOList = boardDAO.selectRemovedArticles(articleNO);
		boardDAO.deleteArticle(articleNO);
		return articleNOList;
	}
}
