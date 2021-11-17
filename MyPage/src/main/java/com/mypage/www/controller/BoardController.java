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

	/**
	 * 원하는 게시물 읽기
	 * @param boardNo
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="/read")
	String read(@RequestParam("boardNo") String boardNo, Model model, BoardVo vo) {
		model.addAttribute("list",service.selectBoardReader(vo));
		return "board/read";
	}

	/**
	 * 게시물 수정 페이지
	 * @return
	 */
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	String boardModify(@RequestParam("boardNo") String boardNo, BoardVo vo, Model model) {
		model.addAttribute("list", service.selectBoardReader(vo));
		return "board/modify";
	}

	/**
	 * 수정된 게시물 갱신
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="/update", method=RequestMethod.POST)
	String boardUpdate(BoardVo vo) {
		service.modifyBoard(vo);
		return "redirect:/board";
	}

	/**
	 * 게시물 삭제
	 * @param boardNo
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	String boardDelete(@RequestParam("boardNo") String boardNo, BoardVo vo) {
		service.deleteBoard(vo);
		return "redirect:/board";
	}

}
