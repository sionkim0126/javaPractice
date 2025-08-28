package sec05.ex01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	//DB에 존재하는지 확인하는 함수
	public boolean isExisted(MemberDTO memberDTO) {
		boolean result = false;
		String id = memberDTO.getId();
		String pw = memberDTO.getPw();
		try {
			//DB연결
			con = dataFactory.getConnection();
			//쿼리작성 id,pwd가 일치하는 행 개수(count)가 1이면 true 그외 false
			String query = "select decode(count(*), 1,'true','false')as result from t_member";
			query += " where id = ? and pwd = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			//결과 변환 DB에서 가져온 true/false 를 boolean 으로 변환
			result = Boolean.parseBoolean(rs.getString("result"));
			System.out.println("result = " + result);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}
