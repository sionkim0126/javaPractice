package sec04.ex03;

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
	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
			/* 1. JNDI 탐색을 위한 시작점(InitialContext) 생성 톰캣 서버가 관리하는 리소스(환경 설정, DataSource 등)에 접근할 수 있는 진입점
			 * 			
			   2. 현재 웹 애플리케이션 전용 환경(Context)으로 이동 "java:/comp/env"는 톰캣이 제공하는 표준 환경 이름 공간
			 *    여기 안에 context.xml이나 web.xml에서 정의한 리소스들이 등록되어 있음
			 *    
			 * 3. 환경 설정 안에서 "jdbc/oracle"이라는 이름의 리소스를 찾음 context.xml에 미리 정의된 DataSource(커넥션 풀) 객체를 가져옴
			 가져온 객체를 DataSource 타입으로 캐스팅해서 dataFactory 변수에 저장 */
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberDTO> listMembers(){
		List<MemberDTO>list = new ArrayList<MemberDTO>();
		
		try {
			con = dataFactory.getConnection();
			String query = "select * from t_member";
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("쿼리 연결 성공");
			
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("name");
				Date joinDate = rs.getDate("joinDate");
				MemberDTO vo = new MemberDTO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo); //생성자 호출
			}
			rs.close();
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
