package sec01.ex01;

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
	private Connection con;
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
			String query = "select * from t_member order by joinDate desc";
			con = dataFactory.getConnection();
			System.out.println("쿼리 : " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name =  rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberDTO memberDTO = new MemberDTO(id, pwd, name, email, joinDate);
				membersList.add(memberDTO);
			}
			rs.close();
			pstmt.close();
			con.close();
			
		}catch(Exception e) {
			
		}
		return membersList;
	}
	
	public void addMember(MemberDTO dto) {
		try {
			String id = dto.getId();
			String pwd = dto.getPwd();
			String name = dto.getName();
			String email = dto.getEmail();
			
			con = dataFactory.getConnection();
			String query = "insert into t_member(id, pwd, name, email, joinDate) values("
					+ " ?, ?, ?, ?, sysdate)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
