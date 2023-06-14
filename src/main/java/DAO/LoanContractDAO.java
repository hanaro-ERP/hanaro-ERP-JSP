package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanContractDAO {
    private Connection conn;	//DB 커넥션 연결 객체
    private static final String USERNAME = "hanaro_yk";	// DBMS접속 시 아이디
    private static final String PASSWORD = "hanaro_yk123";	// DBMS접속 시 비밀번호
    private static final String URL 
    = "jdbc:mysql://localhost:3306/hanaro_loan_system?serverTimezone=UTC";	// DBMS접속할 db명
    
    public LoanContractDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } 
        catch (Exception e) {
            try {
                conn.close();
            } catch (SQLException e1) {    }
        }
    }
}
