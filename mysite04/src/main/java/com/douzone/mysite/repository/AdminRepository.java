package com.douzone.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.SiteVo;

@Repository
public class AdminRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public SiteVo findOne() {
		
		return sqlSession.selectOne("admin.findOne");
	}

	public SiteVo findByNo(Long no) {
		
		return sqlSession.selectOne("admin.findByNo", no);
	}

	public boolean update(SiteVo siteVo) {
		int count = sqlSession.update("admin.update", siteVo);
		return count == 1;
		
	}

	public SiteVo getSite() {
		return sqlSession.selectOne("admin.getSite");
	}

}
