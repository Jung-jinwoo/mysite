package com.douzone.mysite.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.PageVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService bs;
	
	@Autowired
	private UserService us;
	
	@RequestMapping(value="/page/{pageno}&{start}&{end}", method=RequestMethod.GET)
	public String page(	@PathVariable("pageno") String pageno,
						@PathVariable("start") String start,
						@PathVariable("end") String end,
						@AuthUser UserVo authUser, PageVo pageVo, Model model) {
		
		int pageNo = Integer.parseInt(pageno);
		int startNo = Integer.parseInt(start);
		int endNo = Integer.parseInt(end);
		
		if(pageNo < startNo) {
			pageVo.setStart(startNo -1);
			pageVo.setEnd(endNo -1);
			
		} else if(pageNo > endNo) {
			pageVo.setStart(startNo +1);
			pageVo.setEnd(endNo +1);
		}
		pageVo.setTotalpage(Math.ceil(bs.getFindAll().size() / 5.0));
		pageVo.setPrev(pageNo -1);
		pageVo.setNext(pageNo +1);
		pageVo.setCurrentno(pageNo);
		
		List<BoardVo> listAll = bs.getFindAll();
		List<BoardVo> list = bs.getPageBoard(pageNo);
		Map<String,Object> map = new HashMap<>();
		map.put("list", list);
		map.put("page", pageVo);
		map.put("listAll", listAll);
		model.addAllAttributes(map);
		
		return "board/list";
	}
	
	@RequestMapping("/search")
	public String search(PageVo pageVo, Model model) {
		List<BoardVo> list = bs.findByKwd(pageVo.getKwd());
		Map<String,Object> map = new HashMap<>();
		map.put("list", list);
		map.put("page", pageVo);
		model.addAllAttributes(map);
		return "board/list";
	}
	
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(@AuthUser UserVo authUser, BoardVo boardVo) {			
		bs.insert(boardVo);
		
		return "redirect:/board/page/1&1&5";
	}
	
	@Auth
	@RequestMapping(value="/writeattach/{userNo}&{boardNo}", method=RequestMethod.GET)
	public String writeattach(@PathVariable("userNo") Long userNo,
								@PathVariable("boardNo") Long no,
								Model model) {
		BoardVo boardVo = bs.findByNo(userNo, no);
		UserVo userVo = us.findByNo(userNo);
		
		Map<String,Object> map = new HashMap<>();
		map.put("board", boardVo);
		map.put("user", userVo);
		model.addAllAttributes(map);
		
		return "board/writeattach";
	}
	
	@RequestMapping(value="/writeattach", method=RequestMethod.POST)
	public String writeattach(BoardVo boardVo) {
		
		bs.insertAttach(boardVo);
		return "redirect:/board/page/1&1&5";
	}
	
	
	@RequestMapping(value="/view/{userNo}&{no}&{currentno}&{start}&{end}", method=RequestMethod.GET)
	public String view(BoardVo boardVo, PageVo pageVo,
						@PathVariable("userNo") Long userNo,
						@PathVariable("no") Long no,
						Model model) {
		bs.hit(userNo,no);
		
		BoardVo board = bs.findByNo(userNo,no);
		Map<String,Object> map = new HashMap<>();
		map.put("board", board);
		map.put("page", pageVo);
		model.addAllAttributes(map);
		
		return "board/view";
	}
	
	@Auth
	@RequestMapping(value="/update/{userNo}&{currentno}&{start}&{end}", method=RequestMethod.GET)
	public String update(BoardVo boardVo, PageVo pageVo, @AuthUser UserVo authUser, Model model) {
		
		boardVo = bs.findByUserNo(boardVo.getUserNo());
		Map<String,Object> map = new HashMap<>();
		map.put("board", boardVo);
		map.put("page", pageVo);
		model.addAllAttributes(map);
		
		return "board/update";
	}
	
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@AuthUser UserVo authUser, BoardVo boardVo, PageVo pageVo) {
		
		bs.update(boardVo);
		System.out.println(boardVo);
		return "redirect:/board/view/"+boardVo.getUserNo() + 
				"&" + boardVo.getNo() + 
				"&" + pageVo.getCurrentno() +
				"&" + pageVo.getStart() +
				"&" + pageVo.getEnd();
	}
	
	@Auth
	@RequestMapping(value="/delete/{userNo}&{no}&{currentno}&{start}&{end}", method=RequestMethod.GET)
	public String delete(@AuthUser UserVo authUser,BoardVo boardVo, PageVo pageVo) {
		bs.delete(boardVo);
		
		return "redirect:/board/page/" + pageVo.getCurrentno() + 
				"&" + pageVo.getStart() + 
				"&" + pageVo.getEnd();
	}
	
}
