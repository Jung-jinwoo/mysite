package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<GuestbookVo> list = guestbookService.list();
		model.addAttribute("list", list);
		return "guestbook/list";
	}
	
	@RequestMapping("/list/spa")
	public String spa() {
		return "guestbook/list-spa";
	}
	
	@Auth
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(@PathVariable Long no, Model model) {
		model.addAttribute("no", no);
		return "guestbook/delete";
	}
	
	@Auth
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(GuestbookVo guestboookVo) {
		guestbookService.delete(guestboookVo);
		return "redirect:/guestbook/list";
	}
	
	@Auth
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(GuestbookVo guestbookVo) {
		guestbookService.insert(guestbookVo);
		return "redirect:/guestbook/list";
	}
	
	
}
