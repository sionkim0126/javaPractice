package sec03.brd07;

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
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
			
		}catch (Exception e) {
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
			System.out.println("query :" + query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int level = rs.getInt("level");
				int articleNO = rs.getInt("articleNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				
				ArticleDTO article = new ArticleDTO(level, articleNO, title, content, id, writeDate);
				
				articlesList.add(article);
			}
			rs.close();
			pstmt.close();
			conn.close();			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return articlesList;
	}
	
	public int insertNewArticle(ArticleDTO article) {
		int articleNO = getNewArticleNO();
		try {
			int parentNO = article.getParentNO();
			String title = article.getTitle();
			String content = article.getContent();
			String imageFileName = article.getImageFileName();
			String id = article.getId();
			
			conn = dataFactory.getConnection();
			String query = "INSERT INTO T_BOARD(articleNO, parentNO, title, content, imageFileName, id)"
					+ " VALUES(?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			pstmt.setInt(2, parentNO);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, imageFileName);
			pstmt.setString(6, id);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return articleNO;
	}
	
	private int getNewArticleNO() {
		try {
			conn = dataFactory.getConnection();
			String query = "select MAX(articleNO) from t_board ";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return( rs.getInt(1) + 1);
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArticleDTO selectArticle(int _articleNO) {
		ArticleDTO article = new ArticleDTO();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from t_board where articleNO = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, _articleNO);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int articleNO = rs.getInt("ARTICLENO");
				int parentNO = rs.getInt("PARENTNO");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String imageFileName = rs.getString("IMAGEFILENAME");
				Date writeDate = rs.getDate("WRITEDATE");
				String id = rs.getString("ID");
				
				article.setArticleNO(articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setContent(content);
				article.setImageFileName(imageFileName);
				article.setWriteDate(writeDate);
				article.setId(id);
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return article;
	}
	
	public void updateArticle(ArticleDTO article) {
		try {
			int articleNO = article.getArticleNO();
			String title = article.getTitle();
			String content = article.getContent();
			String imageFileName = article.getImageFileName();
			
			conn = dataFactory.getConnection();
			String query = "UPDATE t_board SET title = ?, content = ?";
			if(imageFileName != null && imageFileName.length() != 0) {
				query += ", imageFileName = ?"
						+ " where articleNO = ?";
			}else {
				query += " where articleNO = ?";
			}
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			if(imageFileName != null && imageFileName.length() != 0) {
				pstmt.setString(3, imageFileName);
				pstmt.setInt(4, articleNO);
			}else {
				pstmt.setInt(3, articleNO);
			}
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Integer> selectRemovedArticles(int _articleNO){
		List<Integer> articleNOList = new ArrayList<Integer>();
		try {
			conn = dataFactory.getConnection();
			String query = "select articleNO from t_board "
					+ "START WITH articleNO = ?"
					+ " CONNECT BY PRIOR articleNO = parentNO";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, _articleNO);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int articleNO = rs.getInt("articleNO");
				articleNOList.add(articleNO);
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return articleNOList;
	}
	
	public void deleteArticle(int articleNO) {
		try {
			conn = dataFactory.getConnection();
			String query = "DELETE FROM t_board WHERE articleNO IN("
			        + " SELECT articleNO FROM t_board"
			        + " START WITH articleNO = ?"
			        + " CONNECT BY PRIOR articleNO = parentNO)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
