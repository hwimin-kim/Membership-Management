package org.crm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInsert {
	static void userIns(String name, String id, String pwd, int age, String area)
			throws ClassNotFoundException, SQLException {

		String sql = "insert into " + DatabaseConnection.getTableName() + " values(?,?,?,?,?,?,?)";

		Connection conn = DatabaseConnection.getConn();
		PreparedStatement pstmt = null;

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, null);
		pstmt.setString(2, name);
		pstmt.setString(3, id);
		pstmt.setString(4, pwd);
		pstmt.setInt(5, age);
		pstmt.setString(6, area);
		pstmt.setDate(7, java.sql.Date.valueOf(LocalDate.getCurrentDate()));

		int result = pstmt.executeUpdate();
		if (result > 0) {
			System.out.println("회원가입이 완료되었습니다.");
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}
}
