package com.mrt.mvc.model.dto;

public class Race {
	private int raceNo;
	private String raceName;
	private String raceDate;
	private String racePlace;
	private String raceDistance;
	private String raceUrl;
	private int userNo;
	private String raceRegDate;
	private int cityNo;
	public Race() {
		
	}
	public Race(int raceNo, String raceName, String raceDate, String racePlace, String raceDistance, String raceUrl,
			int userNo, String raceRegDate, int cityNo) {
		super();
		this.raceNo = raceNo;
		this.raceName = raceName;
		this.raceDate = raceDate;
		this.racePlace = racePlace;
		this.raceDistance = raceDistance;
		this.raceUrl = raceUrl;
		this.userNo = userNo;
		this.raceRegDate = raceRegDate;
		this.cityNo = cityNo;
	}
	public int getRaceNo() {
		return raceNo;
	}
	public void setRaceNo(int raceNo) {
		this.raceNo = raceNo;
	}
	public String getRaceName() {
		return raceName;
	}
	public void setRaceName(String raceName) {
		this.raceName = raceName;
	}
	public String getRaceDate() {
		return raceDate;
	}
	public void setRaceDate(String raceDate) {
		this.raceDate = raceDate;
	}
	public String getRacePlace() {
		return racePlace;
	}
	public void setRacePlace(String racePlace) {
		this.racePlace = racePlace;
	}
	public String getRaceDistance() {
		return raceDistance;
	}
	public void setRaceDistance(String raceDistance) {
		this.raceDistance = raceDistance;
	}
	public String getRaceUrl() {
		return raceUrl;
	}
	public void setRaceUrl(String raceUrl) {
		this.raceUrl = raceUrl;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getRaceRegDate() {
		return raceRegDate;
	}
	public void setRaceRegDate(String raceRegDate) {
		this.raceRegDate = raceRegDate;
	}
	public int getCityNo() {
		return cityNo;
	}
	public void setCityNo(int cityNo) {
		this.cityNo = cityNo;
	}
	@Override
	public String toString() {
		return "Race [raceNo=" + raceNo + ", raceName=" + raceName + ", raceDate=" + raceDate + ", racePlace="
				+ racePlace + ", raceDistance=" + raceDistance + ", raceUrl=" + raceUrl + ", userNo=" + userNo
				+ ", raceRegDate=" + raceRegDate + ", cityNo=" + cityNo + "]";
	}
	
}
