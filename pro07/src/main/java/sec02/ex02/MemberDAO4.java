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

public class MemberDAO4 {
	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;
	
	public MemberDAO4() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberVO4> listMembers(){
		List<MemberVO4> list = new ArrayList<MemberVO4>();
		
		try {
			// connDB();
			con = dataFactory.getConnection();
			String query = "select * from t_member";
			System.out.println("PrepareStatement : " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberVO4 vo = new MemberVO4();//2, 3, 4, 5 생성자 호풀은 set에서만 호출된다.
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public void addMember(MemberVO4 memberVO4) {
		try {			
			con = dataFactory.getConnection();
			String id = memberVO4.getId();
			String pwd = memberVO4.getPwd();
			String name = memberVO4.getName();
			String email = memberVO4.getEmail();
			
			String query = "insert into t_member";
			query += " (id, pwd, name, email )";
			query += " values(?, ?, ?, ? )";
			System.out.println("prepareStatement: " + query);
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate();
			pstmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	private void connDB() {
//		 try {
//			 Class.forName("oracle.jdbc.driver.OracleDriver");
//		     System.out.println("Oracle 드라이버 로딩 성공"); 
//		     String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
//		     String user = "scott";
//		     String pwd = "sion1234";
//		     con = DriverManager.getConnection(url, user, pwd);
//		     System.out.println("Connection 생성 성공"); 
//		     }catch(Exception e) {
//		  e.printStackTrace();
//		  }
//	}

}
