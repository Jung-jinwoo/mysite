package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> findAllByPage(int pageNo){
		List<BoardVo> boardVo = new ArrayList<BoardVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "select b.title, b.contents, b.hit, "
					+ "b.reg_date, b.group_no, b.order_no, b.depth, u.no , u.name, b.no, b.status  "
					+ "from board b, user u "
					+ "where b.user_no = u.no "
					+ "order by b.group_no desc, b.order_no asc "
					+ "limit ?, 5";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, ((pageNo-1) * 5));
			
			// 5. SQL 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String title = rs.getString(1);
				String contents = rs.getString(2);
				Long hit = rs.getLong(3);
				String regDate = rs.getString(4);
				Long groupNo  = rs.getLong(5);
				Long orderNo = rs.getLong(6);
				Long depth = rs.getLong(7);
				Long userNo = rs.getLong(8);
				String userName = rs.getString(9);
				Long boardNo = rs.getLong(10);
				int status = rs.getInt(11);
				
				BoardVo board = new BoardVo();
				board.setTitle(title);
				board.setContents(contents);
				board.setHit(hit);
				board.setRegDate(regDate);
				board.setGroupNo(groupNo);
				board.setOrderNo(orderNo);
				board.setDepth(depth);
				board.setUserNo(userNo);
				board.setUserName(userName);
				board.setNo(boardNo);
				board.setStatus(status);
				
				boardVo.add(board);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
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

		return boardVo;
	}
	
	
	public List<BoardVo> findAll() {
		List<BoardVo> boardVo = new ArrayList<BoardVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "select b.title, b.contents, b.hit, "
					+ "b.reg_date, b.group_no, b.order_no, b.depth, u.no , u.name, b.no, b.status  "
					+ "from board b, user u "
					+ "where b.user_no = u.no "
					+ "order by b.group_no desc, b.order_no asc";
			pstmt = conn.prepareStatement(sql);

			// 5. SQL 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String title = rs.getString(1);
				String contents = rs.getString(2);
				Long hit = rs.getLong(3);
				String regDate = rs.getString(4);
				Long groupNo  = rs.getLong(5);
				Long orderNo = rs.getLong(6);
				Long depth = rs.getLong(7);
				Long userNo = rs.getLong(8);
				String userName = rs.getString(9);
				Long boardNo = rs.getLong(10);
				int status = rs.getInt(11);
				
				BoardVo board = new BoardVo();
				board.setTitle(title);
				board.setContents(contents);
				board.setHit(hit);
				board.setRegDate(regDate);
				board.setGroupNo(groupNo);
				board.setOrderNo(orderNo);
				board.setDepth(depth);
				board.setUserNo(userNo);
				board.setUserName(userName);
				board.setNo(boardNo);
				board.setStatus(status);
				
				boardVo.add(board);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
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

		return boardVo;
	}
	public boolean update(BoardVo boardVo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "update board set title=?, contents=? where user_no=? and no=?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(binding)
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContents());
			pstmt.setLong(3, boardVo.getUserNo());
			pstmt.setLong(4, boardVo.getNo());
			
			
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
	
	public boolean updateHit(Long userNo, Long boardNo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "update board set hit = hit + 1 where user_no=? and no = ?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(binding)
			pstmt.setLong(1, userNo);
			pstmt.setLong(2, boardNo);

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
	
	public BoardVo findByNo(Long no, Long boardNum) {
		BoardVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "select title, contents, hit, reg_date, group_no, order_no, depth, user_no, no, status from board where user_no = ? and no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			pstmt.setLong(2, boardNum);

			// 5. SQL 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String title = rs.getString(1);
				String contents = rs.getString(2);
				Long hit = rs.getLong(3);
				String regDate = rs.getString(4);
				Long groupNo = rs.getLong(5);
				Long orderNo = rs.getLong(6);
				Long depth = rs.getLong(7);
				Long userNo = rs.getLong(8);
				Long boardNo = rs.getLong(9);

				vo = new BoardVo();
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setNo(boardNo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
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

		return vo;
	}
	
	public BoardVo findByUserNo(Long no) {
		BoardVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "select title, contents, hit, reg_date, group_no, order_no, depth, user_no, no, status from board where user_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);

			// 5. SQL 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String title = rs.getString(1);
				String contents = rs.getString(2);
				Long hit = rs.getLong(3);
				String regDate = rs.getString(4);
				Long groupNo = rs.getLong(5);
				Long orderNo = rs.getLong(6);
				Long depth = rs.getLong(7);
				Long userNo = rs.getLong(8);
				Long boardNo = rs.getLong(9);

				vo = new BoardVo();
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setNo(boardNo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
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

		return vo;
	}
	
	public boolean insertAttach(BoardVo boardVo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "insert into board values(null, ?, ?, ?, now(), ?,?,?, ?,?)";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(binding)
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContents());
			pstmt.setLong(3, boardVo.getHit());
			pstmt.setLong(4, boardVo.getGroupNo());
			pstmt.setLong(5, boardVo.getOrderNo());
			pstmt.setLong(6, boardVo.getDepth());
			pstmt.setLong(7, boardVo.getUserNo());
			pstmt.setInt(8, boardVo.getStatus());
			
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

	public boolean insert(BoardVo boardVo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "insert into board values "
					+ "(null,?,?,?,now(), "
					+ "(select g+1 from (select max(group_no) as g from board)as gn ) "
					+ ",?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(binding)
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContents());
			pstmt.setLong(3, boardVo.getHit());
			pstmt.setLong(4, boardVo.getOrderNo());
			pstmt.setLong(5, boardVo.getDepth());
			pstmt.setLong(6, boardVo.getUserNo());
			pstmt.setInt(7, boardVo.getStatus());
			
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
	
	public boolean delete(BoardVo boardVo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "update board set status=? where user_no=? and no=? ";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(binding)
			pstmt.setInt(1, 0);
			pstmt.setLong(2, boardVo.getUserNo());
			pstmt.setLong(3, boardVo.getNo());

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


	public List<BoardVo> findByKwd(String kwd) {
		kwd = "%" + kwd + "%";
		return sqlSession.selectList("board.findByKwd", kwd);
	}
}
