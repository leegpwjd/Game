package Game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class JoinDB {
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			String user = "game";
			String pwd = "1234";
			String url = "jdbc:oracle:thin:@//192.168.20.7:1521/xe";
			conn = DriverManager.getConnection(url, user, pwd);
			
		}catch (ClassNotFoundException e) {
			System.out.println("DB 드라이버 로딩 실패 : " + e.toString());
		} catch (SQLException e) {
			System.out.println("DB접속 실패 : " + e.toString());
		}catch(Exception e) {
			System.out.println("Error");
		}
		return conn;
	}
	
	//id중복확인
	public boolean JoinID(String id) {
		Connection conn = getConnection();
		
		String sql = "";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			sql = "select id " + 
					"from game_user " + 
					"where id = ? ";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, id);
			
			int result = stmt.executeUpdate();
			if(result == 1) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeResources(conn, stmt, rs);
		}
		return false;
	}
	
	//회원가입
	public boolean Join(User user) {
		
		Connection conn = getConnection();
		
		String sql = "";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			sql = "insert into game_user(name, id, pw, email) " + 
					"values(?, ?, ?, ?)";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getId());
			stmt.setString(3, user.getPw());
			stmt.setString(4, user.getEmail());
			
			int result = stmt.executeUpdate();
			if(result == 1) {
				return true;
			}else {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeResources(conn, stmt, rs);
		}
		return false;
	}
	
	private static void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
