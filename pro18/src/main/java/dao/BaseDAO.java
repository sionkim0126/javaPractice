package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class BaseDAO {

	public BaseDAO() {
		
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
