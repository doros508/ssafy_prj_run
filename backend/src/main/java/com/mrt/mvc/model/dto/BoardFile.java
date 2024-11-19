package com.mrt.mvc.model.dto;

public class BoardFile {
	private String fileNo;
	private String oriName;
	private int boardNo;
	public BoardFile() {
		
	}
	public BoardFile(String fileNo, String oriName, int boardNo) {
		super();
		this.fileNo = fileNo;
		this.oriName = oriName;
		this.boardNo = boardNo;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public String getOriName() {
		return oriName;
	}
	public void setOriName(String oriName) {
		this.oriName = oriName;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	@Override
	public String toString() {
		return "BoardFile [fileNo=" + fileNo + ", oriName=" + oriName + ", boardNo=" + boardNo + "]";
	}
	
}
