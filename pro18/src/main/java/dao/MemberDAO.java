package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.MemberDTO;

public class MemberDAO extends BaseDAO {
	private DataSource dataFactory;
	
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
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String id = member.getId();
			String pwd = member.getPwd();
			String name = member.getName();
			String email = member.getEmail();
			
			conn = dataFactory.getConnection();
			String query = "INSERT INTO t_member(id, pwd, name, email, joinDate)"
					+ " VALUES(?, ?, ?, ?, sysdate)";
			System.out.println("query :" + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(null, pstmt, conn);
		}
	}
	
	public MemberDTO login(String _id, String _pwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDTO member = null;
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM t_member WHERE id = ? AND pwd = ?";
			System.out.println("query :" + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, _id);
			pstmt.setString(2, _pwd);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				//if를 쓰는 이유는 한명의 결과만 가져오는 것이 정상
                member = new MemberDTO();
                member.setId(rs.getString("id"));
                member.setPwd(rs.getString("pwd"));
            }
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs, pstmt, conn);
		}
		return member;
	}
}