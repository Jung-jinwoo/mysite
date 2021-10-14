package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class WriteAttachAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long userNo = Long.parseLong(request.getParameter("userNo"));
		Long boardNo = Long.parseLong(request.getParameter("boardNo"));
		Long groupNo = Long.parseLong(request.getParameter("groupNo"));
		Long orderNo = Long.parseLong(request.getParameter("orderNo"));
		Long depth = Long.parseLong(request.getParameter("depth"));
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		
		
		
		BoardVo boardVo = new BoardVo();
		boardVo.setTitle(title);
		boardVo.setContents(contents);
		boardVo.setUserNo(userNo);
		boardVo.setNo(boardNo);
		boardVo.setGroupNo(groupNo);
		boardVo.setOrderNo(orderNo + 1L);
		boardVo.setDepth(depth + 1L);
		boardVo.setStatus(1);
		
		new BoardDao().insertAttach(boardVo);
		
		MvcUtil.redirect("/mysite02/board", request, response);
	}

}
