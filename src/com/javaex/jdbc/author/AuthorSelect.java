package com.javaex.jdbc.author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorSelect {

	public static void main(String[] args) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 얻어오기
			String url="jdbc:oracle:thin:@localhost:1521:xe";//db가있는 주소@주소:포트:
			conn=DriverManager.getConnection(url,"webdb","webdb"); //연결정보갖고있음
			System.out.println("접속성공");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query="select author_id"
					+ ",author_name"
					+ ",author_desc"
					+ " from author";
			pstmt=conn.prepareStatement(query);
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery(query);
			// 4.결과처리  잘내려가면 T마지막줄이면 F
			while(rs.next()) {
				int authorId=rs.getInt("author_id");//1번째로우에서 컴럼
				String authorName=rs.getString("author_name");
				String authorDesc=rs.getString("author_desc");
				
				System.out.println(authorId+"\t"+authorName+"\t"+authorDesc);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리

			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);

			}

		}

	}

}
