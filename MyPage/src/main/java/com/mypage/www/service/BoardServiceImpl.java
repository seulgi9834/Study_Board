package com.mypage.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypage.www.dao.BoardDao;
import com.mypage.www.vo.BoardVo;

@Service("BoardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao dao;

	/**
	 * 게시물 리스트 읽기
	 */
	@Override
	public List<BoardVo> selectBoard(){
		return dao.selectBoard();
	}

	/**
	 * 게시물 쓰기
	 */
	@Override
	public int insertBoard(BoardVo vo) {
		return dao.insertBoard(vo);
	}
}
