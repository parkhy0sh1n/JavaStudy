package ex01_connection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MainClass {

	public static void ex01() {
		
		// 클래스를 로드하는 방법(메모리에 로드한다 : 사용하겠다)
		try {
			// 데이터 베이스와 연결할 드라이버 클래스를 찾아서 로드하는 역할.
			Class.forName("oracle.jdbc.OracleDriver");  // oracle.jdbc.driver.OracleDriver도 가능하다.
			System.out.println("클래스가 로드되었습니다.");
			// 여기서 에러가 있다면 드라이버 로드에 실패한 것이므로 예외 처리.
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public static void ex02() {
		
		// Oracle 접속 정보
		// jdbc 라이브러리 : oracle로 연결 : thin(자바)로 연결 : localhost 내부 ip로 접속 : 포트번호 1521 : 서비스 네임 xe
		String url = "jdbc:oracle:thin:@localhost:1521:xe";		
		String user = "GDJ61";
		String password = "1111";
		
		// Oracle DataBase와 연결할 때 사용하는 Connection 인터페이스 선언
		Connection con = null;
		
		// DriverManager 클래스로부터 Connection 객체를 받아 온다.		
		try {
			// 선언한 con에 생성된 Connection 객체대입.
			// DriverManager.getConnection(연결문자열(jdbc:oracle:thin:@localhost:1521:xe), DB_ID, DB_PW)
			con = DriverManager.getConnection(url, user, password);
			System.out.println("DB에 접속되었습니다.");
			// 여기서 에러가 있다면 Connection 객체 생성에 실패한 것이므로 예외 처리.
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		// 사용이 끝난 Connection 객체는 반드시 닫아야 한다.
		// 사용한 뒤 반납하지 않으면 연결 유지
		try {
			// 객체가 정상적으로 생성되었다면 더이상 con은 null 값을 가지지 않으므로 con.close();로 Connection객체를 닫아준다.
			if(con != null) {
				con.close();
			}
			// 마지막의 예외처리 문장은 어떤 예외로 인해 conn.colse()실행에 문제가 생겼을 때 throw하는 용도로 써준다.
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void ex03() {
		
		BufferedReader reader = null;
		Connection con = null;
		
		try {
			// 프로퍼티 파일을 읽는 문자 입력 스트림 생성하기
			reader = new BufferedReader(new FileReader("db.properties"));
			/* 
				프로퍼티 파일을 읽어서 프로퍼티 객체 생성하기
				Properties 클래스는 Hashtables의 하위 클래스이다.
				Hashtables를 상속 받았기 때문에 Map의 속성 즉, Key와 Value를 갖는다.
				HashMap과 큰 차이가 없지만, Properties 클래스는 파일 입출력을 지원한다.
				key=value 형식으로 작성 된 파일을 key와 value로 나누어 저장할 때 유용하다.
			*/
			Properties properties = new Properties();
			// 메소드의 이름 그대로 파일의 내용을 읽어서 Key-Value의 형태로 분류해서 맵에 보관한다.
			properties.load(reader);
			// 프로터티 객체에 저장된 각 Property 문자열로 읽기
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			// DriverManager로부터 Connection 객체 얻기
			con = DriverManager.getConnection(url, user, password);
			System.out.println("DB에 접속되었습니다.");
		} catch(IOException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
			// 예외처리 없이 드라이버를 로드하고 객체생성이 정상적으로 완료되었다면 finally를 이용하여 꼭 실행되게 한다.
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Connection getConnection() {
		
		Connection con = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Properties properties = new Properties();
			properties.load(new BufferedReader(new FileReader("db.properties")));
			con = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));
		} catch (Exception e) {  // ClassNotFoundException, SQLException, IOException
			e.printStackTrace();
		}
		return con;
	}
	
	public static void main(String[] args) {
		Connection con = getConnection();
		System.out.println("DB에 접속되었습니다.");
	}

}