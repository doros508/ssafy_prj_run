package com.mrt.mvc.model.dto;

public class MagazineFile {
	private String fileNo; // UUID + 파일 원본이름
	private String oriName; // 파일 원본 이름
	private String path; // 파일 저장 경로(업로드 날짜로 폴더링 할 예정)
	private String systemName; // 시스템상 파일 이름
	private int magazineNo; // 매거진 번호
	public MagazineFile() {
		
	}
	public MagazineFile(String fileNo, String oriName, String path, String systemName, int magazineNo) {
		super();
		this.fileNo = fileNo;
		this.oriName = oriName;
		this.path = path;
		this.systemName = systemName;
		this.magazineNo = magazineNo;
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
	public int getMagazineNo() {
		return magazineNo;
	}
	public void setMagazineNo(int magazineNo) {
		this.magazineNo = magazineNo;
	}
	@Override
	public String toString() {
		return "MagazineFile [fileNo=" + fileNo + ", oriName=" + oriName + ", path=" + path + ", systemName="
				+ systemName + ", magazineNo=" + magazineNo + "]";
	}
	
	
	
}
