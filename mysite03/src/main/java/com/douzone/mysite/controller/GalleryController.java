package com.douzone.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.GalleryService;
import com.douzone.mysite.vo.GalleryVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("")
	public String index(HttpSession session, Model model) {
		UserVo user = (UserVo)session.getAttribute("authUser");
		if(user == null) {
			return "redirect:/";
		}
		List<GalleryVo> list = galleryService.findAll();
		model.addAttribute("list", list);
		
		return "gallery/index";
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(@RequestParam(value="comments", required=true, defaultValue="") String comments,
						@RequestParam(value="file") MultipartFile file) {
			
		String url = galleryService.restore(file);
		GalleryVo vo = new GalleryVo();
		vo.setUrl(url);
		vo.setComments(comments);
		
		galleryService.insert(vo);
		
		return "redirect:/gallery";
	}
	
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable Long no) {
		galleryService.delete(no);
		
		return "redirect:/gallery";
	}
	
	
}
