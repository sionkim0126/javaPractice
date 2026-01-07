package sec03.brd04;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;
	
	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ArticleDTO> selectAllArticles(){
		List<ArticleDTO> articlesList = new ArrayList<ArticleDTO>();
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT LEVEL, articleNO, parentNO, title, content, id, writeDate"
					+ " from t_board"
					+ " START WITH parentNO = 0"
					+ " CONNECT BY PRIOR articleNO = parentNO"
					+ " ORDER SIBLINGS BY articleno DESC";
			System.out.println("query : " + query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int level = rs.getInt("level");
				int articleNO = rs.getInt("articleNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				
				ArticleDTO articleDTO = new ArticleDTO(level, articleNO, title, content, id, writeDate);
				
				articlesList.add(articleDTO);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return articlesList;
	}
	
	public ArticleDTO selectArticle(int articleNO) {
		ArticleDTO article = new ArticleDTO();
		try {
			conn = dataFactory.getConnection();
			String query = "select articleNO, parentNO, title, content, imageFileName, id, writeDate"
					+ " from t_board"
					+ " where articleNO = ? ";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int _articleNO = rs.getInt("articleNO");
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String imageFileName = rs.getString("imageFileName");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				
				article.setArticleNO(_articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setContent(content);
				article.setImageFileName(imageFileName);
				article.setId(id);
				article.setWriteDate(writeDate);
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return article;
	}

}
