package com.mrt.mvc.model.dto;

public class Magazine {
	private int magazineNo;
	private String magazineTitle;
	private String magazineAdminNickname; // 관리자 닉네임
	private String magazineContent;
	private String magazineRegDate;
	private int magazineViewCnt;
	private int adminNo; // 관리자 번호
	private MagazineFile magazineFile;
	
	public Magazine() {
		
	}

	public Magazine(int magazineNo, String magazineTitle, String magazineAdminNickname, String magazineContent,
			String magazineRegDate, int magazineViewCnt, int adminNo, MagazineFile magazineFile) {
		super();
		this.magazineNo = magazineNo;
		this.magazineTitle = magazineTitle;
		this.magazineAdminNickname = magazineAdminNickname;
		this.magazineContent = magazineContent;
		this.magazineRegDate = magazineRegDate;
		this.magazineViewCnt = magazineViewCnt;
		this.adminNo = adminNo;
		this.magazineFile = magazineFile;
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

	public String getMagazineAdminNickname() {
		return magazineAdminNickname;
	}

	public void setMagazineAdminNickname(String magazineAdminNickname) {
		this.magazineAdminNickname = magazineAdminNickname;
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

	public int getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}

	public MagazineFile getMagazineFile() {
		return magazineFile;
	}

	public void setMagazineFile(MagazineFile magazineFile) {
		this.magazineFile = magazineFile;
	}

	@Override
	public String toString() {
		return "Magazine [magazineNo=" + magazineNo + ", magazineTitle=" + magazineTitle + ", magazineAdminNickname="
				+ magazineAdminNickname + ", magazineContent=" + magazineContent + ", magazineRegDate="
				+ magazineRegDate + ", magazineViewCnt=" + magazineViewCnt + ", adminNo=" + adminNo + ", magazineFile="
				+ magazineFile + "]";
	}
	
	
}
