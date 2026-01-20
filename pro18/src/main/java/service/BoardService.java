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
}
