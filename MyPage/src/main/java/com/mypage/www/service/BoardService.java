package com.mypage.www.service;

import java.util.List;

import com.mypage.www.vo.BoardVo;

public interface BoardService {

	public List<BoardVo> selectBoard(); // 게시물 리스트 읽기
}
