package ex04_update;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class MainClass {

	public static void main(String[] args) {
		// id가 admin인 회원이 작성한 게시들의 title 앞에 [new]를 추가하시오.
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Properties p = new Properties();
			p.load(new BufferedReader(new FileReader("db.properties")));
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, p);
			String sql = "update board_tbl set title = '[new]' || title";
			sql += " where board_no = (select member_no from member_tbl where id = ?)";
			ps = con.prepareStatement(sql);
			String id = "master";
			ps.setString(1, id);
			int insResult = ps.executeUpdate();
			System.out.println(insResult + "개의 행이 수정되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
