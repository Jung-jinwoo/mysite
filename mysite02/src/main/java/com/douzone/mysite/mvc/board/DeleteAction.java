package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long userNo = Long.parseLong(request.getParameter("userNo"));
		Long boardNo = Long.parseLong(request.getParameter("boardNo"));
		
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		Long no = authUser.getNo();
		
		if(no != userNo) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		
		BoardVo board = new BoardVo();
		board.setNo(boardNo);
		board.setUserNo(userNo);
		board.setStatus(0);
		new BoardDao().delete(board);
		
		MvcUtil.redirect(request.getContextPath() + "/board", request, response);
	}

}
