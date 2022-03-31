package org.crm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseSelect {

	static void userList(int menuIdx, String id) throws SQLException, ClassNotFoundException {
		String sql = null;
		Connection conn = DatabaseConnection.getConn();
		PreparedStatement pstmt = null;

		// sql문 선택
		switch (menuIdx) {
		// 전체 회원 조회
		case 1:
			sql = "select * from " + DatabaseConnection.getTableName();
			break;
		// 회원 검색(menuIdx == 2)
		default:
			sql = "select * from " + DatabaseConnection.getTableName() + " where id = ?";
			break;
		}

		pstmt = conn.prepareStatement(sql);
		// select문 id 조건 설정
		if (menuIdx == 2) {
			pstmt.setString(1, id);
		}
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			System.out.print("number: " + rs.getInt("number"));
			System.out.print(" name: " + rs.getString("name"));
			System.out.print(" id: " + rs.getString("id"));
			System.out.print(" pwd: " + rs.getString("pwd"));
			System.out.print(" age: " + rs.getInt("age"));
			System.out.print(" area: " + rs.getString("area"));
			System.out.println(" date: " + rs.getDate("date"));
		}
		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	static int userId(String id) throws SQLException, ClassNotFoundException {
		int cnt = 0;
		String sql = "select count(id) as cnt from " + DatabaseConnection.getTableName() + " where id = ?";
		Connection conn = DatabaseConnection.getConn();
		PreparedStatement pstmt = null;

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			System.out.println("cnt: " + rs.getString("cnt"));
			cnt = Integer.parseInt(rs.getString("cnt"));
		}
		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
		return cnt;
	}
}
