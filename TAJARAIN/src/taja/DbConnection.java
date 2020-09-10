package taja;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DbConnection {
	//싱글톤 생성 시작
	private static DbConnection instance = new DbConnection();

	public static DbConnection getInstance() {
		return instance;
	}

	private DbConnection() {
	};

	// 연결
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 적재 성공");
		} catch (ClassNotFoundException e1) {
			System.out.println("드라이버 적재 실패");
		} // 드라이버 적재

		String url = "jdbc:oracle:thin:@net.yjc.ac.kr:1521:orcl";
		String id = "s1501291";
		String pw = "p1501291";

		try {
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("데이터베이스 연결 성공.");
		} catch (SQLException e) {
			System.out.println("데이터 베이스 연결에 실패하였습니다..");
		}
		return conn;
	}
			
	//랭킹에 점수등록
	public void insert(String 학번, String 이름,String 점수,String 시간) {
		Connection conn = null;
		PreparedStatement pstmt = null;
						
		
		try {
			conn = getConnection();
			String sql="insert into taja values (?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,학번);
			pstmt.setString(2,이름);
			pstmt.setString(3,점수);
			pstmt.setString(4,시간);
			pstmt.executeUpdate();	
			
			System.out.println("taja 테이블에 새로운 레코드를 추가했습니다.");
		} catch (SQLException e) {
			System.out.println("taja 테이블에 새로운 레코드를 실패했습니다.");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		
		
		
	}
	
	public ArrayList<String> rank(){
		ArrayList<String> rankview = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rank = "";
		
		System.out.println("랭크시작");
		try {
			conn = getConnection();
		
			String sql = "select rownum as RANK,score,id,passwd,time from "
					+ "(select id,passwd,score,time from taja where score is not null order by to_number(score) desc) where rownum <=5 ";
			
		
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
		
		
			
			while(rs.next()){
				
				rank = rs.getString("rank")+"★"+rs.getString("id")+"車"+rs.getString("passwd")+"在"+rs.getString("score")+"映"+rs.getString("time");
				
				rankview.add(rank);
				
//				System.out.println(rank);
				
				
				
			}
			
			
			
		} catch (SQLException e) {
			rankview.add("fail");
			
			e.printStackTrace();
			System.out.println("랭킹보기에실패했습니다.");
			return rankview;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return rankview;
		
	}
}