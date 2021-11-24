package com.mypage.www.dao;

import java.util.HashMap;
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

	/**
	 * 게시물 카운트
	 * @return
	 */
	public int countBoard() {
		return sqlSession.selectOne("board.countBoard");
	}

	/**
	 * 게시물 페이지네이션
	 * @param displayPost
	 * @param postNum
	 * @return
	 */
	public List pageBoard(int displayPost, int postNum) {
		HashMap data = new HashMap();

		data.put("displayPost", displayPost);
		data.put("postNum", postNum);

		return sqlSession.selectList("board.pageBoard",data);
	}

	/**
	 * 게시물 검색
	 * @param displayPost
	 * @param postNum
	 * @param searchType
	 * @param keyword
	 * @return
	 */
	public List<BoardVo> searchBoard(int displayPost, int postNum, String searchType, String keyword){
		HashMap<String, Object> data = new HashMap<String, Object>();

		data.put("displayPost", displayPost);
		data.put("postNum", postNum);

		data.put("searchType", searchType);
		data.put("keyword", keyword);

		return sqlSession.selectList("board.searchBoard",data);
	}

	/**
	 * 검색된 게시물의 갯수 출력
	 * @param searchType
	 * @param keyword
	 * @return
	 */
	public int searchCount(String searchType, String keyword) {
		HashMap data = new HashMap();

		data.put("searchType", searchType);
		data.put("keyword", keyword);

		return sqlSession.selectOne("board.searchCount", data);
	}
}
