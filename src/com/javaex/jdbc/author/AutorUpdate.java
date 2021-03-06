package com.javaex.jdbc.author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AutorUpdate {

	public static void main(String[] args) {
		String name="강풀";//입력받음
		String desc="김해";
		int num=2;
		//0. import java.sql.*;
		Connection conn=null;
		PreparedStatement pstmt=null;

		try {
			//1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. Connection 얻어오기
			String url="jdbc:oracle:thin:@localhost:1521:xe";//db가있는 주소@주소:포트:
			conn=DriverManager.getConnection(url,"webdb","webdb"); //연결정보갖고있음
			System.out.println("접속성공");
			
			//3. SQL문 준비 / 바인딩 / 실행
			
			//String query= "UPDATE author set author_name='한씨', author_desc='서울' where author_id=2";
			String query= "UPDATE author set author_name=?, author_desc=? where author_id=?";
			
			pstmt=conn.prepareStatement(query);//커리정보 날릴수 있는애
			
			pstmt.setString(1,name);//?,?자리
			pstmt.setString(2,desc);
			pstmt.setInt(3,num);
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
