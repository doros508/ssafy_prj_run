package com.mrt.mvc.model.dto;

public class Board {
	private int boardNo;
	private String boardTitle;
	private String boardUserNickname;
	private String boardContent;
	private String boardRegDate;
	private int boardViewCnt;
	private int userNo;
	private BoardFile boardFile;
	
	public Board() {
		
	}

	public Board(int boardNo, String boardTitle, String boardUserNickname, String boardContent, String boardRegDate,
			int boardViewCnt, int userNo, BoardFile boardFile) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardUserNickname = boardUserNickname;
		this.boardContent = boardContent;
		this.boardRegDate = boardRegDate;
		this.boardViewCnt = boardViewCnt;
		this.userNo = userNo;
		this.boardFile = boardFile;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardUserNickname() {
		return boardUserNickname;
	}

	public void setBoardUserNickname(String boardUserNickname) {
		this.boardUserNickname = boardUserNickname;
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

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public BoardFile getBoardFile() {
		return boardFile;
	}

	public void setBoardFile(BoardFile boardFile) {
		this.boardFile = boardFile;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardUserNickname=" + boardUserNickname
				+ ", boardContent=" + boardContent + ", boardRegDate=" + boardRegDate + ", boardViewCnt=" + boardViewCnt
				+ ", userNo=" + userNo + ", boardFile=" + boardFile + "]";
	};
	
	
}
