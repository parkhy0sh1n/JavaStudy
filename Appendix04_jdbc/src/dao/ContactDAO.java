package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import dto.ContactDTO;

/*
	DAO
	1. Database Access Object
	2. Database에 접근해서 쿼리문을 실행하고 쿼리문의 실행 결과를 받아온다.
	3. 여러 객체가 만들어 지지 않도록 singleton 패턴으로 생성한다.
*/

public class ContactDAO {

	/********** 1. singleton **********/
	private static ContactDAO dao = new ContactDAO();
	// 외부에서 객체 생성되지 않도록 private 처리.
	private ContactDAO() { }
	// 외부에서는 이걸 통해서만 객체 생성 가능.
	public static ContactDAO getInstance() {
		// 이미 존재하는 객체를 계속해서 불러온다.
		return dao;
	}
	
	/********** 2. field **********/
	// ContactDAO 클래스의 메소드들이 공통으로 사용할 요소를 선언한다.
	private Connection con;        // DB 접속
	private PreparedStatement ps;  // 쿼리문 실행
	private ResultSet rs;          // SELECT 결과
	private String sql;            // 쿼리문 자체
	private int res;               // INSERT,UPDATE,DELETE 결과
	
	/********** 3. method **********/
	// CRUD : CREATE(INSERT), READ(SELECT), UPDATE, DELETE
	// CRUD 메소드의 진행 순서 : Connection 얻기 → CRUD 작업 → 사용한 자원 반납
	
	// 공통 메소드 - 1 (Connection 얻기)
	// Driver는 맨 처음 한 번만 호출하면 된다.
	private Connection getConnection() {
		
		Connection con = null;
		
		try {
			// 드라이버 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			/* 
				프로퍼티 파일을 읽어서 프로퍼티 객체 생성하기
				Properties 클래스는 Hashtables의 하위 클래스이다.
				Hashtables를 상속 받았기 때문에 Map의 속성 즉, Key와 Value를 갖는다.
				HashMap과 큰 차이가 없지만, Properties 클래스는 파일 입출력을 지원한다.
				key=value 형식으로 작성 된 파일을 key와 value로 나누어 저장할 때 유용하다.
			 */
			Properties p = new Properties();
			p.load(new BufferedReader(new FileReader("db.properties")));
			// 접속하고 Connection 객체의 참조값 얻어오기(사용하고자 하는 DB에 ID/PW로 접속하여 DB 연동하는 핵심 객체)
			con = DriverManager.getConnection(p.getProperty("url"), p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	// 공통 메소드 - 2 (사용한 자원 반납)
	private void close() {
		try {
			// 사용한 객체를 닫을 때는 가지고 온 순서 역순으로 닫아 준다.
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
		하나의 행을 각 컬럼대로 뽑는 것은 번거롭고 힘들다.
		ContactDTO 객체를 이용해 하나의 데이터(row) 객체화해서 적용한다.
		가장 중요한 부분이다.
	 */
	
	// CRUD 메소드 - 1 (연락처 추가하기)
	// 1. 반환값   : 0(실패) 또는 1(성공)
	// 2. 매개변수 : ContactDTO contact 객체에는 연락처 정보(name, tel, email, address)가 모두 저장되어 있다.
	public int insertContact(ContactDTO contact) {
		
		try {
			// DB 연결
			con = getConnection();
			// 쿼리문 작성
			sql = "INSERT INTO CONTACT_TBL(CONTACT_NO, NAME, TEL, EMAIL, ADDRESS) VALUES(CONTACT_SEQ.NEXTVAL, ?, ?, ?, ?)";
			// SQL문 준비
			ps = con.prepareStatement(sql);
			// 준비된 SQL문의 물음표(?) 마다 값 바인딩
			ps.setString(1, contact.getName());
			ps.setString(2, contact.getTel());
			ps.setString(3, contact.getEmail());
			ps.setString(4, contact.getAddress());
			// DB에 데이터를 업데이트
			// 이떄 자동으로 COMMIT; 된다.
			// INSERT, UPDATE, DELETE를 수행한다.
			// 변화된 row의 갯수를 리턴한다.
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체를 닫아준다.
			close();
		}
		// 결과를 리턴한다.
		return res;
	}
	
	// CRUD 메소드 - 2 (연락처 삭제하기)
	// 1. 반환값   : 0(실패) 또는 1(성공)
	// 2. 매개변수 : int contact_no 변수에는 삭제할 연락처의 고유 번호가 저장되어 있다.
	public int deleteContact(int contact_no) {
		
		try {
			con = getConnection();
			sql = "DELETE FROM CONTACT_TBL WHERE CONTACT_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, contact_no);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return res;
	}
	
	
	// CRUD 메소드 - 3 (이름을 이용한 연락처 조회하기)
	// 1. 반환값   : List<ContactDTO>
	// 2. 매개변수 : String name 변수에는 조회할 연락처의 이름이 저장되어 있다.
	public List<ContactDTO> selectContactsByName(String name) {
		// 조회이기 때문에 객체가 없을 수도 있고, 여러개일 수도 있다. 그래서 그 전에 ArrayList를 선언해서 list안에 객체를 넣어준다.
		List<ContactDTO> contactList = new ArrayList<ContactDTO>();
		
		try {
			con = getConnection();
			sql =  "SELECT CONTACT_NO, NAME, TEL, EMAIL, ADDRESS";
			sql += "  FROM CONTACT_TBL";
			sql += " WHERE NAME = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			// ResultSet 객체에서 필요한 값을 얻어낸다.
			// next() : SELECT된 데이터의 row를 한 줄씩 읽어온다. (읽어올 row가 없다면 리턴하고 반복문은 종료된다.)
			while(rs.next()) {
				ContactDTO contact = new ContactDTO();
				// 데이터를 Contact_no, name, tel, email, address 객체에 담는다.
				contact.setContact_no( rs.getInt("CONTACT_NO") );
				contact.setName( rs.getString("NAME") );
				contact.setTel( rs.getString("TEL") );
				contact.setEmail( rs.getString("EMAIL") );
				contact.setAddress( rs.getString("ADDRESS") );
				contactList.add(contact);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return contactList;
	}
	
	
	// CRUD 메소드 - 4 (연락처 수정하기)
	// 1. 반환값   : 0(실패) 또는 1(성공)
	// 2. 매개변수 : ContactDTO contact 객체에는 연락처 정보(name, tel, email, address)가 모두 저장되어 있다.
	public int updateContact(ContactDTO contact) {
		
		try {
			con = getConnection();
			sql  = "UPDATE CONTACT_TBL";
			sql += "   SET NAME = ?, TEL = ?, EMAIL = ?, ADDRESS = ?";
			sql += " WHERE CONTACT_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, contact.getName());
			ps.setString(2, contact.getTel());
			ps.setString(3, contact.getEmail());
			ps.setString(4, contact.getAddress());
			ps.setInt(5, contact.getContact_no());
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return res;
	}
	
	// CRUD 메소드 - 5 (연락처 No를 이용한 연락처 조회하기)
	// 1. 반환값   : ContactDTO
	// 2. 매개변수 : int contact_no 변수에는 조회할 연락처의 고유 번호가 저장되어 있다.
	public ContactDTO selectContactByNo(int contact_no) {
		
		ContactDTO contact = null;
		
		try {
			con = getConnection();
			sql  = "SELECT CONTACT_NO, NAME, TEL, EMAIL, ADDRESS";
			sql += "  FROM CONTACT_TBL";
			sql += " WHERE CONTACT_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, contact_no);
			rs = ps.executeQuery();
			if(rs.next()) {
				contact = new ContactDTO();
				contact.setContact_no(contact_no);
				contact.setName( rs.getString(2) );
				contact.setTel( rs.getString(3) );
				contact.setEmail( rs.getString(4) );
				contact.setAddress( rs.getString(5) );
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return contact;
	}
	
	// CRUD 메소드 - 6 (전체 연락처 조회하기)
		// 1. 반환값   : List<ContactDTO>
		// 2. 매개변수 : 없다.
		public List<ContactDTO> selectAllContacts() {
			// 조회이기 때문에 객체가 없을 수도 있고, 여러개일 수도 있다. 그래서 그 전에 ArrayList를 선언해서 list안에 객체를 넣어준다.
			List<ContactDTO> contactList = new ArrayList<ContactDTO>();
			try {
				con = getConnection();
				sql =  "SELECT CONTACT_NO, NAME, TEL, EMAIL, ADDRESS";
				sql += "  FROM CONTACT_TBL";
				sql += " ORDER BY CONTACT_NO DESC";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()) {
					ContactDTO contact = new ContactDTO();
					contact.setContact_no( rs.getInt("CONTACT_NO") );
					contact.setName( rs.getString("NAME") );
					contact.setTel( rs.getString("TEL") );
					contact.setEmail( rs.getString("EMAIL") );
					contact.setAddress( rs.getString("ADDRESS") );
					contactList.add(contact);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close();
			}
			return contactList;
		}
	}
