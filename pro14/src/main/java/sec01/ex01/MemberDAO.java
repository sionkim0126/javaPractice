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
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberBean> listMembers(){
		List<MemberBean> list = new ArrayList<MemberBean>();
		try {
		con = dataFactory.getConnection();
		String query = "select * from t_member";
		query += " order by joindate desc";
		pstmt = con.prepareStatement(query);
		System.out.println("적용 커리 : " + query);
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {			
			String id = rs.getString(1);
			String pw = rs.getString(2);
			String name = rs.getString(3);
			String email = rs.getString(4);
			Date joinDate = rs.getDate(5);
			
			MemberBean bean = new MemberBean();
			bean.setId(id);
			bean.setPwd(pw);
			bean.setName(name);
			bean.setEmail(email);
			bean.setJoinDate(joinDate);
			
			list.add(bean);/*필수 요소*/
		}
		rs.close();
		pstmt.close();
		con.close();
				
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void addMember(MemberBean memberBean) {
		try {
			con = dataFactory.getConnection();
			String id = memberBean.getId();
			String pwd = memberBean.getPwd();
			String name = memberBean.getName();
			String email = memberBean.getEmail();
			
			String query = "insert into t_member ";
			query += "values(?, ?, ?, ?, sysdate)";
			System.out.println("적용 커리 : " + query);
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
