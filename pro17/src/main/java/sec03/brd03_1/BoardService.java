package sec03.brd03_1;

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
	
	/*//단순 파일업로드 사용 
	 * public void addArticle(ArticleDTO article) {
	 * boardDAO.insertNewArticle(article); }
	 */
	
	public int addArticle(ArticleDTO article) {
		return boardDAO.insertNewArticle(article);
		//리턴 값 : articleNO
	}
	
}
