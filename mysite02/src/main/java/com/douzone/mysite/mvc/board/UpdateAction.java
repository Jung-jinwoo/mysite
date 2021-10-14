package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		Long userNo = Long.parseLong(request.getParameter("userNo"));
		Long boardNo = Long.parseLong(request.getParameter("boardNo"));
		
		BoardVo board = new BoardVo();
		board.setTitle(title);
		board.setContents(contents);
		board.setUserNo(userNo);
		board.setNo(boardNo);
		
		new BoardDao().update(board);
		
		MvcUtil.redirect(request.getContextPath() + "/board", request, response);
	}

}
