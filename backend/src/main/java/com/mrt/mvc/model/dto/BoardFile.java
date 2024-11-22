package com.mrt.mvc.model.dto;

public class BoardFile {
	private String fileNo; // UUID + 파일 원본이름
	private String oriName; // 파일 원본 이름
	private String path; // 파일 저장 경로(업로드 날짜로 폴더링 할 예정)
	private String systemName; // 시스템상 파일 이름
	private int boardNo; // 게시글 번호
	public BoardFile() {
		
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
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	@Override
	public String toString() {
		return "BoardFile [fileNo=" + fileNo + ", oriName=" + oriName + ", path=" + path + ", systemName=" + systemName
				+ ", boardNo=" + boardNo + "]";
	}
	
	
}
