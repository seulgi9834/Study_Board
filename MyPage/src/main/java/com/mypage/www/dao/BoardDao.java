package com.mypage.www.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mypage.www.vo.BoardVo;

@Repository("BoardDao")
public class BoardDao {

	@Autowired
	SqlSession sqlSession;

	/**
	 * 게시글 리스트 읽기
	 * @return
	 */
	public List <BoardVo> selectBoard(){
		return sqlSession.selectList("board.selectBoard");
	}

	/**
	 * 게시글 작성
	 * @param vo
	 * @return
	 */
	public int insertBoard(BoardVo vo) {
		return sqlSession.insert("board.insertBoard", vo);
	}

	/**
	 * 게시글 읽기
	 * @param vo
	 * @return
	 */
	public BoardVo selectBoardReader(BoardVo vo) {
		return sqlSession.selectOne("board.selectBoardReader",vo);
	}

	/**
	 * 게시물 수정
	 * @param vo
	 * @return
	 */
	public int modifyBoard(BoardVo vo) {
		return sqlSession.update("board.modifyBoard",vo);
	}

	/**
	 * 게시물 삭제
	 * @param vo
	 * @return
	 */
	public int deleteBoard(BoardVo vo) {
		return sqlSession.delete("board.deleteBoard",vo);
	}
}
