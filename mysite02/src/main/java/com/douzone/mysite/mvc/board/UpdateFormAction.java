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

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		Long no = authUser.getNo();
		
		int current = Integer.parseInt(request.getParameter("page"));
		int start = Integer.parseInt(request.getParameter("start"));
		int end = Integer.parseInt(request.getParameter("end"));
		
		Long userNo = Long.parseLong(request.getParameter("userNo"));
		
		if(userNo != no) {
			MvcUtil.redirect(request.getContextPath() + "/board?a=page&page=" + current + "&start=" + start + "&end=" + end, request, response);
			return;
		}
		
		BoardVo board = new BoardDao().findByUserNo(userNo);
		request.setAttribute("board", board);
		MvcUtil.forward("board/modify", request, response);

	}

}
