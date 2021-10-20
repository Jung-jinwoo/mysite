package com.douzone.mysite.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.PageVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService bs;
	
	@RequestMapping(value="/page/{pageno}/{start}/{end}", method=RequestMethod.GET)
	
	public String page(	@RequestParam(value="pageno", required=true, defaultValue="") int pageno,
						@RequestParam(value="start", required=true, defaultValue="") int start,
						@RequestParam(value="end", required=true, defaultValue="") int end,
						HttpSession session, PageVo pageVo, Model model) {
		
		
		if(pageno < start) {
			pageVo.setStart(start -1);
			pageVo.setEnd(end -1);
		} else if(pageno > end) {
			pageVo.setStart(start +1);
			pageVo.setEnd(end +1);
		}
		pageVo.setTotalpage(Math.ceil(bs.getFindAll().size() / 5.0));
		pageVo.setPrev(pageVo.getCurrentno() -1);
		pageVo.setNext(pageVo.getCurrentno() +1);
		
		List<BoardVo> list = bs.getPageBoard(pageno);
		Map<String,Object> map = new HashMap<>();
		map.put("list", list);
		map.put("page", pageVo);
		model.addAllAttributes(map);
		
		System.out.println(pageno);
		System.out.println(list);
		
		return "board/list";
	}
	
	
}
