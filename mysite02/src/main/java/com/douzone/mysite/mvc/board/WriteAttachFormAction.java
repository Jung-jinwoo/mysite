package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.dao.UserDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class WriteAttachFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long userNo = Long.parseLong(request.getParameter("no"));
		Long boardNo = Long.parseLong(request.getParameter("boardNo"));
		
		BoardVo board = new BoardDao().findByNo(userNo, boardNo);
		UserVo user = new UserDao().findByNo(userNo);
		
		request.setAttribute("board", board);
		request.setAttribute("user", user);
		
		MvcUtil.forward("board/writeattach", request, response);

	}

}
