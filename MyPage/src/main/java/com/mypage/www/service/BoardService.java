package com.mypage.www.service;

import java.util.List;

import com.mypage.www.vo.BoardVo;

public interface BoardService {

	public List<BoardVo> selectBoard(); // 게시물 리스트 읽기

	public int insertBoard(BoardVo vo); // 게시물 작성

	public BoardVo selectBoardReader(BoardVo vo); // 게시물 읽기

	public int modifyBoard(BoardVo vo); // 게시물 수정

	public int deleteBoard(BoardVo vo); // 게시물 삭제
}
