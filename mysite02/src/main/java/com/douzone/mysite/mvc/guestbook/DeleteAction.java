package com.douzone.mysite.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.GuestbookDao;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.parseLong(request.getParameter("no"));
		String password = request.getParameter("password");

		GuestbookVo guestbookVo = new GuestbookVo();
		guestbookVo.setNo(no);
		guestbookVo.setPassword(password);
		new GuestbookDao().delete(guestbookVo);

		MvcUtil.redirect("guestbook", request, response);
	}

}
