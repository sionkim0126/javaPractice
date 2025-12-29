package sec02.ex02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;

	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberDTO> listMembers(){
		List<MemberDTO> membersList = new ArrayList<MemberDTO>();
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM t_member order by joindate desc";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				
				MemberDTO dto = new MemberDTO(id, pwd, name, email, joinDate);
				
				membersList.add(dto);
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return membersList;
	}
	
	public void addMember(MemberDTO dto) {
		String id = dto.getId();
		String pwd = dto.getPwd();
		String name = dto.getName();
		String email = dto.getEmail();
		
		try {
			conn = dataFactory.getConnection();
			String query = "insert into t_member(id, pwd, name, email, joinDate)"
					+ " values(?, ?, ?, ?, sysdate )";
			pstmt = conn.prepareStatement(query);
			System.out.println("query : " + query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public MemberDTO findMember(String id) {
		MemberDTO memInfo = null;
		try {
			conn = dataFactory.getConnection();
			String query = "select * from t_member where id = ? ";
			System.out.println("query : " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while( rs.next()) {
				String _id = rs.getString(1);
				String _pwd = rs.getString(2);
				String _name = rs.getString(3);
				String _email = rs.getString(4);
				Date _joinDate = rs.getDate(5);
				memInfo = new MemberDTO(_id, _pwd, _name, _email, _joinDate);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return memInfo;
	}
	
	public void modMember(MemberDTO dto) {
		String id = dto.getId();
		String pwd = dto.getPwd();
		String name = dto.getName();
		String email = dto.getEmail();
		
		try {
			conn = dataFactory.getConnection();
			String query = "update t_member set pwd = ?, name = ?, email = ? where id = ?";
			System.out.println("query : " + query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pwd);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, id);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void delMember(String id) {
		String query = "delete from t_member where id = ? ";
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(query);
			System.out.println("query : " + query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
