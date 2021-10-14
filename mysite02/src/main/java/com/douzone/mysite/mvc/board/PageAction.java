package com.douzone.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.PageVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class PageAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int current = Integer.parseInt(request.getParameter("page"));
		int start = Integer.parseInt(request.getParameter("start"));
		int end = Integer.parseInt(request.getParameter("end"));
		
		PageVo page = new PageVo();
		
		if(request.getParameter("page").equals("0")) {
			page.setCurrentno(1);
			
		} else {
			page.setCurrentno((int)current);
		}
		
		if(current > page.getEnd()) {
			page.setStart(start + 1);
			page.setEnd(end + 1);
		}
		
		List<BoardVo> list = new BoardDao().findAllByPage(page.getCurrentno());
		List<BoardVo> totallist = new BoardDao().findAll();
		
		page.setPrev(page.getCurrentno() -1);
		page.setNext(page.getCurrentno() +1);
		page.setTotalpage(Math.ceil(totallist.size() / 5.0));
		page.setStart(start);
		page.setEnd(end);

		request.setAttribute("list", list);
		request.setAttribute("page", page);
		
		MvcUtil.forward("/board/list", request, response);
	}

}
