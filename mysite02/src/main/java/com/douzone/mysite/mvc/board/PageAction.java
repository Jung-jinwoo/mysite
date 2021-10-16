package com.douzone.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.PageVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class PageAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Validation check
		HttpSession session = request.getSession();
		if(session.getAttribute("authUser") == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		
		
		int current = Integer.parseInt(request.getParameter("page"));
		int start = Integer.parseInt(request.getParameter("start"));
		int end = Integer.parseInt(request.getParameter("end"));
		String kwd = request.getParameter("kwd");

		PageVo page = new PageVo();
		
		if(request.getParameter("page").equals("0")) {
			page.setCurrentno(1);
			
		} else {
			page.setCurrentno((int)current);
		}
		
		if(page.getCurrentno() < start) {
			start -= 1;
			end -= 1;
		} else if(page.getCurrentno() > end) {
			start += 1;
			end += 1;
		}
		
		if(kwd == null) {
			kwd = "";
		} 
		
		List<BoardVo> list = new BoardDao().findAllByPage(page.getCurrentno(), kwd);
		
		page.setPrev(page.getCurrentno() -1);
		page.setNext(page.getCurrentno() +1);
		page.setStart(start);
		page.setEnd(end);
		page.setTotalpage(Math.ceil(new BoardDao().findAll(kwd).size() / 5.0));
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		
		MvcUtil.forward("/board/list", request, response);
	}

}
