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

	/**
	 * 게시물 읽기
	 */
	@Override
	public BoardVo selectBoardReader(BoardVo vo) {
		return dao.selectBoardReader(vo);
	}

	/**
	 * 게시물 수정
	 */
	@Override
	public int modifyBoard(BoardVo vo) {
		return dao.modifyBoard(vo);
	}

	/**
	 * 게시물 삭제
	 */
	@Override
	public int deleteBoard(BoardVo vo) {
		return dao.deleteBoard(vo);
	}

	/**
	 * 게시물 카운트
	 */
	@Override
	public int countBoard() {
		return dao.countBoard();
	}

	/**
	 * 게시물 페이지네이션
	 */
	@Override
	public List pageBoard(int displayPost, int postNum) {
		return dao.pageBoard(displayPost, postNum);
	}

	/**
	 * 게시물 검색
	 */
	@Override
	public List<BoardVo> searchBoard(int displayPost, int postNum, String searchType, String keyword){
		return dao.searchBoard(displayPost, postNum, searchType, keyword);
	}

	/**
	 * 검색된 게시물의 갯수
	 */
	@Override
	public int searchCount(String searchType, String keyword) {
		return dao.searchCount(searchType, keyword);
	}
}
