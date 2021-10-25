package com.douzone.mysite.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.service.AdminService;
import com.douzone.mysite.vo.SiteVo;

@Controller
public class MainController {

	@Autowired
	private AdminService adminService;
	
	@RequestMapping({"", "/main"})
	public String index(Model model, SiteVo siteVo) {
		siteVo = adminService.getSite();
		model.addAttribute("siteVo", siteVo);
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping("/msg01")
	public String message01() {
		return "안녕";
	}
	
	@ResponseBody
	@RequestMapping("/msg02")
	public Map message02(HttpServletResponse response) {
		//response.setContentType("application/json: charset:utf-8");
		//response.getWriter().print("{\"message\":"\Hello World\"}");
		
		Map<String, Object> map = new HashMap<>();
		map.put("message", "Hello World");
		
		return map;
		}
}
