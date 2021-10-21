package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.UserRepositoryException;
import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;

	public boolean update(UserVo userVo) {
		int count = sqlSession.update("user.update", userVo);
		return count == 1;
	}
	
	public UserVo findByNo(Long no) {
		return sqlSession.selectOne("user.findByNo", no);
	}
	
	public UserVo findByEmailAndPassword(String email, String password) throws UserRepositoryException {
		Map<String, String> map = new HashMap<>();
		map.put("e", email);
		map.put("p", password);
		
		return sqlSession.selectOne("user.findByEmailAndPassword", map);
	}

	public boolean insert(UserVo userVo) throws UserRepositoryException {
		int count = sqlSession.insert("user.insert", userVo);
		return count == 1;
	}
	
	public boolean delete(UserVo userVo) {
		int count = sqlSession.delete("user.delete", userVo);
		return count == 1;
	}

}
