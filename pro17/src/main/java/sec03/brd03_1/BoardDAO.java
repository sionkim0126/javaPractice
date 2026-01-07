package sec03.brd03_1;

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
	
	/*//단순 파일 업로드 *글 번호를 반환하지 않음* 
	 * public void insertNewArticle(ArticleDTO article) { try { conn =
	 * dataFactory.getConnection(); int articleNO = getNewArticleNO(); int parentNO
	 * = article.getParentNO(); String title = article.getTitle(); String content =
	 * article.getContent(); String id = article.getId(); String imageFileName =
	 * article.getImageFileName();
	 * 
	 * String query =
	 * "INSERT INTO T_BOARD(articleNO, parentNO, title, content, imageFileName, id)"
	 * + " VALUES(?, ?, ?, ?, ?, ?) "; System.out.println("query :" + query); pstmt
	 * = conn.prepareStatement(query); pstmt.setInt(1, articleNO); pstmt.setInt(2,
	 * parentNO); pstmt.setString(3, title); pstmt.setString(4, content);
	 * pstmt.setString(5, imageFileName); pstmt.setString(6, id);
	 * pstmt.executeUpdate();
	 * 
	 * pstmt.close(); conn.close();
	 * 
	 * }catch(Exception e) { e.printStackTrace(); } }
	 */
	
	public int insertNewArticle(ArticleDTO article) {
		int articleNO = getNewArticleNO();
		try {
			conn = dataFactory.getConnection();
			int parentNO = article.getParentNO();
			String title = article.getTitle();
			String content = article.getContent();
			String id = article.getId();
			String imageFileName = article.getImageFileName();
			
			String query = "INSERT INTO T_BOARD(articleNO, parentNO, title, content, imageFileName, id)"
					+ " VALUES(?, ?, ?, ?, ?, ?) ";
			System.out.println("query :" + query);
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
			
			
		}catch(Exception e) {
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
				return(rs.getInt(1)+1);
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
