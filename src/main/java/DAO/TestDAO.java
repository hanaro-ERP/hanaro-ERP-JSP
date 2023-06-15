package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDAO {
	private Connection conn;
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "rkddkwl123!";
    private static final String URL = "jdbc:mysql://hanaroerpprojectinstance.cueixcjf2n4d.ap-northeast-2.rds.amazonaws.com:3306/hanaroErpDatabase";//DBMS�젒�냽�븷 db紐�
    
    public TestDAO() {
        try {
            System.out.println("DB 연결 시작");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("뭐야");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("DB 연결 완료");
        } catch (Exception e) {
            System.out.println("연결 오류 " + e);
            try {
                conn.close();
            } catch (SQLException e1) {    }
        }        
    }
    
    public void selectOne() {
        String sql = "select * from test";
        PreparedStatement pstmt = null;
        try {
        	System.out.println("hello");
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                System.out.println("id: " + rs.getInt("id"));
            }
        } catch (Exception e) {
            System.out.println("select 오류" + e);
        }    finally {
            try {
                if(pstmt!=null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {}
        }
    }    
}
