package Game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class LoginDB {
	public static Connection getConnection() {
		Connection conn = null;

		try {
			Class.forName("oracle.jdbc.OracleDriver");

			String user = "game";
			String pwd = "1234";
			String url = "jdbc:oracle:thin:@//192.168.20.7:1521/xe";
			conn = DriverManager.getConnection(url, user, pwd);

		} catch (ClassNotFoundException e) {
			System.out.println("DB 드라이버 로딩 실패 : " + e.toString());
		} catch (SQLException e) {
			System.out.println("DB접속 실패 : " + e.toString());
		} catch (Exception e) {
			System.out.println("Error");
		}
		return conn;
	}
	
	//id존재여부 확인
	public boolean LoginID(String id) {
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
		}
		return false;
	}

	// id/pw 일치 확인
	public boolean Login(String id, String pw) {
		String pwDB = null;

		Connection conn = getConnection();

		String sql = "";

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			sql = "select pw " + "from game_user " + "where id = ? ";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				pwDB = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (pwDB.equals(pw)) {
			return true;
		} else {
			return false;
		}
	}
}
