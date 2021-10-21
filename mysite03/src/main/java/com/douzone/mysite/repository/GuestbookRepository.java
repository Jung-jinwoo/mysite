package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.GuestbookRepositoryException;
import com.douzone.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVo> findAll() throws GuestbookRepositoryException {
		
		return sqlSession.selectList("guestbook.findAll");
	}

	public boolean insert(GuestbookVo guestbookvo) {
		
		int count = sqlSession.insert("guestbook.insert", guestbookvo);
		System.out.println(guestbookvo);
		return count == 1;
		
	}
	
	public boolean delete(GuestbookVo guestbookvo) {
		int count = sqlSession.delete("guestbook.delete", guestbookvo);
		return count == 1;
	}

}
