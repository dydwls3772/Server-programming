package com.inhatc.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
//	@GetMapping("/board")
//	@ResponseBody
//	public String board() {
//	System.out.println("board");
//	return "board";
//	}
	
	@GetMapping("/")
	public String root() {
		return "redirect:/question/list";
	}
}
