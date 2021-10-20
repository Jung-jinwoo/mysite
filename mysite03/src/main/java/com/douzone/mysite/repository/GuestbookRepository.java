package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.GuestbookRepositoryException;
import com.douzone.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
	public List<GuestbookVo> findAll() throws GuestbookRepositoryException {
		
//		StopWatch sw = new StopWatch();
//		sw.start();
		
		List<GuestbookVo> result = new ArrayList<GuestbookVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "select no, name, message, date_format(reg_date, '%Y/%m/%d %H:%i:%s') "
					+ "from guestbook "
					+ "order by reg_date";
			pstmt = conn.prepareStatement(sql);

			// 5. SQL 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String message = rs.getString(3);
				String regDate = rs.getString(4);

				GuestbookVo book = new GuestbookVo();
				book.setNo(no);
				book.setName(name);
				book.setMessage(message);
				book.setRegDate(regDate);

				result.add(book);
			}

		} catch (SQLException e) {
			throw new GuestbookRepositoryException(e.toString());
		} finally {
			// clean up
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

//		sw.stop();
//		Long totalTime = sw.getTotalTimeMillis();
//		System.out.println("[Excution Time][GuestbookRepository.findAll]" + totalTime + "millis");
		
		return result;
	}

	public boolean insert(GuestbookVo guestbookvo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "insert into guestbook values(null, ?, ?, ?, now())";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(binding)
			pstmt.setString(1, guestbookvo.getName());
			pstmt.setString(2, guestbookvo.getPassword());
			pstmt.setString(3, guestbookvo.getMessage());

			// 5. SQL 실행
			int count = pstmt.executeUpdate();

			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// clean up
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public boolean delete(GuestbookVo guestbookVo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "delete from guestbook "
					+ "where no=? "
					+ "and password=? ";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(binding)
			pstmt.setLong(1, guestbookVo.getNo());
			pstmt.setString(2, guestbookVo.getPassword());
			

			// 5. SQL 실행
			int count = pstmt.executeUpdate();

			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// clean up
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3307/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return conn;
	}
}
