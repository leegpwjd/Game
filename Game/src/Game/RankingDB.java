package Game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class RankingDB {
	
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
			System.out.println("Unknown" + " Error");
		}
		return conn;
	}
	
	//score 등록
	public void insertScore(String userId, int score) {

		Connection conn = getConnection();
		
		String sql = "";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			sql = "insert into ranking(id, score) " + 
					"values( ?, ?)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, userId);
			stmt.setInt(2, score);
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String[]> Ranking() {
		ArrayList<String[]> ranking = new ArrayList<String[]>();
		
		Connection conn = getConnection();
		
		String sql = "";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			sql = "select * " + 
					"from ranking " + 
					"order by score desc";
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();

			while(rs.next()) {
				String id = rs.getString(1);
				int score = rs.getInt(2);
			
				ranking.add(new String[] {id, String.valueOf(score)});

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ranking;
	}
}
