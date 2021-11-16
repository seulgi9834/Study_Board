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
}
