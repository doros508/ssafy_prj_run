package com.mrt.mvc.model.dto;

public class Crew {
    private int crewNo;
    private String crewName;
    private int crewSize;
    private int districtNo;
    private int cityNo;
    private String crewContent;
    private String crewUrl;
    private String crewDay;
    private String crewLat;  //위도
    private String crewLng;		//경도
    private String crewLocation;  //상세위치
    
    

    public Crew() {
    }

    

    public String getCrewLat() {
		return crewLat;
	}



	public void setCrewLat(String crewLat) {
		this.crewLat = crewLat;
	}



	public String getCrewLng() {
		return crewLng;
	}



	public void setCrewLng(String crewLng) {
		this.crewLng = crewLng;
	}



	public String getCrewLocation() {
		return crewLocation;
	}



	public void setCrewLocation(String crewLocation) {
		this.crewLocation = crewLocation;
	}



	public Crew(int crewNo, String crewName, int crewSize, int districtNo, int cityNo, String crewContent,
			String crewUrl, String crewDay, String crewLat, String crewLng, String crewLocation) {
		super();
		this.crewNo = crewNo;
		this.crewName = crewName;
		this.crewSize = crewSize;
		this.districtNo = districtNo;
		this.cityNo = cityNo;
		this.crewContent = crewContent;
		this.crewUrl = crewUrl;
		this.crewDay = crewDay;
		this.crewLat = crewLat;
		this.crewLng = crewLng;
		this.crewLocation = crewLocation;
	}



	public int getCrewNo() {
        return crewNo;
    }

    public void setCrewNo(int crewNo) {
        this.crewNo = crewNo;
    }

    public String getCrewName() {
        return crewName;
    }

    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }

    public int getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(int crewSize) {
        this.crewSize = crewSize;
    }

    public int getDistrictNo() {
        return districtNo;
    }

    public void setDistrictNo(int districtNo) {
        this.districtNo = districtNo;
    }

    public int getCityNo() {
        return cityNo;
    }

    public void setCityNo(int cityNo) {
        this.cityNo = cityNo;
    }

    public String getCrewContent() {
        return crewContent;
    }

    public void setCrewContent(String crewContent) {
        this.crewContent = crewContent;
    }

    public String getCrewUrl() {
        return crewUrl;
    }

    public void setCrewUrl(String crewUrl) {
        this.crewUrl = crewUrl;
    }

    public String getCrewDay() {
        return crewDay;
    }

    public void setCrewDay(String crewDay) {
        this.crewDay = crewDay;
    }



	@Override
	public String toString() {
		return "Crew [crewNo=" + crewNo + ", crewName=" + crewName + ", crewSize=" + crewSize + ", districtNo="
				+ districtNo + ", cityNo=" + cityNo + ", crewContent=" + crewContent + ", crewUrl=" + crewUrl
				+ ", crewDay=" + crewDay + ", crewLat=" + crewLat + ", crewLng=" + crewLng + ", crewLocation="
				+ crewLocation + "]";
	}

   
}