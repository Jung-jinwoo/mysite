package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.AdminService;
import com.douzone.mysite.vo.SiteVo;

@Auth(role="ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@RequestMapping("/main/{no}")
	public String main(SiteVo siteVo, Model model) {
		siteVo = adminService.findByNo(siteVo.getNo());
		model.addAttribute("siteVo", siteVo);
		
		return "admin/main";
	}
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
	
	@RequestMapping(value="/main/update", method=RequestMethod.POST)
	public String mainUpdate(SiteVo siteVo,
							@RequestParam(value="file") MultipartFile file) {
		adminService.restore(siteVo, file);
		return "redirect:/";
	}
}