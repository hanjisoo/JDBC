package com.javaex.jdbc.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookUpdate {

	public static void main(String[] args) {
		String title = "패션짱";
		String pubs = "북스";
		String date = "2012-01-22";
		int authorId=4;
		int bookId = 4;

		//0. import java.sql.*;
		Connection conn=null;
		PreparedStatement pstmt=null;

		try {
			//1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. Connection 얻어오기
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			conn=DriverManager.getConnection(url,"webdb","webdb"); 
			System.out.println("접속성공");
			
			//3. SQL문 준비 / 바인딩 / 실행
			
			String query= "UPDATE book set title=?, pubs=?, pub_date=?,author_id=? where book_id=?";
			
			pstmt=conn.prepareStatement(query);//커리정보 날릴수 있는애
			
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, date);
			pstmt.setInt(4, authorId);
			pstmt.setInt(5, bookId);
			int count=pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println(count+"건 정리");
		}catch(ClassNotFoundException e){
			System.out.println("error: 드라이버 로딩 실패-"+ e );
		}catch(SQLException e) {
			System.out.println("error:"+e);
		}finally {
			//5. 자원정리
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(SQLException e) {
				System.out.println("error:"+e);
			}
			
		}
	}

}
