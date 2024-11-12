package com.mrt.mvc.model.dto;

public class Board {
	private int boardNo;
	private BoardFile boardFile;
	private String boardTitle;
	private String boardContent;
	private String boardRegDate;
	private int boardViewCnt;
	private int boardUserNo;
	
	public Board() {
		
	};
	
	public Board(int boardNo, BoardFile boardFile, String boardTitle, String boardContent, String boardRegDate,
			int boardViewCnt, int boardUserNo) {
		this.boardNo = boardNo;
		this.boardFile = boardFile;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardRegDate = boardRegDate;
		this.boardViewCnt = boardViewCnt;
		this.boardUserNo = boardUserNo;
	}

	public int getBoardNo() {
		return boardNo;
	} 

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public BoardFile getBoardFile() {
		return boardFile;
	}

	public void setBoardFile(BoardFile boardFile) {
		this.boardFile = boardFile;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardRegDate() {
		return boardRegDate;
	}

	public void setBoardRegDate(String boardRegDate) {
		this.boardRegDate = boardRegDate;
	}

	public int getBoardViewCnt() {
		return boardViewCnt;
	}

	public void setBoardViewCnt(int boardViewCnt) {
		this.boardViewCnt = boardViewCnt;
	}

	public int getBoardUserNo() {
		return boardUserNo;
	}

	public void setBoardUserNo(int boardUserNo) {
		this.boardUserNo = boardUserNo;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardFile=" + boardFile + ", boardTitle=" + boardTitle
				+ ", boardContent=" + boardContent + ", boardRegDate=" + boardRegDate + ", boardViewCnt="
				+ boardViewCnt + ", boardUserNo=" + boardUserNo + "]";
	}
	
	
}
