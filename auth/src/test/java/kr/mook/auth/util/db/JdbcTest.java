package kr.mook.auth.util.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * JDBC 연결 테스트<br/>
 * - JDBC가 정상적으로 연결 되었는지 테스트<br/>
 * - 정상적으로 연결되었을 경우 현재 날짜를 비교하여 쿼리가 정상적으로 실행되는지 테스트<br/>
 * - application.properties의 데이터베이스 정보를 가져오기 위해 @SpringBootTest를 통해 테스트 진행 
 * 
 * @since 2025. 9. 27.
 * @version 0.1
 * @author Inmook, Jeong
 */
@SpringBootTest
public class JdbcTest {
	
	/**
	 * MySQL JDBC Driver Class Name
	 */
	@Value("${spring.datasource.driver-class-name}")
	private String className;
	
	/**
	 * MySQL Database URL
	 */
	@Value("${spring.datasource.url}")
	private String url;
	
	/**
	 * MySQL User Name
	 */
	@Value("${spring.datasource.username}")
	private String userName;
	
	/**
	 * MySQL User Password
	 */
	@Value("${spring.datasource.password}")
	private String password;
	
	@Test
	void testJdbcConnection() {
		try {
			// MySQL Driver Load
			Class.forName(className);
			
			// 데이터베이스 연결
			Connection conn = DriverManager.getConnection(url, userName, password);
			
			// Connection 객체가 Null이 아닌지 확인
			assertNotNull(conn);
			
			// MySQL을 통해 현재 날짜(형식 : yyyyMMdd)를 조회하도록 쿼리 실행 
			PreparedStatement pstmt = conn.prepareStatement("SELECT DATE_FORMAT(NOW(), '%Y%m%d') AS NOW FROM DUAL");
			ResultSet rs = pstmt.executeQuery();
			
			// 쿼리 결과 가져오기
			String result = "";
			while(rs.next()) {
				result = rs.getString("NOW");
			}
			
			// Java를 통해 현재 날짜를 조회
			String now = new SimpleDateFormat("yyyyMMdd").format(new Date());
			
			// MySQL의 현재 날짜와 Java의 현재 날짜가 동일한지 확인
			assertEquals(result, now);
			
			// Resources Close
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
