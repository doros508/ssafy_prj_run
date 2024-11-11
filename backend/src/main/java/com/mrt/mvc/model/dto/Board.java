package com.mrt.mvc.model.dto;

public class Board {
	private int cBoardNo;
	private String cBoardFileNo;
	private String cBoardTitle;
	private String cBoardContent;
	private String cBoardRegDate;
	private int cBoardViewCnt;
	private int cBoardUserNo;
	
	public Board() {
		
	};
	
	public Board(int cBoardNo, String cBoardFileNo, String cBoardTitle, String cBoardContent, String cBoardRegDate,
			int cBoardViewCnt, int cBoardUserNo) {
		this.cBoardNo = cBoardNo;
		this.cBoardFileNo = cBoardFileNo;
		this.cBoardTitle = cBoardTitle;
		this.cBoardContent = cBoardContent;
		this.cBoardRegDate = cBoardRegDate;
		this.cBoardViewCnt = cBoardViewCnt;
		this.cBoardUserNo = cBoardUserNo;
	}

	public int getcBoardNo() {
		return cBoardNo;
	}

	public void setcBoardNo(int cBoardNo) {
		this.cBoardNo = cBoardNo;
	}

	public String getcBoardFileNo() {
		return cBoardFileNo;
	}

	public void setcBoardFileNo(String cBoardFileNo) {
		this.cBoardFileNo = cBoardFileNo;
	}

	public String getcBoardTitle() {
		return cBoardTitle;
	}

	public void setcBoardTitle(String cBoardTitle) {
		this.cBoardTitle = cBoardTitle;
	}

	public String getcBoardContent() {
		return cBoardContent;
	}

	public void setcBoardContent(String cBoardContent) {
		this.cBoardContent = cBoardContent;
	}

	public String getcBoardRegDate() {
		return cBoardRegDate;
	}

	public void setcBoardRegDate(String cBoardRegDate) {
		this.cBoardRegDate = cBoardRegDate;
	}

	public int getcBoardViewCnt() {
		return cBoardViewCnt;
	}

	public void setcBoardViewCnt(int cBoardViewCnt) {
		this.cBoardViewCnt = cBoardViewCnt;
	}

	public int getcBoardUserNo() {
		return cBoardUserNo;
	}

	public void setcBoardUserNo(int cBoardUserNo) {
		this.cBoardUserNo = cBoardUserNo;
	}

	@Override
	public String toString() {
		return "CBoard [cBoardNo=" + cBoardNo + ", cBoardFileNo=" + cBoardFileNo + ", cBoardTitle=" + cBoardTitle
				+ ", cBoardContent=" + cBoardContent + ", cBoardRegDate=" + cBoardRegDate + ", cBoardViewCnt="
				+ cBoardViewCnt + ", cBoardUserNo=" + cBoardUserNo + "]";
	}
	
	
}
