package com.javaex.jdbc.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookDelete {

	public static void main(String[] args) {
		int bookId=4;
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
			String query= "DELETE FROM book where book_id=?";
			pstmt=conn.prepareStatement(query);
			
			pstmt.setInt(1, bookId);
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
