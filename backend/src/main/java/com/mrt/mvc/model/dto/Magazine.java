package com.mrt.mvc.model.dto;

import java.util.List;

public class Magazine {
	private int magazineNo;
	private String magazineTitle;
	private String magazineUserNickname;
	private String magazineContent;
	private String magazineRegDate;
	private int magazineViewCnt;
	private int userNo;
	private List<MagazineFile> magazineFileList;
	public Magazine() {
		
	}
	public Magazine(int magazineNo, String magazineTitle, String magazineUserNickname, String magazineContent,
			String magazineRegDate, int magazineViewCnt, int userNo, List<MagazineFile> magazineFileList) {
		super();
		this.magazineNo = magazineNo;
		this.magazineTitle = magazineTitle;
		this.magazineUserNickname = magazineUserNickname;
		this.magazineContent = magazineContent;
		this.magazineRegDate = magazineRegDate;
		this.magazineViewCnt = magazineViewCnt;
		this.userNo = userNo;
		this.magazineFileList = magazineFileList;
	}
	public int getMagazineNo() {
		return magazineNo;
	}
	public void setMagazineNo(int magazineNo) {
		this.magazineNo = magazineNo;
	}
	public String getMagazineTitle() {
		return magazineTitle;
	}
	public void setMagazineTitle(String magazineTitle) {
		this.magazineTitle = magazineTitle;
	}
	public String getMagazineUserNickname() {
		return magazineUserNickname;
	}
	public void setMagazineUserNickname(String magazineUserNickname) {
		this.magazineUserNickname = magazineUserNickname;
	}
	public String getMagazineContent() {
		return magazineContent;
	}
	public void setMagazineContent(String magazineContent) {
		this.magazineContent = magazineContent;
	}
	public String getMagazineRegDate() {
		return magazineRegDate;
	}
	public void setMagazineRegDate(String magazineRegDate) {
		this.magazineRegDate = magazineRegDate;
	}
	public int getMagazineViewCnt() {
		return magazineViewCnt;
	}
	public void setMagazineViewCnt(int magazineViewCnt) {
		this.magazineViewCnt = magazineViewCnt;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public List<MagazineFile> getMagazineFileList() {
		return magazineFileList;
	}
	public void setMagazineFileList(List<MagazineFile> magazineFileList) {
		this.magazineFileList = magazineFileList;
	}
	@Override
	public String toString() {
		return "Magazine [magazineNo=" + magazineNo + ", magazineTitle=" + magazineTitle + ", magazineUserNickname="
				+ magazineUserNickname + ", magazineContent=" + magazineContent + ", magazineRegDate=" + magazineRegDate
				+ ", magazineViewCnt=" + magazineViewCnt + ", userNo=" + userNo + ", magazineFileList="
				+ magazineFileList + "]";
	}
	
}
