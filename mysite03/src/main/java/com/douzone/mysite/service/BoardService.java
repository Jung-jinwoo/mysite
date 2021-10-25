package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardRepository br;

	public List<BoardVo> getPageBoard(int currentno) {
		return  br.findAllByPage(currentno);
	}
	
	public List<BoardVo> getFindAll(){
		return br.findAll();
	}
	
	public List<BoardVo> findByKwd(String kwd){
		return br.findByKwd(kwd);
	}
	
	public void insert(BoardVo boardVo) {
		br.insert(boardVo);
	}

	public BoardVo findByNo(Long userNo, Long no) {
		return br.findByNo(userNo, no);
	}
	
	public void insertAttach(BoardVo boardVo) {
		br.insertAttach(boardVo);
	}

	public BoardVo findByUserNo(Long userNo) {
		return br.findByUserNo(userNo);
	}

	public void update(BoardVo boardVo) {
		br.update(boardVo);
	}
	
	public void delete(BoardVo boardVo) {
		br.delete(boardVo);
	}

	public void hit(Long userNo, Long no) {
		br.updateHit(userNo, no);
		
	}
}
