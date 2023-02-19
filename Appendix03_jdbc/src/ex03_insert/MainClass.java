package ex03_insert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class MainClass {
	
	// 상수 삽입
	public static void ex01() {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Properties p = new Properties();
			p.load(new BufferedReader(new FileReader("db.properties")));
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, p);
			String sql = "INSERT INTO MEMBER_TBL(MEMBER_NO, ID, NAME, ADDRESS, JOIN_DATE)";
			sql += " VALUES(MEMBER_SEQ.NEXTVAL, 'admin2', '관리자', '서울', to_date('2023-02-15', 'yyyy-mm-dd'))";
			ps = con.prepareStatement(sql);
			// 영향 받은 행의 수를 int형으로 반환한다.
			// 주로 insert, delete, update를 수행할 때 사용한다.
			int insResult = ps.executeUpdate();
			System.out.println(insResult + "개의 행이 삽입되었습니다.");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// 변수 삽입
	public static void ex02() {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Properties p = new Properties();
			p.load(new BufferedReader(new FileReader("db.properties")));
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, p);
			String sql = "INSERT INTO MEMBER_TBL(MEMBER_NO, ID, NAME, ADDRESS, JOIN_DATE)";
			sql += " VALUES(MEMBER_SEQ.NEXTVAL, ?, ?, ?, sysdate)";
			ps = con.prepareStatement(sql);
			String id = "master";
			String name = "운영자";
			String address = "경기";
			// ?로 비워두었던 자리에 데이터(변수값)을 넣어주는 과정이다.
			// 인덱스에 해당하는 숫자는 물음표의 순번이다.
			// setString : 인덱스를 String값으로 지정한다.
			ps.setString(1, id); 		// 1번째 물음표(?) 자리에 id를 넣으시오.
		    ps.setString(2, name);      // 2번째 물음표(?) 자리에 name를 넣으시오.
		    ps.setString(3, address);   // 3번째 물음표(?) 자리에 address를 넣으시오.
		    int insResult = ps.executeUpdate();
		    System.out.println(insResult + "개의 행이 삽입되었습니다.");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void ex03() {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Properties p = new Properties();
			p.load(new BufferedReader(new FileReader("db.properties")));
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, p);
			String sql = "INSERT INTO BOARD_TBL(BOARD_NO, MEMBER_NO, TITLE, CONTENT, CREATE_DATE)";
				  sql += " VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, 1);
			ps.setString(2, "김민서의 하루");
			ps.setString(3, "너무 졸리다");
			int insResult = ps.executeUpdate();
			System.out.println(insResult + "개의 행이 삽입되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) ps.close();
				if(con != null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		ex03();
		
	}

}
