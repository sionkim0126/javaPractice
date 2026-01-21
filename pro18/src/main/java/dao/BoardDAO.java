package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.ArticleDTO;

public class BoardDAO extends BaseDAO {
	DataSource dataFactory;
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
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ArticleDTO> articlesList = new ArrayList<ArticleDTO>();
		
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT LEVEL, articleNO, parentNO, title, content, id, writeDate"
					+ " from t_board"
					+ " START WITH parentNO = 0"
					+ " CONNECT BY PRIOR articleNO = parentNO"
					+ " ORDER SIBLINGS BY articleno DESC";
			/* System.out.println("query :" + query); */
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
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
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs, pstmt, conn);
		}
		return articlesList;
	}
	
	public int insertNewArticle(ArticleDTO article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int articleNO = selectArticleNO();
		try {			
			int parentNO = article.getParentNO();
			String id = article.getId();
			String title = article.getTitle();
			String content = article.getContent();
			String imageFileName = article.getImageFileName();
		
			conn = dataFactory.getConnection();
			String query = "INSERT INTO T_BOARD(articleNO, parentNO, title, content, imageFileName, id)"
					+ " VALUES(?, ?, ?, ?, ?, ?)";
			/* System.out.println("query :" + query); */
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			pstmt.setInt(2, parentNO);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, imageFileName);
			pstmt.setString(6, id);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(null, pstmt, conn);
		}
		return articleNO;
	}
	
	private int selectArticleNO() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT MAX(articleNO) FROM t_board";
			/* System.out.println("query :" + query); */
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return( rs.getInt(1) + 1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs, pstmt, conn);
		}
		return 0;
	}
	
	public ArticleDTO selectArticle(int articleNO) {
		ArticleDTO article = new ArticleDTO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM t_board where articleNO = ?";
			/* System.out.println("query :" + query); */
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				article.setArticleNO(rs.getInt("ARTICLENO"));
				article.setParentNO(rs.getInt("PARENTNO"));
				article.setTitle(rs.getString("TITLE"));
				article.setContent(rs.getString("CONTENT"));
				article.setImageFileName(rs.getString("IMAGEFILENAME"));
				article.setWriteDate(rs.getDate("WRITEDATE"));
				article.setId(rs.getString("ID"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs, pstmt, conn);
		}
		return article;
	}
	
	public String selectArticleWriter(int articleNO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String writer = null;
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT ID FROM t_board WHERE ARTICLENO = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				writer = rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(rs, pstmt, conn);
		}
		return writer;
	}
	
	public void updateArticle(ArticleDTO article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String title = article.getTitle();
			String content = article.getContent();
			String imageFileName = article.getImageFileName();
			int articleNO = article.getArticleNO();
			
			conn = dataFactory.getConnection();
			String query = "UPDATE t_board set TITLE = ?, CONTENT =?";
			if(imageFileName != null && imageFileName.length() != 0) {
				query += ", IMAGEFILENAME = ?"
						+ " WHERE ARTICLENO = ?";
			}else {
				query += " WHERE ARTICLENO = ?";
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
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(null, pstmt, conn);
		}
	}
	
	
}
