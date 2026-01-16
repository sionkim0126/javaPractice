package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.MemberDTO;

public class MemberDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addMember(MemberDTO member) {
		try {
			String id = member.getId();
			String pwd = member.getPwd();
			String name = member.getName();
			String email = member.getEmail();
			
			conn = dataFactory.getConnection();
			String query = "INSERT INTO t_member(id, pwd, name, email, joinDate)"
					+ " VALUES(?, ?, ?, ?, sysdate)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(null, pstmt, conn);
		}
	}
	
	//실무 사용
	protected final void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if(rs != null) 
				rs.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			if(pstmt != null)
				pstmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if(conn != null)
				conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}