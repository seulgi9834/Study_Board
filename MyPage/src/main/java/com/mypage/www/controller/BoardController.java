package com.mypage.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mypage.www.service.BoardService;
import com.mypage.www.vo.BoardVo;

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

	/**
	 * 게시글 작성 페이지
	 * @return
	 */
	@RequestMapping(value="write")
	String boardWrite() {
		return "board/write";
	}

	/**
	 * 작성된 게시글 등록
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="regist", method=RequestMethod.POST)
	String boardRegist(BoardVo vo) {
		int res = service.insertBoard(vo);
		return "redirect:/board";
	}

	@RequestMapping(value="/read")
	String read(@RequestParam("boardNo") String boardNo, Model model, BoardVo vo) {
		model.addAttribute("list",service.selectBoardReader(vo));
		return "board/read";
	}
}
