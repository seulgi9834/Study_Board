package com.mypage.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mypage.www.service.BoardService;

@Controller
@RequestMapping(value="board")
public class BoardController {

	@Autowired
	BoardService service;

	/**
	 * 게시판 리스트 읽기
	 * @param model
	 * @return
	 */
	@RequestMapping(value="")
	String boardList(Model model) {
		model.addAttribute("list", service.selectBoard());
		return "board/list";
	}
}
