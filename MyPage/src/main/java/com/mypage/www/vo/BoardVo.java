package com.mypage.www.vo;

import java.util.Date;

public class BoardVo {

	private Integer boardNo; // 게시물 번호
	private String boardTitle; // 게시물 제목
	private String boardWriter; // 게시물 저자
	private Date regDt; // 작성 날짜
	private Integer boardViews; // 게시물 조회수
	private String boardContent; // 게시물 내용
	private String fileName; // 게시물 첨부파일명
	private String thumFileName; // 썸네일 첨부파일 이름

	public String getThumFileName() {
		return thumFileName;
	}
	public void setThumFileName(String thumFileName) {
		this.thumFileName = thumFileName;
	}
	public Integer getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(Integer boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public Integer getBoardViews() {
		return boardViews;
	}
	public void setBoardViews(Integer boardViews) {
		this.boardViews = boardViews;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public String toString() {
		return "BoardVo [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardWriter=" + boardWriter
				+ ", regDt=" + regDt + ", boardViews=" + boardViews + ", boardContent=" + boardContent + ", fileName="
				+ fileName + "]";
	}



}
